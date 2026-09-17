#Riesgos técnico e identificación de problemas

Degradación de Datos bajo Carga: 
A medida que la tabla escala las consultas SELECT sin índices o filtro van a provocar un alto consumo de CPU, agotamiento de la memoria caché y problemas graves 
para el usuario que lo esté manipulando.  

Escrituras compleja: 
Las operaciones INSERT, UPDATE y DELETE, elevan el nivel de la escritura y el bloqueo de las tablas en cuestion a medida que suba se puede ir haciendo un
espagueti.

Bloqueos:
Las actualizaciones frecuentes sobre el inventario o los precios pueden causar bloqueos de transacciones, bloqueos mutuos (deadlocks) o bloqueos a nivel 
de página en motores como InnoDB o SQL Server, deteniendo las peticiones del usuario.  

Distorcion en la memoria cache: 
Ejecutar pruebas de rendimiento repetidamente sin limpiar la caché de datos genera resultados falsos positivos, ya que los datos se leen directamente 
desde la memoria RAM y no desde el disco. 

Estrategias de MitigaciónEstrategia de Indexación Optimizada:
Implementar índices compuestos orientados específicamente a los filtros de consulta más frecuentes (como CategoriaId y Precio), en lugar de indexar 
cada columna de la tabla. 

Operaciones en bloques:
Ejecutar tareas masivas de INSERT o DELETE en bloques pequeños para minimizar la sobrecarga de las transacciones y los bloqueos de tabla por asi decirlo es una 
manera de la cual puede ocasionar riesgos técnicos siendo un posible problema de sobrecarga de operaciones por ende se busca dejar lo mas completo posible para 
la tienda electrónica.
 
