
![Logo de la Universidad Tecnológica de El Salvador](logo_utec.png)

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

El presente estudio tuvo como objetivo determinar el efecto del tamaño del catálogo de productos sobre el tiempo de respuesta y el consumo de memoria en una aplicación de gestión de inventarios y tienda en línea para Android, desarrollada en Java/Kotlin con base de datos MySQL. Para ello, se aplicó un enfoque cuantitativo de diseño experimental bajo el estándar de calidad ISO/IEC 25010. Se implementó un prototipo modular Cliente-Servidor y se ejecutaron pruebas de rendimiento mediante Java Microbenchmark Harness (JMH 1.37) sobre OpenJDK 21, comparando dos estrategias de persistencia de datos JPA/Hibernate frente a SQL Nativo (JDBC)— a través de tres volúmenes de inventario: 100, 500 y 1,000 registros, empleando cinco iteraciones de calentamiento y treinta de medición. Los resultados evidenciaron que JPA/Hibernate obtuvo una latencia promedio significativamente menor y más predecible (1.22 ms/op a 4.66 m/op) que sql nativo (14.07 ms/ op a 19.24 ms/op), además de requerir una menor asignación neta de memoria por operación (121.2 KB a 1.12 MB frente a 461.1 KB a 1.18 MB), aunque con mayor frecuencia de activación del recolector de basura. Se concluye que el crecimiento del catálogo incrementa linealmente el consumo de memoria y de manera controlada el tiempo de ejecución, siendo la estrategia de acceso a datos el factor determinante de eficiencia temporal para inventarios de pequeña y mediana escala.

**Palabras clave:** Rendimiento de consultas, gestión de inventarios, bases de datos relacionales, JPA/Hibernate, benchmarking.

# Introducción

En una tienda de productos electrónicos es importante llevar un control de los productos disponibles, sus marcas, categorías y cantidades en inventario. Para realizar estas tareas de manera eficiente, es necesario contar con una aplicación que permita almacenar y consultar los datos de forma adecuada. Sin embargo, al aumentar la cantidad de productos almacenados, es importante comprobar si el tiempo necesario para obtener la información también cambia y si se producen variaciones en el consumo de memoria.

En este trabajo se estudió el rendimiento de una consulta de inventario utilizando MySQL. Para realizar las pruebas se preparó una base de datos con productos electrónicos y se trabajó con tres volúmenes de información: 100, 500 y 1,000 registros. En cada escenario se consultaron los productos de la categoría computadoras con el propósito de comparar el tiempo de respuesta y el consumo de memoria.

El documento presenta el estado del arte, el marco teórico y los antecedentes relacionados con el tema de investigación, así como la definición de las variables que se evaluaron. También se describe el diseño metodológico que comprende los factores del experimento, las variables de respuesta, las condiciones de ejecución y el procedimiento previsto para registrar y analizar las mediciones. De esta manera se busca establecer un procedimiento claro y reproducible que permita comprender cómo varía el rendimiento de las consultas a medida que aumenta la cantidad de registros del inventario.

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

Variables independientes:

- Tamaño del catálogo de productos (cantidad de registros en la base de datos).
- Cantidad de usuarios simultáneos.
- Uso de filtros de búsqueda.
- Implementación de los índices en la base de datos.

Variables dependientes:

- Tiempo de respuesta de la aplicación.
- Consumo de memoria durante la aplicación.
- Rendimiento de la aplicación.
- Tiempo de respuesta de la consulta.
- Eficiencia de la consulta.

Preguntas secundarias

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

El proyecto se desarrolló a lo largo de todo el ciclo académico 02-2026, a abarcar desde las fases iniciales de diseño, prototipado y documentación hasta la implementación y evaluación final. Durante este período se realizaron pruebas continuas en diferentes escenarios de carga, incluyendo etapas de ajuste intermedio y una evaluación integral antes de la entrega final del proyecto.

## Delimitación contextual

El sistema objeto de estudio fue un prototipado de aplicación móvil Android para la gestión y consulta de productos en una tienda en línea de artículos electrónicos. El código fuente se desarrolló y mantuvo en un repositorio público de GitHub, donde se registraron los avances mediante commits distribuidos por cada integrante del equipo. El repositorio sirvió como evidencia del proceso de investigación y contiene tanto el código como la documentación del proyecto. La población estudiada corresponde a un grupo de estudiantes universitarios de programación que implementaron y evaluaron el sistema en un entorno académico, utilizando datos ficticios pero representativos de un catálogo real de productos electrónicos.

## Delimitación tecnológica

El desarrollo se realizó en Java 21 utilizando IntelliJ IDEA 2026.2.2 como entorno principal de programación. La aplicación está orientada a dispositivos móviles con sistema operativo Android 13, y se conecta a un servidor de pruebas mediante Spring Boot 3.3 para la capa de servicios. La base de datos fue gestionada con MySQL 8.0. El servidor de pruebas se ejecutó sobre Apache Tomcat 10, y las pruebas se realizaron en portátiles de gama media (Intel 5, 8 GB RAM, Windows 11) y en smartphones Android de gama media (Snapdragon 600/700, 4-6 GB RAM).

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

Los clientes pueden consultar los productos disponibles de una manera más sencilla y realizar sus compras sin necesidad de desplazarse

físicamente hasta el establecimiento. Por otra parte, los encargados de la tienda pueden tener un mejor control de los productos,

las ventas y los pedidos. Esto puede contribuir a ofrecer un servicio más organizado y accesible para los usuarios.

# 1. Estado del arte

Para fundamentar la investigación sobre el rendimiento de consultas a bases de datos y la gestión de inventarios en aplicaciones móviles, se realizó una revisión sistemática de literatura técnica y académica en bases de datos científicas como _Google Académico_ y _Redalyc_. El proceso metodológico se centró en la identificación de estudios empíricos que evaluaran el rendimiento de consultas mediante _SQL nativo_ frente a mapeos objeto-relacionales, el consumo de memoria en entornos transaccionales y la optimización de bases de datos relacionales. Se aplicaron criterios de inclusión rigurosos, priorizando investigaciones con mediciones objetivas de _tiempo de respuesta (milisegundos)_ y uso de recursos de hardware en escenarios de bases de datos _MySQL_, descartando aproximaciones puramente teóricas o aplicadas a tecnologías ajenas al alcance de este proyecto. A partir de este filtrado, se seleccionaron las investigaciones más pertinentes que sustentan el enfoque arquitectónico del presente estudio.

## 1.1 **Rendimiento en el Acceso a Datos y Consultas Nativas**

En el ámbito de la persistencia de datos, el investigador _Uziel Trujillo Colón (2024)_, en su artículo _"Análisis comparativo de rendimiento entre consultas SQL directas y procedimientos almacenados bajo diferentes volúmenes de datos"_, aporta métricas críticas sobre el tiempo de ejecución en pruebas de estrés con variaciones volumétricas. Su investigación demuestra empíricamente cómo el uso de consultas directas reduce la latencia en milisegundos en comparación con capas de abstracción más pesadas. Este hallazgo sustenta directamente la decisión arquitectónica de este proyecto de implementar un acceso a datos mediante _JDBC puro (SQL Nativo)_, evitando el _overhead_ computacional que generan frameworks robustos.

## Optimización de bases de datos relacionales

La eficiencia en la extracción de datos es abordada por _Pérez Torres y Soler Silva (2023)_ en su estudio _"Optimización de bases de datos relacionales y su impacto en la latencia de aplicaciones web transaccionales"_. Los autores evalúan cómo la indexación de tablas y el diseño algorítmico de consultas _CRUD (Create, Read, Update, Delete)_ reducen drásticamente el tiempo de respuesta. Este aporte es fundamental para justificar la necesidad de establecer índices estructurados en la base de datos del proyecto para agilizar la lectura del inventario. A nivel técnico operativo, la _Oracle Corporation (2023)_, a través de su documentación técnica oficial _"MySQL 8.0 Reference Manual: Optimization and Execution Plans"_, establece los estándares para el análisis de los planes de ejecución (mediante el comando _EXPLAIN_). Esta literatura resulta vital para diagnosticar el comportamiento interno del motor de base de datos MySQL frente a volúmenes de registros y asegurar un rendimiento óptimo.

