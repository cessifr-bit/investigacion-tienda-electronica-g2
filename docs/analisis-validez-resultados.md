# Plan de Análisis Estadístico, Validez y Resultados

## 1. Organización y Resumen de Resultados (JMH Benchmark)

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

### Análisis de los Datos
- **Tiempo de respuesta:** La implementación con JPA/Hibernate mostró una velocidad notablemente mayor y consistente en todos los volúmenes, tardando entre 1.22 ms y 4.66 ms. En contraste, SQL Nativo presentó tiempos mayores (14.07 ms a 19.24 ms) con mayor dispersión y error experimental.
- **Consumo de memoria (GC):** La asignación de memoria (`B/op`) escala de forma lineal conforme aumenta el volumen de datos en ambos métodos, pero JPA mantiene un consumo menor de memoria asignada en volúmenes bajos y medianos.

---

## 2. Plan de Análisis Estadístico
- **Métricas:** Media aritmética del tiempo de operación (`ms/op`), percentiles clave (p50, p90, p95, p99) para evaluar estabilidad de colas de latencia, y tasa normalizada de asignación de memoria (`B/op`).
- **Prueba de Hipótesis:** Prueba t de Student para muestras independientes con un nivel de significancia $\alpha = 0.05$ para comprobar si la diferencia de rendimiento entre JPA y SQL Nativo es estadísticamente significativa.

---

## 3. Amenazas a la Validez
- **Validez Interna:**
  - El benchmark incluyó 5 iteraciones de precalentamiento (warmup) de 10 segundos para mitigar la optimización en tiempo de ejecución de la máquina virtual (JIT).
  - Presencia del agente de depuración del IDE (`idea_rt.jar`) durante la toma de métricas, lo que añade sobrecarga al entorno de prueba.
- **Validez Externa:**
  - Las mediciones se realizaron en un entorno monohilo (`threads: 1`, `forks: 1`) bajo Windows con OpenJDK 21. El comportamiento puede variar bajo alta concurrencia real o en contenedores Linux.
- **Validez de Construcción:**
  - Se midió el costo computacional de la consulta a nivel de aplicación aislada, sin contemplar la latencia de red entre cliente HTTP y servidor web.
