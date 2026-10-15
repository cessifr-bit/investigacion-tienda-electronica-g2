package consulta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultaJPA {

    private static EntityManagerFactory fabrica =
            crearFabrica();

    private static EntityManagerFactory crearFabrica() {
        Map<String, Object> propiedades = new HashMap<>();

        propiedades.put(
                "jakarta.persistence.jdbc.driver",
                "com.mysql.cj.jdbc.Driver"
        );

        propiedades.put(
                "jakarta.persistence.jdbc.url",
                System.getenv().getOrDefault(
                        "DB_URL",
                        "jdbc:mysql://localhost:3306/TiendaTecnologiaDB"
                )
        );

        propiedades.put(
                "jakarta.persistence.jdbc.user",
                "root"
        );

        propiedades.put(
                "jakarta.persistence.jdbc.password",
                System.getenv().getOrDefault(
                        "DB_PASSWORD",
                        "PROGRA2"
                )
        );

        return Persistence.createEntityManagerFactory(
                "TiendaPU",
                propiedades
        );
    }

    public static int consultarProductos(int limite) {
        if (limite <= 0) {
            throw new IllegalArgumentException(
                    "El limite debe ser mayor que cero"
            );
        }

        if (!fabrica.isOpen()) {
            throw new IllegalStateException(
                    "La fábrica JPA está cerrada."
            );
        }

        EntityManager em = fabrica.createEntityManager();

        try {
            List<Producto> productos = em.createQuery(
                            "SELECT p FROM Producto p "
                                    + "ORDER BY p.idProducto",
                            Producto.class
                    )
                    .setMaxResults(limite)
                    .getResultList();

            return productos.size();

        } finally {
            em.close();
        }
    }

    public static void iniciarBenchmark() {
        if (fabrica.isOpen()) {
            return;
        }

        fabrica = crearFabrica();
    }

    public static void cerrarBenchmark() {
        if (fabrica != null && fabrica.isOpen()) {
            fabrica.close();
        }
    }

    public static void cerrar() {
        cerrarBenchmark();
    }
}