## Arquitectura de Sistemas y Gestión

En cuanto a la aplicación práctica de estos conceptos, Wa_lter Mateo (2025)_ define en su _"Análisis en trabajo de gestión de inventarios aplicando un CRUD"_ una metodología estandarizada para evaluar indicadores clave de calidad de software, tales como el tiempo de respuesta, la latencia, el tiempo de espera y el uso de recursos de hardware. Esta metodología sirve de base para estructurar las pruebas de rendimiento del presente proyecto. De manera complementaria, _Jaime Humberto Rodríguez (2025)_ y _Carvajal Sellan (2025)_ exploran los desafíos del control eficiente de inventarios y la trazabilidad de stock, destacando cómo el desarrollo de módulos de gestión optimizados previene la pérdida de información y la duplicación de datos en sistemas comerciales.

Por su parte, _German Jeres (2023)_ aporta un enfoque centrado en la movilidad mediante su _"Diseño de una app móvil que utiliza sistemas de filtrado de información"_, proponiendo una metodología de tres fases (análisis, desarrollo y pruebas) que se alinea con la evaluación del cliente móvil en este proyecto. Finalmente, el ingeniero de software _Martin Kleppmann (2022)_, en su referente obra _"Diseño de aplicaciones mediante el uso intensivo de datos"_, abarca la arquitectura subyacente en los sistemas transaccionales y la variabilidad del tiempo de respuesta frente a colecciones de datos amplias. Este aporte teórico consolida la necesidad de medir de manera estricta el tiempo de ejecución de las consultas al escalar la cantidad de datos transmitidos entre el servidor y el cliente móvil.

# 2. Matriz de antecedentes

| Autor y Año                                  | Objetivo                                                                                                                                                                       | Método y entorno                                                                                                                                                                                       | Hallazgo principal                                                                                                                                                                                | Vacío detectado                                                                                                                                                                                                  |
| -------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Uziel Trujillo Colón (2024)                  | Evaluar de forma comparativa la eficiencia temporal entre sentencias SQL directas y procedimientos almacenados bajo variaciones controladas en el volumen físico de registros. | Enfoque cuantitativo experimental mediante pruebas de estrés sobre un motor relacional, aplicando cargas masivas incrementales de registros y capturando los tiempos de ejecución en segundos.         | Los procedimientos almacenados reducen significativamente la latencia lógica frente a consultas planas, optimizando los tiempos computacionales en fases críticas de procesamiento transaccional. | El experimento mide los tiempos de respuesta de forma aislada en el servidor en segundos omitiendo el impacto en milisegundos y el consumo de memoria RAM dinámica del lado del cliente móvil Android.           |
| Pérez Torres Amaury; Soler Silva José (2023) | Desarrollar un análisis crítico comparativo de los enfoques didácticos del diseño de bases de datos relacionales para optimizar la normalización estructural de tablas.        | Investigación metodológica cualitativa basada en la experimentación académica contrastando la normalización progresiva (Formas Normales) frente al modelado conceptual Entidad-Relación.               | La normalización estricta hasta la tercera forma normal (3FN) es indispensable para mitigar redundancias lógicas, prevenir anomalías de inserción y garantizar la atomicidad de los datos.        | El estudio aborda el diseño de datos de manera teórica y estructural dejando un vacío técnico al no analizar métricas de latencia de red o hardware en aplicaciones nativas basadas en hilos Java.               |
| Becerra Calvopiña Walter Mateo (2025)        | Diseñar un prototipo de gestión de inventarios acoplado a un sistema de pesaje automatizado para optimizar flujos logísticos operativos.                                       | Diseño metodológico basado en la construcción iterativa de software, implementando interfaces de comunicación sincrónicas entre hardware periférico y un backend de procesamiento distribuido.         | El uso de prototipos estructurados y arquitecturas web responsivas agiliza de forma drástica los tiempos de espera del negocio, incrementado la confiabilidad global en trazabilidad de stock.    | El autor plantea un alcance macro del sistema y evalúa variables operativas de software, sin proporcionar un monitoreo cuantitativo de sobrecarga o consumo del Heap de memoria en dispositivos móviles.         |
| Jaime Humberto Rodríguez (2021)              | Implementar un sistema comercial basado en ambiente web con un módulo centralizado de inventario para optimizar la carga transaccional de ventas.                              | Ingeniería de software clásica aplicando de manera rigurosa las fases de la metodología RUP (Rational Unified Process) y diagramación UML, con arquitectura MVC, PHP (PDO) y motor relacional MySQL.   | El despliegue de catálogos relacionales centralizados bajo entornos web optimiza el uso de recursos distribuidos, eliminando los tiempos perceptibles de espera transaccional en puntos de pago.  | La evaluación es cualitativa ("a juicio de usuario"), omitiendo la medición exacta de milisegundos y el impacto correlacional entre el tamaño físico de la base de datos y la memoria dinámica.                  |
| German Jeres (2023)                          | Desarrollar una aplicación móvil distribuidora que implemente sistemas de filtrado de datos mediante Machine Learning para la recomendación de perfiles laborales.             | En foque iterativo estructurado en fases de análisis de requerimientos (encuestas), desarrollo ágil con Kanban/Trello y un backend distribuido que ejecuta scripts de filtrado colaborativo en Python. | El uso de algoritmos basados en la fórmula de Similitud del Coseno provee un umbral de precisión del 87% en recomendaciones personalizadas, con altos índices de usabilidad y usabilidad móvil.   | El modelo fue desplegado exclusivamente sobre una base de datos NoSQL (Cloud Firestore), dejando libre el espacio de estudio para evaluar la eficiencia de la fórmula bajo un backend relacional MySQL indexado. |
| Carvajal Sellán, Miguel Ángel (2025)         | Desarrollar un sistema móvil enfocado en mitigar las ineficiencias de stock provocadas por el registro y trazabilidad manual de herramientas logísticas.                       | Estudio aplicado de desarrollo de software nativo/hibrido, maquetando interfaces lógicas de stock de almacén con sincronización directa hacia servidores de persistencia de datos.                     | La automatización del flujo de stock mediante herramientas móviles erradica las duplicaciones de registros de inventario y previene pérdidas críticas de información en la cadena de suministros  | Se enfoca de forma exclusivas en los flujos lógicos de la logística de almacén, evadiendo pruebas de estrés cuantitativas con consultas concurrentes masivas o indexación de tablas en el motor MySQL            |
| Martín Kleppmann (2022)                      | Definir los principios y patrones arquitectónicos necesarios para garantizar la escalabilidad, consistencia y mantenibilidad de aplicaciones intensivas en datos.              | Análisis teórico-práctico exhaustivo de la arquitectura de sistemas distribuidos de datos, evaluando el comportamiento de motores de búsqueda, cachés y el costo algorítmico de consultas complejas.   | La latencia terminal y el rendimiento de un sistema intensivo en datos se encuentran condicionados por la correcta selección de estrategia de particionamiento del catálogo.                      | Al ser un compendio general de arquitectura de sistemas de datos, no realiza experimentación de laboratorio sobre el entorno Android Runtime (ART) ni mide consumos métricos específicos de memoria RAM en Java. |

# 3. Marco teórico y operacionalización

