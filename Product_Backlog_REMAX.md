# Product Backlog – Proyecto REMAX

Product Backlog de **150 tareas de desarrollo**, derivadas de las 26 historias de usuario (HU). Cada HU se descompone en tareas de Base de datos, Backend, Frontend, Pruebas y Documentación. Estimación total: **485 puntos**.

Prioridad: Alta / Media / Baja. Puntos: escala Fibonacci (1, 2, 3, 5). Fase Gantt: fase del diagrama de Gantt donde se ejecuta. Estado inicial: Por hacer.

## Resumen por épica

| Épica | Tareas | Puntos | Prioridad |
|---|---:|---:|---|
| E01 Base de datos | 12 | 45 | Alta |
| E02 Autenticación y roles | 10 | 34 | Alta |
| E03 Usuarios y clientes | 9 | 26 | Alta |
| E04 Captaciones | 11 | 33 | Alta |
| E05 Inmuebles | 12 | 41 | Alta |
| E06 Publicaciones | 9 | 24 | Media |
| E07 Tasaciones | 8 | 19 | Media |
| E08 Visitas | 9 | 27 | Media |
| E09 Ofertas | 9 | 30 | Alta |
| E10 Operaciones, ventas y alquileres | 14 | 52 | Alta |
| E11 Franquicias | 8 | 25 | Media |
| E12 Comisiones | 8 | 23 | Media |
| E13 Chat | 7 | 23 | Baja |
| E14 Auditorías | 6 | 17 | Media |
| E15 Integración y pruebas | 8 | 32 | Alta |
| E16 Documentación y entrega | 10 | 34 | Media |
| **Total** | **150** | **485** | |

## Resumen por tipo

| Tipo | Tareas | Puntos |
|---|---:|---:|
| Base de datos | 20 | 61 |
| Backend | 51 | 180 |
| Frontend | 37 | 127 |
| Pruebas | 32 | 83 |
| Documentación | 10 | 34 |

## Historias de usuario

| HU | Historia |
|---|---|
| HU-01 | Como administrador quiero que el sistema almacene de forma íntegra la información del negocio para poder consultarla y mantenerla. |
| HU-02 | Como usuario quiero registrarme e iniciar sesión para acceder al sistema de forma segura. |
| HU-03 | Como administrador quiero que cada rol tenga permisos propios para proteger la información. |
| HU-04 | Como administrador quiero gestionar usuarios (alta, baja y modificación) para mantener al equipo actualizado. |
| HU-05 | Como agente quiero registrar y consultar clientes para darles seguimiento. |
| HU-06 | Como agente quiero registrar la captación de un inmueble para sumarlo a la cartera. |
| HU-07 | Como agente quiero seguir el estado de mis captaciones para saber cuáles avanzan. |
| HU-08 | Como agente quiero dar de alta, modificar y dar de baja inmuebles para mantener la cartera al día. |
| HU-09 | Como cliente quiero listar, filtrar y ver el detalle de los inmuebles para encontrar el que me interesa. |
| HU-10 | Como agente quiero publicar un inmueble con fotos y datos para que sea visible a los clientes. |
| HU-11 | Como agente quiero pausar o finalizar una publicación para que no se muestren inmuebles no disponibles. |
| HU-12 | Como agente quiero solicitar y registrar una tasación para fijar el precio de un inmueble. |
| HU-13 | Como cliente quiero agendar una visita a un inmueble para conocerlo. |
| HU-14 | Como agente quiero registrar el resultado de una visita para saber el interés del cliente. |
| HU-15 | Como cliente quiero realizar una oferta sobre un inmueble para iniciar una negociación. |
| HU-16 | Como agente quiero aceptar, rechazar o contraofertar una oferta para cerrar el mejor trato. |
| HU-17 | Como agente quiero registrar una operación de venta para concretar la transacción. |
| HU-18 | Como agente quiero registrar un alquiler (contrato y vigencia) para gestionarlo. |
| HU-19 | Como gerente quiero consultar el estado de todas las operaciones para controlar el negocio. |
| HU-20 | Como interesado quiero enviar una solicitud de franquicia para sumarme a la red. |
| HU-21 | Como administrador quiero gestionar las franquicias y sus sucursales para administrar la red. |
| HU-22 | Como gerente quiero calcular y registrar las comisiones de cada operación para liquidarlas correctamente. |
| HU-23 | Como agente o cliente quiero chatear dentro del sistema para comunicarnos sobre un inmueble. |
| HU-24 | Como administrador quiero auditar las acciones de los usuarios para tener trazabilidad. |
| HU-25 | Como equipo quiero probar e integrar el sistema para entregar un producto sin errores. |
| HU-26 | Como equipo quiero documentar el sistema para que pueda instalarse, usarse y mantenerse. |

