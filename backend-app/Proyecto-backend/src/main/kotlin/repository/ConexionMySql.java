package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySql {
    private static final String URL = "jdbc:mysql://localhost:3306/TiendaTecnologicaDB2?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("¡Conexión exitosa a la base de datos!");
        } catch (Exception e) {
            System.err.println("--- ERROR DETALLADO DE CONEXIÓN ---");
            e.printStackTrace(); // Esto nos dirá exactamente si es la contraseña, el puerto o el usuario
        }
        return conexion;
    }
}