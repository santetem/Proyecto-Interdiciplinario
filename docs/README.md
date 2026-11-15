# Inmobiliaria – API REST (Spring Boot + MySQL)

Frontend (HTML/CSS/JS estilo REMAX) → **API en STS (puerto 8086)** → **MySQL en phpMyAdmin (base `remax`)**.

## 1. Arquitectura (para la defensa)

```
Frontend (fetch)  →  Controller  →  Service  →  DAO (Repository)  →  Stored Procedures  →  MySQL
                      HTTP/JSON     reglas de     acceso a datos        sp_General_*
                      + códigos     negocio       (JPA / EntityManager)
                      + roles       + validación
```

| Capa | Paquete | Qué hace | Ejemplo (Inmueble) |
|---|---|---|---|
| Controller | `controller` | Recibe HTTP, devuelve `ResponseEntity` con código HTTP. No tiene lógica de negocio. | `InmuebleController` |
| Service | `service` | Valida (con `throw`), arma los datos y llama al DAO. | `ImplementacionInmuebleService` |
| DAO | `dao` | Ejecuta los stored procedures. Un DAO genérico sirve a todas las entidades. | `ImplementacionGenericDao` |
| Factory | `factory` | Entrega los DAOs como interfaz. | `IDaoFactory`, `MySQLDaoFactory` |
| DTO | `dto` | Lo que viaja por la API (no se expone la entidad ni la contraseña). | `InmuebleDTO` |
| Modelo | `model` | Entidades JPA (tablas). | `Inmueble` |
| Seguridad | `security` | Sesión por token + roles. | `AuthInterceptor`, `@RequiereRol` |
| Errores | `exception` | Traduce excepciones a códigos HTTP. | `GlobalExceptionHandler` |

**Diferencia con Servlets (visto en clase):** en vez de una clase `HttpServlet` con `doGet/doPost`, Spring Boot usa `@RestController` con `@GetMapping/@PostMapping/...`.
Spring crea los objetos y los **inyecta por constructor** (inyección de dependencias), y convierte JSON ↔ objetos Java solo (Jackson). La idea es la misma: Controller (MVC) → DAO (acceso a datos) → BD.

**Patrones:** Singleton (`Conexion`), Factory (`IDaoFactory`/`MySQLDaoFactory`/`DaoFactory`), DAO (`*Repository`), DTO (`dto/`), MVC (model / controller / REST como vista JSON).

## 2. Qué se corrigió según la devolución del profe

| Observación | Qué se hizo |
|---|---|
| Arquitectura Controller–Service–DAO | Los controllers solo llaman a services; services usan la Factory. Ver sección 1 y `diagrama-clases.png`. |
| Seguridad / roles | Login devuelve un **token**; `AuthInterceptor` lo valida y `@RequiereRol` define qué rol puede cada endpoint (401 sin sesión, 403 sin rol). Los roles salen de la tabla `roles` / `relacionroles` de la base. |
| Factory | `IDaoFactory` (+ 1 método por cada uno de los 30 DAOs), `MySQLDaoFactory`, `DaoFactory`. Los services reciben la fábrica, no los DAOs concretos. |
| Git | En el zip entregado no hay marcadores `<<<<<<<`. Ver checklist de Git abajo. |
| Tests | Reescritos: prueban los métodos **reales** de DAO, Services, Controllers, Factory y seguridad con JUnit 5 + Mockito (sin MySQL). Se borraron los tests que comparaban literales. |
| Códigos HTTP | Todos los endpoints devuelven `ResponseEntity`: 200, 201, 400, 401, 403, 404, 409, 500 (`GlobalExceptionHandler`). |
| Postman | `docs/postman/Inmobiliaria.postman_collection.json` (36 requests con tests de status). |
| Diagrama de clases | `docs/diagrama-clases.png` (fuente `.mmd`). |
| SQL Injection (hallazgo extra) | Los services concatenaban texto del usuario en el SQL. Ahora todo pasa por `SqlUtil` (escapa comillas y barras). |

### Roles y permisos (nombres exactos de la tabla `roles`)
| Endpoint | Público | Cliente / Propietario | Agente | Gerente | Administrador |
|---|---|---|---|---|---|
| `GET /inmuebles`, `GET /inmuebles/{id}` | ✔ | ✔ | ✔ | ✔ | ✔ |
| `POST/PUT/PATCH /inmuebles` | ✘ | ✘ | ✔ | ✔ | ✔ |
| `DELETE /inmuebles/{id}` | ✘ | ✘ | ✘ | ✘ | ✔ |
| `/alquileres`, `/ventas` | ✘ | ✘ | ✔ | ✔ | ✔ (DELETE alquiler: solo Administrador) |
| `GET /api/usuarios`, `/api/usuarios/{id}` | ✘ | ✘ | ✘ | ✘ | ✔ |
| `register`, `login` | ✔ | ✔ | ✔ | ✔ | ✔ |

El token se envía en la cabecera `Authorization: Bearer <token>`. Todo usuario que se registra queda con rol **Cliente** (vía `sp_AltaUsuarioConRol`).

### Uso de la base de datos
- `sp_General_*` (listar, buscar, insertar, actualizar, eliminar): DAO genérico.
- `sp_AltaUsuarioConRol`: registro de usuario + rol en una transacción.
- `sp_ConcretarVenta`: acepta la oferta, crea operación, venta y comisión (con ROLLBACK si falla).
- Los triggers de la base (ej. superficie cubierta > total, monto de oferta <= 0) lanzan `SQLSTATE 45000`; la API lo devuelve como **400** con el mensaje del trigger.
- Contraseñas: las nuevas se guardan con **BCrypt**. El login también acepta las de texto plano de los datos de prueba (ej. `123456`).

## 3. Versión de Java y Tomcat
- El proyecto está configurado para **Java 17** (`pom.xml`: `java.version` 17) y Spring Boot 3.2.5. Funciona con JRE/JDK 17 o superior.
- **No hace falta instalar Tomcat**: Spring Boot trae un Tomcat 10.1 embebido. Al hacer *Run As → Spring Boot App* se usa ese. Tomcat 10.1 es justo el que corresponde a Spring Boot 3 (Jakarta EE 10).
- Si en algún momento hay que desplegar el `.war` en un Tomcat 10.1 externo también funciona (`SpringBootServletInitializer`).
- Antes de entregar: botón derecho al proyecto → Maven → Update Project (Alt+F5), y en Properties → Java Compiler / Project Facets verificar que diga 17.

## 4. Cómo probar
1. phpMyAdmin (XAMPP): importar `remax.sql` (base `remax`, usuario root sin clave). Los usuarios de prueba ya traen rol; `docs/sql/opcional_asignar_roles.sql` es opcional.
2. STS: Run As → Spring Boot App (puerto 8086).
3. Postman: importar la colección y ejecutar 1 - Usuarios y Login primero (guarda los tokens).
4. Tests: clic derecho al proyecto → Run As → JUnit Test (o `mvn test`).

## 5. Checklist de Git antes de entregar
- `git status` y buscar marcadores: `grep -rn "<<<<<<<\|>>>>>>>" src`
- Resolver cada conflicto, `git add`, `git commit`.
- `git shortlog -sn` para verificar que aparecen los commits de **todos** los integrantes.
- No subir `target/` (ya está en `.gitignore`).

## 6. Carpetas de documentación
Carpeta grupal (diagrama, Postman, SQL, este README) en `docs/`. Cada integrante debe tener su carpeta individual en el repositorio (ej. `docs/individual/<nombre>/`).
