# Entrega 2 — Marco teórico y diseño metodológico

**Título de la investigación:** “Análisis del rendimiento de consultas y operaciones de gestión de inventarios en una aplicación para una tienda de productos electrónicos”

## Introducción (Cesia — Integrante 1)

En una tienda de productos electrónicos es importante llevar un control de los productos disponibles, sus marcas, categorías y cantidades en inventario. Para realizar estas tareas de manera eficiente, es necesario contar con una aplicación que permita almacenar y consultar los datos de forma adecuada. Sin embargo, al aumentar la cantidad de productos almacenados, es importante comprobar si el tiempo necesario para obtener la información también cambia y si se producen variaciones en el consumo de memoria. 

En este trabajo se estudiará el rendimiento de una consulta de inventario utilizando MySQL. Para realizar las pruebas se preparará una base de datos con productos electrónicos y se trabajará con tres volúmenes de información: 100, 500 y 1,000 registros. En cada escenario se consultarán los productos de la categoría computadoras con el propósito de comparar el tiempo de respuesta y el consumo de memoria.

El documento presenta el estado del arte, el marco teórico y los antecedentes relacionados con el tema de investigación, así como la definición de las variables que se evaluarán. También se describe el diseño metodológico que comprende los factores del experimento, las variables de respuesta, las condiciones de ejecución y el procedimiento previsto para registrar y analizar las mediciones. De esta manera se busca establecer un procedimiento claro y reproducible que permita comprender cómo varía el rendimiento de las consultas a medida que aumenta la cantidad de registros del inventario.

## Estado del arte (Adrián — Integrante 2)

## 1. Estado del arte (Adrián — Integrante 2)

*1) Cadena: ("JPA" OR "Hibernate") AND ("SQL" OR "JDBC") AND ("rendimiento" OR "tiempo de respuesta")
Base: Google Académico
Fecha: 2026-10-12
Resultados:  aproximadamente 1320 resultados
Seleccionados: 2 artículos

Criterios de inclusión:
*Investigaciones que comparen directamente el rendimiento entre JPA/Hibernate y SQL nativo/JDBC.
*	Estudios que incluyan mediciones del tiempo de respuesta (milisegundos) en operaciones de consultas.

Criterios de exclusión:
*	Artículos puramente teóricos 
*	Documentos enfocados únicamente en bases de datos o no relacionales.

*2) Cadena: ("ORM" OR "mapeo objeto relacional") AND ("sobrecarga" OR "consumo de memoria") AND ("base de datos")
Base: Google Académico
Fecha: 2026-10-12
Resultados: ~2,400 resultados
Seleccionados: 4 artículos

Criterios de inclusión:
*	Artículos científicos con evaluación del consumo de memoria (RAM / Megabytes) e impacto del overhead generado por frameworks ORM.
*	Publicaciones con texto completo accesible para su análisis metodológico.
*	La sobrecarga con el consumo de memoria para el nivel de las consultas.

Criterios de exclusión:
*	Foros de discusión o documentación ambigua y sin profundidad.
*	Investigaciones sin métricas objetivas de uso de recursos de hardware

*3)Cadena: ("rendimiento de base de datos" OR "optimización de consultas") AND ("inventario" OR "comercio electrónico")
Base: Google Académico
Fecha: 2026-10-12
Resultados: ~1,120 resultados
Seleccionados: 2 artículos

Criterios de inclusión:
*	La optimización de consultas sobre el inventario
*	Trabajos académicos centrados en la optimización y rendimiento de consultas en entornos de comercio electrónico, catálogo o sistemas de inventario.
*	Publicaciones de pruebas clara.

Criterios de exclusión:
*	Trabajos enfocados sin evaluación de la capa de datos.
*	Presentaciones de diapositivas.
*	Artículos con dudoso proceso de pago para el acceso.

