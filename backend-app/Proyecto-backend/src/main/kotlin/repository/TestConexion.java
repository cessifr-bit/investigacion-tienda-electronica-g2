package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) {
        System.out.println("=== PROBANDO CONEXIÓN A MYSQL ===");

        var conexion = ConexionMySql.obtenerConexion();
        if (conexion != null) {
            System.out.println("¡Conexión establecida con éxito a la base de datos!");
        } else {
            System.out.println("Error: No se pudo conectar a la base de datos.");
        }



        System.out.println("\n=== PROBANDO LISTADO DE PRODUCTOS ===");
        String sql = "SELECT * FROM productos";
        try (var con = ConexionMySql.obtenerConexion();
             var pst = con.prepareStatement(sql);
             var rs = pst.executeQuery()) {

            boolean hayProductos = false;
            while (rs.next()) {
                hayProductos = true;
                String nombreProd = rs.getString("nombre");
                double precioProd = rs.getDouble("precio");
                System.out.println("- " + nombreProd + " | Precio: $" + precioProd);
            }

            if (!hayProductos) {
                System.out.println("No hay productos registrados o la tabla está vacía.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}