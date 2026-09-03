DELIMITER //

CREATE PROCEDURE sp_registrar_operacion(
    IN p_id_oferta INT,
    IN p_tipo_operacion VARCHAR(50), 
    IN p_monto_final DECIMAL(15,2),
    IN p_meses_alquiler INT 
)
BEGIN
    DECLARE v_id_operacion INT;

  
    INSERT INTO Operaciones (id_oferta, tipo_operacion, fecha, estado)
    VALUES (p_id_oferta, p_tipo_operacion, CURDATE(), 'Completada');
    
    SET v_id_operacion = LAST_INSERT_ID();

  
    IF p_tipo_operacion = 'Venta' THEN
     
        INSERT INTO Ventas (id_operacion, fecha, monto_final, estado)
        VALUES (v_id_operacion, CURDATE(), p_monto_final, 'Pendiente Escritura');
        
    ELSEIF p_tipo_operacion = 'Alquiler' THEN
       
        INSERT INTO Alquileres (id_operacion, fecha_inicio, fecha_fin, monto, estado)
        VALUES (v_id_operacion, CURDATE(), DATE_ADD(CURDATE(), INTERVAL p_meses_alquiler MONTH), p_monto_final, 'Activo');
        
    END IF;

    
    UPDATE Ofertas SET estado = 'Aceptada' WHERE id_oferta = p_id_oferta;

END //

DELIMITER ;
