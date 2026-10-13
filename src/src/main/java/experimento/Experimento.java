package experimento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.BufferedWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Experimento: efecto de la estrategia de acceso a datos y del volumen
 * de registros sobre el tiempo de respuesta y la memoria pico.
 *
 *   Factor A: estrategia  -> JDBC (SQL nativo) / JPA (Hibernate)
 *   Factor B: volumen     -> 1.000 / 10.000 / 100.000 registros
 *   Respuesta: tiempo (ms) y memoria pico (MB)
 *   Repeticiones: 30 por combinacion, tras 5 de calentamiento
 *   Orden de las 6 series: aleatorio con semilla fija (42)
 *
 * Base de datos: UNA sola (TiendaTecnologiaDB, ver crear_base_experimento.sql).
 * El nivel de volumen se controla con WHERE IdProducto <= nivel.
 *
 * Resultados: experimentos/resultados.csv
 */
public class Experimento {

    // ---------------- CONFIGURACION (ajustar) ----------------
    static final String URL = "jdbc:mysql://localhost:3306/TiendaTecnologiaDB"
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USUARIO = "tiendaelectrica";
    static final String CLAVE = "TU_CLAVE";

    static final int[] NIVELES = {1000, 10000, 100000};
    static final int CALENTAMIENTO = 5;
    static final int REPETICIONES = 30;
    static final long SEMILLA = 42;

    // true = antes de cada serie el programa espera a que reinicies el
    // servicio MySQL (cache de la base reiniciada entre series).
    static final boolean PAUSAR_ENTRE_SERIES = true;

    static final String ARCHIVO_CSV = "experimentos/resultados.csv";

    // Misma consulta para las dos estrategias (SQL y JPQL equivalentes).
    static final String SQL =
            "SELECT IdProducto, Nombre, Marca, Categoria, CantidadDisponible "
          + "FROM Productos "
          + "WHERE IdProducto <= ? AND Categoria = 'Computadoras' "
          + "ORDER BY Nombre";
    static final String JPQL =
            "SELECT p FROM Producto p "
          + "WHERE p.id <= :limite AND p.categoria = 'Computadoras' "
          + "ORDER BY p.nombre";
    // ---------------------------------------------------------

    record Serie(String estrategia, int nivel) { }

    record Medicion(double tiempoMs, double picoMb, double asignadaMb, int filas) { }

    /** Evita que la JVM descarte el trabajo de la consulta. */
    static long sumidero = 0;

    /** Permite leer cuantos bytes asigno el hilo actual (HotSpot). */
    static final com.sun.management.ThreadMXBean HILO =
            (com.sun.management.ThreadMXBean) ManagementFactory.getThreadMXBean();

    interface Ejecucion {
        int ejecutar() throws Exception;
    }

    public static void main(String[] args) throws Exception {
        // 1) Las 6 combinaciones, en orden aleatorio reproducible (seed 42)
        List<Serie> series = new ArrayList<>();
        for (String estrategia : new String[] {"JDBC", "JPA"}) {
            for (int nivel : NIVELES) {
                series.add(new Serie(estrategia, nivel));
            }
        }
        Collections.shuffle(series, new Random(SEMILLA));

        System.out.println("Orden de ejecucion (seed " + SEMILLA + "):");
        for (int i = 0; i < series.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + series.get(i).estrategia()
                    + " / " + series.get(i).nivel());
        }

        // 2) Archivo de resultados
        Path ruta = Paths.get(ARCHIVO_CSV);
        if (ruta.getParent() != null) {
            Files.createDirectories(ruta.getParent());
        }
        boolean archivoNuevo = !Files.exists(ruta);

        Scanner teclado = new Scanner(System.in);

        try (BufferedWriter out = Files.newBufferedWriter(
                ruta, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            if (archivoNuevo) {
                out.write("orden_serie,estrategia,nivel,repeticion,tiempo_ms,memoria_pico_mb,memoria_asignada_mb,filas\n");
                out.flush();
            }

            // 3) Ejecutar cada serie
            for (int i = 0; i < series.size(); i++) {
                Serie serie = series.get(i);
                int orden = i + 1;

                if (PAUSAR_ENTRE_SERIES) {
                    System.out.printf("%nSerie %d/%d (%s, %d). Reinicia el servicio MySQL "
                            + "y presiona Enter...%n",
                            orden, series.size(), serie.estrategia(), serie.nivel());
                    teclado.nextLine();
                }

                ejecutarSerie(serie, orden, out);
            }
        }
        System.out.println("\nListo. Resultados en " + ARCHIVO_CSV);
    }

