# ADR 0001: Comparación entre JDBC y JPA/Hibernate

- **Estado:** Aceptado.
- **Fecha:** 2026-10-15.

## Contexto

El proyecto busca comparar el rendimiento de las consultas a una base de datos MySQL utilizando JDBC nativo y JPA con Hibernate.

## Decisión

Implementar ambas alternativas de acceso a datos en Java y utilizar JMH para medir su rendimiento bajo condiciones equivalentes.

## Consecuencias

- Se podrán comparar los tiempos de ejecución de ambas alternativas.
- Se recopilarán métricas de rendimiento y memoria disponibles mediante JMH.
- Ambas alternativas deberán trabajar con datos equivalentes.
- Los resultados dependerán de la configuración y las condiciones del experimento.