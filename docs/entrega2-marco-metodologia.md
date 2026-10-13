# Entrega 2 — Marco teórico y diseño metodológico

## Introducción (Cesia — Integrante 1)


## Estado del arte (Adrián — Integrante 2)


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