*4)Cadena: ("benchmarking" OR "evaluación de rendimiento") AND ("MySQL" AND ("consultas CRUD")
Base: Google Académico
Fecha: 2026-10-12
Resultados: 3
Seleccionados: 3 artículos

Criterios de inclusión:
*	Ejecución de operaciones de lectura, escritura, creación y eliminación (CRUD).
*	Investigaciones que detallen las condiciones para el gran volumen de datos.
*	Proceso para la elaboración de aplicaciones de alto rendimiento para manejar volúmenes de datos relacionado con el crud.

Criterios de exclusión:
*	Evaluaciones de rendimiento sobre motores de bases de datos obsoletos.
*	Documentos duplicados o resúmenes preliminares de investigaciones ya publicadas.
*	Información desfasada principalmente artículos con ideas vagas.

*5)Cadena: ("volumen de registros" OR "escalabilidad") AND ("tiempo de ejecución") AND ("consultas SQL")
Base: Google Académico
Fecha: 2026-10-12
Resultados: ~1110  resultados
Seleccionados: 4 artículos

Criterios de inclusión:
*	Estudios experimentales que evalúen la variación del tiempo de ejecución al escalar progresivamente el volumen de registros en las tablas involucradas en el proyecto.
*	Investigaciones sobre las consultas con el tiempo de ejecución para medir el tiempo con el cual se realiza la consulta en un volumen grande de datos.

Criterios de exclusión:
*	Investigaciones que no apliquen a entornos relacionales estándar.
*	Artículos introductorios sin nada de profundización en el tema como forma de atraer consultas, pero sin nada de texto nutritivo.
*	Artículos sin desglose metodológico sobre el número de consultas o iteraciones.

*6)Cadena: ("rendimiento de base de datos" OR "tiempo de respuesta") AND ("MySQL" OR "SQL Server") AND ("Crud con base de datos")
Base: Redalyc
Fecha: 2026-10-12
Resultados: ~145 resultados
Seleccionados: 4 artículos

Criterios de inclusión:
*	Investigaciones científicas en ciencias de la computación.
*	Relación con MySql con la operación crud principalmente con las labores en las consultas, su fin de relación.
*	Estudios con pruebas empíricas sobre operaciones de selección, inserción, actualización y eliminación las operaciones crud.

Criterios de exclusión:
*	Artículos de revisión o ensayos teóricos sin experimentación práctica.
*	Aplicación de Crud a un lenguaje que no será de implementación en el diseño final del proyecto.
*	Investigaciones aplicadas a bases de datos NoSql.

*7)Cadena: ("mapeo objeto relacional" OR "ORM" OR "JPA") AND ("sobrecarga" OR "costo computacional") AND ("base de datos MySql")
Base: Redalyc
Fecha: 2026-10-12
Resultados: ~98 resultados
Seleccionados: 5 artículos

Criterios de inclusión:
*	Artículos arbitrados enfocados en analizar la eficiencia y sobrecarga para evitar el que se congele, o salga de servicio por el nivel de volumen de datos.
*	Evaluaciones con métricas sobre uso de memoria o tiempos de procesamiento.
*	Uso de variables de prueba para medir el tiempo de respuesta.

Criterios de exclusión:
*	Manuales de programación o guías introductorias de uso de software.
*	Documentos sin análisis comparativo explicito entre tecnologías de persistencia.
*	Documentos que no sean de utilidad para MySql

*8)Cadena: ("sistema de inventario" OR "comercio electrónico") AND ("optimización de consultas" OR "rendimiento web, aplicación movil")
Base: Redalyc
Fecha: 2026-10-12
Resultados: ~82 resultados
Seleccionados: 7 artículos

Criterios de inclusión:
*	Investigaciones de desarrollo de software orientadas a la optimización de la capa de datos en sistemas de inventario (como el del proyecto)
*	Estudios que detallan la arquitectura y tiempos de ejecución en el servidor.
*	Estudios que incluyan el desarrollo para aplicación móvil.

Criterios de exclusión:
*	Trabajos del área administrativa/logística que no evalúen componentes de software ni bases de datos.
*	Documentos que no sean compatibles con Android
*	Información que no este sustentada, sin haber estado sometida a pruebas, siendo una información de relleno

*9)Cadena: ("volumen de datos" OR "escalabilidad") AND ("tiempo de ejecución") AND ("consultas en MySQL")
Base: Redalyc
Fecha: 2026-10-12
Resultados: ~112 resultados
Seleccionados: 3 artículos

Criterios de inclusión:
*	Experimentos cuantitativos que analicen el aumentar progresivamente el número de registros en tablas SQL
*	Implementación en otros programas y aplicaciones móviles un uso eficaz de MySql

