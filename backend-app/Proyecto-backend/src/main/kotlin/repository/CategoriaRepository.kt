package repository

import config.ConexionDB

data class Categoria(val idCategoria: Int, val nombre: String)

class CategoriaRepository {

    fun listarActivas(): List<Categoria> {
        val sql = "SELECT id_categoria, nombre FROM categorias WHERE activo = 1 ORDER BY nombre ASC"
        val lista = mutableListOf<Categoria>()
        ConexionDB.obtenerConexion().use { conn ->
            conn.prepareStatement(sql).use { ps ->
                ps.executeQuery().use { rs ->
                    while (rs.next()) {
                        lista.add(Categoria(rs.getInt("id_categoria"), rs.getString("nombre")))
                    }
                }
            }
        }
        return lista
    }
}