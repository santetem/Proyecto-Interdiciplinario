-- Vista Solicitudes Franquicias
create view vista_solicitudes as
select 
s.id_solicitud, 
u.id_usuario,
u.nombre, 
u.apellido,
u.correo,
s.fecha,
s.estado,
s.observaciones
from SoliFranquicias s
join Usuarios u on s.id_usuario = u.id_usuario;