    static void ejecutarSerie(Serie serie, int orden, BufferedWriter out) throws Exception {
        int nivel = serie.nivel();

        if (serie.estrategia().equals("JDBC")) {
            try (Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE)) {
                correr(serie, orden, out, () -> ejecutarJdbc(con, nivel));
            }
        } else {
            // Una fabrica nueva por serie: se reinician las caches de Hibernate.
            Map<String, Object> propiedades = Map.of(
                    "jakarta.persistence.jdbc.url", URL,
                    "jakarta.persistence.jdbc.user", USUARIO,
                    "jakarta.persistence.jdbc.password", CLAVE);
            EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory("tienda", propiedades);
            try {
                correr(serie, orden, out, () -> ejecutarJpa(emf, nivel));
            } finally {
                emf.close();
            }
        }
    }

    /** Calentamiento + repeticiones de una serie. */
    static void correr(Serie serie, int orden, BufferedWriter out, Ejecucion ejecucion)
            throws Exception {
        for (int i = 0; i < CALENTAMIENTO; i++) {
            ejecucion.ejecutar();
        }

        for (int r = 1; r <= REPETICIONES; r++) {
            Medicion m = medir(ejecucion);

            out.write(String.format(Locale.US, "%d,%s,%d,%d,%.3f,%.3f,%.3f,%d%n",
                    orden, serie.estrategia(), serie.nivel(), r,
                    m.tiempoMs(), m.picoMb(), m.asignadaMb(), m.filas()));
            out.flush();

            System.out.printf(Locale.US,
                    "%s | %d | rep %d | %.3f ms | pico %.3f MB | asignada %.3f MB | %d filas%n",
                    serie.estrategia(), serie.nivel(), r,
                    m.tiempoMs(), m.picoMb(), m.asignadaMb(), m.filas());
        }
    }

    // ---------------- Estrategia 1: JDBC (SQL nativo) ----------------

    static int ejecutarJdbc(Connection con, int limite) throws SQLException {
        List<Producto> lista = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(SQL)) {
            ps.setInt(1, limite);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Producto(
                            rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getInt(5)));
                }
            }
        }
        return consumir(lista);
    }

    // ---------------- Estrategia 2: JPA / Hibernate ----------------

    static int ejecutarJpa(EntityManagerFactory emf, int limite) {
        // EntityManager nuevo en cada ejecucion: sin cache de primer nivel previa.
        EntityManager em = emf.createEntityManager();
        try {
            List<Producto> lista = em.createQuery(JPQL, Producto.class)
                    .setParameter("limite", limite)
                    .getResultList();
            return consumir(lista);
        } finally {
            em.close();
        }
    }

    /** Recorre el resultado (igual para las dos estrategias). */
    static int consumir(List<Producto> lista) {
        long suma = 0;
        for (Producto p : lista) {
            suma += p.getCantidadDisponible() + p.getNombre().length();
        }
        sumidero += suma;
        return lista.size();
    }

    // ---------------- Medicion de tiempo y memoria ----------------

    /**
     * Mide una ejecucion.
     *  - Tiempo: System.nanoTime() alrededor de la ejecucion.
     *  - Memoria pico: maximo del heap usado durante la ejecucion, menos el
     *    heap usado justo antes (tras un System.gc()). La JVM actualiza este
     *    dato por bloques grandes, asi que con pocos registros puede dar 0.
     *  - Memoria asignada: bytes que la ejecucion pidio a la JVM (hilo
     *    actual). Tiene resolucion fina y complementa al pico.
     */
    static Medicion medir(Ejecucion ejecucion) throws Exception {
        System.gc();
        reiniciarPicos();
        long base = heapPicoBytes();
        long asignadoAntes = HILO.getCurrentThreadAllocatedBytes();

        long inicio = System.nanoTime();
        int filas = ejecucion.ejecutar();
        double ms = (System.nanoTime() - inicio) / 1_000_000.0;

        long asignado = HILO.getCurrentThreadAllocatedBytes() - asignadoAntes;
        long pico = heapPicoBytes();
        double picoMb = Math.max(0, pico - base) / (1024.0 * 1024.0);
        return new Medicion(ms, picoMb, asignado / (1024.0 * 1024.0), filas);
    }

    static void reiniciarPicos() {
        for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (pool.getType() == MemoryType.HEAP) {
                pool.resetPeakUsage();
            }
        }
    }

    /** Suma del pico de cada zona del heap desde el ultimo reinicio de picos. */
    static long heapPicoBytes() {
        long total = 0;
        for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (pool.getType() == MemoryType.HEAP) {
                total += pool.getPeakUsage().getUsed();
            }
        }
        return total;
    }
}
