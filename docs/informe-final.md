
![Logo de la Universidad Tecnológica de El Salvador](imagenes/logo_utec.png)

<div align="center">

# UNIVERSIDAD TECNOLÓGICA DE EL SALVADOR

**Facultad de Informática y Ciencias Aplicadas**

**Escuela de Informática**

**Carrera: Ingeniería en Sistemas y Computación**

**Asignatura: Programación III - Sección 03**

**Ciclo: 02-2026**

**Docente:** Ing. Eliseo Ernesto Hernández Díaz

---

**Título de la investigación:**

**«Análisis del rendimiento de consultas y operaciones de gestión de inventarios en una aplicación para una tienda de productos electrónicos»**

**Grupo N.º 2**

---

**Integrantes del equipo**

| Estudiante | Carnet |
|:---|:---:|
| Cortez Herrera, Darlyn Julissa | 25-1070-2024 |
| Funes Ruiz, Cesia Esmeralda | 25-4620-2024 |
| Gil Martínez, David Enrique | 27-1352-2025 |
| Jacinto Vega, Adrián de Jesús | 25-4552-2024 |
| Jiménez Martínez, Hazel Daniela | 25-4093-2024 |
| Morales Fuentes, Abner Danilo | 27-1354-2025 |
| Recinos Miranda, Gerardo Emilio | 25-4042-2024 |

**Repositorio del proyecto:**