Criterios de exclusión:
*	Artículos sobre bases de datos distribuidas masivas 
*	Entornos ajenos al de aplicación final
*	Uso de tecnologías que están fuera de las planificadas

*10)Cadena: ("consumo de memoria" OR "recursos de hardware") AND ("base de datos relacional") AND ("evaluación de rendimiento, tiempo de respuesta")
Base: Redalyc
Fecha: 2026-10-12
Resultados: ~76 resultados
Seleccionados: 4 artículos

Criterios de inclusión:
*	Publicaciones académicas que midan de forma objetiva el impacto en memoria RAM y CPU al ejecutar consultas de datos.
*	Artículos con metodología de entorno controlado y repetibilidad de pruebas.
*	Variable de prueba usada para medir el tiempo de respuesta.

Criterios de exclusión:
*	Reportes técnicos o conferencias cortas sin artículo en extenso.
*	Documentos que no toquen el tema de hardware, rendimiento y consultas.
*	Sin ningún tipo de metodología.

## Fuentes


*1)
Uziel Trujillo Colón (2024). Análisis comparativo de rendimiento entre consultas SQL directas y procedimientos almacenados bajo diferentes volúmenes de datos. Ciencia Latina Revista Científica Multidisciplinar.
Año: 2024
Enlace real: https://ciencialatina.org/index.php/cienciala/article/download/13434/19351/
Aporte al proyecto: Aporta métricas de tiempo de ejecución (segundos) en pruebas de estrés con variaciones volumétricas de datos.

*2)
Microsoft Learn. (2025). Ediciones y características admitidas de SQL Server 2025: Optimización de consultas, bloqueo optimizado y rendimiento por lotes. Microsoft Technical Documentation.
Año: 2025 
Enlace real:https://learn.microsoft.com/es-es/sql/sql-server/editions-and-components-of-sql-server-2025
Aporte al proyecto: Documentación técnica oficial sobre los mecanismos internos de procesamiento de consultas y control de memoria caché en entornos de bases de datos empresariales.

*3)
Cancio, C. (2025). Comparativa de rendimiento entre Spring Boot (Java), .NET y arquitecturas de persistencia backend. Guías de Arquitectura de Software y Rendimiento.
Año: 2025 
Enlace real: https://www.cesarcancino.com/comparativa-de-rendimiento-entre-nestjs-net-spring-boot-laravel-y-django-cual-elegir-en-2025/
Aporte al proyecto: Analiza el comportamiento en crudo de entornos Java y su interacción con frameworks de persistencia pesados frente a micro-servicios y consultas optimizadas.

*4)
Pérez Torres, Amaury; Soler Silva, José . Optimización de bases de datos relacionales y su impacto en la latencia de aplicaciones web transaccionales. Revista Tecnológica Universitaria.
Año: 2023 
Enlace real: https://www.redalyc.org/articulo.oa?id=589165883005
Aporte al proyecto: Evalúa cómo la indexación de tablas y el diseño de consultas CRUD reducen drásticamente el tiempo de respuesta en milisegundos.

*5)
Oracle MySQL Documentation. MySQL 8.0 Reference Manual: Optimization, Execution Plans and EXPLAIN ANALYZE. Oracle Corporation.
Año: 2023 
Enlace real: https://dev.mysql.com/doc/refman/8.0/en/optimization.html
Aporte al proyecto: Fuente oficial para el análisis de planes de ejecución (EXPLAIN) y su aplicación práctica en el diagnóstico de rendimiento de motores MySQL.

*6)
Alter Mateo. Análisis en trabajo de gestión de inventarios aplicando un crud específicamente para las labores requeridas, un prototipo de aplicación.
Año: 2025 
Enlace real: https://repositorio.puce.edu.ec/server/api/core/bitstreams/7f806b6c-dfb4-46fb-bc9b-1f606e8999d5/content
Aporte al proyecto: Define la metodología estandarizada para evaluar indicadores clave de calidad de software: tiempo de respuesta, latencia, tiempo de espera y uso de recursos de hardware a su vez trabajando con una base y tomar ideas para el proyecto. 

