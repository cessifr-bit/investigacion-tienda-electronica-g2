package api

import io.javalin.Javalin
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.javalin.http.ForbiddenResponse
import io.javalin.http.HttpResponseException
import io.javalin.http.InternalServerErrorResponse
import io.javalin.http.NotFoundResponse
import io.javalin.http.UnauthorizedResponse
import model.Producto
import repository.CategoriaRepository
import repository.ProductoRepository
import repository.UsuarioRepository
import service.AuthService
import java.sql.SQLIntegrityConstraintViolationException

data class AjusteStock(val delta: Int)

data class RegistroRequest(
    val nombreCompleto: String,
    val email: String,
    val password: String,
    val telefono: String? = null,
    val direccion: String? = null
)

data class LoginRequest(
    val email: String,
    val password: String
)

private fun Context.idDeRuta(): Int =
    pathParam("id").toIntOrNull()
        ?: throw BadRequestResponse("El id debe ser un número")

private fun validar(p: Producto) {
    if (p.codigoSku.isBlank()) throw BadRequestResponse("El SKU es obligatorio")
    if (p.nombre.isBlank()) throw BadRequestResponse("El nombre es obligatorio")
    if (p.precio.signum() < 0) throw BadRequestResponse("El precio no puede ser negativo")
    if (p.stock < 0) throw BadRequestResponse("El stock no puede ser negativo")
}

// Comprueba que el usuario tenga un token JWT válido
private fun Context.obtenerRol(auth: AuthService): String {
    val authorization = header("Authorization")
        ?: throw UnauthorizedResponse("Se requiere iniciar sesión")

    if (!authorization.startsWith("Bearer ", ignoreCase = true)) {
        throw UnauthorizedResponse("Formato de token inválido")
    }

    val token = authorization.substringAfter("Bearer ").trim()

    if (token.isBlank()) {
        throw UnauthorizedResponse("Token no proporcionado")
    }

    return try {
        val jwt = auth.validarToken(token)
        jwt.getClaim("rol").asString()
            ?: throw UnauthorizedResponse("Token sin rol")
    } catch (e: HttpResponseException) {
        throw e
    } catch (e: Exception) {
        throw UnauthorizedResponse("Token inválido o expirado")
    }
}

// Comprueba que el usuario sea administrador
private fun Context.exigirAdmin(auth: AuthService) {
    if (obtenerRol(auth) != "ADMIN") {
        throw ForbiddenResponse("No tienes permisos de administrador")
    }
}

