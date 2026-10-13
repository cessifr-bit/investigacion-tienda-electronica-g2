package experimento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad de la tabla Productos. La usan las dos estrategias:
 * JPA/Hibernate la carga directamente y JDBC arma un objeto por fila.
 */
@Entity
@Table(name = "Productos")
public class Producto {

    @Id
    @Column(name = "IdProducto")
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Marca")
    private String marca;

    @Column(name = "Categoria")
    private String categoria;

    @Column(name = "CantidadDisponible")
    private int cantidadDisponible;

    /** Requerido por JPA. */
    protected Producto() {
    }

    public Producto(Integer id, String nombre, String marca, String categoria, int cantidadDisponible) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.cantidadDisponible = cantidadDisponible;
    }

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getMarca() { return marca; }
    public String getCategoria() { return categoria; }
    public int getCantidadDisponible() { return cantidadDisponible; }
}
