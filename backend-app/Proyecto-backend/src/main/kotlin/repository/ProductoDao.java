package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import model.Producto;
import repository.ConexionMySql;

public class ProductoDao {

    public boolean registrarProducto(String nombre, String descripcion, double precio, int stock) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionMySql.obtenerConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nombre);
            pst.setString(2, descripcion);
            pst.setDouble(3, precio);
            pst.setInt(4, stock);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarProducto(int id, String nombre, String descripcion, double precio, int stock) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, stock = ? WHERE id = ?";
        try (Connection con = ConexionMySql.obtenerConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nombre);
            pst.setString(2, descripcion);
            pst.setDouble(3, precio);
            pst.setInt(4, stock);
            pst.setInt(5, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection con = ConexionMySql.obtenerConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection con = ConexionMySql.obtenerConexion();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("codigoSku"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getBigDecimal("precio"),
                        rs.getInt("stock"),
                        rs.getString("imagenUrl"),
                        rs.getObject("idCategoria") != null ? rs.getInt("idCategoria") : null,
                        rs.getObject("idMarca") != null ? rs.getInt("idMarca") : null,
                        rs.getBoolean("estado"),
                        rs.getString("nombreCategoria"),
                        rs.getString("nombreMarca")



                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
