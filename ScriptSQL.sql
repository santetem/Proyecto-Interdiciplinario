DROP DATABASE IF EXISTS remax;
CREATE DATABASE remax;
USE remax;

-- 1. Usuarios
create table Usuarios (
    id_usuario int auto_increment primary key,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    tag varchar(50) not null unique,
    correo varchar(150) not null unique,
    upassword varchar(255) not null,
    dni varchar(20) not null unique
);

-- 2. Roles
create table Roles (
    id_rol int auto_increment primary key,
    nombre varchar(100) not null,
    descripcion varchar(255)
);

-- 3. Permisos
create table Permisos (
    id_permiso int auto_increment primary key,
    nombre varchar(100) not null,
    descripcion varchar(255)
);

-- 4. Relacionroles
create table RelacionRoles (
    id_relrol int auto_increment primary key,
    id_usuario int not null,
    id_rol int not null,
    foreign key (id_usuario) references usuarios(id_usuario),
    foreign key (id_rol) references roles(id_rol)
);

-- 5. Relacionpermisos
create table RelacionPermisos (
    id_relpermiso int auto_increment primary key,
    id_rol int not null,
    id_permiso int not null,
    foreign key (id_rol) references roles(id_rol),
    foreign key (id_permiso) references permisos(id_permiso)
);

-- 6. Solifranquicias
create table SoliFranquicias (
    id_solicitud int auto_increment primary key,
    id_usuario int not null,
    fecha date not null,
    estado varchar(50) not null,
    observaciones text,
    foreign key (id_usuario) references usuarios(id_usuario)
);

-- 7. Franquicias
create table Franquicias (
    id_franquicia int auto_increment primary key,
    id_solicitud int not null,
    nombre varchar(150) not null,
    direccion varchar(255) not null,
    estado varchar(50) not null,
    foreign key (id_solicitud) references SoliFranquicias(id_solicitud)
);

-- 8. Personales
create table Personales (
    id_personal int auto_increment primary key,
    id_usuario int not null,
    id_franquicia int null,
    foreign key (id_usuario) references usuarios(id_usuario),
    foreign key (id_franquicia) references Franquicias(id_franquicia)
);

-- 9. Captaciones
create table Captaciones (
    id_captacion int auto_increment primary key,
    id_operador int not null,
    id_propietario int not null,
    caracteristicas text,
    fecha date not null,
    estado varchar(50) not null,
    observaciones text,
    foreign key (id_operador) references usuarios(id_usuario),
    foreign key (id_propietario) references usuarios(id_usuario)
);

-- 10. Inmuebles
create table Inmuebles (
    id_inmueble int auto_increment primary key,
    id_captacion int not null,
    id_propietario int not null,
    direccion varchar(255) not null,
    tipo_inmueble varchar(100) not null,
    superficie_cubierta decimal(10,2) unsigned not null,
    superficie_total decimal(10,2) unsigned not null,
    cantidad_habitaciones int unsigned default 0,
    cantidad_banos int unsigned default 0,
    cantidad_cocheras int unsigned default 0,
    antiguedad_estado varchar(50) not null,
    foreign key (id_captacion) references Captaciones(id_captacion),
    foreign key (id_propietario) references usuarios(id_usuario)
);

-- 11. Caracteristicas
create table Caracteristicas (
    id_caracteristica int auto_increment primary key,
    categoria varchar(100) not null, 
    nombre varchar(100) not null unique 
);

-- 12. Inmueble_caracteristicas
create table Inmueble_Caracteristicas (
    id_inmueble int not null,
    id_caracteristica int not null,
    primary key (id_inmueble, id_caracteristica),
    foreign key (id_inmueble) references Inmuebles(id_inmueble) on delete cascade,
    foreign key (id_caracteristica) references Caracteristicas(id_caracteristica) on delete cascade
);

-- 13. Visitas
create table Visitas (
    id_visita int auto_increment primary key,
    id_inmueble int not null,
    id_usuario int not null,
    fecha datetime not null,
    estado varchar(50) not null,
    observaciones text,
    foreign key (id_inmueble) references Inmuebles(id_inmueble),
    foreign key (id_usuario) references usuarios(id_usuario)
);

-- 14. Multimedia
create table Multimedia (
    id_multimedia int auto_increment primary key,
    id_inmueble int not null,
    tipo varchar(50) not null,
    url varchar(255),
    foreign key (id_inmueble) references Inmuebles(id_inmueble)
);

-- 15. Tasaciones
create table Tasaciones (
    id_tasacion int auto_increment primary key,
    id_inmueble int not null,
    id_encargado int not null,
    valor_estimado decimal(15,2),
    valor_final decimal(15,2),
    fecha date not null,
    estado varchar(50) not null,
    foreign key (id_inmueble) references Inmuebles(id_inmueble),
    foreign key (id_encargado) references usuarios(id_usuario)
);

-- 16. Publicaciones
create table Publicaciones (
    id_publicacion int auto_increment primary key,
    id_inmueble int not null,
    id_agente int not null,
    fecha datetime not null,
    foreign key (id_inmueble) references Inmuebles(id_inmueble),
    foreign key (id_agente) references usuarios(id_usuario)
);