*7)
Jaime Humberto Rodriguez. Análisis en trabajo de gestión de inventarios en la gran cantidad de datos aplicados principalmente en el desarrollo con la construcción del CRUD (Créate, Read, Update, Delete), aplicable a todos los gestores del sistema para punto de venta, gestor de inventarios, de usuarios, 
Año: 2025 
Enlace real: https://barnes.edu.pe/wp-content/uploads/2025/04/Diseno-e-Implementacion-de-un-Sistema-POS-con-Modulo-de-Gestion.pdf
Aporte al proyecto: Define un modelo a seguir de manera en la cual se pueda adaptar, estudiar, manejar un posible escenario aplicado a nuestro proyecto.

*8)
German Jeres (2023) Diseño de una app móvil que utiliza sistemas de filtrado de información para identificar, recomendar y describir los datos de contacto de personas que laboran.
Año: 2023
Enlace real: https://repository.unab.edu.co/server/api/core/bitstreams/7d46ce55-0b99-44d1-831c-5b5c17ba75f7/content
Aporte al proyecto: Se vio principalmente en el objetivo que se tomo de desarrollar un prototipo de aplicación móvil para generar recomendaciones de empleado a empresas del sector del calzado. Se propuso una metodología para el desarrollo del proyecto en la cual consistió en tres fases siendo la de análisis y diseño de requerimientos, desarrollo y análisis de pruebas por ello el aporte al proyecto es para fines de implementación y aprender de proyectos establecidos a buscar mejorar. 

*9)
Carvajal Sellan, Miguel Angel.  Inventario de aplicaciones móviles para la logística de desarrollo de software gestión de datos o una aplicación móvil.
Año: 2025
Enlace real: https://bibdigital.epn.edu.ec/handle/15000/27114
Aporte al proyecto: Es un articulo que describe un poco del proyecto el como se enfrenta el control eficiente del inventario, ya que el registro, ingreso, salida y trazabilidad de stock por la realización de forma manual o con herramientas dispersas genera duplicaciones, errores y pérdida de información. De esta manera se busca implementar los mejores aportes para sustentar un excelente rendimiento del proyecto.  

*10)
Martin Kleppmann.  Diseño de aplicaciones mediante el uso intensivo de datos mediante un software libre y de código abierto.
Año: 2022
Enlace real: https://books.google.com.sv/books?hl=es&lr=&id=t3J6EAAAQBAJ&oi=fnd&pg=PT9&dq=tiempo+de+ejecucion+de+una+consulta+en+mayor+cantidad+de+datos&ots=SH0LjZl-H5&sig=F-fSFM5T3xEvazqfttE4H23YEJU&redir_esc=y#v=onepage&q=tiempo%20de%20ejecucion%20de%20una%20consulta%20en%20mayor%20cantidad%20de%20datos&f=false
Aporte al proyecto: Abarca el como manejar el principal manejo de datos ya que se necesita ver el tiempo de respuesta en una cantidad de datos amplia, el aporte es ver la arquitectura en los sistemas de datos.

## 2. Matriz de antecedentes (Hazel — Integrante 3)

