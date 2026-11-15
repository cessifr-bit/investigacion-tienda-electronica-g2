package repository

import config.ConexionDB
import model.Producto
import java.math.BigDecimal
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Types

class ProductoRepository {

    // 1. CREAR PRODUCTO (INSERT)
    fun insertar(producto: Producto): Int {
        val sql = """
            INSERT INTO productos (codigo_sku, nombre, descripcion, precio, stock, imagen_url, id_categoria, id_marca)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()

        ConexionDB.obtenerConexion().use { conn ->
            conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { ps ->
                ps.setString(1, producto.codigoSku)
                ps.setString(2, producto.nombre)
                ps.setString(3, producto.descripcion)
                ps.setBigDecimal(4, producto.precio)
                ps.setInt(5, producto.stock)
                ps.setString(6, producto.imagenUrl)

                if (producto.idCategoria != null) ps.setInt(7, producto.idCategoria) else ps.setNull(7, Types.INTEGER)
                if (producto.idMarca != null) ps.setInt(8, producto.idMarca) else ps.setNull(8, Types.INTEGER)

                ps.executeUpdate()
                ps.generatedKeys.use { keys ->
                    if (keys.next()) return keys.getInt(1)
                }
            }
        }
        return -1
    }

    // 2. LISTAR CATÁLOGO COMPLETO ACTIVO
    fun listarActivos(): List<Producto> {
        val sql = """
            SELECT p.*, c.nombre AS nombre_categoria, m.nombre AS nombre_marca
            FROM productos p
            LEFT JOIN categorias c ON p.id_categoria = c.id_categoria
            LEFT JOIN marcas m ON p.id_marca = m.id_marca
            WHERE p.estado = 1
            ORDER BY p.id_producto ASC
        """.trimIndent()

        val lista = mutableListOf<Producto>()
        ConexionDB.obtenerConexion().use { conn ->
            conn.prepareStatement(sql).use { ps ->
                ps.executeQuery().use { rs ->
                    while (rs.next()) {
                        lista.add(mapearProducto(rs))
                    }
                }
            }
        }
        return lista
    }

    // 3. OBTENER PRODUCTO POR ID
    fun obtenerPorId(idProducto: Int): Producto? {
        val sql = """
            SELECT p.*, c.nombre AS nombre_categoria, m.nombre AS nombre_marca
            FROM productos p
            LEFT JOIN categorias c ON p.id_categoria = c.id_categoria
            LEFT JOIN marcas m ON p.id_marca = m.id_marca
            WHERE p.id_producto = ?
        """.trimIndent()

        ConexionDB.obtenerConexion().use { conn ->
            conn.prepareStatement(sql).use { ps ->
                ps.setInt(1, idProducto)
                ps.executeQuery().use { rs ->
                    if (rs.next()) return mapearProducto(rs)
                }
            }
        }
        return null
    }

    // 4. BUSCAR POR NOMBRE O CÓDIGO SKU
    fun buscarPorNombreOSku(criterio: String): List<Producto> {
        val sql = """
            SELECT p.*, c.nombre AS nombre_categoria, m.nombre AS nombre_marca
            FROM productos p
            LEFT JOIN categorias c ON p.id_categoria = c.id_categoria
            LEFT JOIN marcas m ON p.id_marca = m.id_marca
            WHERE p.estado = 1 AND (p.nombre LIKE ? OR p.codigo_sku LIKE ?)
            ORDER BY p.nombre ASC
        """.trimIndent()

        val lista = mutableListOf<Producto>()
        val patron = "%$criterio%"

        ConexionDB.obtenerConexion().use { conn ->
            conn.prepareStatement(sql).use { ps ->
                ps.setString(1, patron)
                ps.setString(2, patron)
                ps.executeQuery().use { rs ->
                    while (rs.next()) {
                        lista.add(mapearProducto(rs))
                    }
                }
            }
        }
        return lista
    }

    // 5. FILTRAR POR CATEGORÍA Y RANGO DE PRECIOS
    fun filtrarPorCategoriaYPrecio(idCategoria: Int, precioMin: BigDecimal, precioMax: BigDecimal): List<Producto> {
        val sql = """
            SELECT p.*, c.nombre AS nombre_categoria, m.nombre AS nombre_marca
            FROM productos p
            LEFT JOIN categorias c ON p.id_categoria = c.id_categoria
            LEFT JOIN marcas m ON p.id_marca = m.id_marca
            WHERE p.estado = 1 
              AND p.id_categoria = ? 
              AND p.precio BETWEEN ? AND ?
            ORDER BY p.precio ASC
        """.trimIndent()

        val lista = mutableListOf<Producto>()
        ConexionDB.obtenerConexion().use { conn ->
            conn.prepareStatement(sql).use { ps ->
                ps.setInt(1, idCategoria)
                ps.setBigDecimal(2, precioMin)
                ps.setBigDecimal(3, precioMax)
                ps.executeQuery().use { rs ->
                    while (rs.next()) {
                        lista.add(mapearProducto(rs))
                    }
                }
            }
        }
        return lista
    }

    // 6. CONSULTAR PRODUCTOS CON BAJO STOCK
    fun listarBajoStock(umbral: Int = 20): List<Producto> {
        val sql = """
            SELECT p.*, c.nombre AS nombre_categoria, m.nombre AS nombre_marca
            FROM productos p
            LEFT JOIN categorias c ON p.id_categoria = c.id_categoria
            LEFT JOIN marcas m ON p.id_marca = m.id_marca
            WHERE p.estado = 1 AND p.stock <= ?
            ORDER BY p.stock ASC
        """.trimIndent()

        val lista = mutableListOf<Producto>()
        ConexionDB.obtenerConexion().use { conn ->
            conn.prepareStatement(sql).use { ps ->
                ps.setInt(1, umbral)
                ps.executeQuery().use { rs ->
                    while (rs.next()) {
                        lista.add(mapearProducto(rs))
                    }
                }
            }
        }
        return lista
    }

    // 7. ACTUALIZAR PRODUCTO
    fun actualizar(producto: Producto): Boolean {
        val sql = """
            UPDATE productos
            SET codigo_sku = ?, nombre = ?, descripcion = ?, precio = ?,
                stock = ?, imagen_url = ?, id_categoria = ?, id_marca = ?
            WHERE id_producto = ?
        """.trimIndent()

        ConexionDB.obtenerConexion().use { conn ->
            conn.prepareStatement(sql).use { ps ->
                ps.setString(1, producto.codigoSku)
                ps.setString(2, producto.nombre)
                ps.setString(3, producto.descripcion)
                ps.setBigDecimal(4, producto.precio)
                ps.setInt(5, producto.stock)
                ps.setString(6, producto.imagenUrl)

                if (producto.idCategoria != null) ps.setInt(7, producto.idCategoria) else ps.setNull(7, Types.INTEGER)
                if (producto.idMarca != null) ps.setInt(8, producto.idMarca) else ps.setNull(8, Types.INTEGER)

                ps.setInt(9, producto.idProducto)
                return ps.executeUpdate() > 0
            }
        }
    }

    // 8. AJUSTAR STOCK
    fun ajustarStock(idProducto: Int, deltaCantidad: Int): Boolean {
        val sql = """
            UPDATE productos
            SET stock = stock + ?
            WHERE id_producto = ? AND (stock + ?) >= 0
        """.trimIndent()

        ConexionDB.obtenerConexion().use { conn ->
            conn.prepareStatement(sql).use { ps ->
                ps.setInt(1, deltaCantidad)
                ps.setInt(2, idProducto)
                ps.setInt(3, deltaCantidad)
                return ps.executeUpdate() > 0
            }
        }
    }

    // 9. ELIMINAR PRODUCTO
    fun eliminarLogico(idProducto: Int): Boolean {
        val sql = "UPDATE productos SET estado = 0 WHERE id_producto = ?"

        ConexionDB.obtenerConexion().use { conn ->
            conn.prepareStatement(sql).use { ps ->
                ps.setInt(1, idProducto)
                return ps.executeUpdate() > 0
            }
        }
    }

    private fun mapearProducto(rs: ResultSet): Producto {
        return Producto(
            idProducto = rs.getInt("id_producto"),
            codigoSku = rs.getString("codigo_sku"),
            nombre = rs.getString("nombre"),
            descripcion = rs.getString("descripcion"),
            precio = rs.getBigDecimal("precio"),
            stock = rs.getInt("stock"),
            imagenUrl = rs.getString("imagen_url"),
            idCategoria = rs.getObject("id_categoria") as? Int,
            idMarca = rs.getObject("id_marca") as? Int,
            estado = rs.getBoolean("estado"),
            nombreCategoria = rs.getString("nombre_categoria"),
            nombreMarca = rs.getString("nombre_marca")
        )
    }
}