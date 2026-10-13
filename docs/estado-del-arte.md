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
*	Documentos que no toquen el tema de hardware, rendimiento y consultas.
*	Sin ningún tipo de metodología.