fun main() {

    val productos = ProductoRepository()
    val categorias = CategoriaRepository()
    val usuarios = UsuarioRepository()
    val auth = AuthService()

    val app = Javalin.create()

    // ==========================================
    // AUTENTICACIÓN
    // ==========================================

    // POST /api/auth/registro
    app.post("/api/auth/registro") { ctx ->

        val datos = ctx.bodyAsClass(RegistroRequest::class.java)

        if (datos.nombreCompleto.isBlank()) {
            throw BadRequestResponse("El nombre es obligatorio")
        }

        if (datos.email.isBlank() ||
            !androidSafeEmail(datos.email)
        ) {
            throw BadRequestResponse("Correo electrónico inválido")
        }

        if (datos.password.length < 8) {
            throw BadRequestResponse("La contraseña debe tener al menos 8 caracteres")
        }

        if (datos.telefono?.length ?: 0 > 20) {
            throw BadRequestResponse("El teléfono no puede superar 20 caracteres")
        }

        if (datos.direccion?.length ?: 0 > 255) {
            throw BadRequestResponse("La dirección no puede superar 255 caracteres")
        }

        val email = datos.email.trim().lowercase()

        if (usuarios.buscarPorEmail(email) != null) {
            throw BadRequestResponse("El correo ya está registrado")
        }

        val passwordHash = auth.encriptarPassword(datos.password)

        val id = usuarios.registrar(
            nombreCompleto = datos.nombreCompleto,
            email = email,
            passwordHash = passwordHash,
            telefono = datos.telefono,
            direccion = datos.direccion
        )

        if (id <= 0) {
            throw InternalServerErrorResponse("No se pudo registrar el usuario")
        }

        ctx.status(201).json(
            mapOf(
                "mensaje" to "Usuario registrado correctamente",
                "idUsuario" to id,
                "nombreCompleto" to datos.nombreCompleto,
                "email" to email,
                "rol" to "CLIENTE"
            )
        )
    }

    // POST /api/auth/login
    app.post("/api/auth/login") { ctx ->

        val datos = ctx.bodyAsClass(LoginRequest::class.java)

        if (datos.email.isBlank() || datos.password.isBlank()) {
            throw BadRequestResponse("Correo y contraseña son obligatorios")
        }

        val usuario = usuarios.buscarPorEmail(datos.email.trim().lowercase())
            ?: throw UnauthorizedResponse("Correo o contraseña incorrectos")

        if (!auth.verificarPassword(datos.password, usuario.passwordHash)) {
            throw UnauthorizedResponse("Correo o contraseña incorrectos")
        }

        val token = auth.generarToken(usuario)

        ctx.json(
            mapOf(
                "mensaje" to "Inicio de sesión correcto",
                "token" to token,
                "usuario" to mapOf(
                    "idUsuario" to usuario.idUsuario,
                    "nombreCompleto" to usuario.nombreCompleto,
                    "email" to usuario.email,
                    "rol" to usuario.rol
                )
            )
        )
    }

    // ==========================================
    // PRODUCTOS
    // ==========================================

    // GET /api/productos
    // Público: búsqueda, filtros y catálogo
    app.get("/api/productos") { ctx ->

        val q = ctx.queryParam("q")
        val categoria = ctx.queryParam("categoria")?.toIntOrNull()
        val min = ctx.queryParam("min")?.toBigDecimalOrNull()
        val max = ctx.queryParam("max")?.toBigDecimalOrNull()
        val bajoStock = ctx.queryParam("bajoStock")?.toIntOrNull()

        val resultado = when {
            !q.isNullOrBlank() -> productos.buscarPorNombreOSku(q)

            categoria != null && min != null && max != null ->
                productos.filtrarPorCategoriaYPrecio(categoria, min, max)

            bajoStock != null -> productos.listarBajoStock(bajoStock)

            else -> productos.listarActivos()
        }

        ctx.json(resultado)
    }

    // GET /api/productos/{id}
    app.get("/api/productos/{id}") { ctx ->
        val producto = productos.obtenerPorId(ctx.idDeRuta())
            ?: throw NotFoundResponse("Producto no encontrado")

        ctx.json(producto)
    }

    // POST /api/productos
    // Solo ADMIN
    app.post("/api/productos") { ctx ->

        ctx.exigirAdmin(auth)

        val nuevo = ctx.bodyAsClass(Producto::class.java)
        validar(nuevo)

        val id = productos.insertar(nuevo)

        if (id <= 0) {
            throw InternalServerErrorResponse("No se pudo insertar el producto")
        }

        ctx.status(201).json(productos.obtenerPorId(id)!!)
    }

    // PUT /api/productos/{id}
    // Solo ADMIN
    app.put("/api/productos/{id}") { ctx ->

        ctx.exigirAdmin(auth)

        val id = ctx.idDeRuta()
        val datos = ctx.bodyAsClass(Producto::class.java).copy(idProducto = id)

        validar(datos)

        if (!productos.actualizar(datos)) {
            throw NotFoundResponse("Producto no encontrado")
        }

        ctx.json(productos.obtenerPorId(id)!!)
    }

    // PATCH /api/productos/{id}/stock
    // Solo ADMIN
    app.patch("/api/productos/{id}/stock") { ctx ->

        ctx.exigirAdmin(auth)

        val id = ctx.idDeRuta()
        val ajuste = ctx.bodyAsClass(AjusteStock::class.java)

        if (!productos.ajustarStock(id, ajuste.delta)) {

            if (productos.obtenerPorId(id) == null) {
                throw NotFoundResponse("Producto no encontrado")
            }

            ctx.status(409).json(mapOf("error" to "Stock insuficiente"))

        } else {
            ctx.json(productos.obtenerPorId(id)!!)
        }
    }

    // DELETE /api/productos/{id}
    // Solo ADMIN
    app.delete("/api/productos/{id}") { ctx ->

        ctx.exigirAdmin(auth)

        if (!productos.eliminarLogico(ctx.idDeRuta())) {
            throw NotFoundResponse("Producto no encontrado")
        }

        ctx.status(204)
    }

    // ==========================================
    // CATEGORÍAS
    // ==========================================

    // GET /api/categorias
    app.get("/api/categorias") { ctx ->
        ctx.json(categorias.listarActivas())
    }

    // ==========================================
    // MANEJO DE ERRORES
    // ==========================================

    app.exception(HttpResponseException::class.java) { e, ctx ->
        ctx.status(e.status).json(
            mapOf("error" to (e.message ?: "Error"))
        )
    }

    app.exception(SQLIntegrityConstraintViolationException::class.java) { _, ctx ->
        ctx.status(409).json(
            mapOf("error" to "El correo, SKU o alguna referencia ya existe")
        )
    }

    app.exception(Exception::class.java) { e, ctx ->
        e.printStackTrace()

        ctx.status(500).json(
            mapOf("error" to "Error interno del servidor")
        )
    }

    // Iniciar servidor después de registrar todas las rutas
    app.start("0.0.0.0", 8080)

    println("API lista en http://localhost:8080/api/productos")
}

// Validación sencilla de correo electrónico
private fun androidSafeEmail(email: String): Boolean {
    return Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        .matches(email.trim())
}
