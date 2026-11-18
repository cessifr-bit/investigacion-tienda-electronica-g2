package config

import java.sql.Connection
import java.sql.DriverManager

object ConexionDB {
    private const val URL = "jdbc:mysql://localhost:3306/TiendaTecnologicaDB2?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"
    private const val USER = "root"
    private const val PASSWORD = "PROGRA2" // Cambiar si tienen contraseña en mysql

    fun obtenerConexion(): Connection {
        Class.forName("com.mysql.cj.jdbc.Driver")
        return DriverManager.getConnection(URL, USER, PASSWORD)
    }
}