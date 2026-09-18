# Cronograma

## Actividades

- Análisis del problema
- Diseño de la base de datos
- Diseño de las operaciones CRUD
- Diseño de pruebas experimentales
- Implementación del sistema
- Ejecución de experimentos
- Análisis de resultados
- Correcciones y mejoras finales
- Redacción del informe final

## Registro de cambios

| Fecha | Commit | Descripción |
|---|---|---|
| 13/09/2026 | docs(timeline): define project activities | Definición de actividades del proyecto |
| 13/09/2026 | docs(timeline): establish project dates | Establecimiento de fechas |
| 16/09/2026 | docs(timeline): add delivery milestones | Incorporación de cuatro hitos de entrega |
| 16/09/2026 | docs(timeline): add schedule buffer | Inclusión de holgura para experimentos |
| 17/09/2026 | docs(timeline): update timeline and log | Actualización final y registro de cambios |

```mermaid
gantt
    title Cronograma del Proyecto
    dateFormat YYYY-MM-DD
    axisFormat %d/%m

    section Investigación
    Análisis del problema :t1, 2026-09-06, 9d
    Entrega 1 :milestone, m1, 2026-09-18, 0d

    section Diseño
    Diseño de la base de datos :t2, 2026-09-20, 7d
    Diseño de operaciones CRUD :t3, after t2, 7d
    Diseño de pruebas experimentales :t4, after t3, 7d
    Entrega 2 :milestone, m2, 2026-10-12, 0d

    section Desarrollo
    Implementación del sistema :t5, 2026-10-13, 14d
    Ejecución de experimentos :t6, after t5, 7d
    Holgura y repetición de experimentos :crit, t7, after t6, 7d
    Entrega 3 :milestone, m3, 2026-11-10, 0d

    section Análisis
    Análisis de resultados :t8, 2026-11-11, 7d
    Correcciones y mejoras finales :t9, after t8, 7d
    Redacción del informe final :t10, after t9, 7d
    Entrega 4 :milestone, m4, 2026-12-06, 0d

