drop database if exists remax;
create database remax;
use remax;

-- 1. Usuarios
create table Usuarios (
    id_usuario int auto_increment primary key,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    tag varchar(50) not null unique,
    correo varchar(100) not null unique,
    upassword varchar(255) not null,
    dni varchar(20) not null unique
);

-- 2. Roles
create table Roles (
    id_rol int auto_increment primary key,
    nombre varchar(50) not null,
    descripcion varchar(255)
);

-- 3. Permisos
create table Permisos (
    id_permiso int auto_increment primary key,
    nombre varchar(100) not null,
    descripcion varchar(255)
);

-- 4. RelacionRoles
create table RelacionRoles (
    id_relrol int auto_increment primary key,
    id_usuario int not null,
    id_rol int not null,
    foreign key (id_usuario) references Usuarios(id_usuario),
    foreign key (id_rol) references Roles(id_rol)
);

-- 5. RelacionPermisos
create table RelacionPermisos (
    id_relpermiso int auto_increment primary key,
    id_rol int not null,
    id_permiso int not null,
    foreign key (id_rol) references Roles(id_rol),
    foreign key (id_permiso) references Permisos(id_permiso)
);

-- 6. SoliFranquicias
create table SoliFranquicias (
    id_solicitud int auto_increment primary key,
    id_usuario int not null,
    fecha date not null,
    estado varchar(50) not null,
    observaciones varchar(255),
    foreign key (id_usuario) references Usuarios(id_usuario)
);

-- 7. Franquicias
create table Franquicias (
    id_franquicia int auto_increment primary key,
    id_solicitud int not null,
    nombre varchar(100) not null,
    direccion varchar(255) not null,
    estado varchar(50) not null,
    foreign key (id_solicitud) references SoliFranquicias(id_solicitud)
);

-- 8. Personales
create table Personales (
    id_personal int auto_increment primary key,
    id_usuario int not null,
    id_franquicia int,
    foreign key (id_usuario) references Usuarios(id_usuario),
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
    observaciones varchar(255),
    foreign key (id_operador) references Usuarios(id_usuario),
    foreign key (id_propietario) references Usuarios(id_usuario)
);

-- 10. Inmuebles
create table Inmuebles (
    id_inmueble int auto_increment primary key,
    id_captacion int not null,
    id_propietario int not null,
    direccion varchar(255) not null,
    tipo_inmueble varchar(50) not null,
    superficie_cubierta decimal(10,2),
    superficie_total decimal(10,2),
    cantidad_habitaciones int,
    cantidad_banos int,
    cantidad_cocheras int,
    antiguedad_estado varchar(100),
    localidad varchar(100) not null,
    foreign key (id_captacion) references Captaciones(id_captacion),
    foreign key (id_propietario) references Usuarios(id_usuario)
);

-- 11. Caracteristicas
create table Caracteristicas (
    id_caracteristica int auto_increment primary key,
    categoria varchar(50) not null,
    nombre varchar(100) not null unique
);

-- 12. Inmueble_Caracteristicas
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
    observaciones varchar(255),
    foreign key (id_inmueble) references Inmuebles(id_inmueble),
    foreign key (id_usuario) references Usuarios(id_usuario)
);

-- 14. Multimedia
create table Multimedia (
    id_multimedia int auto_increment primary key,
    id_inmueble int not null,
    tipo varchar(50) not null,
    url varchar(500) not null,
    foreign key (id_inmueble) references Inmuebles(id_inmueble)
);

-- 15. Tasaciones
create table Tasaciones (
    id_tasacion int auto_increment primary key,
    id_inmueble int not null,
    id_encargado int not null,
    valor_estimado decimal(15,2) not null,
    valor_final decimal(15,2),
    fecha date not null,
    estado varchar(50) not null,
    foreign key (id_inmueble) references Inmuebles(id_inmueble),
    foreign key (id_encargado) references Usuarios(id_usuario)
);

-- 16. Publicaciones
create table Publicaciones (
    id_publicacion int auto_increment primary key,
    id_inmueble int not null,
    id_agente int not null,
    fecha date not null,
    foreign key (id_inmueble) references Inmuebles(id_inmueble),
    foreign key (id_agente) references Usuarios(id_usuario)
);