-- 17. Ofertas
create table Ofertas (
    id_oferta int auto_increment primary key,
    id_usuario int not null,
    id_inmueble int not null,
    monto decimal(15,2) not null,
    fecha datetime not null,
    estado varchar(50) not null,
    foreign key (id_usuario) references usuarios(id_usuario),
    foreign key (id_inmueble) references Inmuebles(id_inmueble)
);

-- 18. Historial_oferta
create table Historial_oferta (
    id_histoferta int auto_increment primary key,
    id_oferta int not null,
    id_usuario int not null,
    fecha datetime not null,
    accion varchar(100) not null,
    monto decimal(15,2),
    observaciones text,
    foreign key (id_oferta) references Ofertas(id_oferta),
    foreign key (id_usuario) references usuarios(id_usuario)
);

-- 19. Operaciones
create table Operaciones (
    id_operacion int auto_increment primary key,
    id_oferta int not null,
    tipo_operacion varchar(50) not null,
    fecha date not null,
    estado varchar(50) not null,
    foreign key (id_oferta) references Ofertas(id_oferta)
);

-- 20. Ventas
create table Ventas (
    id_venta int auto_increment primary key,
    id_operacion int not null,
    fecha date not null,
    monto_final decimal(15,2) not null,
    estado varchar(50) not null,
    foreign key (id_operacion) references Operaciones(id_operacion)
);

-- 21. Escribanias
create table Escribanias (
    id_escribania int auto_increment primary key,
    nombre varchar(150) not null,
    telefono varchar(50),
    correo varchar(150)
);

-- 22. Escrituras
create table Escrituras (
    id_escritura int auto_increment primary key,
    id_venta int not null,
    id_escribania int not null,
    fecha date,
    estado varchar(50) not null,
    foreign key (id_venta) references Ventas(id_venta),
    foreign key (id_escribania) references Escribanias(id_escribania)
);

-- 23. Alquileres
create table Alquileres (
    id_alquiler int auto_increment primary key,
    id_operacion int not null,
    fecha_inicio date not null,
    fecha_fin date,
    monto decimal(15,2) not null,
    estado varchar(50) not null,
    foreign key (id_operacion) references Operaciones(id_operacion)
);

-- 24. Contratos
create table Contratos (
    id_contrato int auto_increment primary key,
    id_alquiler int not null,
    fecha date not null,
    monto_final decimal(15,2),
    estado varchar(50) not null,
    foreign key (id_alquiler) references Alquileres(id_alquiler)
);

-- 25. Cobranzas
create table Cobranzas (
    id_cobranza int auto_increment primary key,
    id_alquiler int not null,
    fecha date not null,
    monto decimal(15,2) not null,
    estado varchar(50) not null,
    foreign key (id_alquiler) references Alquileres(id_alquiler)
);

-- 26. Liquidaciones
create table Liquidaciones (
    id_liquidacion int auto_increment primary key,
    id_alquiler int not null,
    fecha date not null,
    monto decimal(15,2) not null,
    foreign key (id_alquiler) references Alquileres(id_alquiler)
);

-- 27. Auditorias
create table Auditorias (
    id_auditoria int auto_increment primary key,
    id_inmueble int not null,
    id_usuario int not null,
    fecha datetime not null,
    estado varchar(50) not null,
    observaciones text,
    foreign key (id_inmueble) references Inmuebles(id_inmueble),
    foreign key (id_usuario) references usuarios(id_usuario)
);


DELIMITER //

-- 1. Consultar todos los registros de cualquier tabla
CREATE PROCEDURE sp_ObtenerTodos(
    IN p_tabla VARCHAR(64)
)
BEGIN
    SET @sql = CONCAT('SELECT * FROM ', p_tabla);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END //

-- 2. Consultar un registro por ID en cualquier tabla
CREATE PROCEDURE sp_ObtenerPorId(
    IN p_tabla VARCHAR(64),
    IN p_campo_id VARCHAR(64),
    IN p_id INT
)
BEGIN
    SET @sql = CONCAT('SELECT * FROM ', p_tabla, ' WHERE ', p_campo_id, ' = ?');
    PREPARE stmt FROM @sql;
    SET @id_val = p_id;
    EXECUTE stmt USING @id_val;
    DEALLOCATE PREPARE stmt;
END //

-- 3. Eliminar un registro por ID en cualquier tabla
CREATE PROCEDURE sp_EliminarPorId(
    IN p_tabla VARCHAR(64),
    IN p_campo_id VARCHAR(64),
    IN p_id INT
)
BEGIN
    SET @sql = CONCAT('DELETE FROM ', p_tabla, ' WHERE ', p_campo_id, ' = ?');
    PREPARE stmt FROM @sql;
    SET @id_val = p_id;
    EXECUTE stmt USING @id_val;
    DEALLOCATE PREPARE stmt;
END //

-- 4. Contar la cantidad total de registros de cualquier tabla
CREATE PROCEDURE sp_ContarRegistros(
    IN p_tabla VARCHAR(64)
)
BEGIN
    SET @sql = CONCAT('SELECT COUNT(*) AS total_registros FROM ', p_tabla);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END //

DELIMITER ;