## Backlog detallado

### E01 Base de datos

*Prioridad Alta · Fases 4 y 5 (T34–T51)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-001 | HU-01 | Base de datos | Relevar entidades y atributos del dominio inmobiliario | 3 | Por hacer |
| PB-002 | HU-01 | Base de datos | Definir claves primarias, foráneas y cardinalidades | 3 | Por hacer |
| PB-003 | HU-01 | Base de datos | Diseñar el DER y el modelo relacional | 5 | Por hacer |
| PB-004 | HU-01 | Base de datos | Normalizar tablas hasta 3FN | 3 | Por hacer |
| PB-005 | HU-01 | Base de datos | Crear script de tablas con PK y FK | 5 | Por hacer |
| PB-006 | HU-01 | Base de datos | Configurar restricciones (UNIQUE, CHECK, NOT NULL) y relaciones | 3 | Por hacer |
| PB-007 | HU-01 | Base de datos | Cargar datos de prueba (script seed) | 2 | Por hacer |
| PB-008 | HU-01 | Base de datos | Crear consultas SQL y vistas para listados y reportes | 5 | Por hacer |
| PB-009 | HU-01 | Base de datos | Desarrollar procedimientos almacenados | 5 | Por hacer |
| PB-010 | HU-01 | Base de datos | Desarrollar triggers de integridad y auditoría | 5 | Por hacer |
| PB-011 | HU-01 | Base de datos | Crear usuarios, roles y permisos de la BD | 3 | Por hacer |
| PB-012 | HU-01 | Pruebas | Probar integridad, consultas y rendimiento de la BD | 3 | Por hacer |

### E02 Autenticación y roles

*Prioridad Alta · Fase 6 (T55–T61)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-013 | HU-01 | Backend | Configurar proyecto Spring y conexión con la base de datos | 3 | Por hacer |
| PB-014 | HU-01 | Backend | Definir arquitectura por capas (modelo, DAO, servicio, controlador) | 3 | Por hacer |
| PB-015 | HU-02 | Backend | Crear entidad y repositorio de usuario con contraseña cifrada | 3 | Por hacer |
| PB-016 | HU-02 | Backend | Implementar endpoint de registro de usuarios | 3 | Por hacer |
| PB-017 | HU-02 | Backend | Implementar endpoint de login y emisión de token | 5 | Por hacer |
| PB-018 | HU-03 | Backend | Implementar autorización por roles (admin, gerente, agente, cliente) | 5 | Por hacer |
| PB-019 | HU-02 | Frontend | Maquetar y programar pantalla de inicio de sesión | 3 | Por hacer |
| PB-020 | HU-02 | Frontend | Maquetar y programar pantalla de registro con validaciones | 3 | Por hacer |
| PB-021 | HU-03 | Frontend | Ocultar menús y acciones según el rol del usuario | 3 | Por hacer |
| PB-022 | HU-02 | Pruebas | Probar login, registro y permisos por rol | 3 | Por hacer |

### E03 Usuarios y clientes

