package com.example.tienda_electronica.data

// Los nombres coinciden con el JSON que devuelve el backend
data class ProductoDto(
    val idProducto: Int = 0,
    val codigoSku: String = "",
    val nombre: String = "",
    val descripcion: String? = null,
    val precio: Double = 0.0,
    val stock: Int = 0,
    val imagenUrl: String? = null,
    val idCategoria: Int? = null,
    val idMarca: Int? = null,
    val estado: Boolean = true,
    val nombreCategoria: String? = null,
    val nombreMarca: String? = null
)

data class CategoriaDto(
    val idCategoria: Int,
    val nombre: String
)

data class AjusteStockDto(val delta: Int)
data class LoginRequest(
    val email: String,
    val password: String
)

data class UsuarioDto(
    val idUsuario: Int,
    val nombreCompleto: String,
    val email: String,
    val rol: String
)

data class LoginResponse(
    val mensaje: String,
    val token: String,
    val usuario: UsuarioDto
)

data class RegistroRequest(
    val nombreCompleto: String,
    val email: String,
    val password: String,
    val telefono: String? = null,
    val direccion: String? = null
)