-- 17. Conversaciones
create table Conversaciones (
    id_conversacion int auto_increment primary key,
    id_inmueble int,
    estado varchar(50) not null,
    foreign key (id_inmueble) references Inmuebles(id_inmueble)
);

-- 18. Mensajes
create table Mensajes (
    id_mensaje int auto_increment primary key,
    id_conversacion int not null,
    id_usuario int not null,
    fecha_hora datetime not null,
    contenido text not null,
    estado varchar(50),
    foreign key (id_conversacion) references Conversaciones(id_conversacion),
    foreign key (id_usuario) references Usuarios(id_usuario)
);

-- 19. Ofertas
create table Ofertas (
    id_oferta int auto_increment primary key,
    id_usuario int not null,
    id_inmueble int not null,
    monto decimal(15,2) not null,
    fecha date not null,
    estado varchar(50) not null,
    foreign key (id_usuario) references Usuarios(id_usuario),
    foreign key (id_inmueble) references Inmuebles(id_inmueble)
);

-- 20. Historial_oferta
create table Historial_oferta (
    id_histoferta int auto_increment primary key,
    id_oferta int not null,
    id_usuario int not null,
    fecha datetime not null,
    accion varchar(100) not null,
    monto decimal(15,2),
    observaciones varchar(255),
    foreign key (id_oferta) references Ofertas(id_oferta),
    foreign key (id_usuario) references Usuarios(id_usuario)
);

-- 21. Operaciones
create table Operaciones (
    id_operacion int auto_increment primary key,
    id_oferta int not null,
    tipo_operacion varchar(50) not null,
    fecha date not null,
    estado varchar(50) not null,
    foreign key (id_oferta) references Ofertas(id_oferta)
);

-- 22. Comisiones
create table Comisiones (
    id_comision int auto_increment primary key,
    id_operacion int not null,
    id_usuario int not null,
    porcentaje decimal(5,2) not null,
    monto decimal(15,2) not null,
    estado varchar(50) not null,
    foreign key (id_operacion) references Operaciones(id_operacion),
    foreign key (id_usuario) references Usuarios(id_usuario)
);

-- 23. Ventas
create table Ventas (
    id_venta int auto_increment primary key,
    id_operacion int not null,
    fecha date not null,
    monto_final decimal(15,2) not null,
    estado varchar(50) not null,
    foreign key (id_operacion) references Operaciones(id_operacion)
);

-- 24. Escribanias
create table Escribanias (
    id_escribania int auto_increment primary key,
    nombre varchar(100) not null,
    telefono varchar(30),
    correo varchar(100)
);

-- 25. Escrituras
create table Escrituras (
    id_escritura int auto_increment primary key,
    id_venta int not null,
    id_escribania int not null,
    fecha date not null,
    estado varchar(50) not null,
    foreign key (id_venta) references Ventas(id_venta),
    foreign key (id_escribania) references Escribanias(id_escribania)
);

-- 26. Alquileres
create table Alquileres (
    id_alquiler int auto_increment primary key,
    id_operacion int not null,
    fecha_inicio date not null,
    fecha_fin date,
    monto decimal(15,2) not null,
    estado varchar(50) not null,
    foreign key (id_operacion) references Operaciones(id_operacion)
);

-- 27. Tiporegistro
create table Tiporegistro (
    id_tipo int auto_increment primary key,
    nombre varchar(50) not null,
    descripcion varchar(255)
);

-- 28. Registrosalquiler
create table Registrosalquiler (
    id_registro int auto_increment primary key,
    id_alquiler int not null,
    id_tipo int not null,
    fecha date not null,
    monto decimal(15,2) not null,
    estado varchar(50) not null,
    observaciones varchar(255),
    foreign key (id_alquiler) references Alquileres(id_alquiler),
    foreign key (id_tipo) references Tiporegistro(id_tipo)
);

-- 29. Auditorias
create table Auditorias (
    id_auditoria int auto_increment primary key,
    id_inmueble int not null,
    id_usuario int not null,
    fecha datetime not null,
    estado varchar(50) not null,
    observaciones varchar(255),
    foreign key (id_inmueble) references Inmuebles(id_inmueble),
    foreign key (id_usuario) references Usuarios(id_usuario)
);