*Prioridad Alta · Fases 6 y 7 (T62, T70)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-023 | HU-04 | Backend | Desarrollar CRUD de usuarios (servicio y controlador) | 5 | Por hacer |
| PB-024 | HU-04 | Backend | Validar datos únicos y estados de usuario | 2 | Por hacer |
| PB-025 | HU-05 | Backend | Desarrollar CRUD de clientes | 3 | Por hacer |
| PB-026 | HU-05 | Backend | Implementar búsqueda y filtrado de clientes | 2 | Por hacer |
| PB-027 | HU-04 | Frontend | Pantalla de listado y alta/edición de usuarios | 5 | Por hacer |
| PB-028 | HU-05 | Frontend | Pantalla de listado y ficha de clientes | 3 | Por hacer |
| PB-029 | HU-04 | Frontend | Confirmaciones y mensajes de error en gestión de usuarios | 2 | Por hacer |
| PB-030 | HU-04 | Pruebas | Probar alta, baja y modificación de usuarios | 2 | Por hacer |
| PB-031 | HU-05 | Pruebas | Probar registro y búsqueda de clientes | 2 | Por hacer |

### E04 Captaciones

*Prioridad Alta · Fases 6 y 7 (T62, T72)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-032 | HU-06 | Base de datos | Definir tabla de captaciones y estados | 2 | Por hacer |
| PB-033 | HU-06 | Backend | Desarrollar modelo, DAO y servicio de captaciones | 5 | Por hacer |
| PB-034 | HU-06 | Backend | Endpoint de alta de captación con datos del propietario | 3 | Por hacer |
| PB-035 | HU-06 | Backend | Adjuntar documentación y fotos a la captación | 3 | Por hacer |
| PB-036 | HU-07 | Backend | Cambio de estado de captación y registro de historial | 3 | Por hacer |
| PB-037 | HU-07 | Backend | Listado de captaciones por agente y estado | 2 | Por hacer |
| PB-038 | HU-06 | Frontend | Formulario de captación de inmuebles | 5 | Por hacer |
| PB-039 | HU-06 | Frontend | Carga de fotos y documentos en el formulario | 3 | Por hacer |
| PB-040 | HU-07 | Frontend | Tablero de seguimiento de captaciones | 3 | Por hacer |
| PB-041 | HU-06 | Pruebas | Probar el alta de captaciones | 2 | Por hacer |
| PB-042 | HU-07 | Pruebas | Probar cambios de estado e historial | 2 | Por hacer |

### E05 Inmuebles

*Prioridad Alta · Fases 6 y 7 (T62, T69, T71)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-043 | HU-08 | Backend | Desarrollar modelo y DAO de inmuebles y tipos de operación | 3 | Por hacer |
| PB-044 | HU-08 | Backend | Servicio y endpoints de alta, modificación y baja lógica | 5 | Por hacer |
| PB-045 | HU-09 | Backend | Endpoint de listado con filtros (zona, precio, tipo, ambientes) | 5 | Por hacer |
| PB-046 | HU-09 | Backend | Endpoint de detalle de inmueble con imágenes | 3 | Por hacer |
| PB-047 | HU-08 | Frontend | Formulario de alta y edición de inmuebles | 5 | Por hacer |
| PB-048 | HU-09 | Frontend | Página principal con inmuebles destacados | 3 | Por hacer |
| PB-049 | HU-09 | Frontend | Listado de inmuebles con filtros y paginación | 5 | Por hacer |
| PB-050 | HU-09 | Frontend | Página de detalle con galería multimedia | 3 | Por hacer |
| PB-051 | HU-09 | Frontend | Validaciones en JavaScript de filtros y formularios | 2 | Por hacer |
| PB-052 | HU-08 | Pruebas | Probar alta, edición y baja de inmuebles | 2 | Por hacer |
| PB-053 | HU-09 | Pruebas | Probar filtros, listado y detalle | 3 | Por hacer |
| PB-054 | HU-09 | Frontend | Diseño responsive de listado y detalle | 2 | Por hacer |

### E06 Publicaciones

