-- Vista Franquicias
create view vista_franquicias as
select
f.id_franquicia,
f.nombre as nombre_franquicia,
f.direccion,
f.estado as estado_franquicia,
u.id_usuario,
u.nombre as nombre_usuario,
u.apellido as apellido_usuario,
u.correo
from franquicias f
join solicitud s on f.id_solicitud = s.id_solicitud
join usuario u on s.id_usuario = u.id_usuario;
