DELIMITER $$

-- Trigger 1: Auditoría automática en el historial de ofertas al crear una nueva oferta
DROP TRIGGER IF EXISTS trg_after_insert_oferta$$
CREATE TRIGGER trg_after_insert_oferta
AFTER INSERT ON Ofertas
FOR EACH ROW
BEGIN
    INSERT INTO Historial_oferta (id_oferta, id_usuario, fecha, accion, monto, observaciones)
    VALUES (NEW.id_oferta, NEW.id_usuario, NOW(), 'CREACION_OFERTA', NEW.monto, 'Oferta ingresada automáticamente al sistema');
END$$

-- Trigger 2: Validación de superficie (evita que la cubierta sea mayor a la total)
DROP TRIGGER IF EXISTS trg_before_insert_inmueble$$
CREATE TRIGGER trg_before_insert_inmueble
BEFORE INSERT ON Inmuebles
FOR EACH ROW
BEGIN
    IF NEW.superficie_cubierta > NEW.superficie_total THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: La superficie cubierta no puede ser mayor que la superficie total del inmueble.';
    END IF;
END$$

DELIMITER ;