| Autor y Año                                  | Objetivo                                                                                                                                                                       | Método y entorno                                                                                                                                                                                       | Hallazgo principal                                                                                                                                                                                | Vacío detectado                                                                                                                                                                                                  |
| -------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Uziel Trujillo Colón (2024)                  | Evaluar de forma comparativa la eficiencia temporal entre sentencias SQL directas y procedimientos almacenados bajo variaciones controladas en el volumen físico de registros. | Enfoque cuantitativo experimental mediante pruebas de estrés sobre un motor relacional, aplicando cargas masivas incrementales de registros y capturando los tiempos de ejecución en segundos.         | Los procedimientos almacenados reducen significativamente la latencia lógica frente a consultas planas, optimizando los tiempos computacionales en fases críticas de procesamiento transaccional. | El experimento se ejecutó de forma generalizada sobre un motor relacional alterno sin precisar las optimizaciones nativas de MySQL, desalineandose del entorno técnico específico de nuestro proyecto. Además, mide los tiempos de respuesta de forma aislada en el servidor expresados en segundos, omitiedo el comportamiento en milisegundos y el consumo de memoria RAM dinámica del lado del cliente móvil Android.           |
| Pérez Torres Amaury; Soler Silva José (2023) | Desarrollar un análisis crítico comparativo de los enfoques didácticos del diseño de bases de datos relacionales para optimizar la normalización estructural de tablas.        | Investigación metodológica cualitativa basada en la experimentación académica contrastando la normalización progresiva (Formas Normales) frente al modelado conceptual Entidad-Relación.               | La normalización estricta hasta la tercera forma normal (3FN) es indispensable para mitigar redundancias lógicas, prevenir anomalías de inserción y garantizar la atomicidad de los datos.        | El estudio aborda el diseño de datos de manera teórica y estructural dejando un vacío técnico al no analizar métricas de latencia de red o hardware en aplicaciones nativas basadas en hilos Java.               |
| Becerra Calvopiña Walter Mateo (2025)        | Diseñar un prototipo de gestión de inventarios acoplado a un sistema de pesaje automatizado para optimizar flujos logísticos operativos.                                       | Diseño metodológico basado en la construcción iterativa de software, implementando interfaces de comunicación sincrónicas entre hardware periférico y un backend de procesamiento distribuido.         | El uso de prototipos estructurados y arquitecturas web responsivas agiliza de forma drástica los tiempos de espera del negocio, incrementado la confiabilidad global en trazabilidad de stock.    | El autor plantea un alcance macro del sistema y evalúa variables operativas de software, sin proporcionar un monitoreo cuantitativo de sobrecarga o consumo del Heap de memoria en dispositivos móviles.         |
| Jaime Humberto Rodríguez (2021)              | Implementar un sistema comercial basado en ambiente web con un módulo centralizado de inventario para optimizar la carga transaccional de ventas.                              | Ingeniería de software clásica aplicando de manera rigurosa las fases de la metodología RUP (Rational Unified Process) y diagramación UML, con arquitectura MVC, PHP (PDO) y motor relacional MySQL.   | El despliegue de catálogos relacionales centralizados bajo entornos web optimiza el uso de recursos distribuidos, eliminando los tiempos perceptibles de espera transaccional en puntos de pago.  | La evaluación es cualitativa ("a juicio de usuario"), omitiendo la medición exacta de milisegundos y el impacto correlacional entre el tamaño físico de la base de datos y la memoria dinámica.                  |
| German Jeres (2023)                          | Desarrollar una aplicación móvil distribuidora que implemente sistemas de filtrado de datos mediante Machine Learning para la recomendación de perfiles laborales.             | En foque iterativo estructurado en fases de análisis de requerimientos (encuestas), desarrollo ágil con Kanban/Trello y un backend distribuido que ejecuta scripts de filtrado colaborativo en Python. | El uso de algoritmos basados en la fórmula de Similitud del Coseno provee un umbral de precisión del 87% en recomendaciones personalizadas, con altos índices de usabilidad y usabilidad móvil.   | El modelo fue desplegado exclusivamente sobre una base de datos NoSQL (Cloud Firestore), dejando libre el espacio de estudio para evaluar la eficiencia de la fórmula bajo un backend relacional MySQL indexado. |
| Carvajal Sellán, Miguel Ángel (2025)         | Desarrollar un sistema móvil enfocado en mitigar las ineficiencias de stock provocadas por el registro y trazabilidad manual de herramientas logísticas.                       | Estudio aplicado de desarrollo de software nativo/hibrido, maquetando interfaces lógicas de stock de almacén con sincronización directa hacia servidores de persistencia de datos.                     | La automatización del flujo de stock mediante herramientas móviles erradica las duplicaciones de registros de inventario y previene pérdidas criticas de información en la cadena de suministros  | Se enfoca de forma exclusivas en los flujos lógicos de la logística de almacén, avadiendo pruebas de estrés cuantitativas con consultas concurrentes masivas o indexación de tablas en el motor MySQL            |
| Martín Kleppmann (2022)                      | Definir los principios y patrones arquitectónicos necesarios para garantizar la escalabilidad, consistencia y mantenibilidad de aplicaciones intensivas en datos.              | Análisis teórico-práctico exhaustivo de la arquitectura de sistemas distribuidos de datos, evaluando el comportamiento de motores de búsqueda, cachés y el costo algorítmico de consultas complejas.   | La latencia terminal y el rendimiento de un sistema intensivo en datos se encuentran condicionados por la correcta selección de estrategia de particionamiento del catálogo.                      | Al ser un compendio general de arquitectura de sistemas de datos, no realiza experimentación de laboratorio sobre el entorno Android Runtime (ART) ni mide consumos métricos específicos de memoria RAM en Java. |