*Prioridad Media · Fase 6 (T63)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-055 | HU-10 | Backend | Modelo y servicio de publicaciones (estado, vigencia, destacado) | 3 | Por hacer |
| PB-056 | HU-10 | Backend | Endpoint para publicar un inmueble validando datos obligatorios | 3 | Por hacer |
| PB-057 | HU-11 | Backend | Endpoint para pausar, reactivar y finalizar publicaciones | 3 | Por hacer |
| PB-058 | HU-10 | Frontend | Pantalla de gestión de publicaciones del agente | 3 | Por hacer |
| PB-059 | HU-10 | Frontend | Previsualización de la publicación antes de confirmar | 2 | Por hacer |
| PB-060 | HU-11 | Frontend | Acciones de pausar y finalizar con confirmación | 2 | Por hacer |
| PB-061 | HU-10 | Backend | Vincular publicaciones con contenido multimedia | 3 | Por hacer |
| PB-062 | HU-10 | Pruebas | Probar el flujo de publicación | 3 | Por hacer |
| PB-063 | HU-11 | Pruebas | Probar pausa y finalización de publicaciones | 2 | Por hacer |

### E07 Tasaciones

*Prioridad Media · Fase 6 (T63)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-064 | HU-12 | Base de datos | Definir tabla de tasaciones y valores de referencia | 2 | Por hacer |
| PB-065 | HU-12 | Backend | Modelo, DAO y servicio de tasaciones | 3 | Por hacer |
| PB-066 | HU-12 | Backend | Endpoint de solicitud y registro de tasación | 3 | Por hacer |
| PB-067 | HU-12 | Backend | Asociar tasación al inmueble y actualizar precio sugerido | 2 | Por hacer |
| PB-068 | HU-12 | Frontend | Formulario de solicitud de tasación | 3 | Por hacer |
| PB-069 | HU-12 | Frontend | Pantalla de historial de tasaciones del inmueble | 2 | Por hacer |
| PB-070 | HU-12 | Pruebas | Probar el registro de tasaciones | 2 | Por hacer |
| PB-071 | HU-12 | Pruebas | Probar permisos de tasación por rol | 2 | Por hacer |

### E08 Visitas

*Prioridad Media · Fase 6 (T63)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-072 | HU-13 | Base de datos | Definir tabla de visitas y disponibilidad | 2 | Por hacer |
| PB-073 | HU-13 | Backend | Servicio de visitas con validación de choques de horario | 5 | Por hacer |
| PB-074 | HU-13 | Backend | Endpoint para agendar, reprogramar y cancelar visitas | 3 | Por hacer |
| PB-075 | HU-14 | Backend | Endpoint para registrar el resultado de la visita | 2 | Por hacer |
| PB-076 | HU-13 | Frontend | Calendario y formulario de agenda de visitas | 5 | Por hacer |
| PB-077 | HU-13 | Frontend | Listado de visitas del cliente y del agente | 3 | Por hacer |
| PB-078 | HU-14 | Frontend | Formulario de resultado e interés del cliente | 2 | Por hacer |
| PB-079 | HU-13 | Pruebas | Probar agenda y choques de horario | 3 | Por hacer |
| PB-080 | HU-14 | Pruebas | Probar el registro de resultados de visita | 2 | Por hacer |

### E09 Ofertas

*Prioridad Alta · Fases 6 y 7 (T64, T73)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-081 | HU-15 | Base de datos | Definir tabla de ofertas y contraofertas | 2 | Por hacer |
| PB-082 | HU-15 | Backend | Modelo y servicio de ofertas con validación de monto | 3 | Por hacer |
| PB-083 | HU-15 | Backend | Endpoint para registrar una oferta | 3 | Por hacer |
| PB-084 | HU-16 | Backend | Endpoint para aceptar, rechazar y contraofertar | 5 | Por hacer |
| PB-085 | HU-16 | Backend | Notificar el cambio de estado de la oferta | 3 | Por hacer |
| PB-086 | HU-15 | Frontend | Formulario de oferta sobre un inmueble | 3 | Por hacer |
| PB-087 | HU-16 | Frontend | Pantalla de gestión de ofertas del agente | 5 | Por hacer |
| PB-088 | HU-15 | Pruebas | Probar creación de ofertas y validaciones | 3 | Por hacer |
| PB-089 | HU-16 | Pruebas | Probar aceptación, rechazo y contraoferta | 3 | Por hacer |

