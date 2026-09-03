DELIMITER //

CREATE PROCEDURE sp_registrar_tasacion_manual(
    IN p_id_inmueble INT,
    IN p_id_encargado INT,
    IN p_valor_estimado DECIMAL(15,2),
    IN p_valor_manual DECIMAL(15,2)
)
BEGIN
    DECLARE v_precio_definitivo DECIMAL(15,2);

    
    IF p_valor_manual IS NOT NULL AND p_valor_manual > 0 THEN
        SET v_precio_definitivo = p_valor_manual;
    ELSE
        SET v_precio_definitivo = p_valor_estimado;
    END IF;

    
    INSERT INTO Tasaciones (id_inmueble, id_encargado, valor_estimado, valor_final, fecha, estado)
    VALUES (p_id_inmueble, p_id_encargado, p_valor_estimado, v_precio_definitivo, CURDATE(), 'Finalizada');

END //

DELIMITER ;