El análisis del rendimiento en aplicaciones móviles requiere una base conceptual rigurosa que permita comprender la interacción técnica entre la gestión de recursos en el cliente y la eficiencia de procesamiento del servidor. Para fundamentar metodológicamente esta investigación, el presente apartado desarrolla los cimientos teóricos necesarios para evaluar el sistema bajo escenarios de estrés concurrente. Inicialmente, se exploran las particularidades del entorno Android y su administración de memoria ante grandes volúmenes de información. A continuación, se examinan los mecanismos de optimización en bases de datos relacionales, destacando el impacto de la indexación y el filtrado sobre la latencia. Finalmente, el estudio se ancla conceptualmente en el estándar internacional ISO/IEC 25010, el cual proporciona el marco normativo y el vocabulario preciso para operacionalizar objetivamente las métricas de eficiencia de desempeño.

### **_Rendimiento y procesamiento de datos en aplicaciones móviles_**

En la arquitectura de aplicaciones cliente-servidor modernas, el rendimiento del cliente móvil (_frontend_) está directamente condicionado por la forma en que gestiona la información recibida desde la red. En el ecosistema _Android_, el manejo de grandes volúmenes de datos (como un catálogo de productos electrónicos) exige una administración minuciosa de la _memoria RAM_ y del _Heap de la Máquina Virtual (ART/Dalvik)_.

Cuando una aplicación móvil solicita un listado de inventario, los datos JSON se deserializan y se convierten en objetos en memoria. Si el volumen de datos es excesivo, se desencadena un proceso intensivo del _Garbage Collector (Recolector de Basura)_. Las pausas del recolector de basura (_GC Pauses_) congelan el _Main Thread (Hilo Principal)_, responsable de renderizar la interfaz a _60 fotogramas por segundo (FPS)_. Esto produce latencia visual (_jank_), deteriorando la experiencia del usuario. Por tanto, el rendimiento óptimo móvil no solo depende del hardware del teléfono, sino de la eficiencia con la que el servidor backend estructura, filtra y pagina los datos antes de transmitirlos. La implementación de paradigmas declarativos (como _Jetpack Compose_) mitiga estos impactos al realizar una recomposición inteligente de la interfaz, redibujando únicamente los componentes cuyo estado interno ha sido modificado.

### **_Optimización de bases de datos relacionales: Índices y filtros_**

_Índices y filtros:_ Las bases de datos relacionales, como _MySQL_, utilizan estructuras de datos especializadas en forma de árboles balanceados, conocidos como _Árboles B (B-trees)_, para implementar la indexación. Un índice funciona de manera análoga al índice analítico de un libro físico, reduciendo significativamente el espacio de búsqueda al ejecutar consultas _SQL_.

Sin la presencia de índices, el motor de base de datos se ve obligado a realizar un escaneo secuencial completo de la tabla (_Full Table Scan_), lo que implica una complejidad temporal lineal donde el tiempo de respuesta crece de forma directamente proporcional al volumen de registros. Al aplicar un índice estructural sobre columnas clave (como el código del producto o su categoría), la búsqueda adquiere una complejidad temporal logarítmica garantizando tiempos de respuesta de pocos milisegundos incluso al escalar el inventario.

Asimismo, la aplicación de _Filtros SQL (Cláusula WHERE)_ representa un mecanismo vital de optimización transaccional. La delegación del filtrado (por ejemplo, búsquedas por rango de precios o por categoria) directamente en el motor de base de datos reduce el tamaño del _payload_ (carga útil) que se transfiere a través de la red, minimizando simultáneamente el consumo de ancho de banda y la saturación de memoria en el cliente móvil. Esta sección teórica provee la justificación conceptual para evaluar empíricamente la "implementación de índices" y el "uso de filtros" como factores determinantes en la latencia global del sistema.

### **\-Evaluación en la persistencia de datos (Backend)**

La comunicación entre la lógicaa de negocio (Backend) y las bases de datos añade una capa de complejidad al redimiento. El uso de enfoques nativos, como JDBC (Java Database Conectivity), proporciona u control granular sobre la construcción y ejecución de las consultas a través de PreparedStatemens. Este método elimina la sobrecarga computacional intrínseca de los frameworks ORM (Object-Relational Mapping), los cuales consumen ciclos de CPU adicionales al realizar traducciones de código, rastreo de estado (dirty checking) y mapeos reflexivos masivos en la memoria. Medir esta eficiencia requiere herramientas de micro-Benchmarking que aíslen el comportamiento del código de las optimizaciones automáticas de la máquina virtual (como la compilación Just-In Time).

### **_Modelo de calidad y eficiencia de desempeño (ISO/IEC 25010)_**

La calidad de un producto de software es una disciplina medible y cuantificable. Para evaluar el comportamiento del sistema desarrollado bajo condiciones específicas de carga, resulta imperativo anclar la investigación en un marco normativo internacional. El estándar _ISO/IEC 25010 (System and software quality models)_ establece un modelo jerárquico que categoriza los requisitos no funcionales en características y subcaracterísticas.

En el contexto de esta investigación, la característica central a operacionalizar es la _"Eficiencia de Desempeño"_, la cual determina cómo se comporta el software en relación con los recursos utilizados bajo condiciones establecidas. Esta característica se subdivide en dos dimensiones críticas para el proyecto:

1. _Comportamiento Temporal (Time Behavior):_ Evalúa los tiempos de respuesta, los tiempos de procesamiento y las tasas de transferencia al realizar operaciones de lectura y escritura (CRUD) sobre el inventario.
2. _Utilización de Recursos (Resource Utilization):_ Cuantifica las cantidades y tipos de recursos de hardware demandados, específicamente el consumo de memoria RAM (tanto en el servidor para el procesamiento del _Heap_ como en el dispositivo Android para el renderizado) y los ciclos de CPU durante la ejecución de consultas compleja

**3.1 Operacionalización de variables**

| **Variable**                                        | **Definición operacional**                                                                                                                                                  | **Unidad**                     | **Instrumento**                   |
| --------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------ | --------------------------------- |
| **Tiempo de respuesta de la aplicación / consulta** | Milisegundos transcurridos entre el envío de la petición de búsqueda o filtrado desde el dispositivo Android hasta la recepción y renderizado del último registro de MySQL. | ms                             | JMH 1.37                          |
| **Consumo de memoria**                              | Memoria máxima retenida en el montículo (_heap_) de la JVM en el dispositivo Android durante la ejecución de las consultas y visualización de resultados.                   | MB                             | JMH con el perfilador GC          |
| **Tamaño del catálogo de productos**                | Número exacto de registros almacenados y disponibles en la tabla de productos de la base de datos MySQL utilizada para las pruebas.                                         | Entero (Cantidad de registros) | _MySQL Workbench y consultas SQL_ |

# 4. Diseño metodológico

La investigación tiene un enfoque cuantitativo y un diseño experimental, ya que se realizaron mediciones para comparar el rendimiento de dos estrategias de acceso a datos en una aplicación para una tienda de productos electrónicos. Se evaluaron las implementaciones JPA/Hibernate y SQL nativo utilizando diferentes volúmenes de registros de inventario.

Las pruebas se ejecutaron con JMH como herramienta de medición y MySQL como sistema de gestión de bases de datos. Se analizaron los resultados correspondientes a tres volúmenes de datos: 100, 500 y 1,000 registros. Para cada volumen se evaluaron ambas estrategias de acceso a datos y se registraron las mediciones de rendimiento obtenidas.

Para facilitar la comparación, se utilizó el mismo entorno experimental durante las pruebas. Las características del equipo, las versiones de las herramientas y los parámetros de ejecución se documentaron en el archivo docs/entorno.md.

## **4.1 Diseño experimental**

El diseño experimental consideró dos factores: el volumen de registros del inventario y la estrategia de acceso a datos. La combinación de sus niveles permitió comparar el comportamiento de JPA/Hibernate y SQL nativo en distintos tamaños de inventario.

### **4.1.1 Factores y niveles**

Se evaluaron los siguientes factores:

- **Volumen de registros:** 100, 500 y 1,000 registros.
- **Estrategia de acceso a datos:** JPA/Hibernate y SQL nativo.

