
package com.example.tienda_electronica.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Representa un producto dentro del carrito
data class CarritoItem(
    val producto: ProductoDto,
    val cantidad: Int
)

class TiendaViewModel : ViewModel() {

    private val api = RetrofitClient.api

    var productos by mutableStateOf<List<ProductoDto>>(emptyList())
        private set

    var categorias by mutableStateOf<List<CategoriaDto>>(emptyList())
        private set

    var cargando by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    // Carrito compartido
    var carrito by mutableStateOf<List<CarritoItem>>(emptyList())
        private set

    init {
        recargar()
    }

    // Trae productos y categorías desde la API
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

    // Crea o actualiza un producto
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

    // =========================
    // FUNCIONES DEL CARRITO
    // =========================

    // Agrega un producto o aumenta su cantidad
    fun agregarAlCarrito(producto: ProductoDto): Boolean {

        if (producto.stock <= 0) {
            return false
        }

        val existente = carrito.find {
            it.producto.idProducto == producto.idProducto
        }

        if (existente != null) {

            if (existente.cantidad >= producto.stock) {
                return false
            }

            carrito = carrito.map {
                if (it.producto.idProducto == producto.idProducto) {
                    it.copy(
                        producto = producto,
                        cantidad = it.cantidad + 1
                    )
                } else {
                    it
                }
            }

        } else {
            carrito = carrito + CarritoItem(
                producto = producto,
                cantidad = 1
            )
        }

        return true
    }

    // Cambia la cantidad de un producto
    fun cambiarCantidad(idProducto: Int, nuevaCantidad: Int) {

        if (nuevaCantidad <= 0) {
            quitarDelCarrito(idProducto)
            return
        }

        val item = carrito.find {
            it.producto.idProducto == idProducto
        } ?: return

        if (nuevaCantidad > item.producto.stock) {
            return
        }

        carrito = carrito.map {
            if (it.producto.idProducto == idProducto) {
                it.copy(cantidad = nuevaCantidad)
            } else {
                it
            }
        }
    }

    // Elimina un producto del carrito
    fun quitarDelCarrito(idProducto: Int) {
        carrito = carrito.filter {
            it.producto.idProducto != idProducto
        }
    }

    // Vacía todo el carrito
    fun vaciarCarrito() {
        carrito = emptyList()
    }

    // Calcula el total de unidades
    fun cantidadEnCarrito(): Int {
        return carrito.sumOf { it.cantidad }
    }

    // Calcula el precio total
    fun totalCarrito(): Double {
        return carrito.sumOf {
            it.producto.precio * it.cantidad
        }
    }

    private fun mensajeDe(e: Exception): String = when (e) {
        is HttpException -> when (e.code()) {
            400 -> "Datos inválidos"
            404 -> "Producto no encontrado"
            409 -> "El SKU ya existe o la categoría no es válida"
            else -> "Error del servidor (${e.code()})"
        }

        is IOException ->
            "No se pudo conectar con el servidor. ¿Está encendido el backend?"

        else -> e.message ?: "Error desconocido"
    }
}
