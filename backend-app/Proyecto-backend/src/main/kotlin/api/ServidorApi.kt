package api

import io.javalin.Javalin
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.javalin.http.HttpResponseException
import io.javalin.http.InternalServerErrorResponse
import io.javalin.http.NotFoundResponse
import model.Producto
import repository.CategoriaRepository
import repository.ProductoRepository
import java.sql.SQLIntegrityConstraintViolationException

data class AjusteStock(val delta: Int)

private fun Context.idDeRuta(): Int =
    pathParam("id").toIntOrNull() ?: throw BadRequestResponse("El id debe ser un número")

private fun validar(p: Producto) {
    if (p.codigoSku.isBlank()) throw BadRequestResponse("El SKU es obligatorio")
    if (p.nombre.isBlank()) throw BadRequestResponse("El nombre es obligatorio")
    if (p.precio.signum() < 0) throw BadRequestResponse("El precio no puede ser negativo")
    if (p.stock < 0) throw BadRequestResponse("El stock no puede ser negativo")
}

// Ejecuta ESTE main para levantar la API REST (el Main.kt de la consola no se toca)
fun main() {
    val productos = ProductoRepository()
    val categorias = CategoriaRepository()

    val app = Javalin.create().start("0.0.0.0", 8080)

    // GET /api/productos
    //   opcionales: ?q=texto | ?categoria=1&min=10&max=500 | ?bajoStock=20
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
        val producto = productos.obtenerPorId(ctx.idDeRuta()) ?: throw NotFoundResponse("Producto no encontrado")
        ctx.json(producto)
    }

    // POST /api/productos
    app.post("/api/productos") { ctx ->
        val nuevo = ctx.bodyAsClass(Producto::class.java)
        validar(nuevo)
        val id = productos.insertar(nuevo)
        if (id <= 0) throw InternalServerErrorResponse("No se pudo insertar el producto")
        ctx.status(201).json(productos.obtenerPorId(id)!!)
    }

    // PUT /api/productos/{id}
    app.put("/api/productos/{id}") { ctx ->
        val id = ctx.idDeRuta()
        val datos = ctx.bodyAsClass(Producto::class.java).copy(idProducto = id)
        validar(datos)
        if (!productos.actualizar(datos)) throw NotFoundResponse("Producto no encontrado")
        ctx.json(productos.obtenerPorId(id)!!)
    }

    // PATCH /api/productos/{id}/stock   body: {"delta": -2}
    app.patch("/api/productos/{id}/stock") { ctx ->
        val id = ctx.idDeRuta()
        val ajuste = ctx.bodyAsClass(AjusteStock::class.java)
        if (!productos.ajustarStock(id, ajuste.delta)) {
            if (productos.obtenerPorId(id) == null) throw NotFoundResponse("Producto no encontrado")
            ctx.status(409).json(mapOf("error" to "Stock insuficiente"))
        } else {
            ctx.json(productos.obtenerPorId(id)!!)
        }
    }

    // DELETE /api/productos/{id}  (eliminación lógica)
    app.delete("/api/productos/{id}") { ctx ->
        if (!productos.eliminarLogico(ctx.idDeRuta())) throw NotFoundResponse("Producto no encontrado")
        ctx.status(204)
    }

    // GET /api/categorias
    app.get("/api/categorias") { ctx -> ctx.json(categorias.listarActivas()) }

    // ---- Manejo de errores: siempre responde JSON {"error": "..."} ----
    app.exception(HttpResponseException::class.java) { e, ctx ->
        ctx.status(e.status).json(mapOf("error" to (e.message ?: "Error")))
    }
    app.exception(SQLIntegrityConstraintViolationException::class.java) { _, ctx ->
        ctx.status(409).json(mapOf("error" to "SKU duplicado o categoría/marca inexistente"))
    }
    app.exception(Exception::class.java) { e, ctx ->
        e.printStackTrace()
        ctx.status(500).json(mapOf("error" to "Error interno del servidor"))
    }

    println("API lista en http://localhost:8080/api/productos")
}