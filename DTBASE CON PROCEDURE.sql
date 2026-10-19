USE remax;

-- DELIMITER modifica el terminador para que MySQL compile los bloques completos
DELIMITER $$

-- ====================================================================
-- 1. PROCEDIMIENTO GENERAL PARA LISTAR TODO
-- ====================================================================
DROP PROCEDURE IF EXISTS sp_General_Listar$$
CREATE PROCEDURE sp_General_Listar(
    IN p_nombre_tabla VARCHAR(64)
)
BEGIN
    -- Construimos la consulta concatenando de forma segura la tabla
    SET @sql_query = CONCAT('SELECT * FROM ', p_nombre_tabla);
    
    -- Preparamos y ejecutamos la sentencia dinámica
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END$$

-- ====================================================================
-- 2. PROCEDIMIENTO GENERAL PARA BUSCAR POR ID
-- ====================================================================
DROP PROCEDURE IF EXISTS sp_General_BuscarPorId$$
CREATE PROCEDURE sp_General_BuscarPorId(
    IN p_nombre_tabla VARCHAR(64),
    IN p_nombre_id VARCHAR(64),
    IN p_id INT
)
BEGIN
    SET @sql_query = CONCAT('SELECT * FROM ', p_nombre_tabla, ' WHERE ', p_nombre_id, ' = ?');
    SET @id_val = p_id;
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt USING @id_val;
    DEALLOCATE PREPARE stmt;
END$$

-- ====================================================================
-- 3. PROCEDIMIENTO GENERAL PARA INSERTAR REGISTROS
-- ====================================================================
DROP PROCEDURE IF EXISTS sp_General_Insertar$$
CREATE PROCEDURE sp_General_Insertar(
    IN p_nombre_tabla VARCHAR(64),
    IN p_columnas TEXT,
    IN p_valores TEXT
)
BEGIN
    -- Une las columnas y strings de valores pasados desde la lógica de Java
    SET @sql_query = CONCAT('INSERT INTO ', p_nombre_tabla, ' (', p_columnas, ') VALUES (', p_valores, ')');
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END$$

-- ====================================================================
-- 4. PROCEDIMIENTO GENERAL PARA ACTUALIZAR (PUT Y PATCH)
-- ====================================================================
DROP PROCEDURE IF EXISTS sp_General_Actualizar$$
CREATE PROCEDURE sp_General_Actualizar(
    IN p_nombre_tabla VARCHAR(64),
    IN p_set_valores TEXT,
    IN p_nombre_id VARCHAR(64),
    IN p_id INT
)
BEGIN
    SET @sql_query = CONCAT('UPDATE ', p_nombre_tabla, ' SET ', p_set_valores, ' WHERE ', p_nombre_id, ' = ?');
    SET @id_val = p_id;
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt USING @id_val;
    DEALLOCATE PREPARE stmt;
END$$

-- ====================================================================
-- 5. PROCEDIMIENTO GENERAL PARA ELIMINAR POR ID
-- ====================================================================
DROP PROCEDURE IF EXISTS sp_General_Eliminar$$
CREATE PROCEDURE sp_General_Eliminar(
    IN p_nombre_tabla VARCHAR(64),
    IN p_nombre_id VARCHAR(64),
    IN p_id INT
)
BEGIN
    SET @sql_query = CONCAT('DELETE FROM ', p_nombre_tabla, ' WHERE ', p_nombre_id, ' = ?');
    SET @id_val = p_id;
    
    PREPARE stmt FROM @sql_query;
    EXECUTE stmt USING @id_val;
    DEALLOCATE PREPARE stmt;
END$$

-- Restauramos el delimitador clásico de comandos
DELIMITER ;
