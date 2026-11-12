-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-11-2026 a las 16:36:17
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `remax`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ConcretarVenta` (IN `p_id_oferta` INT, IN `p_monto_final` DECIMAL(15,2), IN `p_id_usuario_comision` INT, IN `p_porcentaje_comision` DECIMAL(5,2))   BEGIN
    -- Manejador de errores: si ocurre cualquier fallo en SQL, se revierte todo
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error en la transacción: No se pudo concretar la venta y se revirtieron los cambios.';
    END;

    -- 1. Iniciamos la transacción explícitamente
    START TRANSACTION;

    -- 2. Actualizar el estado de la oferta a 'Aceptada'
    UPDATE Ofertas 
    SET estado = 'Aceptada' 
    WHERE id_oferta = p_id_oferta;

    -- 3. Registrar la Operación vinculada a la oferta
    INSERT INTO Operaciones (id_oferta, tipo_operacion, fecha, estado)
    VALUES (p_id_oferta, 'VENTA', CURDATE(), 'Completada');
    
    -- Capturamos el ID de la operación recién insertada
    SET @id_op_creada = LAST_INSERT_ID();

    -- 4. Registrar la Venta oficial
    INSERT INTO Ventas (id_operacion, fecha, monto_final, estado)
    VALUES (@id_op_creada, CURDATE(), p_monto_final, 'Finalizada');

    -- 5. Calcular y registrar la comisión correspondiente para el agente
    SET @monto_comision = (p_monto_final * p_porcentaje_comision) / 100;
    
    INSERT INTO Comisiones (id_operacion, id_usuario, porcentaje, monto, estado)
    VALUES (@id_op_creada, p_id_usuario_comision, p_porcentaje_comision, @monto_comision, 'Pendiente');

    -- 6. Si todo salió bien, confirmamos los cambios permanentemente
    COMMIT;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_General_Actualizar` (IN `p_nombre_tabla` VARCHAR(64), IN `p_set_valores` TEXT, IN `p_nombre_id` VARCHAR(64), IN `p_id` INT)   BEGIN
    SET @sql_query = CONCAT('UPDATE ', p_nombre_tabla, ' SET ', p_set_valores, ' WHERE ', p_nombre_id, ' = ?');
    SET @id_val = p_id;
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt USING @id_val;
    DEALLOCATE PREPARE stmt;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_General_BuscarPorId` (IN `p_nombre_tabla` VARCHAR(64), IN `p_nombre_id` VARCHAR(64), IN `p_id` INT)   BEGIN
    SET @sql_query = CONCAT('SELECT * FROM ', p_nombre_tabla, ' WHERE ', p_nombre_id, ' = ?');
    SET @id_val = p_id;
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt USING @id_val;
    DEALLOCATE PREPARE stmt;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_General_Eliminar` (IN `p_nombre_tabla` VARCHAR(64), IN `p_nombre_id` VARCHAR(64), IN `p_id` INT)   BEGIN
    SET @sql_query = CONCAT('DELETE FROM ', p_nombre_tabla, ' WHERE ', p_nombre_id, ' = ?');
    SET @id_val = p_id;
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt USING @id_val;
    DEALLOCATE PREPARE stmt;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_General_Insertar` (IN `p_nombre_tabla` VARCHAR(64), IN `p_columnas` TEXT, IN `p_valores` TEXT)   BEGIN
    -- Une las columnas y strings de valores pasados desde la lógica de Java
    SET @sql_query = CONCAT('INSERT INTO ', p_nombre_tabla, ' (', p_columnas, ') VALUES (', p_valores, ')');
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_General_Listar` (IN `p_nombre_tabla` VARCHAR(64))   BEGIN
    -- Construimos la consulta concatenando de forma segura la tabla
    SET @sql_query = CONCAT('SELECT * FROM ', p_nombre_tabla);
    
    -- Preparamos y ejecutamos la sentencia dinámica
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquileres`
--

CREATE TABLE `alquileres` (
  `id_alquiler` int(11) NOT NULL,
  `id_operacion` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  `monto` decimal(15,2) NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alquileres`
--

