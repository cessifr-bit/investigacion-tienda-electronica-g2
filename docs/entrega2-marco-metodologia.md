# Entrega 2 — Marco teórico y diseño metodológico

## Introducción (Cesia — Integrante 1)


## Estado del arte (Adrián — Integrante 2)

## Estado del arte (Adrián — Integrante 2)

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

## Matriz de antecedentes (Hazel — Integrante 3)


## Marco teórico y operacionalización (Gerardo — Integrante 4)


## Diseño metodológico

La investigación tendrá un enfoque cuantitativo y experimental, ya que se realizarán mediciones para analizar cómo influye el volumen de registros de inventario en el rendimiento de las consultas de una aplicación para una tienda de productos electrónicos.

Se utilizará MySQL como sistema de gestión de bases de datos y se trabajará con diferentes volúmenes de registros. Para cada volumen se ejecutará una consulta de inventario y se registrarán las mediciones correspondientes. Posteriormente, se compararán los resultados obtenidos para identificar las variaciones en el rendimiento asociadas con el aumento de registros.

Para procurar que los resultados sean comparables, las pruebas se realizarán bajo condiciones controladas, manteniendo constantes el equipo, la consulta y la configuración del entorno experimental. Asimismo, se establecerá un procedimiento de ejecución y registro de las mediciones que permita documentar el experimento y facilitar su reproducción.

### Diseño experimental 

El experimento evaluará el rendimiento de las consultas de inventario en una aplicación para una tienda de productos electrónicos, utilizando MySQL como sistema de gestión de bases de datos. Se analizará la variación del rendimiento en función de la cantidad de registros almacenados, manteniendo constante la estrategia de acceso a los datos.

### Factores y niveles

El factor independiente será el volumen de registros del inventario, con tres niveles: 100, 500 y 1,000 registros. Estos niveles permitirán analizar cómo varía el rendimiento de las consultas a medida que aumenta la cantidad de información almacenada.

### Variables de respuesta

Se medirán las siguientes variables de respuesta:

- **Tiempo de respuesta:** tiempo requerido para ejecutar la consulta de inventario y obtener los resultados, expresado en milisegundos (ms).

- **Consumo de memoria:** cantidad máxima de memoria utilizada durante la ejecución, expresada en megabytes (MB).

### Número de repeticiones y calentamiento

Se realizarán 30 repeticiones por cada nivel de volumen de registros, precedidas de 5 ejecuciones de calentamiento. Estas últimas permitirán preparar el entorno de ejecución y no se incluirán en el análisis de las mediciones finales.

### Control de condiciones experimentales

Las pruebas se realizarán en el mismo equipo, con la misma configuración del entorno y utilizando la misma consulta de inventario. Asimismo, se evitarán otras cargas de trabajo que puedan interferir con las mediciones. El procedimiento relacionado con la caché de la base de datos se establecerá y documentará antes de ejecutar los experimentos.

### Orden de ejecución

El orden de ejecución de las pruebas se determinará antes de iniciar las mediciones. Se utilizará un orden aleatorizado con una semilla fija (seed = 42), de acuerdo con la propuesta de la guía, para facilitar la reproducción del procedimiento.

### Registro y comparación de resultados

Las mediciones se almacenarán en un archivo estructurado que permita identificar el volumen de registros utilizado en cada prueba, el número de ejecución y los valores obtenidos para las variables de respuesta. Posteriormente, los resultados se organizarán en tablas y gráficos para comparar el tiempo de respuesta y el consumo de memoria entre los diferentes volúmenes de datos.

Las herramientas de medición, la consulta utilizada y el procedimiento de ejecución se documentarán para facilitar la reproducción del experimento.

### Entorno experimental

Las pruebas se realizarán en un entorno de ejecución cuyas características se documentarán en el archivo docs/entorno.md. Se registrarán las especificaciones del equipo, el sistema operativo, la versión de Java, la versión de MySQL, las bibliotecas utilizadas y las configuraciones relevantes para el experimento. También se describirán las herramientas empleadas para medir el tiempo de respuesta y el consumo de memoria.

### Población, muestra o corpus

La población estará constituida por los registros de productos que pueden formar parte del inventario de una tienda de productos electrónicos. Para el experimento se utilizará un conjunto de datos de prueba con tres volúmenes: 100, 500 y 1,000 registros. Cada registro contendrá información de productos, como identificador, nombre, categoría, precio y cantidad disponible en inventario. Se procurará mantener la misma estructura de datos en los tres escenarios, variando únicamente la cantidad de registros, para evaluar su relación con el rendimiento de las consultas. El origen de los datos y el procedimiento utilizado para generarlos o seleccionarlos se documentarán antes de ejecutar las pruebas.

## Análisis y resultados (Adner — Integrante 7)

### Plan de análisis
### Amenazas a la validez
### Consideraciones éticas


## Referencias