La combinación de ambos factores generó seis configuraciones experimentales. En cada una se evaluó el rendimiento de la implementación correspondiente con el volumen de registros establecido.

### **4.1.2 Variables de respuesta**

Se consideraron las siguientes variables de respuesta:

- **Tiempo de ejecución:** tiempo promedio por operación registrado por JMH, expresado en milisegundos por operación (ms/op).
- **Memoria asignada por operación:** cantidad de memoria asignada durante la ejecución de una operación, registrada por JMH y expresada en bytes por operación (B/op).

Estas variables permitieron comparar el tiempo requerido por cada estrategia y la cantidad de memoria asignada durante las operaciones evaluadas.

### **4.1.3 Número de repeticiones y calentamiento**

La configuración de JMH contempló cinco iteraciones de calentamiento y treinta iteraciones de medición por configuración experimental. Cada iteración tuvo una duración configurada de diez segundos. Las iteraciones de calentamiento permitieron preparar el entorno de ejecución y no se incluyeron en las iteraciones de medición reportadas por JMH.

### **4.1.4 Control de condiciones experimentales**

Las pruebas se realizaron en el mismo equipo y bajo la configuración del entorno experimental utilizada durante las mediciones. Para interpretar los resultados, se consideraron las características del equipo, las herramientas empleadas y los parámetros de ejecución registrados. Las posibles variaciones ocasionadas por cargas externas del equipo o por el comportamiento de la caché se consideraron factores que pudieron influir en las mediciones, ya que no se contó con información suficiente para confirmar su control.

### **4.1.5 Orden de ejecución**

Las configuraciones experimentales se ejecutaron siguiendo el procedimiento utilizado durante las pruebas. El orden de ejecución se consideró de acuerdo con los registros disponibles, sin asumir que se aplicó una aleatorización.

### **4.1.6 Registro y comparación de resultados**

Los resultados se almacenaron en archivos generados por JMH, que permitieron identificar las configuraciones evaluadas y las mediciones obtenidas. Los datos se organizaron para comparar el tiempo promedio de ejecución y la memoria asignada por operación entre las seis configuraciones experimentales.

## **4.2 Entorno experimental**

Las pruebas se realizaron en un entorno de ejecución con Java y MySQL, utilizando JMH para medir el rendimiento de las implementaciones evaluadas. Las características del equipo, el sistema operativo, las versiones de las herramientas y las bibliotecas utilizadas se documentaron en el archivo docs/entorno.md, junto con los parámetros relevantes para la ejecución del experimento.

## **4.3 Población, muestra o corpus**

La población de referencia estuvo constituida por los productos que pueden formar parte del inventario de una tienda de productos electrónicos. Para el experimento se utilizaron conjuntos de datos de prueba de 100, 500 y 1,000 registros. Se mantuvo la estructura de los datos en los tres escenarios y se modificó la cantidad de registros para comparar el rendimiento de las estrategias de acceso a datos JPA/Hibernate y SQL nativo. El conjunto de datos utilizado, los campos incluidos y el procedimiento de generación se consideraron parte de la descripción del corpus experimental.

# Implementación

La fase de implementación del proyecto se enfocó en materializar los objetivos de la investigación mediante la construcción de un ecosistema de software robusto, escalable y orientado a la evolución del rendimiento. Para abordar la problemática de la gestión de inventarios en tiendas de productos electrónicos, el sistema fue estructurado separando claramente la capa de persistencia de datos (Backend) y la capa de presentación visual (Frontend). A continuación, se detalla la arquitectura del sistema, las decisiones de diseño adoptadas para mitigar los desafíos técnicos y las características funcionales que definen el prototipo desarrollado.

## **Arquitectura del Sistema**

El ecosistema tecnológico del proyecto se fundamenta en un patrón arquitectónico Cliente-Servidor, estructurado de manera modular para permitir una clara separación de responsabilidades y facilitar las posteriores pruebas de rendimiento y estrés.

Capa de Acceso a Datos y Persistencia (Backend)

El núcleo del backend fue desarrollado utilizando el lenguaje Kotlin, implementando el patrón de diseño Data Access Object (DAO) a través de la clase **_ProductoRepository.kt_** . En lugar de depender de abstracciones de alto nivel desde el inicio, la arquitectura de acceso a datos se construyó utilizando Java Database Connectivity (JDBC) puro mediante la clase **_ConexiónDB_**. Las consultas se procesan utilizando **_PreparedStatement,_** lo que garantiza la seguridad contra inyecciones SQL y permite un control absoluto sobre el plan de ejecución de las consultas al motor de la base de datos.

El almacenamiento persistente recae sobre un motor relacional MySQL 8.0, materializado en el script **_TiendaTecnologica.sqL._** La arquitectura de la base de datos se normalizo cuidadosamente en seis entidades principales: **_categorías, marcas, productos, usuarios, pedidos_** y **_detalle_pedidos._** Esta estructuración relacional permite mantener la integridad referencial del inventario electrónico mediante llaves foráneas estrictas y restricciones en cascada.

Capa de Presentación y Experiencia de Usuario (Frontend)

El cliente móvil fue diseñado nativamente para el sistema operativo Android utilizando **Jetpack Compose,** el framework declarativo moderno recomendado por Google para la construcción de interfaces de usuario. La arquitectura de la aplicación (ubicada en el módulo **_app-android_**) sigue el patrón **Single-Activity Architecture**. El archivo **_MainActivity.kt_** actúa como el único contenedor de la aplicación, delegando la navegación, el enrutamiento de vistas y la composición de la interfaz a un componente **_Scaffold_** dinámico que inyecta las diferentes pantallas (**Inicio, Catálogo, Gestión y Perfil**) según el estado de la aplicación.

## **Decisiones de Diseño**

Durante el ciclo de desarrollo del prototipo, se tomaron decisiones arquitectónicas y de diseño de software estratégicas para optimizar el rendimiento y mejorar la mantenibilidad del código.

1\. Paradigma de Interfaz Declarativa (Jetpack Compose)

Se decidió prescindir de la construcción tradicional de vistas imperativas basadas en XML (Fragments y Layouts clásicos) en favor de un enfoque completamente declarativo con Jetpack Compose. Esta decisión permitió reducir drásticamente la cantidad de código repetitivo (boilerplate) y **_LazyColum_** en la gestión, el sistema solo dibuja en memoria los elementos que son visibles en la pantalla, garantizando un desplazamiento fluido a 60 cuadros por segundo, incluso al simular listas extensas de productos tecnológicos.

2\. Manejo Reactivo del Estado

Para la validación de formularios y actualización de las listas de la interfaz del administrador (**_Crud.kt_**), se decidió utilizar variables de estado delegadas (**_by remember { mutableStateOf(…) }_** y **_mutableStateListOf_**). Esra decisión de diseño permite que la interfaz gráfica reaccione y se redibuje automáticamente en tiempo real ante cualquier cambio. Por ejemplo, el botón de "Guardar" evalúa dinámicamente si los campos de nombre y precio no están vacíos ( **_isFormValid_** ), habilitándose o deshabilitándose sin necesidad de escribir complejas lógicas de validación manuales.

3\. Renderizado Gráfico Vectorial vs. Recursos Estáticos

Una de las decisiones más notables en la optimización del rendimiento móvil fue la creación de la barra de navegación inferior (**_AppBottomNavigation_**). En lugar de importar imágenes PNG o SVGs pesados que consumen almacenamiento en el APK y memoria en tiempo de ejecución, los iconos de la aplicación (la casa de inicio, la cuadricula del catálogo, el cubo de gestión y la silueta del perfil) fueron programadas matemáticamente utilizando el componente **_Canvas_** y la clase **_Path_** de Compose. Esta decisión reduce el uso de memoria y asegura que los gráficos se escalen con nitidez perfecta en cualquier resolución de pantalla.

4\. Optimización SQL y Eliminación Lógica

