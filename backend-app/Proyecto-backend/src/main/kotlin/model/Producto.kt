package model

import java.math.BigDecimal

data class Producto(
    val idProducto: Int = 0,
    val codigoSku: String = "",
    val nombre: String = "",
    val descripcion: String? = null,
    val precio: BigDecimal = BigDecimal.ZERO,
    val stock: Int = 0,
    val imagenUrl: String? = null,
    val idCategoria: Int? = null,
    val idMarca: Int? = null,
    val estado: Boolean = true,
    val nombreCategoria: String? = null,
    val nombreMarca: String? = null
) {
    constructor() : this(0, "", "", null, BigDecimal.ZERO, 0, null, null, null, true, null, null)
}