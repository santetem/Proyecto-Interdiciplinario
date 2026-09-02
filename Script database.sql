drop database if exists remax;
create database remax;
use remax;

create table usuarios (
    id_usuario int auto_increment primary key,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    tag varchar(50) not null unique,
    correo varchar(150) not null unique,
    upassword varchar(255) not null,
    dni varchar(20) not null unique
);

create table roles (
    id_rol int auto_increment primary key,
    nombre varchar(100) not null,
    descripcion varchar(255)
);

create table permisos (
    id_permiso int auto_increment primary key,
    nombre varchar(100) not null,
    descripcion varchar(255)
);

create table RelacionRoles (
    id_relrol int auto_increment primary key,
    id_usuario int not null,
    id_rol int not null,
    foreign key (id_usuario) references usuarios(id_usuario),
    foreign key (id_rol) references roles(id_rol)
);

create table RelacionPermisos (
    id_relpermiso int auto_increment primary key,
    id_rol int not null,
    id_permiso int not null,
    foreign key (id_rol) references roles(id_rol),
    foreign key (id_permiso) references permisos(id_permiso)
);

create table SoliFranquicia (
    id_solicitud int auto_increment primary key,
    id_usuario int not null,
    fecha date not null,
    estado varchar(50) not null,
    observaciones text,
    foreign key (id_usuario) references usuarios(id_usuario)
);

create table Franquicias (
    id_franquicia int auto_increment primary key,
    id_solicitud int not null,
    nombre varchar(150) not null,
    direccion varchar(255) not null,
    estado varchar(50) not null,
    foreign key (id_solicitud) references SoliFranquicia(id_solicitud)
);

create table Personales (
    id_personal int auto_increment primary key,
    id_usuario int not null,
    id_franquicia int null,
    foreign key (id_usuario) references usuarios(id_usuario),
    foreign key (id_franquicia) references Franquicias(id_franquicia)
);

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

create table Inmuebles (
    id_inmueble int auto_increment primary key,
    id_captacion int not null,
    id_propietario int not null,
    direccion varchar(255) not null,
    tipo_inmueble varchar(100) not null,
    caracteristicas text,
    foreign key (id_captacion) references Captaciones(id_captacion),
    foreign key (id_propietario) references usuarios(id_usuario)
);

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

create table Multimedia (
    id_multimedia int auto_increment primary key,
    id_inmueble int not null,
    tipo varchar(50) not null,
    url varchar(255),
    foreign key (id_inmueble) references Inmuebles(id_inmueble)
);

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

create table Publicaciones (
    id_publicacion int auto_increment primary key,
    id_inmueble int not null,
    id_agente int not null,
    fecha datetime not null,
    foreign key (id_inmueble) references Inmuebles(id_inmueble),
    foreign key (id_agente) references usuarios(id_usuario)
);

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

create table Operaciones (
    id_operacion int auto_increment primary key,
    id_oferta int not null,
    tipo_operacion varchar(50) not null,
    fecha date not null,
    estado varchar(50) not null,
    foreign key (id_oferta) references Ofertas(id_oferta)
);

create table Ventas (
    id_venta int auto_increment primary key,
    id_operacion int not null,
    fecha date not null,
    monto_final decimal(15,2) not null,
    estado varchar(50) not null,
    foreign key (id_operacion) references Operaciones(id_operacion)
);

create table Escribanias (
    id_escribania int auto_increment primary key,
    nombre varchar(150) not null,
    telefono varchar(50),
    correo varchar(150)
);

create table Escrituras (
    id_escritura int auto_increment primary key,
    id_venta int not null,
    id_escribania int not null,
    fecha date,
    estado varchar(50) not null,
    foreign key (id_venta) references Ventas(id_venta),
    foreign key (id_escribania) references Escribanias(id_escribania)
);

create table Alquileres (
    id_alquiler int auto_increment primary key,
    id_operacion int not null,
    fecha_inicio date not null,
    fecha_fin date,
    monto decimal(15,2) not null,
    estado varchar(50) not null,
    foreign key (id_operacion) references Operaciones(id_operacion)
);

create table Contratos (
    id_contrato int auto_increment primary key,
    id_alquiler int not null,
    fecha date not null,
    monto_final decimal(15,2),
    estado varchar(50) not null,
    foreign key (id_alquiler) references Alquileres(id_alquiler)
);

create table Cobranzas (
    id_cobranza int auto_increment primary key,
    id_alquiler int not null,
    fecha date not null,
    monto decimal(15,2) not null,
    estado varchar(50) not null,
    foreign key (id_alquiler) references Alquileres(id_alquiler)
);

create table Liquidaciones (
    id_liquidacion int auto_increment primary key,
    id_alquiler int not null,
    fecha date not null,
    monto decimal(15,2) not null,
    foreign key (id_alquiler) references Alquileres(id_alquiler)
);

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