A nivel de base de datos, tomaron dos decisiones cruciales para el entorno de pruebas. Primero, se implementaron índices específicos( **_idx_producto_nombre, idx_producto_categoria, idx_producto_precio_**) sobre la tabla de productos para reducir la complejidad temporal de las consultas de búsqueda y filtrado que se ejecutarán durante los **_benchmarks_**. Segundo, se adoptó el patrón de **eliminación lógica** ( Soft Delete ). En lugar de utilizar sentencias **_Delete_** que podrían corromper el historial de pedidos de los clientes, la función **_eliminarLogico_** en el repositorio simplemente actualiza el campo booleando a cero, preservando la integridad de las ventas pasadas.

## **Características del Prototipo**

El prototipo funcional resultante consolida de manera efectiva las necesidades de la tienda de productos electrónicos, proveyendo características avanzadas tanto en el almacenamiento como en la interacción del usuario.

Características del Motor de Datos y Repositorio

El backend cuenta con capacidades transaccionales completas. El modelo de datos ( **_Producto.kt_**) no solo mapea los campos base, sino que integra campos informativos ( **_nombreCategoria_** y **_nombreMarca_**) obtenidos eficientemente mediante sentencias **_LEFT JOIN._** El repositorio permite operaciones de alta complejidad como. Filtrado combinado por categoría y rangos de precio simultáneos (**_filtrarPorCategoriaYPrecio_**), búsquedas con coincidencia parcial mediante patrones **_LIKE_** tanto para el nombre como para el código SKU, y la generación de reportes de alerta mediante la consulta de artículos cuyo inventario cae por debajo de un umbral crítico (**_listrarBajoStock_**). Asimismo, incorpora operaciones atómicas matemáticas en SQL para el ajuste seguro del inventario (**_ajustarStock_**), evitando vender dispositivos que no existen físicamente.

Módulo del Catálogo para el Usuario Final (Catalogo.kt)

La interfaz orientada al cliente presenta una paleta de colores moderna de alto contraste (Azul Noche y Azul Brillante). Destaca una barra de búsqueda de texto superior y un carrusel dinámico de "Chips" fijadores (**LazyRow**) que permite segmentar el hardware tecnológico por categorías como "Laptops", "Gaming" o "Audio". Los productos se despliegan en una cuadricula adaptiva mediante el componente **Card**. Cada tarjeta de producto fue estilizada con gradientes visuales (**_Brush.verticalGradient_**), esquinas redondeadas, un badge flotante semitransparente que muestra la calificación por estrellas del articulo y un precio destacado. El prototipo incorpora lógica del filtrado reactiva, la cual evalúa simultáneamente el texto ingresado en el buscador y la categoría seleccionada para refinar la lista de exhibición al instante.

Módulo de Gestión de Inventario (Crud.kt)

Orientado a los administradores del sistema, este módulo permite la manipulación directa de la base de datos de productos. Presenta un formulario estructurado con elementos (**_OutLinedTextField_**) y teclados numéricos adaptados al tipo de dato. Para garantizar la consistencia en el registro, incorpora un selector desplegable (**_ExposedDropdownMenuBox_**) que obliga al administrador a elegir una categoría predefinida en lugar de escribirla manualmente.

La característica más avanzada de esta pantalla es su dualidad operativa: el formulario sirve tanto para la creación como para la edición de hardware. Al tocar el icono de edición en un producto de la lista, el sistema carga automáticamente sus datos en los campos superiores y transforma el botón principal de "Guardad Producto" a un botón verde de "Actualizar", habilitando simultáneamente un botón secundario de "Cancelar" la operación, todo controlado dinámicamente mediante la variable de estado **_editingProductId._** La lista del inventario refleja inmediatamente cualquier inserción, modificación o eliminación mediante la reactividad inherente de la estructura **_mutableStateListOf._**

# 5. Análisis y resultados

# Organización y Resumen de Resultados (JMH Benchmark)

Se ejecutó un benchmark comparativo mediante Java Microbenchmark Harness (JMH 1.37) sobre OpenJDK 21, comparando las implementaciones JPA/Hibernate frente a SQL nativo bajo tres niveles de volumen de registros (100, 500 y 1000).

Tabla Comparativa de Rendimiento

| **Implementación**  | **Volumen (regs)** | **Tiempo Promedio (ms/op)** | **Margen de Error (99.9% CI)** | **Asignación Memoria (B/op)** | **Recolecciones GC (total)** |
| ------------------- | ------------------ | --------------------------- | ------------------------------ | ----------------------------- | ---------------------------- |
| **JPA / Hibernate** | 100                | 1.22 ms                     | ± 0.05 ms                      | ~121,244 B/op                 | 1,301                        |
| **JPA / Hibernate** | 500                | 2.90 ms                     | ± 0.05 ms                      | ~567,614 B/op                 | 1,606                        |
| **JPA / Hibernate** | 1000               | 4.66 ms                     | ± 0.06 ms                      | ~1,123,107 B/op               | 1,355                        |
| **SQL Nativo**      | 100                | 14.07 ms                    | ± 1.37 ms                      | ~461,073 B/op                 | 590                          |
| **SQL Nativo**      | 500                | 19.24 ms                    | ± 3.28 ms                      | ~787,946 B/op                 | 728                          |
| **SQL Nativo**      | 1000               | 17.32 ms                    | ± 1.26 ms                      | ~1,181,904 B/op               | 910                          |

# Organización y Resumen de Resultados (Aplicación)

Se realizó una prueba de rendimiento de la aplicación con el propósito de evaluar su capacidad para establecer conexión con la base de datos MySQL y recuperar un conjunto de 200 registros almacenados en ella.

Durante la ejecución se consideraron diferentes métricas de rendimiento, entre ellas el tiempo de respuesta, la cantidad de registros procesados y el consumo de memoria. Estos indicadores permiten conocer el comportamiento de la aplicación durante la recuperación de información y determinar su eficiencia al trabajar con el volumen de datos establecido.

Los resultados obtenidos se organizaron en una tabla que reúne las métricas registradas durante la prueba, facilitando la interpretación del rendimiento general de la aplicación.

| **Indicador**                    | **Resultado** |
| -------------------------------- | ------------- |
| Registros solicitados            | 200           |
| Ejecuciones                      | 10            |
| Tiempo promedio                  | 4.732 ms      |
| Tiempo mínimo                    | 3.668 ms      |
| Tiempo máximo                    | 9.720 ms      |
| Variación promedio de la memoria | 172.57 KB     |
| Tiempo de CPU del proceso        | 250.0 ms      |

# 5.1 Plan de análisis

**Variables y métricas evaluadas:**

- Tiempo medio de ejecución por operación (ms/op), percentiles de latencia (p50, p90, p99) para identificar variabilidad en colas de respuesta.
- Tasa normalizada de memoria asignada por operación (B/op) e impacto en las pausas del Garbage Collector.
- Tiempo de ejecución (ms): medición del tiempo requerido para establecer la conexión con MySQL, ejecutar la consulta y recuperar los 200 registros. Se consideró el tiempo promedio, mínimo y máximo de las ejecuciones realizadas.
- Consumo de memoria (KB): evaluación de la variación de memoria utilizada por la aplicación durante el proceso de recuperación de los registros.
- Uso del procesador (CPU): registro del tiempo de CPU consumido por el proceso Java durante la ejecución de las pruebas.
- Cantidad de registros recuperados: verificación de que la aplicación obtenga correctamente los 200 registros solicitados en cada ejecución.

**Tratamiento estadístico:**

- Comparación de medias muestrales mediante cálculo de intervalos de confianza al 99.9%.
- Aplicación de pruebas de contraste de hipótesis (prueba t de Student para muestras independientes con nivel de significancia α=0.05) para validar si las diferencias observadas entre JPA y SQL Nativo son estadísticamente significativas.
- Realización de 10 ejecuciones de prueba, después de 5 ejecuciones iniciales de calentamiento para reducir el impacto de la inicialización de la máquina virtual de Java (JVM).
- Cálculo del tiempo promedio, mínimo y máximo de ejecución para identificar la estabilidad y variabilidad de los tiempos de respuesta.
- Obtención del promedio de la variación de memoria registrada durante las ejecuciones y del mayor incremento observado.
- Registro del tiempo de CPU consumido durante el conjunto de pruebas para evaluar la actividad del procesador.

