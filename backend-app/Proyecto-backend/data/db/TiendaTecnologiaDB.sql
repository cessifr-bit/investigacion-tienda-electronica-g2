-- =========================================================================
-- Base de Datos: TiendaTecnologicaDB2
-- Proyecto: investigacion-tienda-electronica-g2 (Android + Maven Benchmark)
-- =========================================================================

DROP DATABASE IF EXISTS TiendaTecnologicaDB2;
CREATE DATABASE TiendaTecnologicaDB2 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE TiendaTecnologicaDB2;

-- -------------------------------------------------------------------------
-- 1. Tabla: categorias
-- Soporta la clasificación en Catalogo.kt y filtros de búsqueda
-- -------------------------------------------------------------------------
CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion VARCHAR(255),
    activo TINYINT(1) DEFAULT 1,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- -------------------------------------------------------------------------
-- 2. Tabla: marcas
-- Fabricantes de los artículos electrónicos
-- -------------------------------------------------------------------------
CREATE TABLE marcas (
    id_marca INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    pais_origen VARCHAR(80),
    activo TINYINT(1) DEFAULT 1
) ENGINE=InnoDB;

-- -------------------------------------------------------------------------
-- 3. Tabla Principal: productos
-- Mapeada directamente para modelo/Producto.java, ConsultaJPA.java, 
-- ConsultaNativa.java y las operaciones de Crud.kt en la app Android.
-- -------------------------------------------------------------------------
CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    codigo_sku VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL CHECK (precio >= 0),
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    imagen_url VARCHAR(500),
    id_categoria INT,
    id_marca INT,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    ultima_actualizacion DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_producto_categoria FOREIGN KEY (id_categoria) 
        REFERENCES categorias(id_categoria) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_producto_marca FOREIGN KEY (id_marca) 
        REFERENCES marcas(id_marca) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Índices para optimizar las pruebas comparativas de rendimiento (JDBC vs JPA)
CREATE INDEX idx_producto_nombre ON productos(nombre);
CREATE INDEX idx_producto_categoria ON productos(id_categoria);
CREATE INDEX idx_producto_precio ON productos(precio);

-- -------------------------------------------------------------------------
-- 4. Tabla: clientes / usuarios
-- Para gestión de acceso y compras desde la app móvil
-- -------------------------------------------------------------------------
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(150) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(255),
    rol ENUM('ADMIN', 'CLIENTE') DEFAULT 'CLIENTE',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- -------------------------------------------------------------------------
-- 5. Tabla: pedidos
-- Cabecera de transacciones de la tienda
-- -------------------------------------------------------------------------
CREATE TABLE pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    estado ENUM('PENDIENTE', 'PAGADO', 'ENVIADO', 'ENTREGADO', 'CANCELADO') DEFAULT 'PENDIENTE',
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (id_usuario) 
        REFERENCES usuarios(id_usuario) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -------------------------------------------------------------------------
-- 6. Tabla: detalle_pedidos
-- Desglose de productos por pedido
-- -------------------------------------------------------------------------
CREATE TABLE detalle_pedidos (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(12, 2) GENERATED ALWAYS AS (cantidad * precio_unitario) STORED,
    CONSTRAINT fk_detalle_pedido FOREIGN KEY (id_pedido) 
        REFERENCES pedidos(id_pedido) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto) 
        REFERENCES productos(id_producto) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- =========================================================================
-- INSERCIÓN DE DATOS SEMILLA (Para pruebas iniciales y Catálogo Android)
-- =========================================================================

INSERT INTO categorias (nombre, descripcion) VALUES
('Laptops y Computadoras', 'Equipos portátiles, de escritorio y estaciones de trabajo'),
('Smartphones y Tablets', 'Teléfonos móviles inteligentes y tabletas'),
('Componentes PC', 'Procesadores, tarjetas gráficas, memoria RAM y almacenamiento'),
('Periféricos y Audio', 'Monitores, teclados mecánicos, ratones y auriculares'),
('Redes y Conectividad', 'Routers, switches y adaptadores Wi-Fi');