## 3. Marco teórico y operacionalización (Gerardo — Integrante 4)

El análisis del rendimiento en aplicaciones móviles requiere una base conceptual rigurosa que permita comprender la interacción técnica entre la gestión de recursos en el cliente y eficiencia de procesamiento del servidor. Para fundamentar metodológicamente esta investigación, el presente apartado desarrolla los cimientos teóricos necesarios para evaluar el sistema bajo escenarios de estrés concurrente. Inicialmente, se exploran las particularidades del entorno Android y su administración de memoria ante grandes volúmenes de información. A continuación, se examinan los mecanismos de optimización en bases de datos relacionales, destacando el impacto de la indexación y el filtrado sobre la latencia. Finalmente, el estudio se ancla conceptualmente en el estándar internacional ISO/IEC 25010, el cual proporciona el marco normativo y el vocabulario preciso para operacionalizar objetivamente las métricas de eficiencia de desempeño.

### - Rendimiento y procesamiento de datos en aplicaciones móviles

El rendimiento de las aplicaciones no es solo una preocupación para la mayoría de las organizaciones; es un factor crítico en el éxito de su negocio. Impulsar el rendimiento óptimo de las aplicaciones y minimizar los costos se ha vuelto primordial a medida que las organizaciones se esfuerzan por lograr las experiencias de usuario positivas. Estas experiencias pueden hacer o deshacer un negocio, por eso priorizar el alto rendimiento entre las aplicaciones no es negociable. Lo que se necesita es una solución que no solo proteja el rendimiento de sus aplicaciones de misión crítica sino que también vaya más allá mediante la reducción de costos, la eficiencia del tiempo y el ahorro monetario

  **. Aporte a la investigación:** Esta categoría proporciona los conceptos fundamentales para comprender el efecto técnico que tienen las variables independientes (volumen   de catálogo y usuarios simultáneos) sobre las variables dependientes, justificando por qué es necesario medir el consumo de recursos en el entorno específico de Android.

### - Optimización de bases de datos relacionales: Índices y filtros

La optimización de bases de datos es la práctica sistemática de mejorar la forma en que una base de datos almacena, recupera y procesa los datos para que pueda ofrecer un rendimiento rápido y predecible.

En esencia, la optimización reduce el trabajo innecesario dentro de la base de datos, mejorando la forma en que elige los planes de ejecución de consultas, estructura el esquema, organiza el almacenamiento y utiliza los índices. Estas mejoras reducen la sobrecarga al optimizar la localidad de los datos y evitar cálculos repetidos. Además, son una de las formas más efectivas de controlar el gasto en la nube.

Pero la optimización de bases de datos no es una simple puesta a un punto puntual. Se trata, más bien, de un esfuerzo de mantenimiento continuo que mantiene el motor de la base de datos alineado con las cambiantes demandas de las aplicaciones, el creciente volumen de datos y las iniciativas de inteligencia artificial (IA). Es la base de prácticamente todos los sistemas de bases de datos importante, desde las bases de datos relacionales hasta los motores de código abierto.

Las bases de datos relacionales como MySQL utilizan estructuras de datos especializadas, como los árboles B (B-trees), para implementar índices. un índice reduce significativamente el espacio de búsqueda al realizar consultas, evitando escaneos secuenciales completos de las tablas (full table scans). Asimismo, la aplicación de filtros (como categoría o precio) en las cláusulas SQL condiciona la cantidad de información que se transfiere por la red y se aloja en la memoria del cliente móvil, impactando directamente en la latencia.

  **. Aporte a la investigación:** Esta sección teórica provee la base conceptual para justificar la evaluación de la "implementación de índices" y el "uso de filtros" como factores experimentales que modifican el tiempo de respuesta y la eficiencia de la consulta formulados en las preguntas secundarias.

### - Modelo de calidad y eficiencia de desempeño (ISO/IEC 25010)

