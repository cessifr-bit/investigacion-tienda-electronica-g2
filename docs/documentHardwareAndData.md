#Hardware and data

Para el desarrollo se necesitará de algunas Especificaciones del Hardware para una mayor optimización y funcionamiento:

+Procesador : Intel Core i7 o AMD Ryzen 7 (8+ núcleos / 16+ hilos) La ejecución en paralelo de instancias de base de datos y un IDE pesado requiere multihilo
constante. IntelliJ IDEA delega el indexado de código, análisis sintáctico y compilación a hilos separados. A su vez, los motores de bases de datos asignan 
hilos independientes para gestionar conexiones concurrentes y consultas pesadas sin bloqueos.
+Memoria RAM:  8 GB / 16 GB Permiten ejecutar un motor de base de datos local y un entorno ligero, pero genera intercambio de disco si SQL Server
y un IDE moderno operan simultáneamente ya que distribuye el consumo de forma eficiente para evitar cuellos de botella.
+Almacenamiento: SSD NVMe o un SSD SATA, ya que los gestores de bases de datos (SQL Server y MySQL) escriben constantemente en archivos de transacciones 
y leen bloques de datos fragmentados. Un HDD mecánico genera latencias de hasta 15-20 ms por operación, ralentizando la ejecución. 
+Entorno de Ejecución

Para la parte de los datos el volumen dependera de la definición de los escenarios de carga para medir la escalabilidad que se adaptara segun los
registros de productos de la tienda electríonica donde contara con atributos como ID, Nombre, Descripción, Precio, Stock por mencionar algunos de ellos.