INSERT INTO `alquileres` (`id_alquiler`, `id_operacion`, `fecha_inicio`, `fecha_fin`, `monto`, `estado`) VALUES
(1, 1, '2026-11-02', '2028-11-02', 9999999999999.99, 'Renovado'),
(2, 1, '2026-11-02', '2027-11-02', 450000.00, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditorias`
--

CREATE TABLE `auditorias` (
  `id_auditoria` int(11) NOT NULL,
  `id_inmueble` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `estado` varchar(50) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `auditorias`
--

INSERT INTO `auditorias` (`id_auditoria`, `id_inmueble`, `id_usuario`, `fecha`, `estado`, `observaciones`) VALUES
(1, 1, 4, '2026-10-08 09:00:00', 'Aprobado', 'Verificación interna de infraestructura conforme.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `captaciones`
--

CREATE TABLE `captaciones` (
  `id_captacion` int(11) NOT NULL,
  `id_operador` int(11) NOT NULL,
  `id_propietario` int(11) NOT NULL,
  `caracteristicas` text DEFAULT NULL,
  `fecha` date NOT NULL,
  `estado` varchar(50) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `captaciones`
--

INSERT INTO `captaciones` (`id_captacion`, `id_operador`, `id_propietario`, `caracteristicas`, `fecha`, `estado`, `observaciones`) VALUES
(1, 1, 2, 'Casa de 2 plantas con cochera y parque', '2026-10-05', 'Activa', 'Documentación de la propiedad al día.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caracteristicas`
--

CREATE TABLE `caracteristicas` (
  `id_caracteristica` int(11) NOT NULL,
  `categoria` varchar(50) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `caracteristicas`
--

INSERT INTO `caracteristicas` (`id_caracteristica`, `categoria`, `nombre`) VALUES
(1, 'Comodidades', 'Piscina'),
(2, 'Seguridad', 'Cámaras de vigilancia'),
(3, 'Exterior', 'Jardín con parrilla');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comisiones`
--

CREATE TABLE `comisiones` (
  `id_comision` int(11) NOT NULL,
  `id_operacion` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `porcentaje` decimal(5,2) NOT NULL,
  `monto` decimal(15,2) NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comisiones`
--

INSERT INTO `comisiones` (`id_comision`, `id_operacion`, `id_usuario`, `porcentaje`, `monto`, `estado`) VALUES
(1, 1, 1, 3.00, 5250.00, 'Liquidada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conversaciones`
--

CREATE TABLE `conversaciones` (
  `id_conversacion` int(11) NOT NULL,
  `id_inmueble` int(11) DEFAULT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `conversaciones`
--

INSERT INTO `conversaciones` (`id_conversacion`, `id_inmueble`, `estado`) VALUES
(1, 1, 'Abierta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escribanias`
--

CREATE TABLE `escribanias` (
  `id_escribania` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `telefono` varchar(30) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `escribanias`
--

INSERT INTO `escribanias` (`id_escribania`, `nombre`, `telefono`, `correo`) VALUES
(1, 'Escribanía Marcone', '+541144445555', 'contacto@marcone.com'),
(2, 'Notaría San Martín', '+541166667777', 'info@sanmartin.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escrituras`
--

CREATE TABLE `escrituras` (
  `id_escritura` int(11) NOT NULL,
  `id_venta` int(11) NOT NULL,
  `id_escribania` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `escrituras`
--

INSERT INTO `escrituras` (`id_escritura`, `id_venta`, `id_escribania`, `fecha`, `estado`) VALUES
(1, 1, 1, '2026-10-30', 'Firmada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `franquicias`
--

CREATE TABLE `franquicias` (
  `id_franquicia` int(11) NOT NULL,
  `id_solicitud` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `franquicias`
--

INSERT INTO `franquicias` (`id_franquicia`, `id_solicitud`, `nombre`, `direccion`, `estado`) VALUES
(1, 1, 'RE/MAX Premium', 'Av. Santa Fe 2500, CABA', 'Activa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_oferta`
--

CREATE TABLE `historial_oferta` (
  `id_histoferta` int(11) NOT NULL,
  `id_oferta` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `accion` varchar(100) NOT NULL,
  `monto` decimal(15,2) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `historial_oferta`
--

INSERT INTO `historial_oferta` (`id_histoferta`, `id_oferta`, `id_usuario`, `fecha`, `accion`, `monto`, `observaciones`) VALUES
(1, 1, 3, '2026-11-02 09:44:32', 'CREACION_OFERTA', 175000.00, 'Oferta ingresada automáticamente al sistema');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inmuebles`
--

CREATE TABLE `inmuebles` (
  `id_inmueble` int(11) NOT NULL,
  `id_captacion` int(11) NOT NULL,
  `id_propietario` int(11) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `tipo_inmueble` varchar(50) NOT NULL,
  `superficie_cubierta` decimal(10,2) DEFAULT NULL,
  `superficie_total` decimal(10,2) DEFAULT NULL,
  `cantidad_habitaciones` int(11) DEFAULT NULL,
  `cantidad_banos` int(11) DEFAULT NULL,
  `cantidad_cocheras` int(11) DEFAULT NULL,
  `antiguedad_estado` varchar(100) DEFAULT NULL,
  `localidad` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inmuebles`
--

INSERT INTO `inmuebles` (`id_inmueble`, `id_captacion`, `id_propietario`, `direccion`, `tipo_inmueble`, `superficie_cubierta`, `superficie_total`, `cantidad_habitaciones`, `cantidad_banos`, `cantidad_cocheras`, `antiguedad_estado`, `localidad`) VALUES
(1, 1, 2, 'Calle Falsa 123', 'Casa', 150.00, 220.00, 4, 2, 1, 'Excelente estado, 5 años de antigüedad', 'Palermo');

--
-- Disparadores `inmuebles`
--
DELIMITER $$
CREATE TRIGGER `trg_before_insert_inmueble` BEFORE INSERT ON `inmuebles` FOR EACH ROW BEGIN
    IF NEW.superficie_cubierta > NEW.superficie_total THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: La superficie cubierta no puede ser mayor que la superficie total del inmueble.';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inmueble_caracteristicas`
--

CREATE TABLE `inmueble_caracteristicas` (
  `id_inmueble` int(11) NOT NULL,
  `id_caracteristica` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inmueble_caracteristicas`
--

INSERT INTO `inmueble_caracteristicas` (`id_inmueble`, `id_caracteristica`) VALUES
(1, 1),
(1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `id_mensaje` int(11) NOT NULL,
  `id_conversacion` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `contenido` text NOT NULL,
  `estado` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`id_mensaje`, `id_conversacion`, `id_usuario`, `fecha_hora`, `contenido`, `estado`) VALUES
(1, 1, 3, '2026-10-07 14:30:00', 'Buenas tardes, ¿se puede coordinar una visita para este sábado?', 'Leído');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multimedia`
--

CREATE TABLE `multimedia` (
  `id_multimedia` int(11) NOT NULL,
  `id_inmueble` int(11) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `url` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `multimedia`
--

INSERT INTO `multimedia` (`id_multimedia`, `id_inmueble`, `tipo`, `url`) VALUES
(1, 1, 'Imagen', 'https://remax.com'),
(2, 1, 'Video', 'https://remax.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ofertas`
--

CREATE TABLE `ofertas` (
  `id_oferta` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_inmueble` int(11) NOT NULL,
  `monto` decimal(15,2) NOT NULL,
  `fecha` date NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ofertas`
--

INSERT INTO `ofertas` (`id_oferta`, `id_usuario`, `id_inmueble`, `monto`, `fecha`, `estado`) VALUES
(1, 3, 1, 175000.00, '2026-10-12', 'Aceptada');

--
-- Disparadores `ofertas`
--
DELIMITER $$
CREATE TRIGGER `trg_after_insert_oferta` AFTER INSERT ON `ofertas` FOR EACH ROW BEGIN
    INSERT INTO Historial_oferta (id_oferta, id_usuario, fecha, accion, monto, observaciones)
    VALUES (NEW.id_oferta, NEW.id_usuario, NOW(), 'CREACION_OFERTA', NEW.monto, 'Oferta ingresada automáticamente al sistema');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operaciones`
--

CREATE TABLE `operaciones` (
  `id_operacion` int(11) NOT NULL,
  `id_oferta` int(11) NOT NULL,
  `tipo_operacion` varchar(50) NOT NULL,
  `fecha` date NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `operaciones`
--

INSERT INTO `operaciones` (`id_operacion`, `id_oferta`, `tipo_operacion`, `fecha`, `estado`) VALUES
(1, 1, 'VENTA', '2026-10-15', 'Completada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `id_permiso` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`id_permiso`, `nombre`, `descripcion`) VALUES
(1, 'ver_usuarios', 'Permite consultar los usuarios registrados.'),
(2, 'crear_usuario', 'Permite crear nuevos usuarios.'),
(3, 'modificar_usuario', 'Permite modificar los datos de los usuarios.'),
(4, 'desactivar_usuario', 'Permite desactivar usuarios.'),
(5, 'ver_roles', 'Permite consultar los roles del sistema.'),
(6, 'crear_rol', 'Permite crear nuevos roles.'),
(7, 'modificar_rol', 'Permite modificar roles existentes.'),
(8, 'asignar_roles', 'Permite asignar roles a los usuarios.'),
(9, 'ver_permisos', 'Permite consultar los permisos del sistema.'),
(10, 'asignar_permisos', 'Permite asignar permisos a los roles.'),
(11, 'ver_franquicias', 'Permite consultar las franquicias.'),
(12, 'crear_franquicia', 'Permite crear franquicias.'),
(13, 'modificar_franquicia', 'Permite modificar franquicias.'),
(14, 'gestionar_solicitudes_franquicia', 'Permite gestionar solicitudes de franquicia.'),
(15, 'ver_captaciones', 'Permite consultar captaciones de propiedades.'),
(16, 'crear_captacion', 'Permite registrar captaciones.'),
(17, 'modificar_captacion', 'Permite modificar captaciones.'),
(18, 'crear_inmueble', 'Permite registrar inmuebles.'),
(19, 'modificar_inmueble', 'Permite modificar inmuebles.'),
(20, 'eliminar_inmueble', 'Permite eliminar inmuebles.'),
(21, 'crear_tasacion', 'Permite registrar tasaciones.'),
(22, 'modificar_tasacion', 'Permite modificar tasaciones.'),
(23, 'crear_publicacion', 'Permite crear publicaciones de inmuebles.'),
(24, 'modificar_publicacion', 'Permite modificar publicaciones.'),
(25, 'eliminar_publicacion', 'Permite eliminar publicaciones.'),
(26, 'gestionar_visitas', 'Permite gestionar visitas a inmuebles.'),
(27, 'gestionar_multimedia', 'Permite gestionar fotos, videos y otros archivos multimedia.'),
(28, 'crear_oferta', 'Permite realizar ofertas sobre inmuebles.'),
(29, 'gestionar_ofertas', 'Permite gestionar ofertas recibidas.'),
(30, 'ver_operaciones', 'Permite consultar operaciones inmobiliarias.'),
(31, 'gestionar_ventas', 'Permite gestionar operaciones de venta.'),
(32, 'gestionar_alquileres', 'Permite gestionar operaciones de alquiler.'),
(33, 'ver_comisiones', 'Permite consultar comisiones.'),
(34, 'gestionar_comisiones', 'Permite gestionar comisiones.'),
(35, 'usar_chat', 'Permite utilizar el sistema de chat.'),
(36, 'gestionar_conversaciones', 'Permite gestionar conversaciones del sistema.'),
(37, 'ver_auditorias', 'Permite consultar auditorías de inmuebles.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personales`
--

CREATE TABLE `personales` (
  `id_personal` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_franquicia` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personales`
--

INSERT INTO `personales` (`id_personal`, `id_usuario`, `id_franquicia`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publicaciones`
--

CREATE TABLE `publicaciones` (
  `id_publicacion` int(11) NOT NULL,
  `id_inmueble` int(11) NOT NULL,
  `id_agente` int(11) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `publicaciones`
--

INSERT INTO `publicaciones` (`id_publicacion`, `id_inmueble`, `id_agente`, `fecha`) VALUES
(1, 1, 1, '2026-10-06');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registrosalquiler`
--

CREATE TABLE `registrosalquiler` (
  `id_registro` int(11) NOT NULL,
  `id_alquiler` int(11) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `monto` decimal(15,2) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `registrosalquiler`
--

INSERT INTO `registrosalquiler` (`id_registro`, `id_alquiler`, `id_tipo`, `fecha`, `monto`, `estado`, `observaciones`) VALUES
(1, 1, 2, '2026-10-28', 1000.00, 'Cobrado', 'Depósito de garantía recibido de conformidad.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relacionpermisos`
--

CREATE TABLE `relacionpermisos` (
  `id_relpermiso` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `id_permiso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `relacionpermisos`
--

INSERT INTO `relacionpermisos` (`id_relpermiso`, `id_rol`, `id_permiso`) VALUES
(1, 1, 28),
(2, 1, 35),
(3, 1, 26),
(4, 1, 30),
(5, 2, 15),
(6, 2, 18),
(7, 2, 19),
(8, 2, 27),
(9, 2, 26),
(10, 2, 28),
(11, 2, 35),
(12, 3, 15),
(13, 3, 16),
(14, 3, 17),
(15, 3, 18),
(16, 3, 19),
(17, 3, 20),
(18, 3, 21),
(19, 3, 22),
(20, 3, 23),
(21, 3, 24),
(22, 3, 25),
(23, 3, 26),
(24, 3, 27),
(25, 3, 28),
(26, 3, 29),
(27, 3, 30),
(28, 3, 31),
(29, 3, 32),
(30, 3, 33),
(31, 3, 35),
(32, 3, 36),
(33, 4, 1),
(34, 4, 2),
(35, 4, 3),
(36, 4, 4),
(37, 4, 5),
(38, 4, 6),
(39, 4, 7),
(40, 4, 8),
(41, 4, 9),
(42, 4, 10),
(43, 4, 11),
(44, 4, 12),
(45, 4, 13),
(46, 4, 14),
(47, 4, 15),
(48, 4, 16),
(49, 4, 17),
(50, 4, 18),
(51, 4, 19),
(52, 4, 20),
(53, 4, 26),
(54, 4, 35),
(55, 4, 36),
(56, 4, 37),
(57, 5, 1),
(58, 5, 5),
(59, 5, 9),
(60, 5, 11),
(61, 5, 12),
(62, 5, 13),
(63, 5, 14),
(64, 5, 15),
(65, 5, 18),
(66, 5, 19),
(67, 5, 21),
(68, 5, 22),
(69, 5, 23),
(70, 5, 24),
(71, 5, 26),
(72, 5, 27),
(73, 5, 29),
(74, 5, 30),
(75, 5, 31),
(76, 5, 32),
(77, 5, 33),
(78, 5, 34),
(79, 5, 35),
(80, 5, 36),
(81, 5, 37),
(82, 6, 11),
(83, 6, 15),
(84, 6, 16),
(85, 6, 17),
(86, 6, 18),
(87, 6, 19),
(88, 6, 21),
(89, 6, 22),
(90, 6, 23),
(91, 6, 24),
(92, 6, 26),
(93, 6, 27),
(94, 6, 28),
(95, 6, 29),
(96, 6, 30),
(97, 6, 35),
(98, 6, 36);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relacionroles`
--

CREATE TABLE `relacionroles` (
  `id_relrol` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `relacionroles`
--

INSERT INTO `relacionroles` (`id_relrol`, `id_usuario`, `id_rol`) VALUES
(1, 5, 1),
(2, 6, 2),
(3, 7, 3),
(4, 8, 6),
(5, 9, 5),
(6, 10, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id_rol` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id_rol`, `nombre`, `descripcion`) VALUES
(1, 'Cliente', 'Usuario que busca propiedades, solicita visitas y realiza ofertas.'),
(2, 'Propietario', 'Usuario que desea vender o alquilar una propiedad.'),
(3, 'Agente', 'Usuario encargado de gestionar propiedades, clientes y operaciones.'),
(4, 'Administrador', 'Usuario encargado de administrar el sistema y sus configuraciones.'),
(5, 'Gerente', 'Usuario con acceso a reportes y funciones de gestión avanzada.'),
(6, 'PersonalFranquicia', 'Usuario perteneciente al personal de una franquicia.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solifranquicias`
--

CREATE TABLE `solifranquicias` (
  `id_solicitud` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `estado` varchar(50) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `solifranquicias`
--

INSERT INTO `solifranquicias` (`id_solicitud`, `id_usuario`, `fecha`, `estado`, `observaciones`) VALUES
(1, 1, '2026-10-01', 'Aprobada', 'Cumple con el perfil de inversor regional.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tasaciones`
--

CREATE TABLE `tasaciones` (
  `id_tasacion` int(11) NOT NULL,
  `id_inmueble` int(11) NOT NULL,
  `id_encargado` int(11) NOT NULL,
  `valor_estimado` decimal(15,2) NOT NULL,
  `valor_final` decimal(15,2) DEFAULT NULL,
  `fecha` date NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tasaciones`
--

INSERT INTO `tasaciones` (`id_tasacion`, `id_inmueble`, `id_encargado`, `valor_estimado`, `valor_final`, `fecha`, `estado`) VALUES
(1, 1, 1, 185000.00, 180000.00, '2026-10-04', 'Finalizada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiporegistro`
--

CREATE TABLE `tiporegistro` (
  `id_tipo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tiporegistro`
--

INSERT INTO `tiporegistro` (`id_tipo`, `nombre`, `descripcion`) VALUES
(1, 'Pago Mensual', 'Cobro del canon de alquiler regular'),
(2, 'Depósito', 'Monto de garantía inicial'),
(3, 'Expensas', 'Gasto de mantenimiento edilicio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `tag` varchar(50) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `upassword` varchar(255) NOT NULL,
  `dni` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nombre`, `apellido`, `tag`, `correo`, `upassword`, `dni`) VALUES
(1, 'Carlos', 'Gómez', 'carlos_agente', 'carlos@remax.com', 'hash_password_1', '20111222'),
(2, 'Ana', 'Martínez', 'ana_propietario', 'ana@gmail.com', 'hash_password_2', '30222333'),
(3, 'Juan', 'Pérez', 'juan_cliente', 'juan@hotmail.com', 'hash_password_3', '40333444'),
(4, 'Admin', 'General', 'admin_root', 'admin@remax.com', 'hash_password_4', '10444555'),
(5, 'Santiago', 'Díaz', 'sdiaz', 'santiago.diaz@remax.com', '123456', '40000005'),
(6, 'Jeremías', 'Nieto', 'jnieto', 'jeremias.nieto@remax.com', '123456', '40000006'),
(7, 'Alba', 'Spitalnik', 'aspitalnik', 'alba.spitalnik@remax.com', '123456', '40000007'),
(8, 'Tiziano', 'Giacomozzi', 'tgiacomozzi', 'tiziano.giacomozzi@remax.com', '123456', '40000008'),
(9, 'Gian', 'Arguello', 'garguello', 'gian.arguello@remax.com', '123456', '40000009'),
(10, 'Gonzalo', 'Consorti', 'gconsorti', 'gonzalo.consorti@remax.com', '123456', '40000010'),
(12, 's', 's', 'coquito', 's@gmail.com', '$2a$10$rpMO1/8TkLzMsZ272OYBu.ykW5WrIwWOcKk3Cz73znsUsgfxH5Gni', '12312312');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id_venta` int(11) NOT NULL,
  `id_operacion` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `monto_final` decimal(15,2) NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id_venta`, `id_operacion`, `fecha`, `monto_final`, `estado`) VALUES
(1, 1, '2026-10-15', 175000.00, 'Finalizada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitas`
--

CREATE TABLE `visitas` (
  `id_visita` int(11) NOT NULL,
  `id_inmueble` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `estado` varchar(50) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `visitas`
--

INSERT INTO `visitas` (`id_visita`, `id_inmueble`, `id_usuario`, `fecha`, `estado`, `observaciones`) VALUES
(1, 1, 3, '2026-10-10 11:00:00', 'Realizada', 'Al cliente le interesó mucho la zona.');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vw_inmuebles_detallados`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vw_inmuebles_detallados` (
`id_inmueble` int(11)
,`tipo_inmueble` varchar(50)
,`direccion` varchar(255)
,`superficie_cubierta` decimal(10,2)
,`superficie_total` decimal(10,2)
,`cantidad_habitaciones` int(11)
,`cantidad_banos` int(11)
,`propietario_nombre` varchar(101)
,`propietario_correo` varchar(100)
,`estado_captacion` varchar(50)
,`fecha_captacion` date
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vw_operaciones_comisiones`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vw_operaciones_comisiones` (
`id_operacion` int(11)
,`tipo_operacion` varchar(50)
,`fecha_operacion` date
,`estado_operacion` varchar(50)
,`monto_oferta` decimal(15,2)
,`usuario_responsable` varchar(101)
,`comision_porcentaje` decimal(5,2)
,`comision_monto` decimal(15,2)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `vw_inmuebles_detallados`
--
DROP TABLE IF EXISTS `vw_inmuebles_detallados`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_inmuebles_detallados`  AS SELECT `i`.`id_inmueble` AS `id_inmueble`, `i`.`tipo_inmueble` AS `tipo_inmueble`, `i`.`direccion` AS `direccion`, `i`.`superficie_cubierta` AS `superficie_cubierta`, `i`.`superficie_total` AS `superficie_total`, `i`.`cantidad_habitaciones` AS `cantidad_habitaciones`, `i`.`cantidad_banos` AS `cantidad_banos`, concat(`u`.`nombre`,' ',`u`.`apellido`) AS `propietario_nombre`, `u`.`correo` AS `propietario_correo`, `c`.`estado` AS `estado_captacion`, `c`.`fecha` AS `fecha_captacion` FROM ((`inmuebles` `i` join `usuarios` `u` on(`i`.`id_propietario` = `u`.`id_usuario`)) join `captaciones` `c` on(`i`.`id_captacion` = `c`.`id_captacion`)) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vw_operaciones_comisiones`
--
DROP TABLE IF EXISTS `vw_operaciones_comisiones`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_operaciones_comisiones`  AS SELECT `o`.`id_operacion` AS `id_operacion`, `o`.`tipo_operacion` AS `tipo_operacion`, `o`.`fecha` AS `fecha_operacion`, `o`.`estado` AS `estado_operacion`, `ofe`.`monto` AS `monto_oferta`, concat(`u`.`nombre`,' ',`u`.`apellido`) AS `usuario_responsable`, `com`.`porcentaje` AS `comision_porcentaje`, `com`.`monto` AS `comision_monto` FROM (((`operaciones` `o` join `ofertas` `ofe` on(`o`.`id_oferta` = `ofe`.`id_oferta`)) join `comisiones` `com` on(`o`.`id_operacion` = `com`.`id_operacion`)) join `usuarios` `u` on(`com`.`id_usuario` = `u`.`id_usuario`)) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alquileres`
--
ALTER TABLE `alquileres`
  ADD PRIMARY KEY (`id_alquiler`),
  ADD KEY `id_operacion` (`id_operacion`);

--
-- Indices de la tabla `auditorias`
--
ALTER TABLE `auditorias`
  ADD PRIMARY KEY (`id_auditoria`),
  ADD KEY `id_inmueble` (`id_inmueble`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `captaciones`
--
ALTER TABLE `captaciones`
  ADD PRIMARY KEY (`id_captacion`),
  ADD KEY `id_operador` (`id_operador`),
  ADD KEY `id_propietario` (`id_propietario`);

--
-- Indices de la tabla `caracteristicas`
--
ALTER TABLE `caracteristicas`
  ADD PRIMARY KEY (`id_caracteristica`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `comisiones`
--
ALTER TABLE `comisiones`
  ADD PRIMARY KEY (`id_comision`),
  ADD KEY `id_operacion` (`id_operacion`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `conversaciones`
--
ALTER TABLE `conversaciones`
  ADD PRIMARY KEY (`id_conversacion`),
  ADD KEY `id_inmueble` (`id_inmueble`);

--
-- Indices de la tabla `escribanias`
--
ALTER TABLE `escribanias`
  ADD PRIMARY KEY (`id_escribania`);

--
-- Indices de la tabla `escrituras`
--
ALTER TABLE `escrituras`
  ADD PRIMARY KEY (`id_escritura`),
  ADD KEY `id_venta` (`id_venta`),
  ADD KEY `id_escribania` (`id_escribania`);

--
-- Indices de la tabla `franquicias`
--
ALTER TABLE `franquicias`
  ADD PRIMARY KEY (`id_franquicia`),
  ADD KEY `id_solicitud` (`id_solicitud`);

--
-- Indices de la tabla `historial_oferta`
--
ALTER TABLE `historial_oferta`
  ADD PRIMARY KEY (`id_histoferta`),
  ADD KEY `id_oferta` (`id_oferta`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `inmuebles`
--
ALTER TABLE `inmuebles`
  ADD PRIMARY KEY (`id_inmueble`),
  ADD KEY `id_captacion` (`id_captacion`),
  ADD KEY `id_propietario` (`id_propietario`);

--
-- Indices de la tabla `inmueble_caracteristicas`
--
ALTER TABLE `inmueble_caracteristicas`
  ADD PRIMARY KEY (`id_inmueble`,`id_caracteristica`),
  ADD KEY `id_caracteristica` (`id_caracteristica`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`id_mensaje`),
  ADD KEY `id_conversacion` (`id_conversacion`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `multimedia`
--
ALTER TABLE `multimedia`
  ADD PRIMARY KEY (`id_multimedia`),
  ADD KEY `id_inmueble` (`id_inmueble`);

--
-- Indices de la tabla `ofertas`
--
ALTER TABLE `ofertas`
  ADD PRIMARY KEY (`id_oferta`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_inmueble` (`id_inmueble`);

--
-- Indices de la tabla `operaciones`
--
ALTER TABLE `operaciones`
  ADD PRIMARY KEY (`id_operacion`),
  ADD KEY `id_oferta` (`id_oferta`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`id_permiso`);

--
-- Indices de la tabla `personales`
--
ALTER TABLE `personales`
  ADD PRIMARY KEY (`id_personal`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_franquicia` (`id_franquicia`);

--
-- Indices de la tabla `publicaciones`
--
ALTER TABLE `publicaciones`
  ADD PRIMARY KEY (`id_publicacion`),
  ADD KEY `id_inmueble` (`id_inmueble`),
  ADD KEY `id_agente` (`id_agente`);

--
-- Indices de la tabla `registrosalquiler`
--
ALTER TABLE `registrosalquiler`
  ADD PRIMARY KEY (`id_registro`),
  ADD KEY `id_alquiler` (`id_alquiler`),
  ADD KEY `id_tipo` (`id_tipo`);

--
-- Indices de la tabla `relacionpermisos`
--
ALTER TABLE `relacionpermisos`
  ADD PRIMARY KEY (`id_relpermiso`),
  ADD KEY `id_rol` (`id_rol`),
  ADD KEY `id_permiso` (`id_permiso`);

--
-- Indices de la tabla `relacionroles`
--
ALTER TABLE `relacionroles`
  ADD PRIMARY KEY (`id_relrol`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_rol` (`id_rol`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `solifranquicias`
--
ALTER TABLE `solifranquicias`
  ADD PRIMARY KEY (`id_solicitud`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `tasaciones`
--
ALTER TABLE `tasaciones`
  ADD PRIMARY KEY (`id_tasacion`),
  ADD KEY `id_inmueble` (`id_inmueble`),
  ADD KEY `id_encargado` (`id_encargado`);

--
-- Indices de la tabla `tiporegistro`
--
ALTER TABLE `tiporegistro`
  ADD PRIMARY KEY (`id_tipo`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `tag` (`tag`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `id_operacion` (`id_operacion`);

--
-- Indices de la tabla `visitas`
--
ALTER TABLE `visitas`
  ADD PRIMARY KEY (`id_visita`),
  ADD KEY `id_inmueble` (`id_inmueble`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alquileres`
--
ALTER TABLE `alquileres`
  MODIFY `id_alquiler` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `auditorias`
--
ALTER TABLE `auditorias`
  MODIFY `id_auditoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `captaciones`
--
ALTER TABLE `captaciones`
  MODIFY `id_captacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `caracteristicas`
--
ALTER TABLE `caracteristicas`
  MODIFY `id_caracteristica` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `comisiones`
--
ALTER TABLE `comisiones`
  MODIFY `id_comision` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `conversaciones`
--
ALTER TABLE `conversaciones`
  MODIFY `id_conversacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `escribanias`
--
ALTER TABLE `escribanias`
  MODIFY `id_escribania` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `escrituras`
--
ALTER TABLE `escrituras`
  MODIFY `id_escritura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `franquicias`
--
ALTER TABLE `franquicias`
  MODIFY `id_franquicia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `historial_oferta`
--
ALTER TABLE `historial_oferta`
  MODIFY `id_histoferta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `inmuebles`
--
ALTER TABLE `inmuebles`
  MODIFY `id_inmueble` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `id_mensaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `multimedia`
--
ALTER TABLE `multimedia`
  MODIFY `id_multimedia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ofertas`
--
ALTER TABLE `ofertas`
  MODIFY `id_oferta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `operaciones`
--
ALTER TABLE `operaciones`
  MODIFY `id_operacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `id_permiso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `personales`
--
ALTER TABLE `personales`
  MODIFY `id_personal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `publicaciones`
--
ALTER TABLE `publicaciones`
  MODIFY `id_publicacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `registrosalquiler`
--
ALTER TABLE `registrosalquiler`
  MODIFY `id_registro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `relacionpermisos`
--
ALTER TABLE `relacionpermisos`
  MODIFY `id_relpermiso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT de la tabla `relacionroles`
--
ALTER TABLE `relacionroles`
  MODIFY `id_relrol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `solifranquicias`
--
ALTER TABLE `solifranquicias`
  MODIFY `id_solicitud` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tasaciones`
--
ALTER TABLE `tasaciones`
  MODIFY `id_tasacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tiporegistro`
--
ALTER TABLE `tiporegistro`
  MODIFY `id_tipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `visitas`
--
ALTER TABLE `visitas`
  MODIFY `id_visita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alquileres`
--
ALTER TABLE `alquileres`
  ADD CONSTRAINT `alquileres_ibfk_1` FOREIGN KEY (`id_operacion`) REFERENCES `operaciones` (`id_operacion`);

--
-- Filtros para la tabla `auditorias`
--
ALTER TABLE `auditorias`
  ADD CONSTRAINT `auditorias_ibfk_1` FOREIGN KEY (`id_inmueble`) REFERENCES `inmuebles` (`id_inmueble`),
  ADD CONSTRAINT `auditorias_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `captaciones`
--
ALTER TABLE `captaciones`
  ADD CONSTRAINT `captaciones_ibfk_1` FOREIGN KEY (`id_operador`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `captaciones_ibfk_2` FOREIGN KEY (`id_propietario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `comisiones`
--
ALTER TABLE `comisiones`
  ADD CONSTRAINT `comisiones_ibfk_1` FOREIGN KEY (`id_operacion`) REFERENCES `operaciones` (`id_operacion`),
  ADD CONSTRAINT `comisiones_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `conversaciones`
--
ALTER TABLE `conversaciones`
  ADD CONSTRAINT `conversaciones_ibfk_1` FOREIGN KEY (`id_inmueble`) REFERENCES `inmuebles` (`id_inmueble`);

--
-- Filtros para la tabla `escrituras`
--
ALTER TABLE `escrituras`
  ADD CONSTRAINT `escrituras_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`),
  ADD CONSTRAINT `escrituras_ibfk_2` FOREIGN KEY (`id_escribania`) REFERENCES `escribanias` (`id_escribania`);

--
-- Filtros para la tabla `franquicias`
--
ALTER TABLE `franquicias`
  ADD CONSTRAINT `franquicias_ibfk_1` FOREIGN KEY (`id_solicitud`) REFERENCES `solifranquicias` (`id_solicitud`);

--
-- Filtros para la tabla `historial_oferta`
--
ALTER TABLE `historial_oferta`
  ADD CONSTRAINT `historial_oferta_ibfk_1` FOREIGN KEY (`id_oferta`) REFERENCES `ofertas` (`id_oferta`),
  ADD CONSTRAINT `historial_oferta_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `inmuebles`
--
ALTER TABLE `inmuebles`
  ADD CONSTRAINT `inmuebles_ibfk_1` FOREIGN KEY (`id_captacion`) REFERENCES `captaciones` (`id_captacion`),
  ADD CONSTRAINT `inmuebles_ibfk_2` FOREIGN KEY (`id_propietario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `inmueble_caracteristicas`
--
ALTER TABLE `inmueble_caracteristicas`
  ADD CONSTRAINT `inmueble_caracteristicas_ibfk_1` FOREIGN KEY (`id_inmueble`) REFERENCES `inmuebles` (`id_inmueble`) ON DELETE CASCADE,
  ADD CONSTRAINT `inmueble_caracteristicas_ibfk_2` FOREIGN KEY (`id_caracteristica`) REFERENCES `caracteristicas` (`id_caracteristica`) ON DELETE CASCADE;

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `mensajes_ibfk_1` FOREIGN KEY (`id_conversacion`) REFERENCES `conversaciones` (`id_conversacion`),
  ADD CONSTRAINT `mensajes_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `multimedia`
--
ALTER TABLE `multimedia`
  ADD CONSTRAINT `multimedia_ibfk_1` FOREIGN KEY (`id_inmueble`) REFERENCES `inmuebles` (`id_inmueble`);

--
-- Filtros para la tabla `ofertas`
--
ALTER TABLE `ofertas`
  ADD CONSTRAINT `ofertas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `ofertas_ibfk_2` FOREIGN KEY (`id_inmueble`) REFERENCES `inmuebles` (`id_inmueble`);

--
-- Filtros para la tabla `operaciones`
--
ALTER TABLE `operaciones`
  ADD CONSTRAINT `operaciones_ibfk_1` FOREIGN KEY (`id_oferta`) REFERENCES `ofertas` (`id_oferta`);

--
-- Filtros para la tabla `personales`
--
ALTER TABLE `personales`
  ADD CONSTRAINT `personales_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `personales_ibfk_2` FOREIGN KEY (`id_franquicia`) REFERENCES `franquicias` (`id_franquicia`);

--
-- Filtros para la tabla `publicaciones`
--
ALTER TABLE `publicaciones`
  ADD CONSTRAINT `publicaciones_ibfk_1` FOREIGN KEY (`id_inmueble`) REFERENCES `inmuebles` (`id_inmueble`),
  ADD CONSTRAINT `publicaciones_ibfk_2` FOREIGN KEY (`id_agente`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `registrosalquiler`
--
ALTER TABLE `registrosalquiler`
  ADD CONSTRAINT `registrosalquiler_ibfk_1` FOREIGN KEY (`id_alquiler`) REFERENCES `alquileres` (`id_alquiler`),
  ADD CONSTRAINT `registrosalquiler_ibfk_2` FOREIGN KEY (`id_tipo`) REFERENCES `tiporegistro` (`id_tipo`);

--
-- Filtros para la tabla `relacionpermisos`
--
ALTER TABLE `relacionpermisos`
  ADD CONSTRAINT `relacionpermisos_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`),
  ADD CONSTRAINT `relacionpermisos_ibfk_2` FOREIGN KEY (`id_permiso`) REFERENCES `permisos` (`id_permiso`);

--
-- Filtros para la tabla `relacionroles`
--
ALTER TABLE `relacionroles`
  ADD CONSTRAINT `relacionroles_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `relacionroles_ibfk_2` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`);

--
-- Filtros para la tabla `solifranquicias`
--
ALTER TABLE `solifranquicias`
  ADD CONSTRAINT `solifranquicias_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `tasaciones`
--
ALTER TABLE `tasaciones`
  ADD CONSTRAINT `tasaciones_ibfk_1` FOREIGN KEY (`id_inmueble`) REFERENCES `inmuebles` (`id_inmueble`),
  ADD CONSTRAINT `tasaciones_ibfk_2` FOREIGN KEY (`id_encargado`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_operacion`) REFERENCES `operaciones` (`id_operacion`);

--
-- Filtros para la tabla `visitas`
--
ALTER TABLE `visitas`
  ADD CONSTRAINT `visitas_ibfk_1` FOREIGN KEY (`id_inmueble`) REFERENCES `inmuebles` (`id_inmueble`),
  ADD CONSTRAINT `visitas_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