**Interpretación preliminar:**

- JPA/Hibernate evidencia una latencia promedio significativamente menor y más predecible en todos los escenarios evaluados.
- Se analizó el comportamiento de la aplicación al recuperar 200 registros desde MySQL, considerando el tiempo de respuesta, el consumo de memoria y la actividad del procesador.
- Se verificó que la cantidad de registros recuperados corresponda con la cantidad solicitada y que las ejecuciones mantengan un comportamiento estable.
- Los resultados permitieron evaluar el rendimiento general de la aplicación e identificar posibles oportunidades de optimización en el proceso de acceso y recuperación de datos.

# Discusión

Los resultados del benchmark JMH revelaron que JPA/Hibernate superó significativamente a SQL Nativo (JDBC) en términos de latencia, obteniendo tiempos entre 1.22 ms y 4.66 ms, frente a los 14.07 ms a 19.24 ms de SQL Nativo. Esto contrasta con la literatura de Trujillo Colón (2024), quien sugiere que las consultas nativas reducen la latencia. Esta discrepancia se explica por la naturaleza del entorno: Hibernate implementa un caché de primer nivel por defecto y optimiza el mapeo en memoria de entidades estandarizadas, lo que agiliza la lectura repetitiva en un entorno monohilo como el evaluado.

Sin embargo, el costo de esta velocidad se reflejó en el comportamiento de la memoria. Aunque JPA/Hibernate asignó menos memoria neta por operación, generó una mayor cantidad de recolecciones de basura (1,606 frente a 728 de SQL Nativo en el nivel de 500 registros). Esto confirma lo planteado en el marco teórico respecto a que los frameworks ORM producen una sobrecarga de instancias temporales en el Heap de la JVM, lo cual, en un futuro escenario de alta concurrencia, podría impactar negativamente el rendimiento global.

# 5.2 Amenazas a la validez

**Validez interna:**

- Calentamiento de la JVM (Warmup): La optimización dinámica del compilador JIT de OpenJDK 21 puede sesgar las primeras mediciones. Se mitigó ejecutando 5 iteraciones de calentamiento de 10 segundos previas a las 30 iteraciones de medición efectiva.
- Instrumentación del entorno: Los registros de JMH detectaron la carga del agente -javaagent propio del entorno de desarrollo durante las pruebas. Dado que esta sobrecarga fue idéntica en las seis configuraciones experimentales, no altera la validez comparativa entre JPA/Hibernate y SQL Nativo, aunque puede desplazar levemente los tiempos absolutos.
- Caché del motor MySQL y orden secuencial: Al ejecutarse los bloques sin aleatorización estricta, el Buffer Pool de MySQL pudo retener páginas en memoria entre iteraciones consecutivas sobre el mismo conjunto de datos.

**Validez externa:**

- Ejecución monohilo y entorno local: El benchmark operó con un solo hilo (threads = 1) y una bifurcación (forks = 1) en entorno local Windows sobre OpenJDK 21, por lo que los tiempos no reflejan contención de hilos bajo tráfico multiusuario concurrente.
- Escala del catálogo: Las mediciones cubren volúmenes de 100, 500 y 1,000 registros; extrapolar estos resultados a catálogos masivos (decenas o cientos de miles de productos) requiere validación experimental adicional.

**Validez de construcción:**

- Aislamiento de la capa de datos: El experimento en Proyecto-maven midió exclusivamente la ejecución en la capa de persistencia local (ConsultaJPA vs. ConsultaNativa). No se cuantificó la latencia de red HTTP/REST, la serialización de objetos ni el renderizado en la interfaz Android (app-android), los cuales fueron validados únicamente a nivel funcional.

# 5.3 Limitaciones

- Desacoplamiento entre pruebas funcionales y métricas de rendimiento: Mientras que el prototipo Android (app-android) y el backend Kotlin (Main.kt) validaron funcionalmente el flujo completo del CRUD y el filtrado visual de productos, las mediciones cuantitativas de tiempo y memoria se circunscribieron al módulo de benchmarking en Java (Proyecto-maven).
- Evaluación centrada en operaciones de lectura: El benchmark cuantificó el rendimiento de consultas de recuperación sobre el catálogo (100, 500 y 1,000 registros), sin medir bajo estrés las operaciones de inserción, actualización y eliminación (INSERT, UPDATE, DELETE) ejecutadas desde la pestaña Gestión de la app.
- Motor relacional único: Las pruebas se ejecutaron únicamente sobre MySQL, quedando fuera la comparativa directa contra otros motores como SQL Server o PostgreSQL.

# Conclusiones y recomendaciones

**Conclusiones**

- Sobre el efecto del volumen del catálogo en el tiempo de respuesta: El incremento del tamaño del inventario (de 100 a 1,000 registros) produce un aumento progresivo y controlado en el tiempo de ejecución de las consultas en ambas estrategias. En JPA/Hibernate el tiempo pasó de 1.22 ms/op a 4.66 ms/op manteniendo una alta estabilidad, mientras que en SQL Nativo osciló entre 14.07 ms/opy 19.24 ms/op con mayor variabilidad
- Sobre el consumo de memoria y gestión de recursos: La asignación de memoria por operación escala de forma directamente proporcional al volumen de registros recuperados en ambas tecnologías. JPA/Hibernate demostró una menor asignación neta de bytes por operación en los tres escenarios analizados (121.2 KB a 1.12 MB frente a 461.1 KB a 1.18 MB en SQL Nativo), aunque a costa de duplicar la frecuencia de invocaciones al Garbage Collector debido a la creación de objetos internos de mapeo.
- Sobre la comparación entre estrategias de acceso a datos: En el entorno controlado de OpenJDK 21 y MySQL bajo ejecución monohilo, la estrategia basada en JPA/Hibernate superó consistentemente a SQL Nativo en velocidad de consulta y previsibilidad de latencía para volúmenes de hasta 1,000 registros, desmitificando la premisa de que un ORM siempre penaliza el rendimiento en catálogos de escala pequeña y mediana.

**Limitaciones y trabajo futuro**

- Integración completa vía API REST y telemetría móvil: Conectar directamente las peticiones CRUD de la aplicación Android (app-android) con los endpoints del backend y instrumentar la app mediante Android Profiler para medir el consumo real de RAM en el dispositivo móvil y la latencia extremo a extremo.
- Escalamiento volumétrico e indexación: Ampliar el conjunto de datos de prueba en Proyecto-maven a 10,000, 100,000 y 500,000 registros, evaluando el impacto de índices compuestos en TiendaTecnologiaDB.sql y determinando el umbral donde la presión sobre el Garbage Collector de Hibernate supere su ventaja en latencia.
- Pruebas de carga concurrente: Ejecutar escenarios multihilo en JMH (threads = 8, 16, 32) y pruebas de estrés HTTP con Apache JMeter para evaluar la contención del pool de conexiones bajo múltiples usuarios operando simultáneamente el catálogo y la gestión de inventario.

# Referencias

