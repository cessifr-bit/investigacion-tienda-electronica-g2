-- =====================================================================
-- Base de datos del experimento (UNA sola base): TiendaTecnologiaDB
--
-- La tabla Productos se llena con 100.000 registros (IdProducto 1..100000).
-- Los niveles del Factor B (volumen de registros) se obtienen con la
-- consulta, sin crear otras bases ni tablas:
--     Nivel   1.000  ->  WHERE IdProducto <= 1000
--     Nivel  10.000  ->  WHERE IdProducto <= 10000
--     Nivel 100.000  ->  WHERE IdProducto <= 100000
--
-- Los datos se generan de forma DETERMINISTA (sin RAND): todos los
-- integrantes obtienen exactamente los mismos registros.
--
-- Requisitos: MySQL 8.0 o superior. Ejecutar en UTF-8:
--   mysql --default-character-set=utf8mb4 -u root -p < crear_base_experimento.sql
--
-- Si usas el usuario 'tiendaelectrica', darle permisos (como root):
--   GRANT ALL ON TiendaTecnologiaDB.* TO 'tiendaelectrica'@'localhost';
-- =====================================================================

SET NAMES utf8mb4;
SET SESSION cte_max_recursion_depth = 200000;

CREATE DATABASE IF NOT EXISTS TiendaTecnologiaDB CHARACTER SET utf8mb4;
USE TiendaTecnologiaDB;

-- Se eliminan en orden para respetar las restricciones de clave foránea
DROP TABLE IF EXISTS Vendedores;
DROP TABLE IF EXISTS Usuarios;
DROP TABLE IF EXISTS Productos;

CREATE TABLE Usuarios (
    IdUsuario INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Correo VARCHAR(150) NOT NULL UNIQUE,
    ContrasenaHash VARCHAR(255) NOT NULL,
    Rol VARCHAR(20) NOT NULL,
    FechaRegistro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Activo TINYINT(1) NOT NULL DEFAULT 1,
    CONSTRAINT CK_Usuarios_Rol CHECK (Rol IN ('Administrador', 'Vendedor', 'Cliente'))
);

CREATE TABLE Vendedores (
    IdVendedor INT AUTO_INCREMENT PRIMARY KEY,
    IdUsuario INT NOT NULL UNIQUE,
    CodigoVendedor VARCHAR(20) NOT NULL UNIQUE,
    Telefono VARCHAR(25) NULL,
    FechaContratacion DATE NULL,
    Activo TINYINT(1) NOT NULL DEFAULT 1,
    CONSTRAINT FK_Vendedores_Usuarios FOREIGN KEY (IdUsuario) REFERENCES Usuarios(IdUsuario)
);

-- Sin indices en Categoria/Marca a proposito: el tiempo de respuesta
-- depende del volumen de registros que la consulta tiene que revisar.
CREATE TABLE Productos (
    IdProducto INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(150) NOT NULL,
    Marca VARCHAR(80) NOT NULL,
    Categoria VARCHAR(80) NOT NULL,
    CantidadDisponible INT NOT NULL DEFAULT 0,
    CONSTRAINT CK_Productos_Cantidad CHECK (CantidadDisponible >= 0)
);

-- Usuarios de ejemplo
INSERT INTO Usuarios (Nombre, Correo, ContrasenaHash, Rol) VALUES
('Administrador Demo', 'admin@tiendatecnologia.local', 'REEMPLAZAR_POR_HASH_SEGURO', 'Administrador'),
('Vendedor Demo', 'vendedor@tiendatecnologia.local', 'REEMPLAZAR_POR_HASH_SEGURO', 'Vendedor'),
('Cliente Demo', 'cliente@tiendatecnologia.local', 'REEMPLAZAR_POR_HASH_SEGURO', 'Cliente');

INSERT INTO Vendedores (IdUsuario, CodigoVendedor, Telefono, FechaContratacion)
VALUES (2, 'VEN-0001', '0000-0000', CURRENT_DATE);

