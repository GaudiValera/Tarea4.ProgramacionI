CREATE DATABASE IF NOT EXISTS login_app_db;
USE login_app_db;


CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,           
    nombre VARCHAR(50) NOT NULL,                
    apellido VARCHAR(50) NOT NULL,              
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL, 
    telefono VARCHAR(15),                       
    correo VARCHAR(100) UNIQUE NOT NULL,        
    contrasena VARCHAR(255) NOT NULL,           
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
);

commit;

select * from usuarios;

INSERT INTO usuarios (nombre, apellido, nombre_usuario, telefono, correo, contrasena, fecha_registro)
VALUES 
    ('Ana', 'Lopez', 'anaEstudiante', '8291234567', 'lopez.ana12@gmail.com', '5678', '2025-04-01 19:10:30'),
    ('Carlos', 'Martinez', 'carlos05', '8099876543', 'martinez05carlos@gmail.com', 'carlos', '2025-04-01 19:12:45'),
    ('Sofia', 'Gomez', 'sofiaEstudiante', '8495551234', 'gomez.sofia9@gmail.com', '9012', '2025-04-01 19:15:00'),
    ('Luis', 'Rodriguez', 'luis07', '8294445678', 'rodriguez07luis@gmail.com', 'luis', '2025-04-01 19:17:20'),
    ('Maria', 'Perez', 'mariaEstudiante', '8093339876', 'perez.maria15@gmail.com', '3456', '2025-04-01 19:20:10');
