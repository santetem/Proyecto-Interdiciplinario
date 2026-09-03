CREATE OR REPLACE VIEW vista_inventario_completo AS
SELECT 
    i.id_inmueble,
    i.direccion,
    i.tipo_inmueble,
    i.caracteristicas,
    c.estado AS estado_captacion,
    CONCAT(u_prop.nombre, ' ', u_prop.apellido) AS propietario_nombre,
    u_prop.correo AS propietario_correo,
    CONCAT(u_op.nombre, ' ', u_op.apellido) AS agente_responsable
FROM Inmuebles i
JOIN Captaciones c ON i.id_captacion = c.id_captacion
JOIN Usuarios u_prop ON i.id_propietario = u_prop.id_usuario
JOIN Usuarios u_op ON c.id_operador = u_op.id_usuario;
