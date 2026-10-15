# Entorno experimental

## Equipo de pruebas

- **Procesador (CPU):** pendiente de registrar.
- **Memoria RAM:** pendiente de registrar.
- **Sistema operativo:** pendiente de registrar.

## Software y herramientas

- **Lenguaje de programación:** Java.
- **Versión de Java (JDK):** OpenJDK 21.0.12.
- **Entorno de desarrollo (IDE):** pendiente de confirmar.
- **Sistema gestor de base de datos:** MySQL.
- **Versión de MySQL:** pendiente de verificar.
- **Herramienta de construcción y gestión de dependencias:** pendiente de verificar.
- **Herramienta de medición de rendimiento:** JMH 1.37.
- **Bibliotecas utilizadas:** pendientes de verificar, incluyendo las versiones correspondientes.

## Configuración experimental

Se evaluaron dos estrategias de acceso a datos: JPA/Hibernate y SQL nativo. Para cada estrategia se utilizaron tres volúmenes de inventario: 100, 500 y 1,000 registros, lo que dio lugar a seis configuraciones experimentales.

La configuración de JMH contempló cinco iteraciones de calentamiento y treinta iteraciones de medición por configuración, con una duración de diez segundos por iteración. Las demás condiciones de ejecución y las configuraciones relevantes de la base de datos se documentarán de acuerdo con los registros disponibles.

## Procedimiento de medición

Se utilizó JMH para registrar el tiempo promedio de ejecución, expresado en milisegundos por operación (ms/op), y la memoria asignada por operación, expresada en bytes por operación (B/op). Los resultados se almacenaron en los archivos generados por la herramienta y se utilizaron para comparar las seis configuraciones experimentales.

## Reproducibilidad

Para facilitar la reproducción del experimento, se documentan las versiones del software y las bibliotecas, las características del equipo, la estructura de los datos, la consulta evaluada y los parámetros de ejecución. Los datos que no pudieron confirmarse se identificarán como pendientes de verificar.
