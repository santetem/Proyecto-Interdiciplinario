-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-11-2026 a las 19:41:28
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_alquileres_actualizar` (IN `p_id` INT, IN `p_id_operacion` INT, IN `p_fecha_inicio` DATE, IN `p_fecha_fin` DATE, IN `p_monto` DECIMAL(15,2), IN `p_estado` VARCHAR(50))   BEGIN
    UPDATE alquileres SET id_operacion = p_id_operacion, fecha_inicio = p_fecha_inicio, fecha_fin = p_fecha_fin, monto = p_monto, estado = p_estado WHERE id_alquiler = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_alquileres_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM alquileres WHERE id_alquiler = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_alquileres_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM alquileres WHERE id_alquiler = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_alquileres_insertar` (IN `p_id_operacion` INT, IN `p_fecha_inicio` DATE, IN `p_fecha_fin` DATE, IN `p_monto` DECIMAL(15,2), IN `p_estado` VARCHAR(50))   BEGIN
    INSERT INTO alquileres (id_operacion, fecha_inicio, fecha_fin, monto, estado)
    VALUES (p_id_operacion, p_fecha_inicio, p_fecha_fin, p_monto, p_estado);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_alquileres_listar` ()   BEGIN
    SELECT * FROM alquileres;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_auditorias_actualizar` (IN `p_id` INT, IN `p_id_inmueble` INT, IN `p_id_usuario` INT, IN `p_fecha` DATETIME, IN `p_estado` VARCHAR(50), IN `p_observaciones` VARCHAR(255))   BEGIN
    UPDATE auditorias SET id_inmueble = p_id_inmueble, id_usuario = p_id_usuario, fecha = p_fecha, estado = p_estado, observaciones = p_observaciones WHERE id_auditoria = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_auditorias_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM auditorias WHERE id_auditoria = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_auditorias_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM auditorias WHERE id_auditoria = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_auditorias_insertar` (IN `p_id_inmueble` INT, IN `p_id_usuario` INT, IN `p_fecha` DATETIME, IN `p_estado` VARCHAR(50), IN `p_observaciones` VARCHAR(255))   BEGIN
    INSERT INTO auditorias (id_inmueble, id_usuario, fecha, estado, observaciones)
    VALUES (p_id_inmueble, p_id_usuario, p_fecha, p_estado, p_observaciones);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_auditorias_listar` ()   BEGIN
    SELECT * FROM auditorias;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_captaciones_actualizar` (IN `p_id` INT, IN `p_id_operador` INT, IN `p_id_propietario` INT, IN `p_caracteristicas` TEXT, IN `p_fecha` DATE, IN `p_estado` VARCHAR(50), IN `p_observaciones` VARCHAR(255))   BEGIN
    UPDATE captaciones SET id_operador = p_id_operador, id_propietario = p_id_propietario, caracteristicas = p_caracteristicas, fecha = p_fecha, estado = p_estado, observaciones = p_observaciones WHERE id_captacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_captaciones_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM captaciones WHERE id_captacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_captaciones_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM captaciones WHERE id_captacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_captaciones_insertar` (IN `p_id_operador` INT, IN `p_id_propietario` INT, IN `p_caracteristicas` TEXT, IN `p_fecha` DATE, IN `p_estado` VARCHAR(50), IN `p_observaciones` VARCHAR(255))   BEGIN
    INSERT INTO captaciones (id_operador, id_propietario, caracteristicas, fecha, estado, observaciones)
    VALUES (p_id_operador, p_id_propietario, p_caracteristicas, p_fecha, p_estado, p_observaciones);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_captaciones_listar` ()   BEGIN
    SELECT * FROM captaciones;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_caracteristicas_actualizar` (IN `p_id` INT, IN `p_categoria` VARCHAR(50), IN `p_nombre` VARCHAR(100))   BEGIN
    UPDATE caracteristicas SET categoria = p_categoria, nombre = p_nombre WHERE id_caracteristica = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_caracteristicas_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM caracteristicas WHERE id_caracteristica = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_caracteristicas_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM caracteristicas WHERE id_caracteristica = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_caracteristicas_insertar` (IN `p_categoria` VARCHAR(50), IN `p_nombre` VARCHAR(100))   BEGIN
    INSERT INTO caracteristicas (categoria, nombre)
    VALUES (p_categoria, p_nombre);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_caracteristicas_listar` ()   BEGIN
    SELECT * FROM caracteristicas;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_comisiones_actualizar` (IN `p_id` INT, IN `p_id_operacion` INT, IN `p_id_usuario` INT, IN `p_porcentaje` DECIMAL(5,2), IN `p_monto` DECIMAL(15,2), IN `p_estado` VARCHAR(50))   BEGIN
    UPDATE comisiones SET id_operacion = p_id_operacion, id_usuario = p_id_usuario, porcentaje = p_porcentaje, monto = p_monto, estado = p_estado WHERE id_comision = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_comisiones_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM comisiones WHERE id_comision = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_comisiones_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM comisiones WHERE id_comision = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_comisiones_insertar` (IN `p_id_operacion` INT, IN `p_id_usuario` INT, IN `p_porcentaje` DECIMAL(5,2), IN `p_monto` DECIMAL(15,2), IN `p_estado` VARCHAR(50))   BEGIN
    INSERT INTO comisiones (id_operacion, id_usuario, porcentaje, monto, estado)
    VALUES (p_id_operacion, p_id_usuario, p_porcentaje, p_monto, p_estado);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_comisiones_listar` ()   BEGIN
    SELECT * FROM comisiones;
END$$

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_conversaciones_actualizar` (IN `p_id` INT, IN `p_id_inmueble` INT, IN `p_estado` VARCHAR(50))   BEGIN
    UPDATE conversaciones SET id_inmueble = p_id_inmueble, estado = p_estado WHERE id_conversacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_conversaciones_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM conversaciones WHERE id_conversacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_conversaciones_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM conversaciones WHERE id_conversacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_conversaciones_insertar` (IN `p_id_inmueble` INT, IN `p_estado` VARCHAR(50))   BEGIN
    INSERT INTO conversaciones (id_inmueble, estado)
    VALUES (p_id_inmueble, p_estado);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_conversaciones_listar` ()   BEGIN
    SELECT * FROM conversaciones;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_escribanias_actualizar` (IN `p_id` INT, IN `p_nombre` VARCHAR(100), IN `p_telefono` VARCHAR(30), IN `p_correo` VARCHAR(100))   BEGIN
    UPDATE escribanias SET nombre = p_nombre, telefono = p_telefono, correo = p_correo WHERE id_escribania = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_escribanias_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM escribanias WHERE id_escribania = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_escribanias_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM escribanias WHERE id_escribania = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_escribanias_insertar` (IN `p_nombre` VARCHAR(100), IN `p_telefono` VARCHAR(30), IN `p_correo` VARCHAR(100))   BEGIN
    INSERT INTO escribanias (nombre, telefono, correo)
    VALUES (p_nombre, p_telefono, p_correo);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_escribanias_listar` ()   BEGIN
    SELECT * FROM escribanias;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_escrituras_actualizar` (IN `p_id` INT, IN `p_id_venta` INT, IN `p_id_escribania` INT, IN `p_fecha` DATE, IN `p_estado` VARCHAR(50))   BEGIN
    UPDATE escrituras SET id_venta = p_id_venta, id_escribania = p_id_escribania, fecha = p_fecha, estado = p_estado WHERE id_escritura = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_escrituras_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM escrituras WHERE id_escritura = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_escrituras_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM escrituras WHERE id_escritura = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_escrituras_insertar` (IN `p_id_venta` INT, IN `p_id_escribania` INT, IN `p_fecha` DATE, IN `p_estado` VARCHAR(50))   BEGIN
    INSERT INTO escrituras (id_venta, id_escribania, fecha, estado)
    VALUES (p_id_venta, p_id_escribania, p_fecha, p_estado);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_escrituras_listar` ()   BEGIN
    SELECT * FROM escrituras;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_franquicias_actualizar` (IN `p_id` INT, IN `p_id_solicitud` INT, IN `p_nombre` VARCHAR(100), IN `p_direccion` VARCHAR(255), IN `p_estado` VARCHAR(50))   BEGIN
    UPDATE franquicias SET id_solicitud = p_id_solicitud, nombre = p_nombre, direccion = p_direccion, estado = p_estado WHERE id_franquicia = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_franquicias_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM franquicias WHERE id_franquicia = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_franquicias_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM franquicias WHERE id_franquicia = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_franquicias_insertar` (IN `p_id_solicitud` INT, IN `p_nombre` VARCHAR(100), IN `p_direccion` VARCHAR(255), IN `p_estado` VARCHAR(50))   BEGIN
    INSERT INTO franquicias (id_solicitud, nombre, direccion, estado)
    VALUES (p_id_solicitud, p_nombre, p_direccion, p_estado);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_franquicias_listar` ()   BEGIN
    SELECT * FROM franquicias;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_historial_oferta_actualizar` (IN `p_id` INT, IN `p_id_oferta` INT, IN `p_id_usuario` INT, IN `p_fecha` DATETIME, IN `p_accion` VARCHAR(100), IN `p_monto` DECIMAL(15,2), IN `p_observaciones` VARCHAR(255))   BEGIN
    UPDATE historial_oferta SET id_oferta = p_id_oferta, id_usuario = p_id_usuario, fecha = p_fecha, accion = p_accion, monto = p_monto, observaciones = p_observaciones WHERE id_histoferta = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_historial_oferta_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM historial_oferta WHERE id_histoferta = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_historial_oferta_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM historial_oferta WHERE id_histoferta = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_historial_oferta_insertar` (IN `p_id_oferta` INT, IN `p_id_usuario` INT, IN `p_fecha` DATETIME, IN `p_accion` VARCHAR(100), IN `p_monto` DECIMAL(15,2), IN `p_observaciones` VARCHAR(255))   BEGIN
    INSERT INTO historial_oferta (id_oferta, id_usuario, fecha, accion, monto, observaciones)
    VALUES (p_id_oferta, p_id_usuario, p_fecha, p_accion, p_monto, p_observaciones);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_historial_oferta_listar` ()   BEGIN
    SELECT * FROM historial_oferta;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inmuebles_actualizar` (IN `p_id` INT, IN `p_direccion` VARCHAR(255), IN `p_tipo_inmueble` VARCHAR(50), IN `p_localidad` VARCHAR(100))   BEGIN
    UPDATE inmuebles SET direccion = p_direccion, tipo_inmueble = p_tipo_inmueble, localidad = p_localidad WHERE id_inmueble = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inmuebles_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM inmuebles WHERE id_inmueble = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inmuebles_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM inmuebles WHERE id_inmueble = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inmuebles_insertar` (IN `p_id_captacion` INT, IN `p_id_propietario` INT, IN `p_direccion` VARCHAR(255), IN `p_tipo_inmueble` VARCHAR(50), IN `p_sup_cubierta` DECIMAL(10,2), IN `p_sup_total` DECIMAL(10,2), IN `p_habitaciones` INT, IN `p_banos` INT, IN `p_cocheras` INT, IN `p_antiguedad` VARCHAR(100), IN `p_localidad` VARCHAR(100))   BEGIN
    INSERT INTO inmuebles (id_captacion, id_propietario, direccion, tipo_inmueble, superficie_cubierta, superficie_total, cantidad_habitaciones, cantidad_banos, cantidad_cocheras, antiguedad_estado, localidad)
    VALUES (p_id_captacion, p_id_propietario, p_direccion, p_tipo_inmueble, p_sup_cubierta, p_sup_total, p_habitaciones, p_banos, p_cocheras, p_antiguedad, p_localidad);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inmuebles_listar` ()   BEGIN
    SELECT * FROM inmuebles;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inmueble_caracteristicas_actualizar` (IN `p_id_inmueble` INT, IN `p_id_caracteristica` INT, IN `p_nuevo_id_caracteristica` INT)   BEGIN
    UPDATE inmueble_caracteristicas SET id_caracteristica = p_nuevo_id_caracteristica WHERE id_inmueble = p_id_inmueble AND id_caracteristica = p_id_caracteristica;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inmueble_caracteristicas_buscar_por_id` (IN `p_id_inmueble` INT, IN `p_id_caracteristica` INT)   BEGIN
    SELECT * FROM inmueble_caracteristicas WHERE id_inmueble = p_id_inmueble AND id_caracteristica = p_id_caracteristica;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inmueble_caracteristicas_eliminar` (IN `p_id_inmueble` INT, IN `p_id_caracteristica` INT)   BEGIN
    DELETE FROM inmueble_caracteristicas WHERE id_inmueble = p_id_inmueble AND id_caracteristica = p_id_caracteristica;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inmueble_caracteristicas_insertar` (IN `p_id_inmueble` INT, IN `p_id_caracteristica` INT)   BEGIN
    INSERT INTO inmueble_caracteristicas (id_inmueble, id_caracteristica)
    VALUES (p_id_inmueble, p_id_caracteristica);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inmueble_caracteristicas_listar` ()   BEGIN
    SELECT * FROM inmueble_caracteristicas;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mensajes_actualizar` (IN `p_id` INT, IN `p_id_conversacion` INT, IN `p_id_usuario` INT, IN `p_fecha_hora` DATETIME, IN `p_contenido` TEXT, IN `p_estado` VARCHAR(50))   BEGIN
    UPDATE mensajes SET id_conversacion = p_id_conversacion, id_usuario = p_id_usuario, fecha_hora = p_fecha_hora, contenido = p_contenido, estado = p_estado WHERE id_mensaje = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mensajes_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM mensajes WHERE id_mensaje = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mensajes_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM mensajes WHERE id_mensaje = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mensajes_insertar` (IN `p_id_conversacion` INT, IN `p_id_usuario` INT, IN `p_fecha_hora` DATETIME, IN `p_contenido` TEXT, IN `p_estado` VARCHAR(50))   BEGIN
    INSERT INTO mensajes (id_conversacion, id_usuario, fecha_hora, contenido, estado)
    VALUES (p_id_conversacion, p_id_usuario, p_fecha_hora, p_contenido, p_estado);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mensajes_listar` ()   BEGIN
    SELECT * FROM mensajes;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_multimedia_actualizar` (IN `p_id` INT, IN `p_id_inmueble` INT, IN `p_tipo` VARCHAR(50), IN `p_url` VARCHAR(500))   BEGIN
    UPDATE multimedia SET id_inmueble = p_id_inmueble, tipo = p_tipo, url = p_url WHERE id_multimedia = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_multimedia_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM multimedia WHERE id_multimedia = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_multimedia_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM multimedia WHERE id_multimedia = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_multimedia_insertar` (IN `p_id_inmueble` INT, IN `p_tipo` VARCHAR(50), IN `p_url` VARCHAR(500))   BEGIN
    INSERT INTO multimedia (id_inmueble, tipo, url)
    VALUES (p_id_inmueble, p_tipo, p_url);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_multimedia_listar` ()   BEGIN
    SELECT * FROM multimedia;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ofertas_actualizar` (IN `p_id` INT, IN `p_id_usuario` INT, IN `p_id_inmueble` INT, IN `p_monto` DECIMAL(15,2), IN `p_fecha` DATE, IN `p_estado` VARCHAR(50))   BEGIN
    UPDATE ofertas SET id_usuario = p_id_usuario, id_inmueble = p_id_inmueble, monto = p_monto, fecha = p_fecha, estado = p_estado WHERE id_oferta = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ofertas_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM ofertas WHERE id_oferta = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ofertas_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM ofertas WHERE id_oferta = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ofertas_insertar` (IN `p_id_usuario` INT, IN `p_id_inmueble` INT, IN `p_monto` DECIMAL(15,2), IN `p_fecha` DATE, IN `p_estado` VARCHAR(50))   BEGIN
    INSERT INTO ofertas (id_usuario, id_inmueble, monto, fecha, estado)
    VALUES (p_id_usuario, p_id_inmueble, p_monto, p_fecha, p_estado);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ofertas_listar` ()   BEGIN
    SELECT * FROM ofertas;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_operaciones_actualizar` (IN `p_id` INT, IN `p_id_oferta` INT, IN `p_tipo_operacion` VARCHAR(50), IN `p_fecha` DATE, IN `p_estado` VARCHAR(50))   BEGIN
    UPDATE operaciones SET id_oferta = p_id_oferta, tipo_operacion = p_tipo_operacion, fecha = p_fecha, estado = p_estado WHERE id_operacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_operaciones_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM operaciones WHERE id_operacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_operaciones_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM operaciones WHERE id_operacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_operaciones_insertar` (IN `p_id_oferta` INT, IN `p_tipo_operacion` VARCHAR(50), IN `p_fecha` DATE, IN `p_estado` VARCHAR(50))   BEGIN
    INSERT INTO operaciones (id_oferta, tipo_operacion, fecha, estado)
    VALUES (p_id_oferta, p_tipo_operacion, p_fecha, p_estado);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_operaciones_listar` ()   BEGIN
    SELECT * FROM operaciones;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_permisos_actualizar` (IN `p_id` INT, IN `p_nombre` VARCHAR(100), IN `p_descripcion` VARCHAR(255))   BEGIN
    UPDATE permisos SET nombre = p_nombre, descripcion = p_descripcion WHERE id_permiso = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_permisos_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM permisos WHERE id_permiso = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_permisos_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM permisos WHERE id_permiso = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_permisos_insertar` (IN `p_nombre` VARCHAR(100), IN `p_descripcion` VARCHAR(255))   BEGIN
    INSERT INTO permisos (nombre, descripcion)
    VALUES (p_nombre, p_descripcion);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_permisos_listar` ()   BEGIN
    SELECT * FROM permisos;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_personales_actualizar` (IN `p_id` INT, IN `p_id_usuario` INT, IN `p_id_franquicia` INT)   BEGIN
    UPDATE personales SET id_usuario = p_id_usuario, id_franquicia = p_id_franquicia WHERE id_personal = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_personales_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM personales WHERE id_personal = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_personales_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM personales WHERE id_personal = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_personales_insertar` (IN `p_id_usuario` INT, IN `p_id_franquicia` INT)   BEGIN
    INSERT INTO personales (id_usuario, id_franquicia)
    VALUES (p_id_usuario, p_id_franquicia);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_personales_listar` ()   BEGIN
    SELECT * FROM personales;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_publicaciones_actualizar` (IN `p_id` INT, IN `p_id_inmueble` INT, IN `p_id_agente` INT, IN `p_fecha` DATE)   BEGIN
    UPDATE publicaciones SET id_inmueble = p_id_inmueble, id_agente = p_id_agente, fecha = p_fecha WHERE id_publicacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_publicaciones_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM publicaciones WHERE id_publicacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_publicaciones_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM publicaciones WHERE id_publicacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_publicaciones_insertar` (IN `p_id_inmueble` INT, IN `p_id_agente` INT, IN `p_fecha` DATE)   BEGIN
    INSERT INTO publicaciones (id_inmueble, id_agente, fecha)
    VALUES (p_id_inmueble, p_id_agente, p_fecha);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_publicaciones_listar` ()   BEGIN
    SELECT * FROM publicaciones;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registrosalquiler_actualizar` (IN `p_id` INT, IN `p_id_alquiler` INT, IN `p_id_tipo` INT, IN `p_fecha` DATE, IN `p_monto` DECIMAL(15,2), IN `p_estado` VARCHAR(50), IN `p_observaciones` VARCHAR(255))   BEGIN
    UPDATE registrosalquiler SET id_alquiler = p_id_alquiler, id_tipo = p_id_tipo, fecha = p_fecha, monto = p_monto, estado = p_estado, observaciones = p_observaciones WHERE id_registro = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registrosalquiler_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM registrosalquiler WHERE id_registro = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registrosalquiler_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM registrosalquiler WHERE id_registro = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registrosalquiler_insertar` (IN `p_id_alquiler` INT, IN `p_id_tipo` INT, IN `p_fecha` DATE, IN `p_monto` DECIMAL(15,2), IN `p_estado` VARCHAR(50), IN `p_observaciones` VARCHAR(255))   BEGIN
    INSERT INTO registrosalquiler (id_alquiler, id_tipo, fecha, monto, estado, observaciones)
    VALUES (p_id_alquiler, p_id_tipo, p_fecha, p_monto, p_estado, p_observaciones);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registrosalquiler_listar` ()   BEGIN
    SELECT * FROM registrosalquiler;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_relacionpermisos_actualizar` (IN `p_id` INT, IN `p_id_rol` INT, IN `p_id_permiso` INT)   BEGIN
    UPDATE relacionpermisos SET id_rol = p_id_rol, id_permiso = p_id_permiso WHERE id_relpermiso = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_relacionpermisos_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM relacionpermisos WHERE id_relpermiso = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_relacionpermisos_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM relacionpermisos WHERE id_relpermiso = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_relacionpermisos_insertar` (IN `p_id_rol` INT, IN `p_id_permiso` INT)   BEGIN
    INSERT INTO relacionpermisos (id_rol, id_permiso)
    VALUES (p_id_rol, p_id_permiso);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_relacionpermisos_listar` ()   BEGIN
    SELECT * FROM relacionpermisos;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_relacionroles_actualizar` (IN `p_id` INT, IN `p_id_usuario` INT, IN `p_id_rol` INT)   BEGIN
    UPDATE relacionroles SET id_usuario = p_id_usuario, id_rol = p_id_rol WHERE id_relrol = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_relacionroles_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM relacionroles WHERE id_relrol = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_relacionroles_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM relacionroles WHERE id_relrol = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_relacionroles_insertar` (IN `p_id_usuario` INT, IN `p_id_rol` INT)   BEGIN
    INSERT INTO relacionroles (id_usuario, id_rol)
    VALUES (p_id_usuario, p_id_rol);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_relacionroles_listar` ()   BEGIN
    SELECT * FROM relacionroles;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_roles_actualizar` (IN `p_id` INT, IN `p_nombre` VARCHAR(50), IN `p_descripcion` VARCHAR(255))   BEGIN
    UPDATE roles SET nombre = p_nombre, descripcion = p_descripcion WHERE id_rol = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_roles_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM roles WHERE id_rol = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_roles_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM roles WHERE id_rol = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_roles_insertar` (IN `p_nombre` VARCHAR(50), IN `p_descripcion` VARCHAR(255))   BEGIN
    INSERT INTO roles (nombre, descripcion)
    VALUES (p_nombre, p_descripcion);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_roles_listar` ()   BEGIN
    SELECT * FROM roles;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_solifranquicias_actualizar` (IN `p_id` INT, IN `p_id_usuario` INT, IN `p_fecha` DATE, IN `p_estado` VARCHAR(50), IN `p_observaciones` VARCHAR(255))   BEGIN
    UPDATE solifranquicias SET id_usuario = p_id_usuario, fecha = p_fecha, estado = p_estado, observaciones = p_observaciones WHERE id_solicitud = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_solifranquicias_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM solifranquicias WHERE id_solicitud = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_solifranquicias_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM solifranquicias WHERE id_solicitud = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_solifranquicias_insertar` (IN `p_id_usuario` INT, IN `p_fecha` DATE, IN `p_estado` VARCHAR(50), IN `p_observaciones` VARCHAR(255))   BEGIN
    INSERT INTO solifranquicias (id_usuario, fecha, estado, observaciones)
    VALUES (p_id_usuario, p_fecha, p_estado, p_observaciones);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_solifranquicias_listar` ()   BEGIN
    SELECT * FROM solifranquicias;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tasaciones_actualizar` (IN `p_id` INT, IN `p_id_inmueble` INT, IN `p_id_encargado` INT, IN `p_valor_estimado` DECIMAL(15,2), IN `p_valor_final` DECIMAL(15,2), IN `p_fecha` DATE, IN `p_estado` VARCHAR(50))   BEGIN
    UPDATE tasaciones SET id_inmueble = p_id_inmueble, id_encargado = p_id_encargado, valor_estimado = p_valor_estimado, valor_final = p_valor_final, fecha = p_fecha, estado = p_estado WHERE id_tasacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tasaciones_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM tasaciones WHERE id_tasacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tasaciones_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM tasaciones WHERE id_tasacion = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tasaciones_insertar` (IN `p_id_inmueble` INT, IN `p_id_encargado` INT, IN `p_valor_estimado` DECIMAL(15,2), IN `p_valor_final` DECIMAL(15,2), IN `p_fecha` DATE, IN `p_estado` VARCHAR(50))   BEGIN
    INSERT INTO tasaciones (id_inmueble, id_encargado, valor_estimado, valor_final, fecha, estado)
    VALUES (p_id_inmueble, p_id_encargado, p_valor_estimado, p_valor_final, p_fecha, p_estado);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tasaciones_listar` ()   BEGIN
    SELECT * FROM tasaciones;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tiporegistro_actualizar` (IN `p_id` INT, IN `p_nombre` VARCHAR(50), IN `p_descripcion` VARCHAR(255))   BEGIN
    UPDATE tiporegistro SET nombre = p_nombre, descripcion = p_descripcion WHERE id_tipo = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tiporegistro_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM tiporegistro WHERE id_tipo = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tiporegistro_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM tiporegistro WHERE id_tipo = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tiporegistro_insertar` (IN `p_nombre` VARCHAR(50), IN `p_descripcion` VARCHAR(255))   BEGIN
    INSERT INTO tiporegistro (nombre, descripcion)
    VALUES (p_nombre, p_descripcion);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_tiporegistro_listar` ()   BEGIN
    SELECT * FROM tiporegistro;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_usuarios_actualizar` (IN `p_id` INT, IN `p_nombre` VARCHAR(50), IN `p_apellido` VARCHAR(50), IN `p_correo` VARCHAR(100))   BEGIN
    UPDATE usuarios SET nombre = p_nombre, apellido = p_apellido, correo = p_correo WHERE id_usuario = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_usuarios_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM usuarios WHERE id_usuario = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_usuarios_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM usuarios WHERE id_usuario = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_usuarios_insertar` (IN `p_nombre` VARCHAR(50), IN `p_apellido` VARCHAR(50), IN `p_tag` VARCHAR(50), IN `p_correo` VARCHAR(100), IN `p_upassword` VARCHAR(255), IN `p_dni` VARCHAR(20))   BEGIN
    INSERT INTO usuarios (nombre, apellido, tag, correo, upassword, dni)
    VALUES (p_nombre, p_apellido, p_tag, p_correo, p_upassword, p_dni);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_usuarios_listar` ()   BEGIN
    SELECT * FROM usuarios;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ventas_actualizar` (IN `p_id` INT, IN `p_id_operacion` INT, IN `p_fecha` DATE, IN `p_monto_final` DECIMAL(15,2), IN `p_estado` VARCHAR(50))   BEGIN
    UPDATE ventas SET id_operacion = p_id_operacion, fecha = p_fecha, monto_final = p_monto_final, estado = p_estado WHERE id_venta = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ventas_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM ventas WHERE id_venta = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ventas_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM ventas WHERE id_venta = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ventas_insertar` (IN `p_id_operacion` INT, IN `p_fecha` DATE, IN `p_monto_final` DECIMAL(15,2), IN `p_estado` VARCHAR(50))   BEGIN
    INSERT INTO ventas (id_operacion, fecha, monto_final, estado)
    VALUES (p_id_operacion, p_fecha, p_monto_final, p_estado);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ventas_listar` ()   BEGIN
    SELECT * FROM ventas;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_visitas_actualizar` (IN `p_id` INT, IN `p_id_inmueble` INT, IN `p_id_usuario` INT, IN `p_fecha` DATETIME, IN `p_estado` VARCHAR(50), IN `p_observaciones` VARCHAR(255))   BEGIN
    UPDATE visitas SET id_inmueble = p_id_inmueble, id_usuario = p_id_usuario, fecha = p_fecha, estado = p_estado, observaciones = p_observaciones WHERE id_visita = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_visitas_buscar_por_id` (IN `p_id` INT)   BEGIN
    SELECT * FROM visitas WHERE id_visita = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_visitas_eliminar` (IN `p_id` INT)   BEGIN
    DELETE FROM visitas WHERE id_visita = p_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_visitas_insertar` (IN `p_id_inmueble` INT, IN `p_id_usuario` INT, IN `p_fecha` DATETIME, IN `p_estado` VARCHAR(50), IN `p_observaciones` VARCHAR(255))   BEGIN
    INSERT INTO visitas (id_inmueble, id_usuario, fecha, estado, observaciones)
    VALUES (p_id_inmueble, p_id_usuario, p_fecha, p_estado, p_observaciones);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_visitas_listar` ()   BEGIN
    SELECT * FROM visitas;
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
(12, 's', 's', 'coquito', 's@gmail.com', '$2a$10$rpMO1/8TkLzMsZ272OYBu.ykW5WrIwWOcKk3Cz73znsUsgfxH5Gni', '12312312'),
(13, 'yo', 'yo', 'yoo', 'yo@yahoo.com', '$2a$10$TKQknAiqoqBGgJGPYb5pFeppGeQKQrZsqG7b8Cjxd.MRidLTQHBri', '12345678');

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
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

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
