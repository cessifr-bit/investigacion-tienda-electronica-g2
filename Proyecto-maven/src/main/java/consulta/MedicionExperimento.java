package consulta;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Locale;

public class MedicionExperimento {



        private static final int[] VOLUMENES = {100, 500, 1000};
        private static final int CALENTAMIENTO = 5;
        private static final int REPETICIONES = 30;

        public static void main(String[] args) {

            try (PrintWriter archivo = new PrintWriter(
                    new FileWriter("resultados.csv"))) {

                archivo.println("Metodo,Volumen,Repeticion,TiempoMs");

                // Calentamiento inicial de ambos métodos
                for (int i = 0; i < CALENTAMIENTO; i++) {
                    for (int volumen : VOLUMENES) {
                        ConsultaNativa.consultarProductos(volumen);
                        ConsultaJPA.consultarProductos(volumen);
                    }
                }

                for (int volumen : VOLUMENES) {

                    for (int repeticion = 1;
                         repeticion <= REPETICIONES;
                         repeticion++) {

                        // Alterna el orden para reducir sesgos
                        if (repeticion % 2 == 1) {
                            medirNativa(archivo, volumen, repeticion);
                            medirJPA(archivo, volumen, repeticion);
                        } else {
                            medirJPA(archivo, volumen, repeticion);
                            medirNativa(archivo, volumen, repeticion);
                        }
                    }
                }

                System.out.println(
                        "Mediciones terminadas. Archivo: resultados.csv"
                );

            } catch (Exception e) {
                System.out.println(
                        "Error durante el experimento: " + e.getMessage()
                );
                e.printStackTrace();
            } finally {
                ConsultaJPA.cerrar();
            }
        }

        private static void medirNativa(
                PrintWriter archivo, int volumen, int repeticion)
                throws SQLException {

            long inicio = System.nanoTime();
            int cantidad = ConsultaNativa.consultarProductos(volumen);
            long fin = System.nanoTime();

            guardar(archivo, "SQL_Nativo", volumen, repeticion,
                    (fin - inicio) / 1_000_000.0, cantidad);
        }

        private static void medirJPA(
                PrintWriter archivo, int volumen, int repeticion) {

            long inicio = System.nanoTime();
            int cantidad = ConsultaJPA.consultarProductos(volumen);
            long fin = System.nanoTime();

            guardar(archivo, "JPA_Hibernate", volumen, repeticion,
                    (fin - inicio) / 1_000_000.0, cantidad);
        }

        private static void guardar(
                PrintWriter archivo, String metodo, int volumen,
                int repeticion, double tiempoMs, int cantidad) {

            archivo.printf(Locale.US, "%s,%d,%d,%.6f%n",
                    metodo, volumen, repeticion, tiempoMs);

            archivo.flush();

            if (cantidad != volumen) {
                throw new IllegalStateException(
                        "Se esperaban " + volumen
                                + " productos, pero se recibieron " + cantidad
                );
            }
        }
    }