INSERT INTO marcas (nombre, pais_origen) VALUES
('ASUS', 'Taiwán'),
('Samsung', 'Corea del Sur'),
('Logitech', 'Suiza'),
('AMD', 'Estados Unidos'),
('Corsair', 'Estados Unidos'),
('Apple', 'Estados Unidos'),
('Sony', 'Japón');

INSERT INTO productos (codigo_sku, nombre, descripcion, precio, stock, imagen_url, id_categoria, id_marca) VALUES
('LAP-ASU-001', 'ASUS ROG Strix G16', 'Laptop Gaming Intel Core i7 13650HX, 16GB RAM, RTX 4060, 1TB SSD', 1399.99, 15, 'https://cdn.cs.1worldsync.com/syndication/mediaserverredirect/c8446181243b0d84f9cd66a070fe0111/original.jpg', 1, 1),
('SM-SAM-002', 'Samsung Galaxy S24 Ultra', 'Smartphone 5G, 256GB, 12GB RAM, Cámara 200MP, S-Pen incluido', 1199.50, 25, 'https://images.samsung.com/is/image/samsung/assets/latin/smartphones/galaxy-s24-ultra/images/galaxy-s24-ultra-highlights-color-titanium-violet-back-mo.jpg', 2, 2),
('PER-LOG-003', 'Logitech G Pro X Superlight 2', 'Mouse inalámbrico ligero para eSports, sensor HERO 2 32K DPI', 159.00, 40, 'https://www.logitechstore.com.co/cdn/shop/files/0_6e9dd1d3-bec6-4705-9189-bf506402a05c.jpg', 4, 3),
('COM-AMD-004', 'AMD Ryzen 7 7800X3D', 'Procesador de 8 núcleos y 16 hilos con tecnología 3D V-Cache, Socket AM5', 399.99, 30, 'https://www.amd.com/content/dam/amd/en/images/products/processors/ryzen/2505503-ryzen-7-7800x3d-og.jpg', 3, 4),
('COM-COR-005', 'Corsair Vengeance RGB 32GB (2x16GB)', 'Memoria RAM DDR5 6000MHz CL30 optimizada para AMD Expo / Intel XMP', 114.99, 50, 'https://m.media-amazon.com/images/I/61D2DDpDITL.jpg', 3, 5),
('COM-SAM-006', 'Samsung 990 PRO 2TB NVMe', 'Unidad de estado sólido M.2 PCIe 4.0 hasta 7450 MB/s', 179.99, 35, 'https://images.samsung.com/is/image/samsung/p6pim/us/mz-v9p2t0b-am/gallery/us-990pro-nvme-m2-ssd-mz-v9p2t0b-am-551141318?$product-details-jpg$', 3, 2),
('PER-SON-007', 'Sony WH-1000XM5', 'Auriculares inalámbricos Bluetooth con cancelación de ruido líder', 348.00, 20, 'https://buketomnisportpweb.s3.us-east-2.amazonaws.com/seo/Bb57BdHFX3TyPcJJGmdWNWa5gpkhrCrN584tMvif.jpeg', 4, 7),
('TAB-APP-008', 'iPad Air M2 11 pulgadas', 'Tablet con chip Apple M2, 128GB Wi-Fi, pantalla Liquid Retina', 599.00, 18, 'https://macnificos.com/cdn/shop/files/MUWD3TY_A-rossellimac-productos-macnificos.jpg', 2, 6);

INSERT INTO usuarios (nombre_completo, email, password_hash, telefono, direccion, rol) VALUES
('Administrador Tienda', 'admin@tiendatecnologica.com', '$2a$10$EjemploHashSeguroAdmin123456789', '7000-0001', 'San Salvador, El Salvador', 'ADMIN'),
('Carlos Mendoza', 'carlos.mendoza@email.com', '$2a$10$EjemploHashSeguroCliente98765432', '7111-2233', 'Santa Tecla, La Libertad', 'CLIENTE');
