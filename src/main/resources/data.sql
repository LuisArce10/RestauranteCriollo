-- 1. Eliminar y crear la base de datos
DROP DATABASE IF EXISTS bd_restaurante2;
CREATE DATABASE bd_restaurante2;
USE bd_restaurante2;

-- 2. Tabla de roles
CREATE TABLE rol (
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     nombre VARCHAR(50) NOT NULL
);

-- 3. Tabla de usuarios
CREATE TABLE usuario (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         rol_id INT NOT NULL,
                         email VARCHAR(100) NOT NULL,
                         password VARCHAR(100) NOT NULL,
                         nombre VARCHAR(255) NOT NULL,
                         UNIQUE (email),
                         CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id) REFERENCES rol(id)
);

-- 4. Tabla de categorías de platos
CREATE TABLE categoria_plato (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 nombre VARCHAR(100) NOT NULL
);

-- 5. Tabla de platos
CREATE TABLE plato (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       nombre VARCHAR(100) NOT NULL,
                       descripcion TEXT,
                       precio DECIMAL(10,2) NOT NULL,
                       categoria_plato_id INT NOT NULL,
                       url_imagen VARCHAR(255),
                       estado ENUM('ACTIVO', 'INACTIVO') NOT NULL DEFAULT 'ACTIVO', -- AGREGADO: columna estado requerida por la entidad Java
                       CONSTRAINT fk_plato_categoria FOREIGN KEY (categoria_plato_id) REFERENCES categoria_plato(id)
);

-- 6. Tabla de mesas
CREATE TABLE mesa (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      numero INT NOT NULL,
                      estado ENUM('LIBRE', 'OCUPADA') NOT NULL DEFAULT 'LIBRE',
                      UNIQUE (numero)
);

-- 7. Tabla de pedidos
CREATE TABLE pedido (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        mozo_id INT NOT NULL,
                        mesa_id INT NOT NULL,
                        fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        estado ENUM('PENDIENTE', 'EN_PROCESO', 'LISTO', 'PAGADO') NOT NULL DEFAULT 'PENDIENTE',
                        CONSTRAINT fk_pedido_mozo FOREIGN KEY (mozo_id) REFERENCES usuario(id),
                        CONSTRAINT fk_pedido_mesa FOREIGN KEY (mesa_id) REFERENCES mesa(id)
);

-- 8. Tabla de detalle de pedido
CREATE TABLE detalle_pedido (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                pedido_id INT NOT NULL,
                                plato_id INT NOT NULL,
                                cantidad INT NOT NULL,
                                estado ENUM('RECIBIDO', 'EN_PROCESO', 'LISTO') NOT NULL DEFAULT 'RECIBIDO',
                                CONSTRAINT fk_detalle_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id),
                                CONSTRAINT fk_detalle_pedido_plato FOREIGN KEY (plato_id) REFERENCES plato(id)
);

-- 9. Tabla de pagos
CREATE TABLE pago (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      pedido_id INT NOT NULL,
                      nombre_cliente VARCHAR(100) NOT NULL,
                      dni_ruc VARCHAR(12) NOT NULL,
                      tipo_documento ENUM('BOLETA', 'FACTURA') NOT NULL,
                      numero_documento VARCHAR(100) NOT NULL,
                      fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      monto_total DECIMAL(10,2) NOT NULL,
                      UNIQUE (numero_documento),
                      CONSTRAINT fk_pago_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id)
);

-- 10. Datos iniciales

-- Roles
INSERT INTO rol (nombre) VALUES
                             ('ADMINISTRADOR'),
                             ('MOZO'),
                             ('COCINERO');