La calidad del producto software se interpreta como el grado en que satisface los requisitos de los usuarios aportando un valor, por lo que el modelo de calidad representa la piedra angular para establecer su sistema de evaluación. Para evaluar el comportamiento del sistema bajo condiciones específicas de carga, es indispensable basarse en un modelo estandarizado que categorice estos requisitos (tales como funcionalidad, rendimiento o seguridad) en características y sub-características a tener en cuenta. En este contexto, el estándar de referencia ISO/IEC 25010 establece las características de calidad del software, entre las que destaca la <<eficiencia de desempeño>>. Al determinar de esta manera las propiedades del producto, esta característica evalúa dos aspectos críticos: el comportamiento temporal (tiempos de respuesta y procesamiento) y la utilización de recursos (cantidades y tipos de recursos utilizados, como la memoria, al realizar sus funciones).

  **. Aporte a la investigación:** El estándar ISO/IEC 25010 proporciona el marco normativo y el vocabulario técnico preciso para sustentar y definir operacionalmente las variables dependientes principales de este estudio: el tiempo de respuesta y el consumo de memoria.

### 3.1 Operacionalización de variables

| **Variable**                                        | **Definición operacional**                                                                                                                                                  | **Unidad**                     | **Instrumento**                   |
| --------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------ | --------------------------------- |
| **Tiempo de respuesta de la aplicación / consulta** | Milisegundos transcurridos entre el envío de la petición de búsqueda o filtrado desde el dispositivo Android hasta la recepción y renderizado del último registro de MySQL. | Milisegundos                             | JMH 1.37                          |
| **Consumo de memoria**                              | Memoria máxima retenida en el montículo (_heap_) de la JVM en el dispositivo Android durante la ejecución de las consultas y visualización de resultados.                   | MB                             | JMH con el perfilador GC          |
| **Tamaño del catálogo de productos**                | Número exacto de registros almacenados y disponibles en la tabla de productos de la base de datos MySQL utilizada para las pruebas.                                         | Entero (Cantidad de registros) | _MySQL Workbench y consultas SQL_ |

## 4. Diseño metodológico

La investigación tiene un enfoque cuantitativo y un diseño experimental, ya que se realizaron mediciones para comparar el rendimiento de dos estrategias de acceso a datos en una aplicación para una tienda de productos electrónicos. Se evaluaron las implementaciones JPA/Hibernate y SQL nativo utilizando diferentes volúmenes de registros de inventario.

Las pruebas se ejecutaron con JMH como herramienta de medición y MySQL como sistema de gestión de bases de datos. Se analizaron los resultados correspondientes a tres volúmenes de datos: 100, 500 y 1,000 registros. Para cada volumen se evaluaron ambas estrategias de acceso a datos y se registraron las mediciones de rendimiento obtenidas.

Para facilitar la comparación, se utilizó el mismo entorno experimental durante las pruebas. Las características del equipo, las versiones de las herramientas y los parámetros de ejecución se documentaron en el archivo `docs/entorno.md`.

### 4.1 Diseño experimental

El diseño experimental consideró dos factores: el volumen de registros del inventario y la estrategia de acceso a datos. La combinación de sus niveles permitió comparar el comportamiento de JPA/Hibernate y SQL nativo en distintos tamaños de inventario.

### 4.1.1 Factores y niveles

Se evaluaron los siguientes factores:

- **Volumen de registros:** 100, 500 y 1,000 registros.
- **Estrategia de acceso a datos:** JPA/Hibernate y SQL nativo.

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

### 4.2 Entorno experimental

Las pruebas se realizaron en un entorno de ejecución con Java y MySQL, utilizando JMH para medir el rendimiento de las implementaciones evaluadas. Las características del equipo, el sistema operativo, las versiones de las herramientas y las bibliotecas utilizadas se documentaron en el archivo `docs/entorno.md`, junto con los parámetros relevantes para la ejecución del experimento.

### 4.3 Población, muestra o corpus

La población de referencia estuvo constituida por los productos que pueden formar parte del inventario de una tienda de productos electrónicos. Para el experimento se utilizaron conjuntos de datos de prueba de 100, 500 y 1,000 registros. Se mantuvo la estructura de los datos en los tres escenarios y se modificó la cantidad de registros para comparar el rendimiento de las estrategias de acceso a datos JPA/Hibernate y SQL nativo. El conjunto de datos utilizado, los campos incluidos y el procedimiento de generación se consideraron parte de la descripción del corpus experimental.