-- Generacion determinista de 100.000 productos (10 categorias, 8 marcas).
-- IdProducto = n, asi los niveles 1k y 10k son los primeros registros.
INSERT INTO Productos (IdProducto, Nombre, Marca, Categoria, CantidadDisponible)
WITH RECURSIVE seq AS (
    SELECT 1 AS n
    UNION ALL
    SELECT n + 1 FROM seq WHERE n < 100000
)
SELECT
    n AS IdProducto,
    CONCAT(
        CASE MOD(n - 1, 10) + 1
            WHEN 1  THEN ELT(MOD(DIV(n - 1, 10), 4) + 1, 'Laptop', 'PC de escritorio', 'All in One', 'Mini PC')
            WHEN 2  THEN ELT(MOD(DIV(n - 1, 10), 4) + 1, 'Procesador', 'Tarjeta gráfica', 'Memoria RAM', 'Placa madre')
            WHEN 3  THEN ELT(MOD(DIV(n - 1, 10), 4) + 1, 'Teclado', 'Mouse', 'Webcam', 'Mousepad')
            WHEN 4  THEN ELT(MOD(DIV(n - 1, 10), 4) + 1, 'Monitor 22 pulgadas', 'Monitor 24 pulgadas', 'Monitor 27 pulgadas', 'Monitor 32 pulgadas')
            WHEN 5  THEN ELT(MOD(DIV(n - 1, 10), 4) + 1, 'SSD SATA', 'SSD NVMe', 'Disco duro interno', 'Disco duro externo')
            WHEN 6  THEN ELT(MOD(DIV(n - 1, 10), 4) + 1, 'Router Wi-Fi', 'Switch de red', 'Repetidor Wi-Fi', 'Access Point')
            WHEN 7  THEN ELT(MOD(DIV(n - 1, 10), 4) + 1, 'Impresora multifuncional', 'Impresora láser', 'Escáner', 'Plotter')
            WHEN 8  THEN ELT(MOD(DIV(n - 1, 10), 4) + 1, 'Audífonos', 'Auriculares inalámbricos', 'Parlante', 'Micrófono')
            WHEN 9  THEN ELT(MOD(DIV(n - 1, 10), 4) + 1, 'Smartphone', 'Tablet', 'Lector electrónico', 'Smartwatch')
            ELSE         ELT(MOD(DIV(n - 1, 10), 4) + 1, 'Cargador USB-C', 'Cable USB-C', 'Funda protectora', 'Hub USB')
        END,
        ' ',
        ELT(MOD(n, 6) + 1, 'Básico', 'Pro', 'Plus', 'Ultra', 'Premium', 'Essential'),
        ' ',
        n
    ) AS Nombre,
    ELT(MOD(n * 3 + MOD(n - 1, 10), 8) + 1,
        'Dell', 'HP', 'Logitech', 'Samsung', 'Crucial', 'TP-Link', 'ASUS', 'Kingston') AS Marca,
    ELT(MOD(n - 1, 10) + 1,
        'Computadoras', 'Componentes', 'Perifericos', 'Monitores', 'Almacenamiento',
        'Redes', 'Impresion', 'Audio y video', 'Telefonos y tabletas', 'Accesorios') AS Categoria,
    MOD(n * 37, 251) AS CantidadDisponible
FROM seq;

-- Comprobacion de los tres niveles (debe dar 1000 / 10000 / 100000)
SELECT SUM(IdProducto <= 1000)  AS nivel_1k,
       SUM(IdProducto <= 10000) AS nivel_10k,
       COUNT(*)                 AS nivel_100k
FROM Productos;

-- Filas que devolvera la consulta del experimento en cada nivel
-- (categoria 'Computadoras': debe dar 100 / 1000 / 10000)
SELECT SUM(IdProducto <= 1000)  AS filas_1k,
       SUM(IdProducto <= 10000) AS filas_10k,
       COUNT(*)                 AS filas_100k
FROM Productos WHERE Categoria = 'Computadoras';
