package model

import java.math.BigDecimal

data class Producto(
    val idProducto: Int = 0,
    val codigoSku: String,
    val nombre: String,
    val descripcion: String? = null,
    val precio: BigDecimal,
    val stock: Int,
    val imagenUrl: String? = null,
    val idCategoria: Int? = null,
    val idMarca: Int? = null,
    val estado: Boolean = true,
    // Campos informativos obtenidos mediante JOIN
    val nombreCategoria: String? = null,
    val nombreMarca: String? = null
)