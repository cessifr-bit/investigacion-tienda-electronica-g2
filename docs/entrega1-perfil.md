# Título provisional

_"Análisis del rendimiento de consultas y operaciones de gestión de productos en una aplicación para una tienda de productos electrónicos"_

# Planteamiento del problema

Las tiendas de productos electrónicos manejan información relacionada con productos, precios y características, por lo que requieren operaciones frecuentes de consulta y actualización de datos. En este tipo de aplicaciones, el rendimiento de las consultas y de las operaciones de gestión de productos puede influir en el tiempo de respuesta del sistema y en la eficiencia con la que se procesa la información.

Actualmente, las operaciones de consulta y gestión de productos pueden presentar tiempos de respuesta variables, especialmente cuando aumenta la cantidad de información que debe procesar el sistema. En una tienda de productos electrónicos, estas operaciones son frecuentes, porque la aplicación debe permitir consultar información de los productos, registrar nuevos productos, modificar sus datos y eliminar los que ya no se encuentren disponibles.

Cuando la cantidad de registros aumenta, las consultas y operaciones realizadas sobre la base de datos pueden requerir un mayor procesamiento. Esto puede generar diferencias en los tiempos de respuesta de la aplicación y afectar la eficiencia con la que se administra la información de los productos. Además, el comportamiento del sistema puede variar dependiendo de la forma en que se realizan las consultas y de las características del gestor de base de datos utilizado.

Por esta razón, resulta necesario analizar el rendimiento de estas operaciones bajo diferentes cantidades de información y condiciones de prueba. Los estudios sobre el rendimiento de bases de datos utilizan mediciones y escenarios de prueba para comparar el comportamiento de los sistemas ante diferentes condiciones (Durán-Cazar et al., 2019). De igual forma, el análisis de consultas puede considerar métricas como tiempo de ejecución y el consumo de recursos para identificar aspectos relacionados con el rendimiento (Microsoft, s. f.). En MySQL, el ajuste de las consultas también permite analizar factores relacionados con su eficiencia y con el rendimiento de las aplicaciones que utilizan la base de datos (Google Cloud, s. f.).

A partir de esto, se plantea la necesidad de determinar cómo el tamaño del catálogo de productos puede afectar el tiempo de respuesta y el consumo de memoria de una aplicación de tienda en línea para Android, desarrollada en Java y utilizando MySQL como base de datos. Por tanto, la pregunta principal de investigación es: ¿Cuál es el efecto del tamaño del catálogo de productos sobre el tiempo de respuesta y el consumo de memoria en una aplicación de tienda en línea para Android desarrollada en Java con base de datos en MySQL?

## Referencias

Durán-Cazar, J. W., Tandazo-Gaona, E. J., Morales-Morales, M. R., & Morales Cardoso, S. (2019). Rendimiento de bases de datos columnares. *Ingenius*, (22), 47–58. https://doi.org/10.17163/ings.n22.2019.05

Google Cloud. (s. f.). Cómo aprovechar al máximo el rendimiento de MySQL: ajuste de consultas. https://cloud.google.com/mysql/query-tuning?hl=es

Microsoft. (s. f.). Solución de problemas de consultas de ejecución lenta - SQL Server. https://learn.microsoft.com/es-es/troubleshoot/sql/database-engine/performance/troubleshoot-slow-running-queries

