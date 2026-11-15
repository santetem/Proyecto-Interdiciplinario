-- OPCIONAL.  (remax) ya trae usuarios de prueba con rol (clave 123456):
--   santiago.diaz@remax.com      -> Cliente
--   jeremias.nieto@remax.com     -> Propietario
--   alba.spitalnik@remax.com     -> Agente
--   tiziano.giacomozzi@remax.com -> PersonalFranquicia
--   gian.arguello@remax.com      -> Gerente
--   gonzalo.consorti@remax.com   -> Administrador
-- Los usuarios 1 a 4 (carlos, ana, juan y admin@remax.com) NO tienen fila en relacionroles,
-- por eso no pueden hacer nada que pida rol. Si quiero que admin@remax.com sea Administrador:

INSERT INTO relacionroles (id_usuario, id_rol)
SELECT 4, 4 FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM relacionroles WHERE id_usuario = 4 AND id_rol = 4);
