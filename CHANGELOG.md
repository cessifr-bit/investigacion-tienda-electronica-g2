# Changelog

Todos los cambios notables de este proyecto de investigación se documentan en este archivo.

## [v1.0] - 2026-11-20
### Agregado
- **Backend REST API (`backend-app`):** Implementación de `ServidorApi.kt`, autenticación y control de acceso basado en roles (`AuthService.kt`, `UsuarioRepository.kt`), gestión de categorías (`CategoriaRepository.kt`) y procesamiento de pedidos (`PedidoService.kt`, `PedidoRepository.kt`), además de pruebas HTTP (`pruebas-api.http`).
- **Cliente Móvil Android (`app-android`):** Integración completa con el backend mediante Retrofit (`RetrofitClient.kt`, `ProductoApi.kt`, `TiendaViewModel.kt`), pantallas de inicio de sesión (`LoginScreen.kt`), carrito de compras (`CarritoScreen.kt`), perfil con gestión de sesión (`SesionManager.kt`), catálogo con filtrado y CRUD conectado a API (`Crud.Api.kt`).
- **Documentación y Cierre:** Informe final integrado (`docs/Informe_final.pdf`), documentación técnica (`docs/Documentación_Técnica.pdf`), diagrama de arquitectura (`docs/Diagrama/Arquitectura.jpeg`), guía de instalación y tabla de contribuciones verificables en `README.md`.

### Corregido
- Exclusión de archivos locales de configuración de IDE (`.idea/`) en `.gitignore` para mantener la higiene del repositorio.

## [v0.3] - 2026-11-08
### Agregado
- Ejecución de benchmarks comparativos en Java 21 con JMH 1.37 (`Proyecto-maven`).
- Implementación de pruebas de acceso a datos mediante `ConsultaJPA` (JPA/Hibernate) y `ConsultaNativa` (JDBC) en `MedicionExperimento.java`.
- Exportación de métricas crudas de latencia (`ms/op`) y memoria (`B/op`) en `resultados-jmh.json`.

## [v0.2] - 2026-10-16
### Agregado
- Entrega 2: Marco teórico, estado del arte, matriz de antecedentes, operacionalización de variables bajo estándar ISO/IEC 25010 y diseño experimental (`docs/entrega2-marco-metodologia.pdf`).
- Documentación de especificaciones de hardware y entorno experimental (`docs/entorno.md`).

## [v0.1] - 2026-09-18
### Agregado
- Entrega 1: Perfil de investigación, planteamiento del problema, preguntas de investigación, objetivos y delimitación tecnológica (`docs/entrega1-perfil.pdf`).
- Inicialización de la estructura base del monorepo y archivo de licencia (`LICENSE`).

## Contribuciones del Equipo

| Integrante | Usuarios Git | Rol Principal | Aportes Verificables |
| :--- | :--- | :--- | :--- |
| **Funes Ruiz, Cesia Esmeralda** | `Cesi`, `Cesia Funes` (`@cessifr-bit`) | Arquitectura del monorepo, integración API REST/Retrofit, carrito, roles y UI Android | 48 commits (`ServidorApi.kt`, `AuthService.kt`, `PedidoService.kt`, `RetrofitClient.kt`, `TiendaViewModel.kt`, `CarritoScreen.kt`, `HomeScreen.kt`, `SesionManager.kt`, CI workflow, `entrega1-perfil.md`, `entrega2-marco-metodologia.md`) |
| **Cortez Herrera, Darlyn Julissa** | `DarlynCortez`, `Darlyn Cortez` | Configuración Maven/JMH, integración inicial de API y guía de instalación | 17 commits (`ConsultaBenchmark.java`, `MedicionExperimento.java`, `0001-comparacion-jdbc-jpa.md`, `resultados-jmh.json`, `CatalogoApi.kt`, `Crud.Api.kt`, `README.md`, `entorno.md`) |
| **Recinos Miranda, Gerardo Emilio** | `g_rm0805`, `Gerardo Recinos` | Interfaz de catálogo Android, operacionalización y documentación técnica | 12 commits (`Catalogo.kt`, `docs/Diagrama/Arquitectura.jpeg`, `docs/Documentación_Técnica.pdf`, operacionalización en `entrega2-marco-metodologia.md`, `CHANGELOG.md`) |
| **Jiménez Martínez, Hazel Daniela** | `HazelJM`, `Hazel JM` | Vista CRUD Android, matriz de antecedentes y consolidación del informe final | 12 commits (`Crud.kt`, `docs/Informe_final.pdf`, `docs/informe-final.md`, matriz de antecedentes en `estado-del-arte.md`, `README.md`) |
| **Jacinto Vega, Adrián de Jesús** | `Adrian Jacinto`, `Adrian Vega` | Módulo de autenticación/Login en Android, DAO backend y estado del arte | 12 commits (`LoginScreen.kt`, `VistaLogin.kt`, `ProductoDao.java`, `ConexionMySql.java`, `estado-del-arte.md`, `entrega2-marco-metodologia.md`) |
| **Morales Fuentes, Abner Danilo** | `Danilo-4`, `Danilo Fuentes` | Persistencia backend Kotlin/MySQL, análisis estadístico y validez | 9 commits (`ConexionDB.kt`, `ProductoRepository.kt`, `Producto.kt`, `Main.kt`, `analisis-validez-resultados.md`, objetivos en `entrega1-perfil.md`) |
| **Gil Martínez, David Enrique** | `David Martinez`, `davidmartinez311` | Diseño de base de datos MySQL, roles de usuario y corpus de 1,000 registros | 7 commits (`TiendaTecnologiaDB.sql`, `TiendaTecnologicaDB2.sql`, estructura base `backend-app/Proyecto-backend`, cronograma en `entrega1-perfil.md`) |
