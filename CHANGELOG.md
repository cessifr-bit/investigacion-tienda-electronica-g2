# Changelog

Todos los cambios notables de este proyecto de investigación se documentan en este archivo.

## [v1.0] - 2026-11-15
### Agregado
- Informe final integrado en formato científico (`docs/informe-final.pdf`).
- Aplicación móvil nativa Android (`app-android`) con flujo CRUD completo y filtrado de catálogo.
- Módulo backend en Kotlin (`backend-app`) con conexión a MySQL y esquema `TiendaTecnologiaDB.sql`.
- Documentación de cierre, tabla de contribuciones verificables y revisión de higiene del repositorio.

## [v0.3] - 2026-11-08
### Agregado
- Ejecución de benchmarks comparativos en Java 21 con JMH 1.37 (`Proyecto-maven`).
- Implementación de pruebas de acceso a datos mediante `ConsultaJPA` (JPA/Hibernate) y `ConsultaNativa` (JDBC).
- Exportación de métricas crudas de latencia (`ms/op`) y memoria (`B/op`) en `resultados-jmh.json`.

## [v0.2] - 2026-10-16
### Agregado
- Entrega 2: Marco teórico, estado del arte, operacionalización de variables bajo estándar ISO/IEC 25010 y diseño experimental.
- Documentación de condiciones de entorno (`docs/entorno.md`).

## [v0.1] - 2026-09-18
### Agregado
- Entrega 1: Perfil de investigación, planteamiento del problema, preguntas de investigación, objetivos y delimitación tecnológica.
- Inicialización de la estructura base del repositorio.

## Contribuciones del Equipo

| Integrante | Usuarios Git | Rol Principal | Aportes Verificables |
| :--- | :--- | :--- | :--- |
| **Funes Ruiz, Cesia Esmeralda** | `Cesi`, `Cesia Funes` (`@cessifr-bit`) | Arquitectura del monorepo, interfaz Android (`HomeScreen`, tema) y gestión de entregas | 42 commits (`HomeScreen.kt`, `MainActivity.kt`, estructura `Proyecto-maven` y `app-android`, CI workflow, `entorno.md`, `entrega1-perfil.md`, `entrega2-marco-metodologia.md`) |
| **Cortez Herrera, Darlyn Julissa** | `DarlynCortez`, `Darlyn Cortez` | Configuración del proyecto Maven, benchmark JMH y documentación de entorno | 13 commits (`pom.xml`, `ConsultaBenchmark.java`, `MedicionExperimento.java`, `0001-comparacion-jdbc-jpa.md`, `resultados-jmh.json`, `entorno.md`) |
| **Recinos Miranda, Gerardo Emilio** | `g_rm0805`, `Gerardo Recinos` | Interfaz de catálogo Android, operacionalización y cierre documental | 11 commits (`Catalogo.kt`, `AndroidManifest.xml`, operacionalización y marco teórico en `entrega2-marco-metodologia.md`, `CHANGELOG.md`) |
| **Morales Fuentes, Abner Danilo** | `Danilo-4`, `Danilo Fuentes` | Desarrollo backend Kotlin/MySQL, análisis estadístico y validez | 10 commits (`ConexionDB.kt`, `ProductoRepository.kt`, `Producto.kt`, `Main.kt`, `analisis-validez-resultados.md`, objetivos en `entrega1-perfil.md`) |
| **Jiménez Martínez, Hazel Daniela** | `HazelJM`, `Hazel JM` | Vista CRUD en Android, matriz de antecedentes y pregunta de investigación | 9 commits (`Crud.kt`, matriz de antecedentes en `estado-del-arte.md` y `entrega2-marco-metodologia.md`, `README.md`) |
| **Jacinto Vega, Adrián de Jesús** | `Adrian Vega` | Revisión del estado del arte y consolidación metodológica | 6 commits (`estado-del-arte.md`, `entrega2-marco-metodologia.md`, `entrega1-perfil.md`) |
| **Gil Martínez, David Enrique** | `davidmartinez311`, `David Martinez` | Diseño de base de datos MySQL, corpus de 1,000 registros y estructura backend | 5 commits (`TiendaTecnologicaDB2.sql`, `TiendaTecnologiaDB.sql`, carpeta base `backend-app/Proyecto-backend`, cronograma en `entrega1-perfil.md`) |
