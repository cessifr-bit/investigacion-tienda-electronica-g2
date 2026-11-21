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

data class AjusteStockDto(
    val delta: Int
)

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

// Modelos para registrar una compra
data class CrearPedidoRequest(
    val detalles: List<ProductoPedidoDto>
)

data class ProductoPedidoDto(
    val idProducto: Int,
    val cantidad: Int
)

// Respuesta que devuelve el backend al registrar el pedido
data class PedidoResponse(
    val mensaje: String,
    val idPedido: Int,
    val total: Double,
    val estado: String
)
// Información general de un pedido
data class PedidoAdminDto(
    val idPedido: Int,
    val idUsuario: Int,
    val nombreCliente: String,
    val emailCliente: String,
    val fechaPedido: String,
    val total: Double,
    val estado: String
)

// Productos incluidos en un pedido
data class DetallePedidoDto(
    val idDetalle: Int,
    val idPedido: Int,
    val idProducto: Int,
    val nombreProducto: String,
    val cantidad: Int,
    val precioUnitario: Double,
    val subtotal: Double
)

// Solicitud para actualizar el estado
data class ActualizarEstadoPedidoDto(
    val estado: String
)

// Respuesta del backend al actualizar
data class ActualizarEstadoPedidoResponse(
    val mensaje: String,
    val idPedido: Int,
    val estado: String
)