-- Usuarios
INSERT INTO usuario (rol_id, email, password, nombre) VALUES
                                                          (1, 'admin@restaurante.com',  '$2a$12$8aJXsva7DuuEtDQ/7mQeV.Za5UZkDuiT7VKguXAPBGVfsVLrwCOXC', 'Administrador General'), -- admin123
                                                          (2, 'mozo1@restaurante.com',  '$2a$12$IM4z64PAsNblBclst7z90uzRiFMp6JOwFd2inCf6mweAe8ADGaJ22', 'Luis Pérez'), -- mozo123
                                                          (2, 'mozo2@restaurante.com',  '$2a$12$IM4z64PAsNblBclst7z90uzRiFMp6JOwFd2inCf6mweAe8ADGaJ22', 'María Torres'), -- mozo123
                                                          (3, 'cocina1@restaurante.com','$2a$12$GjuuiEWYeHMJylMwmdefS.PGNrn6OrLDLe3k/gPHbh.9hsnw7CYgu', 'Carlos Sánchez'); -- cocina123

-- Categorías de platos
INSERT INTO categoria_plato (nombre) VALUES
                                         ('Entradas'),
                                         ('Platos de Fondo'),
                                         ('Bebidas'),
                                         ('Postres');

-- Platos
INSERT INTO plato (nombre, descripcion, precio, categoria_plato_id, url_imagen, estado) VALUES
                                                                                            ('Ceviche Clásico', 'Pescado fresco marinado en limón con cebolla y ají', 25.00, 1, 'https://cdn.sanity.io/images/2t27tguw/production/10d0183ff9b2365f4f4650334ef8c0f4d10eab00-730x442.jpg', 'ACTIVO'),
                                                                                            ('Lomo Saltado', 'Carne salteada con cebolla, tomate y papas fritas', 28.00, 2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7txGAv10KVNS3dfGVPNtKlGWyU4D6DincTSuqCqdPrBO5aDeWf6ND4FNo&s=10', 'ACTIVO'),
                                                                                            ('Ají de Gallina', 'Guiso cremoso de ají amarillo con pollo deshilachado', 22.00, 2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIV4N1ciE6P780HCIkl5sye1P9LmDKFHs4-mrxEcUngUIVLoGvJhEXFNoP&s=10', 'ACTIVO'),
                                                                                            ('Chicha Morada', 'Bebida tradicional a base de maíz morado', 5.00, 3, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdL-jOTOjr2s6J82dF488Svi7KvkQPdYwW26Zr1pasWDDvHp2SLAtLCkEE&s=10', 'ACTIVO'),
                                                                                            ('Inka Kola', 'Gaseosa peruana sabor a hierba luisa', 4.00, 3, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdwjujzUnndhACg_UiY5UYpSa40D0lPKHPjeP4FOcSND5Oo1vRdnuKAC8', 'INACTIVO'),
                                                                                            ('Suspiro a la Limeña', 'Postre típico limeño con manjar blanco y merengue', 10.00, 4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSthy-R_J_Y4ugvFY3qxQT0LVwPi9f0eKtykVAjvWIya1TcddXqPrckfUc', 'ACTIVO');

-- Mesas
INSERT INTO mesa (numero, estado) VALUES
                                      (1, 'LIBRE'),
                                      (2, 'OCUPADA'),
                                      (3, 'LIBRE'),
                                      (4, 'OCUPADA');

-- Pedidos
INSERT INTO pedido (mozo_id, mesa_id, estado)
VALUES (2, 2, 'PENDIENTE'),
       (3, 4, 'EN_PROCESO');

-- Detalles de pedido
INSERT INTO detalle_pedido (pedido_id, plato_id, cantidad, estado)
VALUES (1, 1, 2, 'RECIBIDO'),
       (1, 4, 2, 'RECIBIDO'),
       (2, 2, 1, 'EN_PROCESO'),
       (2, 5, 1, 'RECIBIDO');

-- Pagos
INSERT INTO pago (pedido_id, nombre_cliente, dni_ruc, tipo_documento, numero_documento, monto_total)
VALUES (1, 'Juan Ramírez', '12345678', 'BOLETA', 'B001-000001', 60.00);
