package repository

import config.ConexionDB
import java.math.BigDecimal
import java.math.RoundingMode
import java.sql.Statement

// Datos que recibe el repositorio para registrar cada producto
data class ProductoPedido(
    val idProducto: Int,
    val cantidad: Int
)

// Resultado del registro de una compra
data class PedidoCreado(
    val idPedido: Int,
    val total: BigDecimal
)

// Información general de un pedido
data class PedidoResumen(
    val idPedido: Int,
    val idUsuario: Int,
    val nombreCliente: String,
    val emailCliente: String,
    val fechaPedido: String,
    val total: BigDecimal,
    val estado: String
)

// Información de cada producto dentro de un pedido
data class DetallePedido(
    val idDetalle: Int,
    val idPedido: Int,
    val idProducto: Int,
    val nombreProducto: String,
    val cantidad: Int,
    val precioUnitario: BigDecimal,
    val subtotal: BigDecimal
)

class PedidoRepository {

    // Registrar un pedido completo dentro de una transacción
    fun crearPedido(
        idUsuario: Int,
        detalles: List<ProductoPedido>
    ): PedidoCreado {

        require(detalles.isNotEmpty()) {
            "El carrito está vacío"
        }

        require(detalles.all {
            it.idProducto > 0 && it.cantidad > 0
        }) {
            "Los productos y cantidades deben ser válidos"
        }

        require(detalles.map { it.idProducto }.distinct().size == detalles.size) {
            "No se permiten productos repetidos en el pedido"
        }

        val conn = ConexionDB.obtenerConexion()

        try {
            conn.autoCommit = false

            // Se ordenan los productos para reducir conflictos entre compras simultáneas
            val productosOrdenados = detalles.sortedBy { it.idProducto }
            var total = BigDecimal.ZERO

            // Verificar y bloquear temporalmente los productos
            val precios = mutableMapOf<Int, BigDecimal>()

            val sqlProducto = """
                SELECT precio, stock, estado
                FROM productos
                WHERE id_producto = ?
                FOR UPDATE
            """.trimIndent()

            conn.prepareStatement(sqlProducto).use { ps ->
                for (detalle in productosOrdenados) {

                    ps.setInt(1, detalle.idProducto)

                    ps.executeQuery().use { rs ->
                        if (!rs.next()) {
                            throw IllegalArgumentException(
                                "El producto ${detalle.idProducto} no existe"
                            )
                        }

                        val precio = rs.getBigDecimal("precio")
                        val stock = rs.getInt("stock")
                        val activo = rs.getBoolean("estado")

                        if (!activo) {
                            throw IllegalArgumentException(
                                "El producto ${detalle.idProducto} no está disponible"
                            )
                        }

                        if (stock < detalle.cantidad) {
                            throw IllegalArgumentException(
                                "Stock insuficiente para el producto ${detalle.idProducto}"
                            )
                        }

                        precios[detalle.idProducto] = precio
                    }
                }
            }

            // Calcular el total utilizando los precios reales de MySQL
            for (detalle in productosOrdenados) {
                val precio = precios[detalle.idProducto]!!
                val subtotal = precio.multiply(
                    BigDecimal.valueOf(detalle.cantidad.toLong())
                )

                total = total.add(subtotal)
            }

            total = total.setScale(2, RoundingMode.HALF_UP)

            // Crear la cabecera del pedido
            val sqlPedido = """
                INSERT INTO pedidos (id_usuario, total, estado)
                VALUES (?, ?, 'PENDIENTE')
            """.trimIndent()

            val idPedido = conn.prepareStatement(
                sqlPedido,
                Statement.RETURN_GENERATED_KEYS
            ).use { ps ->

                ps.setInt(1, idUsuario)
                ps.setBigDecimal(2, total)

                ps.executeUpdate()

                ps.generatedKeys.use { keys ->
                    if (!keys.next()) {
                        throw IllegalStateException(
                            "No se pudo obtener el ID del pedido"
                        )
                    }

                    keys.getInt(1)
                }
            }

            // Registrar los detalles y descontar las existencias
            val sqlDetalle = """
                INSERT INTO detalle_pedidos
                    (id_pedido, id_producto, cantidad, precio_unitario)
                VALUES (?, ?, ?, ?)
            """.trimIndent()

            val sqlStock = """
                UPDATE productos
                SET stock = stock - ?
                WHERE id_producto = ?
                  AND estado = 1
                  AND stock >= ?
            """.trimIndent()

            conn.prepareStatement(sqlDetalle).use { psDetalle ->
                conn.prepareStatement(sqlStock).use { psStock ->

                    for (detalle in productosOrdenados) {

                        val precio = precios[detalle.idProducto]!!

                        psDetalle.setInt(1, idPedido)
                        psDetalle.setInt(2, detalle.idProducto)
                        psDetalle.setInt(3, detalle.cantidad)
                        psDetalle.setBigDecimal(4, precio)
                        psDetalle.executeUpdate()

                        psStock.setInt(1, detalle.cantidad)
                        psStock.setInt(2, detalle.idProducto)
                        psStock.setInt(3, detalle.cantidad)

                        if (psStock.executeUpdate() != 1) {
                            throw IllegalStateException(
                                "No se pudo actualizar el inventario"
                            )
                        }
                    }
                }
            }

            // Confirmar toda la compra
            conn.commit()

            return PedidoCreado(idPedido, total)

        } catch (e: Exception) {

            // Si algo falla, deshacer todos los cambios
            try {
                conn.rollback()
            } catch (_: Exception) {
                // Se conserva el error original
            }

            throw e

        } finally {
            conn.autoCommit = true
            conn.close()
        }
    }

