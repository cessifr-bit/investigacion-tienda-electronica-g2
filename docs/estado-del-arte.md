## Estado del arte 

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

## 2. Matriz de antecedentes 

| Autor y Año                                  | Objetivo                                                                                                                                                                       | Método y entorno                                                                                                                                                                                       | Hallazgo principal                                                                                                                                                                                | Vacío detectado                                                                                                                                                                                                  |
| -------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Uziel Trujillo Colón (2024)                  | Evaluar de forma comparativa la eficiencia temporal entre sentencias SQL directas y procedimientos almacenados bajo variaciones controladas en el volumen físico de registros. | Enfoque cuantitativo experimental mediante pruebas de estrés sobre un motor relacional, aplicando cargas masivas incrementales de registros y capturando los tiempos de ejecución en segundos.         | Los procedimientos almacenados reducen significativamente la latencia lógica frente a consultas planas, optimizando los tiempos computacionales en fases críticas de procesamiento transaccional. |El experimento se ejecutó de forma generalizada sobre un motor relacional alterno sin precisar las optimizaciones nativas de MySQL, desalineandose del entorno técnico específico de nuestro proyecto. Además, mide los tiempos de respuesta de forma aislada en el servidor expresados en segundos, omitiedo el comportamiento en milisegundos y el consumo de memoria RAM dinámica del lado del cliente móvil Android.       |
| Pérez Torres Amaury; Soler Silva José (2023) | Desarrollar un análisis crítico comparativo de los enfoques didácticos del diseño de bases de datos relacionales para optimizar la normalización estructural de tablas.        | Investigación metodológica cualitativa basada en la experimentación académica contrastando la normalización progresiva (Formas Normales) frente al modelado conceptual Entidad-Relación.               | La normalización estricta hasta la tercera forma normal (3FN) es indispensable para mitigar redundancias lógicas, prevenir anomalías de inserción y garantizar la atomicidad de los datos.        | El estudio aborda el diseño de datos de manera teórica y estructural dejando un vacío técnico al no analizar métricas de latencia de red o hardware en aplicaciones nativas basadas en hilos Java.               |
| Becerra Calvopiña Walter Mateo (2025)        | Diseñar un prototipo de gestión de inventarios acoplado a un sistema de pesaje automatizado para optimizar flujos logísticos operativos.                                       | Diseño metodológico basado en la construcción iterativa de software, implementando interfaces de comunicación sincrónicas entre hardware periférico y un backend de procesamiento distribuido.         | El uso de prototipos estructurados y arquitecturas web responsivas agiliza de forma drástica los tiempos de espera del negocio, incrementado la confiabilidad global en trazabilidad de stock.    | El autor plantea un alcance macro del sistema y evalúa variables operativas de software, sin proporcionar un monitoreo cuantitativo de sobrecarga o consumo del Heap de memoria en dispositivos móviles.         |
| Jaime Humberto Rodríguez (2021)              | Implementar un sistema comercial basado en ambiente web con un módulo centralizado de inventario para optimizar la carga transaccional de ventas.                              | Ingeniería de software clásica aplicando de manera rigurosa las fases de la metodología RUP (Rational Unified Process) y diagramación UML, con arquitectura MVC, PHP (PDO) y motor relacional MySQL.   | El despliegue de catálogos relacionales centralizados bajo entornos web optimiza el uso de recursos distribuidos, eliminando los tiempos perceptibles de espera transaccional en puntos de pago.  | La evaluación es cualitativa ("a juicio de usuario"), omitiendo la medición exacta de milisegundos y el impacto correlacional entre el tamaño físico de la base de datos y la memoria dinámica.                  |
| German Jeres (2023)                          | Desarrollar una aplicación móvil distribuidora que implemente sistemas de filtrado de datos mediante Machine Learning para la recomendación de perfiles laborales.             | En foque iterativo estructurado en fases de análisis de requerimientos (encuestas), desarrollo ágil con Kanban/Trello y un backend distribuido que ejecuta scripts de filtrado colaborativo en Python. | El uso de algoritmos basados en la fórmula de Similitud del Coseno provee un umbral de precisión del 87% en recomendaciones personalizadas, con altos índices de usabilidad y usabilidad móvil.   | El modelo fue desplegado exclusivamente sobre una base de datos NoSQL (Cloud Firestore), dejando libre el espacio de estudio para evaluar la eficiencia de la fórmula bajo un backend relacional MySQL indexado. |
| Carvajal Sellán, Miguel Ángel (2025)         | Desarrollar un sistema móvil enfocado en mitigar las ineficiencias de stock provocadas por el registro y trazabilidad manual de herramientas logísticas.                       | Estudio aplicado de desarrollo de software nativo/hibrido, maquetando interfaces lógicas de stock de almacén con sincronización directa hacia servidores de persistencia de datos.                     | La automatización del flujo de stock mediante herramientas móviles erradica las duplicaciones de registros de inventario y previene pérdidas criticas de información en la cadena de suministros  | Se enfoca de forma exclusivas en los flujos lógicos de la logística de almacén, avadiendo pruebas de estrés cuantitativas con consultas concurrentes masivas o indexación de tablas en el motor MySQL            |
| Martín Kleppmann (2022)                      | Definir los principios y patrones arquitectónicos necesarios para garantizar la escalabilidad, consistencia y mantenibilidad de aplicaciones intensivas en datos.              | Análisis teórico-práctico exhaustivo de la arquitectura de sistemas distribuidos de datos, evaluando el comportamiento de motores de búsqueda, cachés y el costo algorítmico de consultas complejas.   | La latencia terminal y el rendimiento de un sistema intensivo en datos se encuentran condicionados por la correcta selección de estrategia de particionamiento del catálogo.                      | Al ser un compendio general de arquitectura de sistemas de datos, no realiza experimentación de laboratorio sobre el entorno Android Runtime (ART) ni mide consumos métricos específicos de memoria RAM en Java. |

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

*	Documentos que no toquen el tema de hardware, rendimiento y consultas.
*	Sin ningún tipo de metodología.
