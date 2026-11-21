package com.example.tienda_electronica.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

data class CarritoItem(
    val producto: ProductoDto,
    val cantidad: Int
)

class TiendaViewModel : ViewModel() {

    private val api = RetrofitClient.api

    // ==========================================
    // ESTADOS GENERALES
    // ==========================================

    var productos by mutableStateOf<List<ProductoDto>>(emptyList())
        private set

    var categorias by mutableStateOf<List<CategoriaDto>>(emptyList())
        private set

    var cargando by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    // ==========================================
    // ESTADOS DEL CARRITO
    // ==========================================

    var carrito by mutableStateOf<List<CarritoItem>>(emptyList())
        private set

    // ==========================================
    // ESTADOS PARA FINALIZAR LA COMPRA
    // ==========================================

    var procesandoCompra by mutableStateOf(false)
        private set

    var mensajeCompra by mutableStateOf<String?>(null)
        private set

    var pedidoCreado by mutableStateOf<PedidoResponse?>(null)
        private set

    // ==========================================
    // ESTADOS PARA ADMINISTRACIÓN DE PEDIDOS
    // ==========================================

    var pedidosAdmin by mutableStateOf<List<PedidoAdminDto>>(emptyList())
        private set

    var detallesPedido by mutableStateOf<List<DetallePedidoDto>>(emptyList())
        private set

    var cargandoPedidos by mutableStateOf(false)
        private set

    var pedidoSeleccionado by mutableStateOf<Int?>(null)
        private set

    var mensajeAdmin by mutableStateOf<String?>(null)
        private set

    // ==========================================
    // INICIALIZACIÓN
    // ==========================================

    init {
        recargar()
    }

    // ==========================================
    // CARGAR PRODUCTOS Y CATEGORÍAS
    // ==========================================

    fun recargar() {
        viewModelScope.launch {
            cargando = true
            error = null

            try {
                productos = api.listar()
                categorias = api.categorias()
            } catch (e: Exception) {
                error = mensajeDe(e)
            } finally {
                cargando = false
            }
        }
    }

    // ==========================================
    // CREAR O ACTUALIZAR PRODUCTOS
    // ==========================================

    fun guardar(producto: ProductoDto) {
        viewModelScope.launch {
            error = null

            try {
                if (producto.idProducto == 0) {
                    api.crear(producto)
                } else {
                    api.actualizar(producto.idProducto, producto)
                }

                productos = api.listar()

            } catch (e: Exception) {
                error = mensajeDe(e)
            }
        }
    }

    // ==========================================
    // ELIMINAR PRODUCTOS
    // ==========================================

    fun eliminar(idProducto: Int) {
        viewModelScope.launch {
            error = null

            try {
                val respuesta = api.eliminar(idProducto)

                if (!respuesta.isSuccessful) {
                    error = "No se pudo eliminar (código ${respuesta.code()})"
                } else {
                    productos = api.listar()
                }

            } catch (e: Exception) {
                error = mensajeDe(e)
            }
        }
    }

    // ==========================================
    // AGREGAR PRODUCTOS AL CARRITO
    // ==========================================

    fun agregarAlCarrito(producto: ProductoDto): Boolean {

        if (producto.stock <= 0) {
            error = "Este producto no tiene existencias disponibles"
            return false
        }

        val existente = carrito.find {
            it.producto.idProducto == producto.idProducto
        }

        if (existente != null) {

            if (existente.cantidad >= producto.stock) {
                error = "No hay suficiente inventario disponible"
                return false
            }

            carrito = carrito.map {
                if (it.producto.idProducto == producto.idProducto) {
                    it.copy(cantidad = it.cantidad + 1)
                } else {
                    it
                }
            }

        } else {
            carrito = carrito + CarritoItem(producto, 1)
        }

        error = null
        mensajeCompra = null
        pedidoCreado = null

        return true
    }

    // ==========================================
    // CAMBIAR CANTIDAD DE UN PRODUCTO
    // ==========================================

    fun cambiarCantidad(idProducto: Int, nuevaCantidad: Int) {

        if (nuevaCantidad <= 0) {
            quitarDelCarrito(idProducto)
            return
        }

        carrito = carrito.map { item ->

            if (item.producto.idProducto == idProducto) {

                if (nuevaCantidad <= item.producto.stock) {
                    item.copy(cantidad = nuevaCantidad)
                } else {
                    error = "No hay suficiente inventario disponible"
                    item
                }

            } else {
                item
            }
        }
    }

    // ==========================================
    // QUITAR PRODUCTO DEL CARRITO
    // ==========================================