    // Consultar todos los pedidos para el administrador
    fun listarPedidos(): List<PedidoResumen> {

        val sql = """
            SELECT
                p.id_pedido,
                p.id_usuario,
                u.nombre_completo,
                u.email,
                p.fecha_pedido,
                p.total,
                p.estado
            FROM pedidos p
            INNER JOIN usuarios u
                ON p.id_usuario = u.id_usuario
            ORDER BY p.fecha_pedido DESC, p.id_pedido DESC
        """.trimIndent()

        val pedidos = mutableListOf<PedidoResumen>()
        val conn = ConexionDB.obtenerConexion()

        try {
            conn.prepareStatement(sql).use { ps ->
                ps.executeQuery().use { rs ->

                    while (rs.next()) {
                        pedidos.add(
                            PedidoResumen(
                                idPedido = rs.getInt("id_pedido"),
                                idUsuario = rs.getInt("id_usuario"),
                                nombreCliente = rs.getString("nombre_completo"),
                                emailCliente = rs.getString("email"),
                                fechaPedido = rs.getTimestamp("fecha_pedido").toString(),
                                total = rs.getBigDecimal("total"),
                                estado = rs.getString("estado")
                            )
                        )
                    }
                }
            }

            return pedidos
        } finally {
            conn.close()
        }
    }

    // Consultar los detalles de un pedido
    fun obtenerDetalles(idPedido: Int): List<DetallePedido> {

        val sql = """
            SELECT
                dp.id_detalle,
                dp.id_pedido,
                dp.id_producto,
                p.nombre AS nombre_producto,
                dp.cantidad,
                dp.precio_unitario,
                dp.subtotal
            FROM detalle_pedidos dp
            INNER JOIN productos p
                ON dp.id_producto = p.id_producto
            WHERE dp.id_pedido = ?
            ORDER BY dp.id_detalle
        """.trimIndent()

        val detalles = mutableListOf<DetallePedido>()
        val conn = ConexionDB.obtenerConexion()

        try {
            conn.prepareStatement(sql).use { ps ->
                ps.setInt(1, idPedido)

                ps.executeQuery().use { rs ->
                    while (rs.next()) {
                        detalles.add(
                            DetallePedido(
                                idDetalle = rs.getInt("id_detalle"),
                                idPedido = rs.getInt("id_pedido"),
                                idProducto = rs.getInt("id_producto"),
                                nombreProducto = rs.getString("nombre_producto"),
                                cantidad = rs.getInt("cantidad"),
                                precioUnitario = rs.getBigDecimal("precio_unitario"),
                                subtotal = rs.getBigDecimal("subtotal")
                            )
                        )
                    }
                }
            }

            return detalles
        } finally {
            conn.close()
        }
    }

    // Actualizar el estado de un pedido
    fun actualizarEstado(idPedido: Int, estado: String): Boolean {

        val sql = """
            UPDATE pedidos
            SET estado = ?
            WHERE id_pedido = ?
        """.trimIndent()

        val conn = ConexionDB.obtenerConexion()

        try {
            conn.prepareStatement(sql).use { ps ->
                ps.setString(1, estado)
                ps.setInt(2, idPedido)

                return ps.executeUpdate() == 1
            }
        } finally {
            conn.close()
        }
    }
}
