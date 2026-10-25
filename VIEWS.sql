USE remax;

-- Vista 1: Detalle completo de inmuebles con sus propietarios y estado de captación
CREATE OR REPLACE VIEW vw_Inmuebles_Detallados AS
SELECT 
    i.id_inmueble,
    i.tipo_inmueble,
    i.direccion,
    i.superficie_cubierta,
    i.superficie_total,
    i.cantidad_habitaciones,
    i.cantidad_banos,
    CONCAT(u.nombre, ' ', u.apellido) AS propietario_nombre,
    u.correo AS propietario_correo,
    c.estado AS estado_captacion,
    c.fecha AS fecha_captacion
FROM Inmuebles i
JOIN Usuarios u ON i.id_propietario = u.id_usuario
JOIN Captaciones c ON i.id_captacion = c.id_captacion;

-- Vista 2: Resumen de operaciones cerradas con comisiones y agentes
CREATE OR REPLACE VIEW vw_Operaciones_Comisiones AS
SELECT 
    o.id_operacion,
    o.tipo_operacion,
    o.fecha AS fecha_operacion,
    o.estado AS estado_operacion,
    ofe.monto AS monto_oferta,
    CONCAT(u.nombre, ' ', u.apellido) AS usuario_responsable,
    com.porcentaje AS comision_porcentaje,
    com.monto AS comision_monto
FROM Operaciones o
JOIN Ofertas ofe ON o.id_oferta = ofe.id_oferta
JOIN Comisiones com ON o.id_operacion = com.id_operacion
JOIN Usuarios u ON com.id_usuario = u.id_usuario;