### E10 Operaciones, ventas y alquileres

*Prioridad Alta · Fases 6 y 7 (T64, T73)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-090 | HU-17 | Base de datos | Definir tablas de operaciones, ventas y alquileres | 3 | Por hacer |
| PB-091 | HU-17 | Backend | Modelo y servicio de operaciones con máquina de estados | 5 | Por hacer |
| PB-092 | HU-17 | Backend | Endpoint de registro de venta desde una oferta aceptada | 5 | Por hacer |
| PB-093 | HU-17 | Backend | Registro de escritura, forma de pago y cierre de la venta | 3 | Por hacer |
| PB-094 | HU-18 | Backend | Endpoint de alta de contrato de alquiler y vigencia | 5 | Por hacer |
| PB-095 | HU-18 | Backend | Cálculo de vencimientos y renovación de alquileres | 3 | Por hacer |
| PB-096 | HU-19 | Backend | Endpoint de consulta de operaciones por estado y período | 3 | Por hacer |
| PB-097 | HU-17 | Frontend | Pantalla de registro de venta | 5 | Por hacer |
| PB-098 | HU-18 | Frontend | Pantalla de gestión de alquileres y contratos | 5 | Por hacer |
| PB-099 | HU-19 | Frontend | Tablero de operaciones con filtros | 5 | Por hacer |
| PB-100 | HU-17 | Pruebas | Probar el flujo de venta de punta a punta | 3 | Por hacer |
| PB-101 | HU-18 | Pruebas | Probar contratos de alquiler y vencimientos | 3 | Por hacer |
| PB-102 | HU-19 | Pruebas | Probar consultas y permisos del tablero de operaciones | 2 | Por hacer |
| PB-103 | HU-17 | Pruebas | Probar transiciones inválidas de estado de operación | 2 | Por hacer |

### E11 Franquicias

*Prioridad Media · Fases 6 y 7 (T65, T74)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-104 | HU-20 | Base de datos | Definir tablas de franquicias y sucursales | 2 | Por hacer |
| PB-105 | HU-20 | Backend | Endpoint público de solicitud de franquicia | 3 | Por hacer |
| PB-106 | HU-21 | Backend | CRUD de franquicias y sucursales | 5 | Por hacer |
| PB-107 | HU-21 | Backend | Aprobación o rechazo de solicitudes de franquicia | 3 | Por hacer |
| PB-108 | HU-20 | Frontend | Formulario de solicitud de franquicias | 3 | Por hacer |
| PB-109 | HU-21 | Frontend | Panel de administración de franquicias | 5 | Por hacer |
| PB-110 | HU-20 | Pruebas | Probar el envío de solicitudes | 2 | Por hacer |
| PB-111 | HU-21 | Pruebas | Probar el alta y aprobación de franquicias | 2 | Por hacer |

### E12 Comisiones

*Prioridad Media · Fase 6 (T65)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-112 | HU-22 | Base de datos | Definir tabla de comisiones y porcentajes por operación | 2 | Por hacer |
| PB-113 | HU-22 | Backend | Servicio de cálculo de comisión (venta y alquiler) | 5 | Por hacer |
| PB-114 | HU-22 | Backend | Reparto de comisión entre agente, franquicia y casa central | 3 | Por hacer |
| PB-115 | HU-22 | Backend | Endpoint de consulta y liquidación de comisiones | 3 | Por hacer |
| PB-116 | HU-22 | Frontend | Pantalla de comisiones por agente y período | 3 | Por hacer |
| PB-117 | HU-22 | Frontend | Exportar el listado de comisiones | 2 | Por hacer |
| PB-118 | HU-22 | Pruebas | Probar el cálculo y reparto de comisiones | 3 | Por hacer |
| PB-119 | HU-22 | Pruebas | Probar redondeos y casos límite | 2 | Por hacer |

### E13 Chat

