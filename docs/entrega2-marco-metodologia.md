# Entrega 2 — Marco teórico y diseño metodológico

## Introducción (Cesia — Integrante 1)


## Estado del arte (Adrián — Integrante 2)


## Matriz de antecedentes (Hazel — Integrante 3)


## Marco teórico y operacionalización (Gerardo — Integrante 4)


## Diseño metodológico
La investigación tendrá un enfoque cuantitativo y experimental, ya que se realizarán mediciones para analizar cómo influye el volumen de registros de inventario en el rendimiento de las consultas de una aplicación para una tienda de productos electrónicos.

Se utilizará una base de datos como punto de partida y se trabajará con diferentes volúmenes de registros. En cada nivel se ejecutará una misma consulta de inventario y se registrará su tiempo de respuesta. Posteriormente, se compararán las mediciones obtenidas para identificar si el aumento de registros produce cambios en el rendimiento.

Para procurar que los resultados sean comparables, las pruebas se realizarán bajo las mismas condiciones de ejecución, utilizando el mismo equipo, la misma consulta y la misma configuración del entorno experimental. También se efectuarán varias ejecuciones por cada volumen de registros, con el propósito de reducir la influencia de variaciones ocasionales en los tiempos medidos.

Los volúmenes de datos, el número de repeticiones, la consulta específica y las herramientas de medición se definirán antes de ejecutar los experimentos, de manera que el procedimiento pueda documentarse y reproducirse.

### Diseño experimental 

El experimento evaluará el rendimiento de las operaciones de acceso a datos mediante dos estrategias: SQL nativo y JPA/Hibernate. Se utilizarán tres volúmenes de registros: 100, 1,000 y 10,000, con el propósito de analizar cómo influye la cantidad de información almacenada en el rendimiento de las consultas de inventario.

Las variables de respuesta serán el tiempo de respuesta, medido en milisegundos (ms), y la memoria máxima utilizada, expresada en megabytes (MB). Se realizarán 30 repeticiones por cada combinación experimental, precedidas de 5 ejecuciones de calentamiento.

Para mantener condiciones comparables, las pruebas se realizarán en la misma máquina y sin otras cargas de trabajo que puedan interferir con las mediciones. La caché de la base de datos se reiniciará entre series de pruebas. El orden de ejecución de las combinaciones se aleatorizará mediante una semilla fija (seed = 42), con el fin de facilitar la reproducibilidad del experimento.

### Entorno experimental



## Análisis y resultados (Adner — Integrante 7)


## Referencias
