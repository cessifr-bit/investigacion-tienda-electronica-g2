# Título provisional

_"Análisis del rendimiento de consultas y operaciones de gestión de productos en una aplicación para una tienda de productos electrónicos"_

# Planteamiento del problema

Las tiendas de productos electrónicos manejan información relacionada con productos, precios y características, por lo que requieren operaciones frecuentes de consulta y actualización de datos. En este tipo de aplicaciones, el rendimiento de las consultas y de las operaciones de gestión de productos puede influir en el tiempo de respuesta del sistema y en la eficiencia con la que se procesa la información.

Actualmente, las operaciones de consulta y gestión de productos pueden presentar tiempos de respuesta variables, especialmente cuando aumenta la cantidad de información que debe procesar el sistema. En una tienda de productos electrónicos, estas operaciones son frecuentes, porque la aplicación debe permitir consultar información de los productos, registrar nuevos productos, modificar sus datos y eliminar los que ya no se encuentren disponibles.

Cuando la cantidad de registros aumenta, las consultas y operaciones realizadas sobre la base de datos pueden requerir un mayor procesamiento. Esto puede generar diferencias en los tiempos de respuesta de la aplicación y afectar la eficiencia con la que se administra la información de los productos. Además, el comportamiento del sistema puede variar dependiendo de la forma en que se realizan las consultas y de las características del gestor de base de datos utilizado.

Por esta razón, resulta necesario analizar el rendimiento de estas operaciones bajo diferentes cantidades de información y condiciones de prueba. Los estudios sobre el rendimiento de bases de datos utilizan mediciones y escenarios de prueba para comparar el comportamiento de los sistemas ante diferentes condiciones (Durán-Cazar et al., 2019). De igual forma, el análisis de consultas puede considerar métricas como tiempo de ejecución y el consumo de recursos para identificar aspectos relacionados con el rendimiento (Microsoft, s. f.). En MySQL, el ajuste de las consultas también permite analizar factores relacionados con su eficiencia y con el rendimiento de las aplicaciones que utilizan la base de datos (Google Cloud, s. f.).

A partir de esto, se plantea la necesidad de determinar cómo el tamaño del catálogo de productos puede afectar el tiempo de respuesta y el consumo de memoria de una aplicación de tienda en línea para Android, desarrollada en Java y utilizando MySQL como base de datos. Por tanto, la pregunta principal de investigación es: ¿Cuál es el efecto del tamaño del catálogo de productos sobre el tiempo de respuesta y el consumo de memoria en una aplicación de tienda en línea para Android desarrollada en Java con base de datos en MySQL?

# Preguntas de Investigación

## Pregunta Principal
¿Cuál es el efecto del tamaño del catálogo de productos sobre el tiempo de respuesta y el consumo de memoria en una aplicación de tienda en línea para Android desarrollada en Java con base de datos en MySQL?

## Variables de investigación

## Variables independientes:
Tamaño del catálogo de productos (cantidad de registros en la base de datos).
Cantidad de usuarios concurrentes.
Uso de filtros de búsqueda.
Implementación de los índices en la base de datos.

## Variables dependientes:
Tiempo de respuesta de la aplicación.
Consumo de memoria durante la aplicación.
Rendimiento de la aplicación.
Tiempo de respuesta de la consulta.
Eficiencia de la consulta.

## Preguntas Secundarias
1. ¿Cómo influye la cantidad de usuarios concurrentes en el rendimiento de la aplicación Android al realizar búsquedas y consultas de productos?
2. ¿Qué impacto tienen los filtros de búsqueda (categoría y precio) en el tiempo de respuesta de las consultas dentro de la aplicación móvil?
3. ¿En qué medida la implementación de los índices en la base de datos mejora la eficiencia de las consultas en escenarios con grandes volúmenes de datos en un dispositivo Android?

# Objetivo general

Determinar el efecto del tamaño del catálogo de productos sobre el tiempo de respuesta y el consumo de memoria de una aplicación de tienda en línea para Android, desarrollada en Java con una base de datos MySQL.

# Objetivos específicos

1. **Medir** el tiempo de respuesta y el consumo de memoria de la aplicación Android al trabajar con diferentes tamaños de catálogo de productos.

2. **Analizar** la influencia de la cantidad de usuarios concurrentes en el rendimiento de la aplicación Android durante las búsquedas y consultas de productos.

3. **Evaluar** el impacto de los filtros de búsqueda por categoría y precio sobre el tiempo de respuesta de las consultas dentro de la aplicación móvil.

4. **Determinar** en qué medida la implementación de índices en la base de datos mejora el tiempo de respuesta y la eficiencia de las consultas al trabajar con grandes volúmenes de datos.

# Delimitaciones

## Delimitación Teórica

El proyecto se enmarca en el área de ingeniería de software aplicada al desarrollo
móvil, tomando como referencia el modelo de calidad **ISO/IEC 25010** en la 
característica de eficiencia de desempeño. El enfoque se centra en medir tiempos de
respuesta y consumo de recursos de aplicaciones Android, considerando prácticas
comunes de optimización de bases de datos y programación en Java.

## Delimitación Temporal:

El proyecto se desarrollará a lo largo de todo el ciclo académico **02-2026**, abarcando
desde las fases iniciales de diseño, prototipado y documentación hasta la implementación
y evaluación final. Durante este período se realizarán pruebas continuas en diferentes
escenarios de carga, incluyendo etapas de ajuste intermedio y una evaluación integral
antes de la entrega final del proyecto.

## Delimitación Espacial o de Contexto:

El sistema objeto de estudio será un prototipado de aplicación móvil Android para la gestión
y consulta de productos en una tienda en línea de artículos electrónicos. El código fuente
se desarrollará y mantendrá en un repositorio público de GitHub, donde se registrarán los
avances mediante commits distribuidos por cada integrante del equipo. El repositorio
servirá como evidencia del proceso de investigación y contendrá tanto el código como la 
documentación del proyecto. La población estudiada corresponde a un grupo de estudiantes
universitarios de programación que implementarán y evaluarán el sistema en un entorno académico,
utilizando datos ficticios pero representativos de un catálogo real de productos electrónicos.

## Delimitación Tecnológica:

El desarrollo se realizará en Java 21 utilizando IntelliJ IDEA 2026.2.2 como entorno principal
de programación. La aplicación estará orientada a dispositivos móviles con sistema operativo
Android 13, y se conectará a un servidor de pruebas mediante Spring Boot 3.3 para la capa de
servicios. La base de datos será gestionada inicialmente con MySQL 8.0, aunque se evaluará la 
posibilidad de utilizar SQL Server 2022 en escenarios comparativos de rendimiento. El servidor
de pruebas se plantea como posibilidad ejecutarlo sobre Apache Tomcat 10, y las pruebas se
realizarán en laptops de gama media (Intel 5, 8 GB RAM, Windows 11) y en smartphones Android 
de gama media (Snapdragon 600/700, 4-6 GB RAM).

## Referencias

Durán-Cazar, J. W., Tandazo-Gaona, E. J., Morales-Morales, M. R., & Morales Cardoso, S. (2019). Rendimiento de bases de datos columnares. *Ingenius*, (22), 47–58. https://doi.org/10.17163/ings.n22.2019.05

Google Cloud. (s. f.). Cómo aprovechar al máximo el rendimiento de MySQL: ajuste de consultas. https://cloud.google.com/mysql/query-tuning?hl=es

Microsoft. (s. f.). Solución de problemas de consultas de ejecución lenta - SQL Server. https://learn.microsoft.com/es-es/troubleshoot/sql/database-engine/performance/troubleshoot-slow-running-queries

