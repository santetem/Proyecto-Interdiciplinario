USE remax;

DELIMITER $$

DROP PROCEDURE IF EXISTS sp_ConcretarVenta$$
CREATE PROCEDURE sp_ConcretarVenta(
    IN p_id_oferta INT,
    IN p_monto_final DECIMAL(15,2),
    IN p_id_usuario_comision INT,
    IN p_porcentaje_comision DECIMAL(5,2)
)
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

DELIMITER ;