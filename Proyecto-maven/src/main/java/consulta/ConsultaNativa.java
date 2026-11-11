package consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaNativa {

    public static int consultarProductos(int limite)
            throws SQLException {

        if (limite <= 0) {
            throw new IllegalArgumentException(
                    "El limite debe ser mayor que cero"
            );
        }

        String sql = """
                SELECT IdProducto, Nombre, Marca,
                       Categoria, CantidadDisponible
                FROM Productos
                ORDER BY IdProducto
                LIMIT ?
                """;

        int cantidad = 0;

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, limite);

            try (ResultSet resultado =
                         sentencia.executeQuery()) {

                while (resultado.next()) {
                    // Leer las mismas columnas en cada prueba.
                    resultado.getInt("IdProducto");
                    resultado.getString("Nombre");
                    resultado.getString("Marca");
                    resultado.getString("Categoria");
                    resultado.getInt("CantidadDisponible");

                    cantidad++;
                }
            }
        }

        return cantidad;
    }
}