    fun quitarDelCarrito(idProducto: Int) {
        carrito = carrito.filter {
            it.producto.idProducto != idProducto
        }
    }

    // ==========================================
    // VACIAR CARRITO
    // ==========================================

    fun vaciarCarrito() {
        carrito = emptyList()
    }

    // ==========================================
    // CANTIDAD TOTAL DE PRODUCTOS
    // ==========================================

    fun cantidadEnCarrito(): Int {
        return carrito.sumOf { it.cantidad }
    }

    // ==========================================
    // TOTAL DE LA COMPRA
    // ==========================================

    fun totalCarrito(): Double {
        return carrito.sumOf {
            it.producto.precio * it.cantidad
        }
    }

    // ==========================================
    // REGISTRAR PEDIDO EN EL BACKEND
    // ==========================================

    fun finalizarCompra() {

        if (procesandoCompra) return

        if (!SesionManager.haySesionActiva()) {
            error = "Debes iniciar sesión para realizar una compra"
            return
        }

        if (carrito.isEmpty()) {
            error = "El carrito está vacío"
            return
        }

        viewModelScope.launch {

            procesandoCompra = true
            error = null
            mensajeCompra = null
            pedidoCreado = null

            try {

                val detalles = carrito.map { item ->
                    ProductoPedidoDto(
                        idProducto = item.producto.idProducto,
                        cantidad = item.cantidad
                    )
                }

                val solicitud = CrearPedidoRequest(
                    detalles = detalles
                )

                val respuesta = api.registrarPedido(solicitud)

                pedidoCreado = respuesta
                mensajeCompra = respuesta.mensaje

                // Vaciar carrito después de registrar correctamente
                vaciarCarrito()

                // Actualizar inventario
                productos = api.listar()

            } catch (e: HttpException) {

                error = when (e.code()) {
                    400 -> "No se pudo registrar la compra. Verifica los productos y las cantidades."
                    401 -> "Tu sesión ha expirado. Inicia sesión nuevamente."
                    403 -> "No tienes permiso para realizar esta compra."
                    404 -> "Uno de los productos ya no está disponible."
                    409 -> "No hay suficiente inventario para completar la compra."
                    else -> "Error del servidor: ${e.code()}"
                }

            } catch (e: IOException) {

                error = "No se pudo conectar con el servidor. Verifica tu conexión."

            } catch (e: Exception) {

                error = e.message ?: "Ocurrió un error al registrar la compra."

            } finally {
                procesandoCompra = false
            }
        }
    }

    // ==========================================
    // ADMINISTRACIÓN DE PEDIDOS
    // ==========================================

    // Cargar todos los pedidos
    fun cargarPedidosAdmin() {
        viewModelScope.launch {

            cargandoPedidos = true
            mensajeAdmin = null

            try {
                pedidosAdmin = api.listarPedidosAdmin()

            } catch (e: Exception) {
                mensajeAdmin = mensajeDe(e)

            } finally {
                cargandoPedidos = false
            }
        }
    }

    // Consultar detalles de un pedido
    fun cargarDetallesPedido(idPedido: Int) {
        viewModelScope.launch {

            mensajeAdmin = null
            pedidoSeleccionado = idPedido
            detallesPedido = emptyList()

            try {
                detallesPedido = api.obtenerDetallesPedido(idPedido)

            } catch (e: Exception) {
                mensajeAdmin = mensajeDe(e)
            }
        }
    }

    // Actualizar estado de un pedido
    fun actualizarEstadoPedido(
        idPedido: Int,
        nuevoEstado: String
    ) {
        viewModelScope.launch {

            mensajeAdmin = null

            try {

                api.actualizarEstadoPedido(
                    idPedido,
                    ActualizarEstadoPedidoDto(nuevoEstado)
                )

                mensajeAdmin = "Estado del pedido actualizado correctamente"

                // Actualizar la lista de pedidos
                pedidosAdmin = api.listarPedidosAdmin()

            } catch (e: Exception) {
                mensajeAdmin = mensajeDe(e)
            }
        }
    }

    // ==========================================
    // MENSAJES DE ERRORES GENERALES
    // ==========================================

    private fun mensajeDe(e: Exception): String {

        return when (e) {

            is HttpException -> when (e.code()) {
                400 -> "Datos inválidos"
                401 -> "Debes iniciar sesión nuevamente"
                403 -> "No tienes permisos para esta operación"
                404 -> "Producto no encontrado"
                409 -> "El SKU ya existe o la categoría no es válida"
                else -> "Error del servidor: ${e.code()}"
            }

            is IOException ->
                "No se pudo conectar con el servidor"

            else ->
                e.message ?: "Ocurrió un error inesperado"
        }
    }
}
