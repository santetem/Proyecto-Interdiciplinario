DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AceptarOferta` (IN `p_id_oferta` INT, IN `p_tipo_operacion` VARCHAR(50))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    START TRANSACTION;

    UPDATE ofertas SET estado = 'Aceptada' WHERE id_oferta = p_id_oferta;

    INSERT INTO operaciones (id_oferta, tipo_operacion, fecha, estado)
    VALUES (p_id_oferta, p_tipo_operacion, CURDATE(), 'Completada');

    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AltaUsuarioConRol` (IN `p_nombre` VARCHAR(50), IN `p_apellido` VARCHAR(50), IN `p_tag` VARCHAR(50), IN `p_correo` VARCHAR(100), IN `p_upassword` VARCHAR(255), IN `p_dni` VARCHAR(20), IN `p_id_rol` INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    START TRANSACTION;

    INSERT INTO usuarios (nombre, apellido, tag, correo, upassword, dni)
    VALUES (p_nombre, p_apellido, p_tag, p_correo, p_upassword, p_dni);

    INSERT INTO relacionroles (id_usuario, id_rol)
    VALUES (LAST_INSERT_ID(), p_id_rol);

    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_BuscarUsuarioPorCorreo` (IN `p_correo` VARCHAR(100))
BEGIN
    SELECT id_usuario, nombre, apellido, tag, correo, dni
    FROM usuarios
    WHERE correo = p_correo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_CambiarEstadoVisita` (IN `p_id_visita` INT, IN `p_estado` VARCHAR(50))
BEGIN
    UPDATE visitas SET estado = p_estado WHERE id_visita = p_id_visita;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_CaracteristicasDeInmueble` (IN `p_id_inmueble` INT)
BEGIN
    SELECT c.categoria, c.nombre
    FROM inmueble_caracteristicas ic
    JOIN caracteristicas c ON c.id_caracteristica = ic.id_caracteristica
    WHERE ic.id_inmueble = p_id_inmueble;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ConcretarVenta` (IN `p_id_oferta` INT, IN `p_monto_final` DECIMAL(15,2), IN `p_id_usuario_comision` INT, IN `p_porcentaje_comision` DECIMAL(5,2))
BEGIN
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_General_Actualizar` (IN `p_nombre_tabla` VARCHAR(64), IN `p_set_valores` TEXT, IN `p_nombre_id` VARCHAR(64), IN `p_id` INT)
BEGIN
    SET @sql_query = CONCAT('UPDATE ', p_nombre_tabla, ' SET ', p_set_valores, ' WHERE ', p_nombre_id, ' = ?');
    SET @id_val = p_id;
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt USING @id_val;
    DEALLOCATE PREPARE stmt;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_General_BuscarPorId` (IN `p_nombre_tabla` VARCHAR(64), IN `p_nombre_id` VARCHAR(64), IN `p_id` INT)
BEGIN
    SET @sql_query = CONCAT('SELECT * FROM ', p_nombre_tabla, ' WHERE ', p_nombre_id, ' = ?');
    SET @id_val = p_id;
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt USING @id_val;
    DEALLOCATE PREPARE stmt;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_General_Eliminar` (IN `p_nombre_tabla` VARCHAR(64), IN `p_nombre_id` VARCHAR(64), IN `p_id` INT)
BEGIN
    SET @sql_query = CONCAT('DELETE FROM ', p_nombre_tabla, ' WHERE ', p_nombre_id, ' = ?');
    SET @id_val = p_id;
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt USING @id_val;
    DEALLOCATE PREPARE stmt;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_General_Insertar` (IN `p_nombre_tabla` VARCHAR(64), IN `p_columnas` TEXT, IN `p_valores` TEXT)
BEGIN
    -- Une las columnas y strings de valores pasados desde la lógica de Java
    SET @sql_query = CONCAT('INSERT INTO ', p_nombre_tabla, ' (', p_columnas, ') VALUES (', p_valores, ')');
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_General_Listar` (IN `p_nombre_tabla` VARCHAR(64))
BEGIN
    -- Construimos la consulta concatenando de forma segura la tabla
    SET @sql_query = CONCAT('SELECT * FROM ', p_nombre_tabla);
    
    -- Preparamos y ejecutamos la sentencia dinámica
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_InmueblesPorLocalidad` ()
BEGIN
    SELECT localidad, COUNT(*) AS cantidad
    FROM inmuebles
    GROUP BY localidad
    ORDER BY cantidad DESC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_OfertasPorInmueble` (IN `p_id_inmueble` INT)
BEGIN
    SELECT * FROM ofertas
    WHERE id_inmueble = p_id_inmueble
    ORDER BY monto DESC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_PermisosDeRol` (IN `p_id_rol` INT)
BEGIN
    SELECT p.nombre, p.descripcion
    FROM relacionpermisos rp
    JOIN permisos p ON p.id_permiso = rp.id_permiso
    WHERE rp.id_rol = p_id_rol;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_RegistrarInmuebleBasico` (IN `p_id_operador` INT, IN `p_id_propietario` INT, IN `p_direccion` VARCHAR(255), IN `p_tipo_inmueble` VARCHAR(50), IN `p_localidad` VARCHAR(100))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    START TRANSACTION;

    INSERT INTO captaciones (id_operador, id_propietario, fecha, estado)
    VALUES (p_id_operador, p_id_propietario, CURDATE(), 'Activa');

    INSERT INTO inmuebles (id_captacion, id_propietario, direccion, tipo_inmueble, localidad)
    VALUES (LAST_INSERT_ID(), p_id_propietario, p_direccion, p_tipo_inmueble, p_localidad);

    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_RolesDeUsuario` (IN `p_id_usuario` INT)
BEGIN
    SELECT r.id_rol, r.nombre
    FROM relacionroles rr
    JOIN roles r ON r.id_rol = rr.id_rol
    WHERE rr.id_usuario = p_id_usuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_VisitasPorUsuario` (IN `p_id_usuario` INT)
BEGIN
    SELECT * FROM visitas
    WHERE id_usuario = p_id_usuario
    ORDER BY fecha DESC;
END$$

DELIMITER ;