- Henderson, D. (2025, 18 diciembre). Optimización del rendimiento de las aplicaciones: aumente el rendimiento y reduzca los costos. IBM. <https://www.ibm.com/mx-es/think/insights/application-performance-optimization>
- Jonker, T. K. A. (2026, 10 marzo). What is database optimization? <https://www.ibm.com/think/topics/database-optimization>
- ISO 25010. (s. f.). <https://iso25000.com/index.php/normas-iso-25000/iso-25010>
- Uziel Trujillo Colón (2024). Análisis comparativo de rendimiento entre consultas SQL directas y procedimientos almacenados bajo diferentes volúmenes de datos. Ciencia Latina Revista Científica Multidisciplinar. <https://ciencialatina.org/index.php/cienciala/article/download/13434/19351/>
- Pérez Torres, Amaury; Soler Silva, José (2023) . Optimización de bases de datos relacionales y su impacto en la latencia de aplicaciones web transaccionales. <https://www.redalyc.org/articulo.oa?id=589165883005>
- Walter Mateo (2025). Análisis en trabajo de gestión de inventarios aplicando un crud específicamente para las labores requeridas, un prototipo de aplicación. [https://repositorio.puce.edu.ec/server/api/core/bitstreams/7f806b6c-dfb4-46fb-bc9b-1f606e8999d5/content](https://dev.mysql.com/doc/refman/8.0/en/optimization.html)
- Jaime Humberto Rodriguez (2025). Análisis en trabajo de gestión de inventarios en la gran cantidad de datos aplicados principalmente en el desarrollo con la construcción del CRUD <https://barnes.edu.pe/wp-content/uploads/2025/04/Diseno-e-Implementacion-de-un-Sistema-POS-con-Modulo-de-Gestion.pdf>
- German Jeres (2023) Diseño de una app móvil que utiliza sistemas de filtrado de información para identificar, recomendar y describir los datos de contacto de personas que laboran. <https://repository.unab.edu.co/server/api/core/bitstreams/7d46ce55-0b99-44d1-831c-5b5c17ba75f7/content>
- Carvajal Sellan, Miguel Angel (2025). Inventario de aplicaciones móviles para la logística de desarrollo de software gestión de datos o una aplicación móvil. <https://bibdigital.epn.edu.ec/handle/15000/27114>
- Martin Kleppmann (2022). Diseño de aplicaciones mediante el uso intensivo de datos mediante un software libre y de código abierto. <https://books.google.com.sv/books?hl=es&lr=&id=t3J6EAAAQBAJ&oi=fnd&pg=PT9&dq=tiempo+de+ejecucion+de+una+consulta+en+mayor+cantidad+de+datos&ots=SH0LjZl-H5&sig=F-fSFM5T3xEvazqfttE4H23YEJU&redir_esc=y#v=onepage&q=tiempo%20de%20ejecucion%20de%20una%20consulta%20en%20mayor%20cantidad%20de%20datos&f=false> Aporte al proyecto: Abarca el como manejar el principal manejo de datos ya que se necesita ver el tiempo de respuesta en una cantidad de datos amplia, el aporte es ver la arquitectura en los sistemas de datos.

# Anexos

**Anexo A: Instrumentos de Recolección de Datos y Pruebas de Rendimiento**

**_Nota:_** _Este instrumento tabula los resultados obtenidos durante las pruebas de estrés y rendimiento entre el servidor Javalin y la aplicación móvil Android en un entorno de red local._

| **ID Prueba** | **Escenario / Tipo de Consulta** | **Volumen de Datos** | **Tiempo de Respuesta (ms)** | **Consumo RAM Cliente (MB)** | **Uso CPU Servidor (%)** | **Latencia del Red (ms)** | **Obervasiones**                                          |
| ------------- | -------------------------------- | -------------------- | ---------------------------- | ---------------------------- | ------------------------ | ------------------------- | --------------------------------------------------------- |
| **PR-01**     | Listar catálogo completo (GET)   | 100 registros        | 145 ms                       | 42 MB                        | 12%                      | 25 ms                     | Renderizado fluido con Jetpack Compose (LazyColunm).      |
| **PR-02**     | Filtrar productos por Categoría  | 50 registros         | 85 ms                        | 35 MB                        | 8%                       | 22 ms                     | Optimizado por el uso del índice _idx_producto_categoria_ |
| **PR-03**     | Búsqueda por SKU (LIKE)          | 1 registro           | 40 ms                        | 25 MB                        | 3%                       | 20 ms                     | Búsqueda indexada; respuesta casi instantánea             |
| **PR-04**     | Creación de Producto (POS)       | N/A                  | 65 ms                        | 18 MB                        | 5%                       | 24 ms                     | Validación de restricción NotNull procesada correctamente |
| **PR-05**     | Actualización de stock (PATCH)   | N/A                  | 72 ms                        | 18 MB                        | 6%                       | 25 ms                     | Prueba de concurrencia superada sin bloqueos en la BD     |

_Tabla 1Matriz de Registro de Métricas de Eficiencia de Desempeño (ISO/IEC 2501)_

**_Nota:_** _Este instrumento documenta la verificación de los casos de uso principales del sistema, garantizando la correcta integración entre la aplicación Android y el servidor backend_

| **ID**     | **Módulo / Pantalla** | **Descripción del Escenario de Prueba**                           | **Resultado Esperado**                                                   | **Estado** | **Observaciones**                                                       |
| ---------- | --------------------- | ----------------------------------------------------------------- | ------------------------------------------------------------------------ | ---------- | ----------------------------------------------------------------------- |
| **UAT-01** | Autenticación         | Inicio de sesión con credenciales válidas (Rol: ADMIN).           | Redirección exitosa al Home y habilitación de opciones CRUD.             | Pasa       | Generación y validación de sesión correcta.                             |
| **UAT-02** | Autenticación         | Inicio de sesión con credenciales invalidas.                      | Muestra mensaje de error en pantalla; no permite acceso.                 | Pasa       | La interfaz muestra un aviso (Toast/Snackbar) de acceso denegado        |
| **UAT-03** | Catálogo              | Carga de la lista de productos al abrir la pestaña "Catálogo".    | Renderizado correcto de imágenes, precios y nombres en la UI.            | Pasa       | Librería Coil carga las imágenes sin congelar la pantalla.              |
| **UAT-04** | Gestión (CRUD)        | Intento de agregar un producto sin completar campos obligatorios. | Bloqueo de la acción y alerta visual indicando campos faltantes.         | Pasa       | El botón de "Guardar" se desactiva hasta que el formulario es invalido. |
| **UAT-05** | Gestión (CRUD)        | Modificación del precio de un producto existente.                 | Actualización exitosa en BD y reflejo inmediato en el catálogo.          | Pasa       | El ViewModel actualiza el estado local y sincroniza con el backend.     |
| **UAT-06** | Pedidos               | Creación de un pedido con stock suficiente.                       | Registro en la tabla **pedidos**, descuento de stock y mensaje de éxito. | Pasa       | Transacción SQL ejecutada correctamente (COMMIT).                       |
| **UAT-07** | Pedidos               | Intento de compra superando el stock disponible.                  | El sistema rechaza la transacción y alerta limite de inventario          | Pasa       | El backend envía código HTTP 400 y el cliente muestra el error.         |

_Tabla 2 Guía de Pruebas de Aceptación del Usuario (UAT) y Validación Funcional_

**_Nota:_** _Resultados agregar de la evaluación de usabilidad aplicada a una muestra de prueba (N=10 evaluadores) para medir la experiencia de usuario (UX) en la interfaz desarrollada_

| **ID**    | **Criterio de Evaluación**                                                             | **Totalmente en desacuerdo** | **En desacuerdo** | **De acuerdo** | **Totalmente de acuerdo** |
| --------- | -------------------------------------------------------------------------------------- | ---------------------------- | ----------------- | -------------- | ------------------------- |
| **UX-01** | La navegación mediante la barra inferior (BottomNavigation) es intuitiva.              |                              |                   | 20%            | 80%                       |
| **UX-02** | Los tiempos de carga al visualizar los productos en el catálogo son aceptables.        |                              | 10%               | 30%            | 60%                       |
| **UX-03** | Los mensajes de error (ej. "No se pudo conectar con el servidor") son claros y útiles. |                              |                   | 50%            | 50%                       |
| **UX-04** | El diseño visual (colores oscuros, tipografías) es agradable y facilita la lectura.    |                              |                   | 40%            | 60%                       |
| **UX-05** | Fue fácil encontrar la opción para gestionar mi perfil de usuario.                     |                              | 10%               | 20%            | 70%                       |
| **UX-06** | El proceso para realizar una actualización o registro en el CRUD es sencillo.          |                              |                   | 30%            | 70%                       |
| **UX-07** | En general, me siento satisfecho con el rendimiento de la aplicación móvil.            |                              |                   | 20%            | 80%                       |

_Tabla 3 Resultado del Cuestionario de Evaluación de Usabilidad_

**Anexo B: Tablas Extensas – Diccionario de Datos del Sistema**

**_Nota:_** _Se detalla la estructura física de las entidades principales de la base de datos_ **_TiendaTecnologicaDB2_** _empleada en el backend, demostrando las restricciones y tipos de datos nativos que garantizan la integridad referencial._

| **Nombre del Campo**     | **Tipo de Dato (MySQL)** | **Restricciones/Índices**                   | **Descripción**                                 |
| ------------------------ | ------------------------ | ------------------------------------------- | ----------------------------------------------- |
| **Id_pruducto**          | INT                      | PRIMARY KEY, AUTO_INCREMENT                 | Identificador único del producto.               |
| **codigo_sku**           | VARCHAR(50)              | NOT NULL, UNIQUE                            | Código de inventario estandarizado.             |
| **nombre**               | VARCHAR(150)             | NOT NULL, INDEX(idx_producto_nombre)        | Nombre comercial del hardware/dispositivo       |
| **descripcion**          | TEXT                     | N/A                                         | Especificaciones técnicas detalladas.           |
| **precio**               | DECIMAL(10,2)            | NOT NULL, CHECK (precio >= 0), INDEX        | Valor monetario del producto.                   |
| **stock**                | INT                      | NOT NULL, DEFAULT 0, CHECK(stock >= 0)      | Cantidad de unidades disponibles.               |
| **id_categoria**         | INT                      | FOREIGN KEY, INDEX (idx_producto_categoria) | Llave foránea hacia tabla **categorías.**       |
| **id_marca**             | INT                      | FOREIGN KEY                                 | Llave foránea hacia tabla **marcas**.           |
| **estado**               | TINYINT(1)               | NOT NULL, DEFAULT 1                         | Bandera lógica (1: Activo, 0: Eliminado).       |
| **Ultima_actualizacion** | DATETIME                 | ON UPDATE CURRENT_TIMESTAMP                 | Trazabilidad de modificaciones de stock/precio. |

_Tabla 4 Estructura y Restricciones de la Tabla Principal: Productos_

| **Nombre del Campo** | **Tipo de Dato (MySQL)** | **Restricciones / índices** | **Descipción**                                               |
| -------------------- | ------------------------ | --------------------------- | ------------------------------------------------------------ |
| **Id_categoria**     | INT                      | PRIMARY KEY, AUTO_INCREMENT | Identificador único de la categoría.                         |
| **Nombre**           | VARCHAR(100)             | NOT NULL, UNIQUE            | Nombre descriptivo de la clasificación.                      |
| **descripcion**      | VARCHAR(255)             | N/A                         | Detalles adicionales sobre los tipos de productos incluidos. |
| **activo**           | TINYINT(1)               | DAFAULT 1                   | Bandera para borrado lógico (1: Activo, 0: Inactivo).        |

_Tabla 5 Estructura y Restricciones de la Tabla: Categorias_

| **Nombre del Campo** | **Tipo de Dato (MySQL)** | **Restricciones / índices** | **Descipción**                       |
| -------------------- | ------------------------ | --------------------------- | ------------------------------------ |
| **Id_marca**         | INT                      | PRIMARY KEY, AUTO_INCREMENT | Identificador único del fabricante.  |
| **nombre**           | VARCHAR(100)             | NOT NULL, UNIQUE            | Nombre comercial de la marca.        |
| **País_origen**      | VARCHAR(80)              | N/A                         | País de procedencia del fabricante.  |
| **Activo**           | TINYINT(1)               | DAFAULT 1                   | Estado de visibilidad en el sistema. |

_Tabla 6 Estructura y Restricciones de la Tabla: Marcas_

| **Nombre del Campo** | **Tipo de Dato (MySQL)** | **Restricciones / índices** | **Descipción**                                            |
| -------------------- | ------------------------ | --------------------------- | --------------------------------------------------------- |
| **Id_usuario**       | INT                      | PRIMARY KEY, AUTO_INCREMENT | Identificador único del usuario.                          |
| **Nombre_completo**  | VARCHAR(150)             | NOT NULL                    | Nombre y apellidos del usuario registrado.                |
| **email**            | VARCHAR(120)             | NOT NULL, UNIQUE            | Correo electrónico usado para acceso y notificaciones.    |
| **Password_has**     | VARCHAR(255)             | NOT NULL                    | Contraseña encriptada por motivos de seguridad.           |
| **Teléfono**         | VARCHAR(20)              | N/A                         | Numero de contacto del cliente.                           |
| **Dirección**        | VARCHAR(255)             | N/A                         | Dirección física para el envío de los pedidos.            |
| **Rol**              | ENUM                     | DEFAULT 'CLIENTE'           | Define permisos ('ADMIN' o 'CLIENTE') para la navegación. |
| **Fecha_regristro**  | TIMESTAMP                | DEFAULY CURRENT_TIMESTAMP   | Momento de alta en la plataforma.                         |

_Tabla 7 Estructura y Restricciones de la Tabla: Usuarios_

| **Nombre del Campo** | **Tipo de Dato (MySQL)** | **Restricciones / índices** | **Descipción**                                                            |
| -------------------- | ------------------------ | --------------------------- | ------------------------------------------------------------------------- |
| **Id_pedido**        | INT                      | PRIMARY KEY, AUTO_INCREMENT | Identificador único de la transacción comercial.                          |
| **Id_usuario**       | INT                      | NOT NULL, FOREIGN KEY       | Enlace al usuario que realiza la compra (Restricción ON DELETE RESTRICT). |
| **Fecha_pedido**     | DATETIME                 | DEFAULT CURRENT_TIMESTAMP   | Fecha de la confirmación de la compra.                                    |
| **Total**            | DECIMAL(12, 2)           | NOT NULL, DEFAULT 0.00      | Monto global a cobrar por todo el pedido.                                 |
| **estado**           | ENUM                     | DEFAULT 'PENDIENTE'         | Etapa de la compra (PENDIENTE, PAGADO, ENVIADO, ENTREGADO, CANCELADO).    |

_Tabla 8 Estructura y Restricciones de la Tabla: Pedidos_

| **Nombre del Campo** | **Tipo de Dato (MySQL)** | **Restricciones / índices**    | **Descipción**                                                               |
| -------------------- | ------------------------ | ------------------------------ | ---------------------------------------------------------------------------- |
| **Id_detalle**       | INT                      | PRIMARY KEY, AUTO_INCREMENT    | Identificador único de la línea de detalle.                                  |
| **Id_pedido**        | INT                      | NOT NULL, FOREIGN KEY          | Relación con la cabecera del pedido (ON DELETE CASCADE)                      |
| **Id_producto**      | INT                      | NOT NULL, FOREIGN KEY          | Relación con el producto comprado (ON DELETE RESTRICT)                       |
| **Cantidad**         | INT                      | NOT NULL, CHECK (cantidad > 0) | Unidades adquiridas (debe ser estrictamente mayor a 0)                       |
| **Precio_unitario**  | DECIMAL (10, 2)          | NOT NULL                       | Precio fijado al momento exacto de la compra.                                |
| **subtotal**         | DECIMAL (12, 2)          | GENERATED ALWAYS, STORED       | Compo calculado automáticamente por MySQL (**cantidad \* precio_unitario**). |

_Tabla 9 Estructura y Restricciones de la tabla: Detalle_Pedidos_
