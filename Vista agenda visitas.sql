CREATE OR REPLACE VIEW vista_agenda_visitas AS
SELECT 
    v.id_visita,
    v.fecha AS fecha_hora_cita,
    v.estado AS estado_visita,
    v.observaciones AS comentarios_cliente,
    i.direccion AS inmueble_direccion,
    i.tipo_inmueble,
    CONCAT(u_cli.nombre, ' ', u_cli.apellido) AS cliente_nombre,
    u_cli.correo AS cliente_correo
FROM Visitas v
JOIN Inmuebles i ON v.id_inmueble = i.id_inmueble
JOIN Usuarios u_cli ON v.id_usuario = u_cli.id_usuario;
