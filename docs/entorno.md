# Entorno experimental

## Equipo de pruebas

- **Procesador (CPU):** Intel(R) Celeron(R) N4120, frecuencia base de 1.10 GHz.
- **Memoria RAM:** 4 GB instalados, de los cuales 3.83 GB son utilizables.
- **Sistema operativo:** Microsoft Windows 10 Pro Education, versión 22H2, compilación 19045.6466.

## Software y herramientas

- **Lenguaje de programación:** Java.
- **Versión de Java (JDK):** Eclipse Temurin OpenJDK 21.0.12.
- **Entorno de desarrollo (IDE):** IntelliJ IDEA 2026.2.1.
- **Sistema gestor de base de datos: MySQL Community Server.
- **Versión de MySQL:**  8.0.46.
- **Herramienta de construcción y gestión de dependencias:**  Apache Maven 3.9.16, versión integrada (Bundled) en IntelliJ IDEA.
- **Herramienta de medición de rendimiento:JMH 1.37.
- **Bibliotecas utilizadas:** - Hibernate ORM: 6.6.4.Final.
- MySQL Connector/J: 9.1.0.
- Jakarta Persistence API: 3.1.0.
- JMH Core: 1.37.
- JMH Generator Annotation Processor: 1.37.

## Configuración experimental

Se evaluaron dos estrategias de acceso a datos: JPA/Hibernate y SQL nativo. Para cada estrategia se utilizaron tres volúmenes de inventario: 100, 500 y 1,000 registros, lo que dio lugar a seis configuraciones experimentales.

La configuración de JMH contempló cinco iteraciones de calentamiento y treinta iteraciones de medición por configuración, con una duración de diez segundos por iteración. Las demás condiciones de ejecución y las configuraciones relevantes de la base de datos se documentarán de acuerdo con los registros disponibles.

## Procedimiento de medición

Se utilizó JMH para registrar el tiempo promedio de ejecución, expresado en milisegundos por operación (ms/op), y la memoria asignada por operación, expresada en bytes por operación (B/op). Los resultados se almacenaron en los archivos generados por la herramienta y se utilizaron para comparar las seis configuraciones experimentales.

## Reproducibilidad

Para facilitar la reproducción del experimento, se documentan las versiones del software y las bibliotecas, las características del equipo, la estructura de los datos, la consulta evaluada y los parámetros de ejecución. Los datos que no pudieron confirmarse se identificarán como pendientes de verificar.