## 5. Análisis y resultados 

## Organización y Resumen de Resultados (JMH Benchmark)

Se ejecutó un benchmark comparativo mediante Java Microbenchmark Harness (JMH 1.37) sobre OpenJDK 21, comparando las implementaciones `jpaHibernate` frente a `sqlNativo` bajo tres niveles de volumen de registros (100, 500 y 1000).

### Tabla Comparativa de Rendimiento

| Implementación | Volumen (regs) | Tiempo Promedio (ms/op) | Margen de Error (99.9% CI) | Asignación Memoria (B/op) | Recolecciones GC (total) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **JPA / Hibernate** | 100 | **1.22 ms** | ± 0.05 ms | ~121,244 B/op | 1,301 |
| **JPA / Hibernate** | 500 | **2.90 ms** | ± 0.05 ms | ~567,614 B/op | 1,606 |
| **JPA / Hibernate** | 1000 | **4.66 ms** | ± 0.06 ms | ~1,123,107 B/op | 1,355 |
| **SQL Nativo** | 100 | **14.07 ms** | ± 1.37 ms | ~461,073 B/op | 590 |
| **SQL Nativo** | 500 | **19.24 ms** | ± 3.28 ms | ~787,946 B/op | 728 |
| **SQL Nativo** | 1000 | **17.32 ms** | ± 1.26 ms | ~1,181,904 B/op | 910 |

### 5.1 Plan de análisis
**Variables y métricas evaluadas:** 
  - Tiempo medio de ejecución por operación (`ms/op`), percentiles de latencia (p50, p90, p99) para identificar variabilidad en colas de respuesta.
  - Tasa normalizada de memoria asignada por operación (`B/op`) e impacto en las pausas del Garbage Collector.
- **Tratamiento estadístico:** 
  - Comparación de medias muestrales mediante cálculo de intervalos de confianza al 99.9%.
  - Aplicación de pruebas de contraste de hipótesis (prueba t de Student para muestras independientes con nivel de significancia $\alpha = 0.05$) para validar si las diferencias observadas entre JPA y SQL Nativo son estadísticamente significativas.
- **Interpretación preliminar:** 
  - JPA/Hibernate evidencia una latencia promedio significativamente menor y más predecible en todos los escenarios evaluados.
  ---
### 5.2 Amenazas a la validez
**Validez interna:**
  - *Efectos de calentamiento (warmup):* Mitigados mediante 5 iteraciones de 10 segundos para garantizar la compilación JIT antes de tomar métricas.
  - *Sobrecarga de depuración:* Las pruebas registraron la presencia del agente `-javaagent` del entorno de desarrollo, lo cual puede introducir una leve carga fija en la máquina virtual.
- **Validez externa:**
  - *Entorno monohilo:* Las pruebas se ejecutaron con 1 hilo (`threads: 1`) y 1 bifurcación (`forks: 1`) sobre Windows con OpenJDK 21. Los tiempos y la contención de conexiones pueden variar en entornos multiproceso o contenedores de producción.
- **Validez de construcción:**
  - El experimento evalúa únicamente el tiempo de ejecución en capa de datos a nivel local, sin incorporar la latencia real de red ni la serialización hacia un cliente final.
  ---
### 5.3 Consideraciones éticas
- **Licenciamiento:** Las dependencias utilizadas (JMH, Hibernate, JDBC Driver) se rigen por licencias de código abierto compatibles (GPLv2 con Classpath Exception / LGPL). Los datos de prueba se basan en esquemas estándar de dominio público/educativo.
- **Declaración de Uso de IA:** Se utilizaron modelos de lenguaje de inteligencia artificial como herramientas de apoyo para el análisis exploratorio de datos, redacción y estructuración estadística del informe, manteniendo la autoría y validación de los resultados por parte del equipo.


## Referencias

Henderson, D. (2025, 18 diciembre). Optimización del rendimiento de las aplicaciones: aumente el rendimiento y reduzca los costos. IBM. https://www.ibm.com/mx-es/think/insights/application-performance-optimization

Jonker, T. K. A. (2026, 10 marzo). What is database optimization? https://www.ibm.com/think/topics/database-optimization

ISO 25010. (s. f.). https://iso25000.com/index.php/normas-iso-25000/iso-25010
