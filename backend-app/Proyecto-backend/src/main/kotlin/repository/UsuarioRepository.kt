package repository

import config.ConexionDB
import java.sql.Statement

data class UsuarioAuth(
    val idUsuario: Int,
    val nombreCompleto: String,
    val email: String,
    val passwordHash: String,
    val rol: String
)

class UsuarioRepository {

    // Buscar un usuario por correo electrónico
    fun buscarPorEmail(email: String): UsuarioAuth? {
        val sql = """
            SELECT id_usuario, nombre_completo, email, password_hash, rol
            FROM usuarios
            WHERE email = ?
            LIMIT 1
        """.trimIndent()

        ConexionDB.obtenerConexion().use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, email.trim().lowercase())

                stmt.executeQuery().use { rs ->
                    if (rs.next()) {
                        return UsuarioAuth(
                            idUsuario = rs.getInt("id_usuario"),
                            nombreCompleto = rs.getString("nombre_completo"),
                            email = rs.getString("email"),
                            passwordHash = rs.getString("password_hash"),
                            rol = rs.getString("rol")
                        )
                    }
                }
            }
        }

        return null
    }

    // Registrar un nuevo cliente
    fun registrar(
        nombreCompleto: String,
        email: String,
        passwordHash: String,
        telefono: String? = null,
        direccion: String? = null
    ): Int {

        val sql = """
            INSERT INTO usuarios (
                nombre_completo,
                email,
                password_hash,
                telefono,
                direccion,
                rol
            )
            VALUES (?, ?, ?, ?, ?, 'CLIENTE')
        """.trimIndent()

        ConexionDB.obtenerConexion().use { conn ->
            conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            ).use { stmt ->

                stmt.setString(1, nombreCompleto.trim())
                stmt.setString(2, email.trim().lowercase())
                stmt.setString(3, passwordHash)
                stmt.setString(4, telefono)
                stmt.setString(5, direccion)

                stmt.executeUpdate()

                stmt.generatedKeys.use { keys ->
                    if (keys.next()) {
                        return keys.getInt(1)
                    }
                }
            }
        }

        return 0
    }
}
