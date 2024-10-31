-- Crear la base de datos
CREATE DATABASE u243013116_prueba;

-- Usar la base de datos creada
USE u243013116_prueba;

CREATE TABLE empleados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);


CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(15)
);



CREATE TABLE citas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    empleado_id BIGINT NOT NULL,
    inicio DATETIME NOT NULL,
    duracion INT NOT NULL, -- Duración de la cita en minutos (15, 30, 45, 60)
    
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (empleado_id) REFERENCES empleados(id)
);


ALTER TABLE citas
ADD CONSTRAINT unique_cita_empleado_fecha UNIQUE (empleado_id, inicio);


-- Optimización Adicional
-- Índice en la columna inicio:
--     Para mejorar el rendimiento, es recomendable agregar un índice
--     en la columna inicio de la tabla citas, ya que esta columna
--     será frecuentemente consultada.
-- CREATE INDEX idx_inicio ON citas(inicio);