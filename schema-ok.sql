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


INSERT INTO role (role_id, description, name) VALUES (1, 'Manager with all permissions', 'ADMIN');
INSERT INTO role (role_id, description, name) VALUES (2, 'Manager with some permissions', 'EMPLOYEE');
INSERT INTO role (role_id, description, name) VALUES (3, 'User with simple permissions', 'USER');

-- EL TOKEN ES MUY LARGO
ALTER TABLE token MODIFY COLUMN token VARCHAR(512);

-- LO QUE DEVUELVE TOKEN
-- {
--   "roles": "USER,ADMIN,EMPLOYEE",
--   "username": "angelElVergas7",
--   "sub": "7pruebaAngel@gmail.com",
--   "iat": 1733083410,
--   "exp": 1733688210
-- }






--- ESQUE OK
 CREATE TABLE `appointment` (
  `appointment_id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `init_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`appointment_id`),
  KEY `FK8d468o9ib2yp7a2i75muy221i` (`customer_id`),
  KEY `FKet3j9n9rcienfvvmaqfqqngaa` (`employee_id`),
  CONSTRAINT `FK8d468o9ib2yp7a2i75muy221i` FOREIGN KEY (`customer_id`) REFERENCES `person` (`person_id`),
  CONSTRAINT `FKet3j9n9rcienfvvmaqfqqngaa` FOREIGN KEY (`employee_id`) REFERENCES `person` (`person_id`)
)

CREATE TABLE `customer` (
  `send_notification_email` bit(1) NOT NULL,
  `send_notification_phone_number` bit(1) NOT NULL,
  `customer_id` bigint NOT NULL,
  PRIMARY KEY (`customer_id`),
  CONSTRAINT `FKqw58igk7s2kunxvf7ns62ktu2` FOREIGN KEY (`customer_id`) REFERENCES `person` (`person_id`)
) 

CREATE TABLE `employee` (
  `dni` varchar(255) DEFAULT NULL,
  `employee_id` bigint NOT NULL,
  PRIMARY KEY (`employee_id`),
  CONSTRAINT `FKry8nv6j6upshdj0s6g0mrbsp1` FOREIGN KEY (`employee_id`) REFERENCES `person` (`person_id`)
)

CREATE TABLE `person` (
  `dtype` varchar(31) NOT NULL,
  `person_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_last_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `second_last_name` varchar(255) DEFAULT NULL,
  `second_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`person_id`)
)

 CREATE TABLE `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
)

CREATE TABLE `token` (
  `token_id` bigint NOT NULL,
  `is_expired` bit(1) NOT NULL,
  `is_revoked` bit(1) NOT NULL,
  `token` varchar(512) DEFAULT NULL,
  `token_type` enum('BEARER') DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`token_id`),
  UNIQUE KEY `UKpddrhgwxnms2aceeku9s2ewy5` (`token`),
  KEY `FKe32ek7ixanakfqsdaokm4q9y2` (`user_id`),
  CONSTRAINT `FKe32ek7ixanakfqsdaokm4q9y2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
)

CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
)

 CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
)