*Prioridad Baja · Fase 6 (T65)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-120 | HU-23 | Base de datos | Definir tablas de conversaciones y mensajes | 2 | Por hacer |
| PB-121 | HU-23 | Backend | Servicio de mensajería asociado a un inmueble | 5 | Por hacer |
| PB-122 | HU-23 | Backend | Endpoint de envío y lectura de mensajes | 3 | Por hacer |
| PB-123 | HU-23 | Frontend | Pantalla de conversaciones | 5 | Por hacer |
| PB-124 | HU-23 | Frontend | Actualización de mensajes y marca de leído | 3 | Por hacer |
| PB-125 | HU-23 | Pruebas | Probar envío y recepción de mensajes | 3 | Por hacer |
| PB-126 | HU-23 | Pruebas | Probar acceso al chat solo para participantes | 2 | Por hacer |

### E14 Auditorías

*Prioridad Media · Fase 6 (T65)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-127 | HU-24 | Base de datos | Definir tabla de auditoría (usuario, acción, fecha, entidad) | 2 | Por hacer |
| PB-128 | HU-24 | Backend | Interceptor que registra acciones críticas | 5 | Por hacer |
| PB-129 | HU-24 | Backend | Endpoint de consulta de auditoría con filtros | 3 | Por hacer |
| PB-130 | HU-24 | Frontend | Pantalla de consulta de auditorías para el administrador | 3 | Por hacer |
| PB-131 | HU-24 | Pruebas | Probar el registro de acciones auditadas | 2 | Por hacer |
| PB-132 | HU-24 | Pruebas | Probar que solo el administrador accede a la auditoría | 2 | Por hacer |

### E15 Integración y pruebas

*Prioridad Alta · Fases 6, 7 y 8 (T66, T77, T79–T85)*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-133 | HU-25 | Backend | Integrar y validar todos los módulos del Backend | 5 | Por hacer |
| PB-134 | HU-25 | Backend | Manejo global de excepciones y DTO de respuesta | 3 | Por hacer |
| PB-135 | HU-25 | Frontend | Integrar el Frontend con el Backend (consumo de API) | 5 | Por hacer |
| PB-136 | HU-25 | Frontend | Integrar contenido multimedia y llamadas a la API | 3 | Por hacer |
| PB-137 | HU-25 | Pruebas | Planificar y ejecutar pruebas funcionales | 3 | Por hacer |
| PB-138 | HU-25 | Pruebas | Ejecutar pruebas de integración del sistema | 5 | Por hacer |
| PB-139 | HU-25 | Pruebas | Pruebas de seguridad (inyección SQL, XSS, accesos) | 3 | Por hacer |
| PB-140 | HU-25 | Pruebas | Detectar y corregir errores, y pruebas de regresión | 5 | Por hacer |

### E16 Documentación y entrega

*Prioridad Media · Fases 9, 10, 11 y 13*

| ID | HU | Tipo | Tarea | Pts | Estado |
|---|---|---|---|---:|---|
| PB-141 | HU-26 | Documentación | Elaborar el diagrama de clases | 3 | Por hacer |
| PB-142 | HU-26 | Documentación | Elaborar los diagramas de secuencia de los escenarios principales | 5 | Por hacer |
| PB-143 | HU-26 | Documentación | Actualizar y revisar la documentación técnica | 3 | Por hacer |
| PB-144 | HU-26 | Documentación | Elaborar el manual de usuario | 5 | Por hacer |
| PB-145 | HU-26 | Documentación | Elaborar el manual de instalación y deploy | 5 | Por hacer |
| PB-146 | HU-26 | Documentación | Documentar configuración y puesta en funcionamiento | 3 | Por hacer |
| PB-147 | HU-26 | Documentación | Organizar documentación y evidencias de la carpeta de campo | 3 | Por hacer |
| PB-148 | HU-26 | Documentación | Revisar y corregir manuales y carpeta de campo | 2 | Por hacer |
| PB-149 | HU-26 | Documentación | Preparar la entrega final y la presentación | 3 | Por hacer |
| PB-150 | HU-26 | Documentación | Ensayar la defensa del proyecto | 2 | Por hacer |