[investigacion-tienda-electronica-g2](https://github.com/cessifr-bit/investigacion-tienda-electronica-g2)

---

**Lunes 16 de noviembre de 2026**

</div>

---

# Resumen y palabras clave

# Introducción

En una tienda de productos electrónicos es importante llevar un control de los productos disponibles, sus marcas, categorías y cantidades en inventario. Para realizar estas tareas de manera eficiente, es necesario contar con una aplicación que permita almacenar y consultar los datos de forma adecuada. Sin embargo, al aumentar la cantidad de productos almacenados, es importante comprobar si el tiempo necesario para obtener la información también cambia y si se producen variaciones en el consumo de memoria.

En este trabajo se estudiará el rendimiento de una consulta de inventario utilizando MySQL. Para realizar las pruebas se preparará una base de datos con productos electrónicos y se trabajará con tres volúmenes de información: 100, 500 y 1,000 registros. En cada escenario se consultarán los productos de la categoría computadoras con el propósito de comparar el tiempo de respuesta y el consumo de memoria.

El documento presenta el estado del arte, el marco teórico y los antecedentes relacionados con el tema de investigación, así como la definición de las variables que se evaluarán. También se describe el diseño metodológico que comprende los factores del experimento, las variables de respuesta, las condiciones de ejecución y el procedimiento previsto para registrar y analizar las mediciones. De esta manera se busca establecer un procedimiento claro y reproducible que permita comprender cómo varía el rendimiento de las consultas a medida que aumenta la cantidad de registros del inventario.

# Planteamiento del problema

Las tiendas de productos electrónicos manejan información relacionada con productos, precios y características, por lo que requieren operaciones frecuentes de consulta y actualización de datos. En este tipo de aplicaciones, el rendimiento de las consultas y de las operaciones de gestión de productos puede influir en el tiempo de respuesta del sistema y en la eficiencia con la que se procesa la información.

Actualmente, las operaciones de consulta y gestión de productos pueden presentar tiempos de respuesta variables, especialmente cuando aumenta la cantidad de información que debe procesar el sistema. En una tienda de productos electrónicos, estas operaciones son frecuentes, porque la aplicación debe permitir consultar información de los productos, registrar nuevos productos, modificar sus datos y eliminar los que ya no se encuentren disponibles.

Cuando la cantidad de registros aumenta, las consultas y operaciones realizadas sobre la base de datos pueden requerir un mayor procesamiento. Esto puede generar diferencias en los tiempos de respuesta de la aplicación y afectar la eficiencia con la que se administra la información de los productos. Además, el comportamiento del sistema puede variar dependiendo de la forma en que se realizan las consultas y de las características del gestor de base de datos utilizado.

Por esta razón, resulta necesario analizar el rendimiento de estas operaciones bajo diferentes cantidades de información y condiciones de prueba. Los estudios sobre el rendimiento de bases de datos utilizan mediciones y escenarios de prueba para comparar el comportamiento de los sistemas ante diferentes condiciones (Durán-Cazar et al., 2019). De igual forma, el análisis de consultas puede considerar métricas como tiempo de ejecución y el consumo de recursos para identificar aspectos relacionados con el rendimiento (Microsoft, s. f.). En MySQL, el ajuste de las consultas también permite analizar factores relacionados con su eficiencia y con el rendimiento de las aplicaciones que utilizan la base de datos (Google Cloud, s. f.).

A partir de esto, se plantea la necesidad de determinar cómo el tamaño del catálogo de productos puede afectar el tiempo de respuesta y el consumo de memoria de una aplicación de tienda en línea para Android, desarrollada en Java y utilizando MySQL como base de datos. Por tanto, la pregunta principal de investigación es: ¿Cuál es el efecto del tamaño del catálogo de productos sobre el tiempo de respuesta y el consumo de memoria en una aplicación de tienda en línea para Android desarrollada en Java con base de datos en MySQL?

# Pregunta de investigación

## Pregunta principal

¿Cuál es el efecto del tamaño del catálogo de productos sobre el tiempo de respuesta y el consumo de memoria en una aplicación de tienda en línea para Android desarrollada en Java con base de datos MySQL?

## Variables de investigación

**Variables independientes:**

- Tamaño del catálogo de productos (cantidad de registros en la base de datos).
- Cantidad de usuarios simultáneos.
- Uso de filtros de búsqueda.
- Implementación de los índices en la base de datos.

**Variables dependientes:**

- Tiempo de respuesta de la aplicación.
- Consumo de memoria durante la aplicación.
- Rendimiento de la aplicación.
- Tiempo de respuesta de la consulta.
- Eficiencia de la consulta.

### Preguntas secundarias

1. ¿Cómo influye la cantidad de usuarios concurrentes en el rendimiento de la aplicación Android al realizar búsquedas y consultas de productos?
2. ¿Qué impacto tienen los filtros de búsqueda (categoría, precio, disponibilidad) en el tiempo de respuesta de las consultas dentro de la aplicación móvil?
3. ¿En qué medida la implementación de índices en la base de datos mejora la eficiencia de las consultas en escenarios con grandes volúmenes de datos en un dispositivo Android?

# Objetivos

## Objetivo general

Determinar el efecto del tamaño del catálogo de productos sobre el tiempo de respuesta y el consumo de memoria de una aplicación de tienda en línea para Android, desarrollada en Java con una base de datos MySQL.

## Objetivos específicos

1. Medir el tiempo de respuesta y el consumo de memoria de la aplicación Android al trabajar con diferentes tamaños de catálogo de productos.
2. Analizar la influencia de la cantidad de usuarios concurrentes en el rendimiento de la aplicación Android durante las búsquedas y consultas de productos.
3. Evaluar el impacto de los filtros de búsqueda por categoría y precio sobre el tiempo de respuesta de las consultas dentro de la aplicación móvil.
4. Determinar en qué medida la implementación de índices en la base de datos mejora el tiempo de respuesta y la eficiencia de las consultas al trabajar con grandes volúmenes de datos.


# Delimitaciones

## Delimitación teórica

El proyecto se enmarca en el área de ingeniería de software aplicada al desarrollo móvil, tomando como referencia el modelo de calidad ISO/IEC 25010 en la característica de eficiencia de desempeño. El enfoque se centra en medir tiempos de respuesta y consumo de recursos de aplicaciones Android, considerando prácticas comunes de optimización de bases de datos y programación en Java.

## Delimitación temporal

El proyecto se desarrollará a lo largo de todo el ciclo académico 02-2026, a abarcar desde las fases iniciales de diseño, prototipado y documentación hasta la implementación y evaluación final. Durante este período se realizarán pruebas continuas en diferentes escenarios de carga, incluyendo etapas de ajuste intermedio y una evaluación integral antes de la entrega final del proyecto.

## Delimitación contextual

El sistema objeto de estudio será un prototipado de aplicación móvil Android para la gestión y consulta de productos en una tienda en línea de artículos electrónicos. El código fuente se desarrollará y mantendrá en un repositorio público de GitHub, donde se registrarán los avances mediante commits distribuidos por cada integrante del equipo. El repositorio servirá como evidencia del proceso de investigación y contendrá tanto el código como la documentación del proyecto. La población estudiada corresponde a un grupo de estudiantes universitarios de programación que implementarán y evaluarán el sistema en un entorno académico, utilizando datos ficticios pero representativos de un catálogo real de productos electrónicos.

## Delimitación tecnológica

El desarrollo se realizará en Java 21 utilizando IntelliJ IDEA 2026.2.2 como entorno principal de programación. La aplicación estará orientada a dispositivos móviles con sistema operativo Android 13, y se conectará a un servidor de pruebas mediante Spring Boot 3.3 para la capa de servicios. La base de datos será gestionada inicialmente con MySQL 8.0, aunque se evaluará la posibilidad de utilizar SQL Server 2022 en escenarios comparativos de rendimiento. El servidor de pruebas se plantea como posibilidad ejecutarlo sobre Apache Tomcat 10, y las pruebas se realizarán en portátiles de gama media (Intel 5, 8 GB RAM, Windows 11) y en smartphones Android de gama media (Snapdragon 600/700, 4-6 GB RAM).

# Justificación

## Justificación técnica

El desarrollo de una aplicación para una tienda de productos electrónicos se justifica técnicamente porque permite implementar diferentes tecnologías para facilitar la gestión de productos, compras, stock y ventas, así como analizar el rendimiento de las consultas y operaciones realizadas sobre la información almacenada. La aplicación contará con una base de datos para almacenar información de los productos, usuarios, pedidos y existencias, permitiendo que la información se encuentre organizada y pueda ser consultada de manera rápida y sencilla.

Además, el uso de una aplicación permite automatizar procesos que podrían realizarse manualmente, como la búsqueda de productos, el registro de compras y el control de inventario. Esto contribuye a mejorar la eficiencia y reducir posibles errores en el manejo de la información.

## Justificación académica

Este proyecto permite aplicar los conocimientos adquiridos en diferentes áreas de desarrollo de software.

Entre ellos se encuentran la programación, el diseño de bases de datos, la programación orientada a objetos, el manejo de información y el desarrollo de interfaces.

La creación de una aplicación para una tienda electrónica también permite poner en práctica el análisis de requisitos, el diseño de una solución informática y la implementación de funcionalidades que respondan a las necesidades de los usuarios.

## Justificación social/profesional

La aplicación de una tienda electrónica puede beneficiar tanto a los clientes como a los encargados del negocio.

Los clientes pueden consultar los productos disponibles de una manera más sencilla y realizar sus compras sin necesidad de desplazarse físicamente hasta el establecimiento. Por otra parte, los encargados de la tienda pueden tener un mejor control de los productos, las ventas y los pedidos. Esto puede contribuir a ofrecer un servicio más organizado y accesible para los usuarios.

# 1. Estado del arte

Para identificar investigaciones y documentación técnica relacionadas con el rendimiento de consultas a bases de datos, el uso de SQL nativo y JPA/Hibernate, y la gestión de inventarios, se realizaron búsquedas en Google Académico y Redalyc. Se definieron cadenas de búsqueda relacionadas con las variables y tecnologías del proyecto, junto con criterios de inclusión y exclusión para seleccionar las fuentes pertinentes. A continuación, se presentan las cadenas utilizadas, las bases de datos consultadas, las fechas de búsqueda y los resultados obtenidos.

## Cadenas de búsqueda

**Cadena:** ("JPA" OR "Hibernate") AND ("SQL" OR "JDBC") AND ("rendimiento" OR "tiempo de respuesta")

**Base:** Google Académico  
**Fecha:** 2026-10-12  
**Resultados:** aproximadamente 1320 resultados  
**Seleccionados:** 2 artículos

**Criterios de inclusión:** Investigaciones que comparen directamente el rendimiento entre JPA/Hibernate y SQL nativo/JDBC.

- Estudios que incluyan mediciones del tiempo de respuesta (milisegundos) en operaciones de consultas.

**Criterios de exclusión:**

- Artículos puramente teóricos.
- Documentos enfocados únicamente en bases de datos o no relacionales.

**Cadena:** ("ORM" OR "mapeo objeto relacional") AND ("sobrecarga" OR "consumo de memoria") AND ("base de datos")

**Base:** Google Académico  
**Fecha:** 2026-10-12  
**Resultados:** ~2,400 resultados  
**Seleccionados:** 4 artículos

**Criterios de inclusión:**

- Artículos científicos con evaluación del consumo de memoria (RAM / Megabytes) e impacto del overhead generado por frameworks ORM.
- Publicaciones con texto completo accesible para su análisis metodológico.
- La sobrecarga con el consumo de memoria para el nivel de las consultas.

**Criterios de exclusión:**

- Foros de discusión o documentación ambigua y sin profundidad.
- Investigaciones sin métricas objetivas de uso de recursos de hardware.

**Cadena:** ("rendimiento de base de datos" OR "optimización de consultas") AND ("inventario" OR "comercio electrónico")

**Base:** Google Académico  
**Fecha:** 2026-10-12  
**Resultados:** ~1,120 resultados  
**Seleccionados:** 2 artículos

**Criterios de inclusión:**

- La optimización de consultas sobre el inventario.
- Trabajos académicos centrados en la optimización y rendimiento de consultas en entornos de comercio electrónico, catálogo o sistemas de inventario.
- Publicaciones de pruebas clara.

**Criterios de exclusión:**

- Trabajos enfocados sin evaluación de la capa de datos.
- Presentaciones de diapositivas.
- Artículos con dudoso proceso de pago para el acceso.


**Cadena:** ("benchmarking" OR "evaluación de rendimiento") AND ("MySQL" AND ("consultas CRUD")

**Base:** Google Académico  
**Fecha:** 2026-10-12  
**Resultados:** 3  
**Seleccionados:** 3 artículos

**Criterios de inclusión:**

- Ejecución de operaciones de lectura, escritura, creación y eliminación (CRUD).
- Investigaciones que detallen las condiciones para el gran volumen de datos.
- Proceso para la elaboración de aplicaciones de alto rendimiento para manejar volúmenes de datos relacionado con el crud.

**Criterios de exclusión:**

- Evaluaciones de rendimiento sobre motores de bases de datos obsoletos.
- Documentos duplicados o resúmenes preliminares de investigaciones ya publicadas.
- Información desfasada principalmente artículos con ideas vagas.

**Cadena:** ("volumen de registros" OR "escalabilidad") AND ("tiempo de ejecución") AND ("consultas SQL")

**Base:** Google Académico  
**Fecha:** 2026-10-12  
**Resultados:** ~1110 resultados  
**Seleccionados:** 4 artículos

**Criterios de inclusión:**

- Estudios experimentales que evalúen la variación del tiempo de ejecución al escalar progresivamente el volumen de registros en las tablas involucradas en el proyecto.
- Investigaciones sobre las consultas con el tiempo de ejecución para medir el tiempo con el cual se realiza la consulta en un volumen grande de datos.

**Criterios de exclusión:**

- Investigaciones que no apliquen a entornos relacionales estándar.
- Artículos introductorios sin nada de profundización en el tema como forma de atraer consultas, pero sin nada de texto nutritivo.
- Artículos sin desglose metodológico sobre el número de consultas o iteraciones.

**Cadena:** ("rendimiento de base de datos" OR "tiempo de respuesta") AND ("MySQL" OR "SQL Server") AND ("Crud con base de datos")

**Base:** Redalyc  
**Fecha:** 2026-10-12  
**Resultados:** ~145 resultados  
**Seleccionados:** 4 artículos

**Criterios de inclusión:**

- Investigaciones científicas en ciencias de la computación.
- Relación con MySql con la operación crud principalmente con las labores en las consultas, su fin de relación.
- Estudios con pruebas empíricas sobre operaciones de selección, inserción, actualización y eliminación las operaciones crud.

**Criterios de exclusión:**

- Artículos de revisión o ensayos teóricos sin experimentación práctica.
- Aplicación de Crud a un lenguaje que no será de implementación en el diseño final del proyecto.
- Investigaciones aplicadas a bases de datos NoSql.

**Cadena:** ("mapeo objeto relacional" OR "ORM" OR "JPA") AND ("sobrecarga" OR "costo computacional") AND ("base de datos MySql")

**Base:** Redalyc  
**Fecha:** 2026-10-12  
**Resultados:** ~98 resultados  
**Seleccionados:** 5 artículos

**Criterios de inclusión:**

- Artículos arbitrados enfocados en analizar la eficiencia y sobrecarga para evitar el que se congele, o salga de servicio por el nivel de volumen de datos.
- Evaluaciones con métricas sobre uso de memoria o tiempos de procesamiento.
- Uso de variables de prueba para medir el tiempo de respuesta.

**Criterios de exclusión:**

- Manuales de programación o guías introductorias de uso de software.
- Documentos sin análisis comparativo explicito entre tecnologías de persistencia.
- Documentos que no sean de utilidad para MySql.

**Cadena:** ("sistema de inventario" OR "comercio electrónico") AND ("optimización de consultas" OR "rendimiento web, aplicación movil")

**Base:** Redalyc  
**Fecha:** 2026-10-12  
**Resultados:** ~82 resultados  
**Seleccionados:** 7 artículos

**Criterios de inclusión:**

- Investigaciones de desarrollo de software orientadas a la optimización de la capa de datos en sistemas de inventario (como el del proyecto).
- Estudios que detallan la arquitectura y tiempos de ejecución en el servidor.
- Estudios que incluyan el desarrollo para aplicación móvil.

**Criterios de exclusión:**

- Trabajos del área administrativa/logística que no evalúen componentes de software ni bases de datos.
- Documentos que no sean compatibles con Android.
- Información que no esté sustentada, sin haber estado sometida a pruebas, siendo una información de relleno.

**Cadena:** ("volumen de datos" OR "escalabilidad") AND ("tiempo de ejecución") AND ("consultas en MySQL")

**Base:** Redalyc  
**Fecha:** 2026-10-12  
**Resultados:** ~112 resultados  
**Seleccionados:** 3 artículos

**Criterios de inclusión:**

- Experimentos cuantitativos que analicen el aumentar progresivamente el número de registros en tablas SQL.
- Implementación en otros programas y aplicaciones móviles un uso eficaz de MySql.

**Criterios de exclusión:**

- Artículos sobre bases de datos distribuidas masivas.
- Entornos ajenos al de aplicación final.
- Uso de tecnologías que están fuera de las planificadas.

**Cadena:** ("consumo de memoria" OR "recursos de hardware") AND ("base de datos relacional") AND ("evaluación de rendimiento, tiempo de respuesta")

**Base:** Redalyc  
**Fecha:** 2026-10-12  
**Resultados:** ~76 resultados  
**Seleccionados:** 4 artículos

**Criterios de inclusión:**

- Publicaciones académicas que midan de forma objetiva el impacto en memoria RAM y CPU al ejecutar consultas de datos.
- Artículos con metodología de entorno controlado y repetibilidad de pruebas.
- Variable de prueba usada para medir el tiempo de respuesta.

**Criterios de exclusión:**

- Reportes técnicos o conferencias cortas sin artículo en extenso.
- Documentos que no toquen el tema de hardware, rendimiento y consultas.
- Sin ningún tipo de metodología.

## Fuentes

**Uziel Trujillo Colón (2024).** Análisis comparativo de rendimiento entre consultas SQL directas y procedimientos almacenados bajo diferentes volúmenes de datos. Ciencia Latina Revista Científica Multidisciplinar. Año: 2024.

**Enlace real:**

https://ciencialatina.org/index.php/cienciala/article/download/13434/19351/

**Aporte al proyecto:** Aporta métricas de tiempo de ejecución (segundos) en pruebas de estrés con variaciones volumétricas de datos.

**Microsoft Learn. (2025).** Ediciones y características admitidas de SQL Server 2025: Optimización de consultas, bloqueo optimizado y rendimiento por lotes. Microsoft Technical Documentation. Año: 2025.

**Enlace real:** https://learn.microsoft.com/es-es/sql/sql-server/editions-and-components-of-sql-server-2025

**Aporte al proyecto:** Documentación técnica oficial sobre los mecanismos internos de procesamiento de consultas y control de memoria caché en entornos de bases de datos empresariales.

**Cancio, C. (2025).** Comparativa de rendimiento entre Spring Boot (Java), .NET y arquitecturas de persistencia backend. Guías de Arquitectura de Software y Rendimiento. Año: 2025.

**Enlace real:** https://www.cesarcancino.com/comparativa-de-rendimiento-entre-nestjs-net-spring-boot-laravel-y-django-cual-elegir-en-2025/

**Aporte al proyecto:** Analiza el comportamiento en crudo de entornos Java y su interacción con frameworks de persistencia pesados frente a micro-servicios y consultas optimizadas.

**Pérez Torres, Amaury; Soler Silva, José.** Optimización de bases de datos relacionales y su impacto en la latencia de aplicaciones web transaccionales. Revista Tecnológica Universitaria. Año: 2023.

**Enlace real:** https://www.redalyc.org/articulo.oa?id=58916


**Aporte al proyecto:** Evalúa cómo la indexación de tablas y el diseño de consultas CRUD reducen drásticamente el tiempo de respuesta en milisegundos.

**Oracle MySQL Documentation.** MySQL 8.0 Reference Manual: Optimization, Execution Plans and EXPLAIN ANALYZE. Oracle Corporation. Año: 2023.

**Enlace real:** https://dev.mysql.com/doc/refman/8.0/en/optimization.html

**Aporte al proyecto:** Fuente oficial para el análisis de planes de ejecución (EXPLAIN) y su aplicación práctica en el diagnóstico de rendimiento de motores MySQL.

**Alter Mateo.** Análisis en trabajo de gestión de inventarios aplicando un crud específicamente para las labores requeridas, un prototipo de aplicación. Año: 2025.

**Enlace real:** https://repositorio.puce.edu.ec/server/api/core/bitstreams/7f806b6c-dfb4-46fb-bc9b-1f606e8999d5/content

**Aporte al proyecto:** Define la metodología estandarizada para evaluar indicadores clave de calidad de software: tiempo de respuesta, latencia, tiempo de espera y uso de recursos de hardware a su vez trabajando con una base y tomar ideas para el proyecto.

**Jaime Humberto Rodriguez.** Análisis en trabajo de gestión de inventarios en la gran cantidad de datos aplicados principalmente en el desarrollo con la construcción del CRUD (Créate, Read, Update, Delete), aplicable a todos los gestores del sistema para punto de venta, gestor de inventarios, de usuarios. Año: 2025.

**Enlace real:** https://barnes.edu.pe/wp-content/uploads/2025/04/Diseno-e-Implementacion-de-un-Sistema-POS-con-Modulo-de-Gestion.pdf

**Aporte al proyecto:** Define un modelo a seguir de manera en la cual se pueda adaptar, estudiar, manejar un posible escenario aplicado a nuestro proyecto.

**German Jeres (2023).** Diseño de una app móvil que utiliza sistemas de filtrado de información para identificar, recomendar y describir los datos de contacto de personas que laboran. Año: 2023.

**Enlace real:** https://repository.unab.edu.co/server/api/core/bitstreams/7d46ce55-0b99-44d1-831c-5b5c17ba75f7/content

**Aporte al proyecto:** Se vio principalmente en el objetivo que se tomo de desarrollar un prototipo de aplicación móvil para generar recomendaciones de empleado a empresas del sector del calzado. Se propuso una metodología para el desarrollo del proyecto en la cual consistió en tres fases siendo la de análisis y diseño de requerimientos, desarrollo y análisis de pruebas por ello el aporte al proyecto es para fines de implementación y aprender de proyectos establecidos a buscar mejorar.

**Carvajal Sellan, Miguel Angel.** Inventario de aplicaciones móviles para la logística de desarrollo de software gestión de datos o una aplicación móvil. Año: 2025.

**Enlace real:** https://bibdigital.epn.edu.ec/handle/15000/27114

**Aporte al proyecto:** Es un articulo que describe un poco del proyecto el como se enfrenta el control eficiente del inventario, ya que el registro, ingreso, salida y trazabilidad de stock por la realización de forma manual o con herramientas dispersas genera duplicaciones, errores y pérdida de información. De esta manera se busca implementar los mejores aportes para sustentar un excelente rendimiento del proyecto.

**Martin Kleppmann.** Diseño de aplicaciones mediante el uso intensivo de datos mediante un software libre y de código abierto. Año: 2022.

**Enlace real:** https://books.google.com.sv/books?hl=es&lr=&id=t3J6EAAAQBAJ&oi=fnd&pg=PT9&dq=tiempo+de+ejecucion+de+una+consulta+en+mayor+cantidad+de+datos&ots=SH0LjZl-H5&sig=F-fSFM5T3xEvazqfttE4H23YEJU&redir_esc=y#v=onepage&q=tiempo%20de%20ejecucion%20de%20una%20consulta%20en%20mayor%20cantidad%20de%20datos&f=false

**Aporte al proyecto:** Abarca el como manejar el principal manejo de datos ya que se necesita ver el tiempo de respuesta en una cantidad de datos amplia, el aporte es ver la arquitectura en los sistemas de datos.

# 2. Matriz de antecedentes

| Autor y Año | Objetivo | Método y entorno | Hallazgo principal | Vacío detectado |
| --- | --- | --- | --- | --- |
| Uziel Trujillo Colón (2024) | Evaluar de forma comparativa la eficiencia temporal entre sentencias SQL directas y procedimientos almacenados bajo variaciones controladas en el volumen físico de registros. | Enfoque cuantitativo experimental mediante pruebas de estrés sobre un motor relacional, aplicando cargas masivas incrementales de registros y capturando los tiempos de ejecución en segundos. | Los procedimientos almacenados reducen significativamente la latencia lógica frente a consultas planas, optimizando los tiempos computacionales en fases críticas de procesamiento transaccional. | El experimento mide los tiempos de respuesta de forma aislada en el servidor en segundos omitiendo el impacto en milisegundos y el consumo de memoria RAM dinámica del lado del cliente móvil Android. |
| Pérez Torres Amaury; Soler Silva José (2023) | Desarrollar un análisis crítico comparativo de los enfoques didácticos del diseño de bases de datos relacionales para optimizar la normalización estructural de tablas. | Investigación metodológica cualitativa basada en la experimentación académica contrastando la normalización progresiva (Formas Normales) frente al modelado conceptual Entidad-Relación. | La normalización estricta hasta la tercera forma normal (3FN) es indispensable para mitigar redundancias lógicas, prevenir anomalías de inserción y garantizar la atomicidad de los datos. | El estudio aborda el diseño de datos de manera teórica y estructural dejando un vacío técnico al no analizar métricas de latencia de red o hardware en aplicaciones nativas basadas en hilos Java. |
| Becerra Calvopiña Walter Mateo (2025) | Diseñar un prototipo de gestión de inventarios acoplado a un sistema de pesaje automatizado para optimizar flujos logísticos operativos. | Diseño metodológico basado en la construcción iterativa de software, implementando interfaces de comunicación sincrónicas entre hardware periférico y un backend de procesamiento distribuido. | El uso de prototipos estructurados y arquitecturas web responsivas agiliza de forma drástica los tiempos de espera del negocio, incrementado la confiabilidad global en trazabilidad de stock. | El autor plantea un alcance macro del sistema y evalúa variables operativas de software, sin proporcionar un monitoreo cuantitativo de sobrecarga o consumo del Heap de memoria en dispositivos móviles. |
| Jaime Humberto Rodríguez (2021) | Implementar un sistema comercial basado en ambiente web con un módulo centralizado de inventario para optimizar la carga transaccional de ventas. | Ingeniería de software clásica aplicando de manera rigurosa las fases de la metodología RUP (Rational Unified Process) y diagramación UML, con arquitectura MVC, PHP (PDO) y motor relacional MySQL. | El despliegue de catálogos relacionales centralizados bajo entornos web optimiza el uso de recursos distribuidos, eliminando los tiempos perceptibles de espera transaccional en puntos de pago. | La evaluación es cualitativa (“a juicio de usuario”), omitiendo la medición exacta de milisegundos y el impacto correlacional entre el tamaño físico de la base de datos y la memoria dinámica. |
| German Jeres (2023) | Desarrollar una aplicación móvil distribuidora que implemente sistemas de filtrado de datos mediante Machine Learning para la recomendación de perfiles laborales. | En foque iterativo estructurado en fases de análisis de requerimientos (encuestas), desarrollo ágil con Kanban/Trello y un backend distribuido que ejecuta scripts de filtrado colaborativo en Python. | El uso de algoritmos basados en la fórmula de Similitud del Coseno provee un umbral de precisión del 87% en recomendaciones personalizadas, con altos índices de usabilidad y usabilidad móvil. | El modelo fue desplegado exclusivamente sobre una base de datos NoSQL (Cloud Firestore), dejando libre el espacio de estudio para evaluar la eficiencia de la fórmula bajo un backend relacional MySQL indexado. |
| Carvajal Sellán, Miguel Ángel (2025) | Desarrollar un sistema móvil enfocado en mitigar las ineficiencias de stock provocadas por el registro y trazabilidad manual de herramientas logísticas. | Estudio aplicado de desarrollo de software nativo/hibrido, maquetando interfaces lógicas de stock de almacén con sincronización directa hacia servidores de persistencia de datos. | La automatización del flujo de stock mediante herramientas móviles erradica las duplicaciones de registros de inventario y previene pérdidas críticas de información en la cadena de suministros. | Se enfoca de forma exclusivas en los flujos lógicos de la logística de almacén, evadiendo pruebas de estrés cuantitativas con consultas concurrentes masivas o indexación de tablas en el motor MySQL. |
| Martín Kleppmann (2022) | Definir los principios y patrones arquitectónicos necesarios para garantizar la escalabilidad, consistencia y mantenibilidad de aplicaciones intensivas en datos. | Análisis teórico-práctico exhaustivo de la arquitectura de sistemas distribuidos de datos, evaluando el comportamiento de motores de búsqueda, cachés y el costo algorítmico de consultas complejas. | La latencia terminal y el rendimiento de un sistema intensivo en datos se encuentran condicionados por la correcta selección de estrategia de particionamiento del catálogo. | Al ser un compendio general de arquitectura de sistemas de datos, no realiza experimentación de laboratorio sobre el entorno Android Runtime (ART) ni mide consumos métricos específicos de memoria RAM en Java. |

# 3. Marco teórico y operacionalización

El análisis del rendimiento en aplicaciones móviles requiere una base conceptual rigurosa que permita comprender la interacción técnica entre la gestión de recursos en el cliente y la eficiencia de procesamiento del servidor. Para fundamentar metodológicamente esta investigación, el presente apartado desarrolla los cimientos teóricos necesarios para evaluar el sistema bajo escenarios de estrés concurrente. Inicialmente, se exploran las particularidades del entorno Android y su administración de memoria ante grandes volúmenes de información. A continuación, se examinan los mecanismos de optimización en bases de datos relacionales, destacando el impacto de la indexación y el filtrado sobre la latencia. Finalmente, el estudio se ancla conceptualmente en el estándar internacional ISO/IEC 25010, el cual proporciona el marco normativo y el vocabulario preciso para operacionalizar objetivamente las métricas de eficiencia de desempeño.

### Rendimiento y procesamiento de datos en aplicaciones móviles

El rendimiento de las aplicaciones no es solo una preocupación para la mayoría de las organizaciones; es un factor crítico en el éxito de su negocio. Impulsar el rendimiento óptimo de las aplicaciones y minimizar los costos se ha vuelto primordial a medida que las organizaciones se esfuerzan por lograr experiencias de usuario positivas. Estas experiencias pueden hacer o deshacer un negocio, por eso priorizar el alto rendimiento entre las aplicaciones no es negociable. Lo que se necesita es una solución que no solo proteja el rendimiento de sus aplicaciones de misión crítica, sino que también vaya más allá mediante la reducción de costos, la eficiencia del tiempo y el ahorro monetario.

**Aporte a la investigación:** Esta categoría proporciona los conceptos fundamentales para comprender el efecto técnico que tienen las variables independientes (volumen del catálogo y usuarios simultáneos) sobre las variables dependientes, justificando por qué es necesario medir el consumo de recursos en el entorno específico de Android.

### Optimización de bases de datos relacionales: Índices y filtros

La optimización de bases de datos es la práctica sistemática de mejorar la forma en que una base de datos almacena, recupera y procesa los datos para que pueda ofrecer un rendimiento rápido y predecible.

En esencia, la optimización reduce el trabajo innecesario dentro de la base de datos, mejorando la forma en que elige los planes de ejecución de consultas, estructura el esquema, organiza el almacenamiento y utiliza los índices. Estas mejoras reducen la sobrecarga al optimizar la localidad de los datos y evitar cálculos repetidos. Además, son una de las formas más efectivas de controlar el gasto en la nube.

Pero la optimización de bases de datos no es una simple puesta a punto puntual. Se trata, más bien, de un esfuerzo de mantenimiento continuo que mantiene el motor de la base de datos alineado con las cambiantes demandas de las aplicaciones, el creciente volumen de datos y las iniciativas de inteligencia artificial (IA). Es la base de prácticamente todos los sistemas de bases de datos importantes, desde las bases de datos relacionales hasta los motores de código abierto.

Las bases de datos relacionales como MySQL utilizan estructuras de datos especializadas, como los árboles B (B-trees), para implementar índices. Un índice reduce significativamente el espacio de búsqueda al realizar consultas, evitando escaneos secuenciales completos de las tablas (full table scans). Asimismo, la aplicación de filtros (como categoría o precio) en las cláusulas SQL condiciona la cantidad de información que se transfiere por la red y se aloja en la memoria del cliente móvil, impactando directamente en la latencia.

**Aporte a la investigación:** Esta sección teórica provee la base conceptual para justificar la evaluación de la "implementación de índices" y el "uso de filtros" como factores experimentales que modifican el tiempo de respuesta y la eficiencia de la consulta formulados en las preguntas secundarias.

### Modelo de calidad y eficiencia de desempeño (ISO/IEC 25010)

La calidad del producto software se interpreta como el grado en que satisface los requisitos de los usuarios aportando un valor, por lo que el modelo de calidad representa la piedra angular para establecer su sistema de evaluación. Para evaluar el comportamiento del sistema bajo condiciones específicas de carga, es indispensable basarse en un modelo estandarizado que categorice estos requisitos (tales como funcionalidad, rendimiento o seguridad) en características y subcaracterísticas a tener en cuenta. En este contexto, el estándar de referencia ISO/IEC 25010 establece las características de calidad del software, entre las que destaca la «eficiencia de desempeño». Al determinar de esta manera las propiedades del producto, esta característica evalúa dos aspectos críticos: el comportamiento temporal (tiempos de respuesta y procesamiento) y la utilización de recursos (cantidades y tipos de recursos utilizados, como la memoria, al realizar sus funciones).

**Aporte a la investigación:** El estándar ISO/IEC 25010 proporciona el marco normativo y el vocabulario técnico preciso para sustentar y definir operacionalmente las variables dependientes principales de este estudio: el tiempo de respuesta y el consumo de memoria.

### 3.1 Operacionalización de variables

| Variable | Definición operacional | Unidad | Instrumento |
| --- | --- | --- | --- |
| Tiempo de respuesta de la aplicación / consulta | Milisegundos transcurridos entre el envío de la petición de búsqueda o filtrado desde el dispositivo Android hasta la recepción y renderizado del último registro de MySQL. | ms | JMH 1.37 |
| Consumo de memoria | Memoria máxima retenida en el montículo (heap) de la JVM en el dispositivo Android durante la ejecución de las consultas y visualización de resultados. | MB | JMH con el perfilador GC |
| Tamaño del catálogo de productos | Número exacto de registros almacenados y disponibles en la tabla de productos de la base de datos MySQL utilizada para las pruebas. | Entero (Cantidad de registros) | MySQL Workbench y consultas SQL |

# 4. Diseño metodológico

La investigación tiene un enfoque cuantitativo y un diseño experimental, ya que se realizaron mediciones para comparar el rendimiento de dos estrategias de acceso a datos en una aplicación para una tienda de productos electrónicos. Se evaluaron las implementaciones JPA/Hibernate y SQL nativo utilizando diferentes volúmenes de registros de inventario.

Las pruebas se ejecutaron con JMH como herramienta de medición y MySQL como sistema de gestión de bases de datos. Se analizaron los resultados correspondientes a tres volúmenes de datos: 100, 500 y 1,000 registros. Para cada volumen se evaluaron ambas estrategias de acceso a datos y se registraron las mediciones de rendimiento obtenidas.

Para facilitar la comparación, se utilizó el mismo entorno experimental durante las pruebas. Las características del equipo, las versiones de las herramientas y los parámetros de ejecución se documentaron en el archivo docs/entorno.md.

## 4.1 Diseño experimental

El diseño experimental consideró dos factores: el volumen de registros del inventario y la estrategia de acceso a datos. La combinación de sus niveles permitió comparar el comportamiento de JPA/Hibernate y SQL nativo en distintos tamaños de inventario.

### 4.1.1 Factores y niveles

Se evaluaron los siguientes factores:

- Volumen de registros: 100, 500 y 1,000 registros.
- Estrategia de acceso a datos: JPA/Hibernate y SQL nativo.

La combinación de ambos factores generó seis configuraciones experimentales. En cada una se evaluó el rendimiento de la implementación correspondiente con el volumen de registros establecido.

### 4.1.2 Variables de respuesta

Se consideraron las siguientes variables de respuesta:

- **Tiempo de ejecución:** tiempo promedio por operación registrado por JMH, expresado en milisegundos por operación (ms/op).
- **Memoria asignada por operación:** cantidad de memoria asignada durante la ejecución de una operación, registrada por JMH y expresada en bytes por operación (B/op).

Estas variables permitieron comparar el tiempo requerido por cada estrategia y la cantidad de memoria asignada durante las operaciones evaluadas.

### 4.1.3 Número de repeticiones y calentamiento

La configuración de JMH contempló cinco iteraciones de calentamiento y treinta iteraciones de medición por configuración experimental. Cada iteración tuvo una duración configurada de diez segundos. Las iteraciones de calentamiento permitieron preparar el entorno de ejecución y no se incluyeron en las iteraciones de medición reportadas por JMH.

### 4.1.4 Control de condiciones experimentales

Las pruebas se realizaron en el mismo equipo y bajo la configuración del entorno experimental utilizada durante las mediciones. Para interpretar los resultados, se consideraron las características del equipo, las herramientas empleadas y los parámetros de ejecución registrados. Las posibles variaciones ocasionadas por cargas externas del equipo o por el comportamiento de la caché se consideraron factores que pudieron influir en las mediciones, ya que no se contó con información suficiente para confirmar su control.

### 4.1.5 Orden de ejecución

Las configuraciones experimentales se ejecutaron siguiendo el procedimiento utilizado durante las pruebas. El orden de ejecución se consideró de acuerdo con los registros disponibles, sin asumir que se aplicó una aleatorización.

### 4.1.6 Registro y comparación de resultados

Los resultados se almacenaron en archivos generados por JMH, que permitieron identificar las configuraciones evaluadas y las mediciones obtenidas. Los datos se organizaron para comparar el tiempo promedio de ejecución y la memoria asignada por operación entre las seis configuraciones experimentales.

## 4.2 Entorno experimental

Las pruebas se realizaron en un entorno de ejecución con Java y MySQL, utilizando JMH para medir el rendimiento de las implementaciones evaluadas. Las características del equipo, el sistema operativo, las versiones de las herramientas y las bibliotecas utilizadas se documentaron en el archivo docs/entorno.md, junto con los parámetros relevantes para la ejecución del experimento.

## 4.3 Población, muestra o corpus

La población de referencia estuvo constituida por los productos que pueden formar parte del inventario de una tienda de productos electrónicos. Para el experimento se utilizaron conjuntos de datos de prueba de 100, 500 y 1,000 registros. Se mantuvo la estructura de los datos en los tres escenarios y se modificó la cantidad de registros para comparar el rendimiento de las estrategias de acceso a datos JPA/Hibernate y SQL nativo. El conjunto de datos utilizado, los campos incluidos y el procedimiento de generación se consideraron parte de la descripción del corpus experimental.


# Implementación

# 5. Análisis y resultados

# Organización y Resumen de Resultados (JMH Benchmark)

Se ejecutó un benchmark comparativo mediante Java Microbenchmark Harness (JMH 1.37) sobre OpenJDK 21, comparando las implementaciones JPA/Hibernate frente a SQL nativo bajo tres niveles de volumen de registros (100, 500 y 1000).

## Tabla comparativa de rendimiento

| Implementación | Volumen (regs) | Tiempo Promedio (ms/op) | Margen de Error (99.9% CI) | Asignación Memoria (B/op) | Recolecciones GC (total) |
| --- | --- | --- | --- | --- | --- |
| JPA / Hibernate | 100 | 1.22 ms | ± 0.05 ms | ~121,244 B/op | 1,301 |
| JPA / Hibernate | 500 | 2.90 ms | ± 0.05 ms | ~567,614 B/op | 1,606 |
| JPA / Hibernate | 1000 | 4.66 ms | ± 0.06 ms | ~1,123,107 B/op | 1,355 |
| SQL Nativo | 100 | 14.07 ms | ± 1.37 ms | ~461,073 B/op | 590 |
| SQL Nativo | 500 | 19.24 ms | ± 3.28 ms | ~787,946 B/op | 728 |
| SQL Nativo | 1000 | 17.32 ms | ± 1.26 ms | ~1,181,904 B/op | 910 |

# Organización y Resumen de Resultados (Aplicación)

Al momento de ejecutar la aplicación,

# 5.1 Plan de análisis

**Variables y métricas evaluadas:**

- Tiempo medio de ejecución por operación (ms/op), percentiles de latencia (p50, p90, p99) para identificar variabilidad en colas de respuesta.
- Tasa normalizada de memoria asignada por operación (B/op) e impacto en las pausas del Garbage Collector.

**Tratamiento estadístico:**

- Comparación de medias muestrales mediante cálculo de intervalos de confianza al 99.9%.
- Aplicación de pruebas de contraste de hipótesis (prueba t de Student para muestras independientes con nivel de significancia α=0.05) para validar si las diferencias observadas entre JPA y SQL Nativo son estadísticamente significativas.

**Interpretación preliminar:**

- JPA/Hibernate evidencia una latencia promedio significativamente menor y más predecible en todos los escenarios evaluados.

# Discusión

# 5.2 Amenazas a la validez

**Validez interna:**

- **Calentamiento de la JVM (Warmup):** La optimización dinámica del compilador JIT de OpenJDK 21 puede sesgar las primeras mediciones. Se mitigó ejecutando 5 iteraciones de calentamiento de 10 segundos previas a las 30 iteraciones de medición efectiva.

- **Instrumentación del entorno:** Los registros de JMH detectaron la carga del agente -javaagent propio del entorno de desarrollo durante las pruebas. Dado que esta sobrecarga fue idéntica en las seis configuraciones experimentales, no altera la validez comparativa entre JPA/Hibernate y SQL Nativo, aunque puede desplazar levemente los tiempos absolutos.

- **Caché del motor MySQL y orden secuencial:** Al ejecutarse los bloques sin aleatorización estricta, el Buffer Pool de MySQL pudo retener páginas en memoria entre iteraciones consecutivas sobre el mismo conjunto de datos.

**Validez externa:**

- **Ejecución monohilo y entorno local:** El benchmark operó con un solo hilo (threads = 1) y una bifurcación (forks = 1) en entorno local Windows sobre OpenJDK 21, por lo que los tiempos no reflejan contención de hilos bajo tráfico multiusuario concurrente.

- **Escala del catálogo:** Las mediciones cubren volúmenes de 100, 500 y 1,000 registros; extrapolar estos resultados a catálogos masivos (decenas o cientos de miles de productos) requiere validación experimental adicional.

**Validez de construcción:**

- **Aislamiento de la capa de datos:** El experimento en Proyecto-maven midió exclusivamente la ejecución en la capa de persistencia local (ConsultaJPA vs. ConsultaNativa). No se cuantificó la latencia de red HTTP/REST, la serialización de objetos ni el renderizado en la interfaz Android (app-android), los cuales fueron validados únicamente a nivel funcional.

# 5.3 Limitaciones

- **Desacoplamiento entre pruebas funcionales y métricas de rendimiento:** Mientras que el prototipo Android (app-android) y el backend Kotlin (Main.kt) validaron funcionalmente el flujo completo del CRUD y el filtrado visual de productos, las mediciones cuantitativas de tiempo y memoria se circunscribieron al módulo de benchmarking en Java (Proyecto-maven).

- **Evaluación centrada en operaciones de lectura:** El benchmark cuantificó el rendimiento de consultas de recuperación sobre el catálogo (100, 500 y 1,000 registros), sin medir bajo estrés las operaciones de inserción, actualización y eliminación (INSERT, UPDATE, DELETE) ejecutadas desde la pestaña Gestión de la app.

- **Motor relacional único:** Las pruebas se ejecutaron únicamente sobre MySQL, quedando fuera la comparativa directa contra otros motores como SQL Server o PostgreSQL.

# Conclusiones y recomendaciones

## Conclusiones

- **Sobre el efecto del volumen del catálogo en el tiempo de respuesta:** El incremento de registros de 100 a 1,000 produce un aumento lineal y moderado en la latencia de consulta. JPA/Hibernate escaló de $1.22\text{ ms/op}$ a $4.66\text{ ms/op}$ manteniendo alta previsibilidad, mientras que SQL Nativo presentó tiempos significativamente superiores ($14.07\text{ ms/op}$ a $19.24\text{ ms/op}$) y mayor dispersión estadística.

- **Sobre el consumo de memoria y gestión de recursos:** La asignación de memoria por operación aumenta proporcionalmente al volumen de registros en ambas estrategias. JPA/Hibernate consumió menos memoria neta por operación ($121.2\text{ KB}$ a $1.12\text{ MB}$) que SQL Nativo ($461.1\text{ KB}$ a $1.18\text{ MB}$), aunque duplicó la frecuencia de activación del Garbage Collector debido a la creación de estructuras internas de mapeo.

- **Sobre la viabilidad de la arquitectura integrada y estrategias de acceso:** La arquitectura modular desarrollada —compuesta por el cliente nativo Android para gestión y filtrado visual de inventario, el backend en Kotlin/Java y el motor relacional MySQL— demostró ser funcionalmente viable. A nivel de persistencia, JPA/Hibernate superó consistentemente a SQL Nativo en velocidad y estabilidad para catálogos de hasta 1,000 productos en OpenJDK 21.

- **Conclusión general:** El tamaño del catálogo de productos impacta directamente en la asignación de memoria y de forma controlada en el tiempo de consulta, siendo la estrategia de acceso a datos el factor crítico de eficiencia temporal. Para aplicaciones de comercio electrónico y control de inventario de escala pequeña y mediana sobre Java 21 y MySQL, la implementación mediante JPA/Hibernate satisface los criterios de eficiencia de desempeño de la norma ISO/IEC 25010 sin comprometer la fluidez de la aplicación cliente.

## Limitaciones y trabajo futuro

- **Integración completa vía API REST y telemetría móvil:** Conectar directamente las peticiones CRUD de la aplicación Android (app-android) con los endpoints del backend y instrumentar la app mediante Android Profiler para medir el consumo real de RAM en el dispositivo móvil y la latencia extremo a extremo.

- **Escalamiento volumétrico e indexación:** Ampliar el conjunto de datos de prueba en Proyecto-maven a 10,000, 100,000 y 500,000 registros, evaluando el impacto de índices compuestos en TiendaTecnologiaDB.sql y determinando el umbral donde la presión sobre el Garbage Collector de Hibernate supere su ventaja en latencia.

- **Pruebas de carga concurrente:** Ejecutar escenarios multihilo en JMH (threads = 8, 16, 32) y pruebas de estrés HTTP con Apache JMeter para evaluar la contención del pool de conexiones bajo múltiples usuarios operando simultáneamente el catálogo y la gestión de inventario.

# Referencias

- Henderson, D. (2025, 18 diciembre). Optimización del rendimiento de las aplicaciones: aumente el rendimiento y reduzca los costos. IBM. https://www.ibm.com/mx-es/think/insights/application-performance-optimization

- Jonker, T. K. A. (2026, 10 marzo). What is database optimization? https://www.ibm.com/think/topics/database-optimization

- ISO 25010. (s. f.). https://iso25000.com/index.php/normas-iso-25000/iso-25010

# Anexos
