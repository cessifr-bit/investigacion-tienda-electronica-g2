package com.example.tienda_electronica.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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

    // Crea (idProducto == 0) o actualiza un producto y refresca la lista
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

    private fun mensajeDe(e: Exception): String = when (e) {
        is HttpException -> when (e.code()) {
            400 -> "Datos inválidos"
            404 -> "Producto no encontrado"
            409 -> "El SKU ya existe o la categoría no es válida"
            else -> "Error del servidor (${e.code()})"
        }
        is IOException -> "No se pudo conectar con el servidor. ¿Está encendido el backend?"
        else -> e.message ?: "Error desconocido"
    }
}