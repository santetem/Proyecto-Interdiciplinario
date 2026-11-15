nmobiliaria – API REST (Spring Boot + MySQL)

Frontend (HTML/CSS/JS estilo REMAX) -> API en STS (puerto 8086) -> MySQL en phpMyAdmin (base remax).

Arquitectura (para tener fresco en la defensa)

Flujo de datos:
Frontend (fetch) -> Controller (HTTP/JSON + códigos + roles) -> Service (reglas de negocio + validación) -> DAO / Repository (acceso a datos JPA / EntityManager) -> Stored Procedures (sp_General_*) -> MySQL

Capas del proyecto:

Controller (paquete controller): Recibe las peticiones HTTP y devuelve un ResponseEntity con el código correspondiente. Cero lógica de negocio acá. Ejemplo: InmuebleController.

Service (paquete service): Valida con throw, arma los datos necesarios y llama al DAO. Ejemplo: ImplementacionInmuebleService.

DAO (paquete dao): Se encarga de ejecutar los stored procedures. Usamos un DAO genérico para casi todo. Ejemplo: ImplementacionGenericDao.

Factory (paquete factory): Devuelve los DAOs como interfaz para desacoplar código. Ejemplo: IDaoFactory, MySQLDaoFactory.

DTO (paquete dto): Los objetos que viajan por la API (cuidamos de no exponer la entidad completa ni las contraseñas). Ejemplo: InmuebleDTO.

Modelo (paquete model): Las entidades JPA que mapean con las tablas. Ejemplo: Inmueble.

Seguridad (paquete security): Manejo de sesión por token y control de roles. Ejemplo: AuthInterceptor, @RequiereRol.

Errores (paquete exception): Captura y traduce las excepciones a códigos HTTP claros. Ejemplo: GlobalExceptionHandler.

Diferencia clave con Servlets (lo que vimos en clase):
En lugar de lidiar con una clase HttpServlet y métodos manuales doGet/doPost, Spring Boot usa @RestController junto a anotaciones como @GetMapping o @PostMapping. Spring se encarga de instanciar todo y se lo inyecta por constructor (inyección de dependencias), además de encargarse de la serialización automática de objetos a JSON (vía Jackson). El concepto por detrás sigue siendo el mismo: Controller -> DAO -> Base de datos.

Patrones aplicados:
Singleton (Conexion), Factory (IDaoFactory / MySQLDaoFactory), DAO (*Repository), DTO (dto/), y el clásico MVC (con REST sirviendo JSON).

Correcciones hechas a partir de la devolución del profe

Arquitectura Controller-Service-DAO: Limpiamos los controllers para que solo deleguen en los services, y los services ahora usan la Factory obligatoriamente.

Seguridad y roles: El login ahora devuelve un token. El AuthInterceptor se encarga de validarlo y @RequiereRol filtra quién puede entrar a qué endpoint (devuelve 401 si no hay sesión o 403 si no tiene el rol necesario). Los roles vienen directo de la base (roles / relacionroles).

Uso de la Factory: Armamos IDaoFactory (con un método por cada uno de los 30 DAOs), MySQLDaoFactory y DaoFactory. Los services piden la fábrica y no implementaciones concretas.

Limpieza en Git: Revisamos el zip para que no queden conflictos de fusión (<<<<<<<).

Tests unitarios: Reescribimos los tests para que prueben los métodos reales de DAO, Services, Controllers, Factory y seguridad usando JUnit 5 + Mockito (sin levantar MySQL). Sacamos los tests que solo comparaban literales duros.

Códigos HTTP: Todos los endpoints responden con ResponseEntity adecuado: 200, 201, 400, 401, 403, 404, 409 o 500 gestionados desde el GlobalExceptionHandler.

Postman: Dejamos la colección lista en docs/postman/Inmobiliaria.postman_collection.json (36 requests con validaciones de status integradas).

Diagrama de clases: Subimos la imagen a docs/diagrama-clases.png (con su archivo fuente .mmd).

Seguridad extra (SQL Injection): En los services filtramos y pasamos todo texto ingresado por el usuario a través de SqlUtil para escapar comillas y barras.

Roles y Permisos (según la tabla roles):

GET /inmuebles y GET /inmuebles/{id}: Público, Cliente, Propietario, Agente, Gerente, Administrador.

POST / PUT / PATCH /inmuebles: Agente, Gerente, Administrador (Público y Cliente/Propietario denegados).

DELETE /inmuebles/{id}: Solo Administrador.

/alquileres y /ventas: Agente, Gerente, Administrador (Nota: DELETE en alquileres es solo para Administrador).

GET /api/usuarios y GET /api/usuarios/{id}: Solo Administrador.

register y login: Todos (Público y cualquier rol).

Nota: El token se manda siempre en la cabecera como "Authorization: Bearer ". Todo usuario nuevo que se registra se crea automáticamente con rol Cliente gracias al stored procedure sp_AltaUsuarioConRol.

Detalles de la Base de Datos:

Usamos sp_General_* (listar, buscar, insertar, actualizar, eliminar) desde el DAO genérico.

sp_AltaUsuarioConRol: maneja el alta de usuario y asignación de rol en una misma transacción.

sp_ConcretarVenta: valida la oferta, genera la operación, la venta y la comisión correspondiente (hace rollback si algo falla).

Los triggers de MySQL (por ejemplo, cuando la superficie cubierta es mayor a la total, o si mandan un monto menor o igual a cero) tiran un SQLSTATE 45000 que la API intercepta y devuelve prolijamente como un 400 Bad Request con el mensaje original del trigger.

Contraseñas: las nuevas se guardan hasheadas con BCrypt, pero por compatibilidad con los datos de prueba el login también se banca contraseñas en texto plano (como 123456).
