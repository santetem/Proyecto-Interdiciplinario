// guardo la etiqueta donde voy a mostrar los carteles de error o confirmacion
const msgPanel = document.getElementById("mensaje");

// 1. REVISAR PERMISOS DEL USUARIO LOGUEADO
// saco la lista de roles que guarde en el navegador cuando inicio sesion
let rolesUsuario = [];
const rolesGuardados = localStorage.getItem("roles");

if (rolesGuardados) {
    try {
        const lista = JSON.parse(rolesGuardados);
        // paso todos los roles a minuscula para comparar sin problemas
        for (let i = 0; i < lista.length; i++) {
            rolesUsuario.push(String(lista[i]).toLowerCase());
        }
    } catch (error) {
        rolesUsuario = [];
    }
}

// me fijo que puede hacer segun su rol
const puedeVerCaptaciones = rolesUsuario.includes("administrador") || rolesUsuario.includes("gerente") || rolesUsuario.includes("agente");
const puedeVerFranquicias = rolesUsuario.includes("administrador") || rolesUsuario.includes("gerente");


// 2. LIMPIAR TEXTOS ANTES DE MOSTRARLOS
// esto evita que si alguien escribio codigo html en el formulario me rompa la pagina
function limpiarTexto(texto) {
    if (!texto) {
        return "";
    }
    return String(texto)
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll('"', "&quot;");
}


// 2.1 SEPARAR LAS FOTOS DEL TEXTO DE UNA CAPTACION
// devuelve el texto sin la linea "Imagenes:" y el html con las miniaturas (cada una abre la foto grande)
function separarImagenes(texto) {
    const renglones = String(texto || "").split("\n");
    const resto = [];
    let urls = [];

    for (let i = 0; i < renglones.length; i++) {
        if (renglones[i].trim().startsWith("Imagenes:")) {
            urls = renglones[i].replace("Imagenes:", "").split(",");
        } else {
            resto.push(renglones[i]);
        }
    }

    let html = "";
    const urlsOk = [];
    for (let i = 0; i < urls.length; i++) {
        const url = urls[i].trim();
        if (url.startsWith("/uploads/")) {
            urlsOk.push(url);
        }
    }
    if (urlsOk.length > 0) {
        html = '<div class="miniaturas">';
        for (let i = 0; i < urlsOk.length; i++) {
            // limpiarTexto escapa comillas y simbolos para que una url rara no pueda romper el html
            const ruta = limpiarTexto(API_BASE + urlsOk[i]);
            html += `<a href="${ruta}" target="_blank" rel="noopener"><img src="${ruta}" alt="Foto ${i + 1}"></a>`;
        }
        html += "</div>";
    }
    return { texto: resto.join("\n"), html: html };
}


// 3. ARMAR EL HTML DE UNA TARJETA PENDIENTE
function armarTarjeta(tipo, item) {
    let id = "";
    let solicitante = "";
    let detalle = "";
    let campoObservaciones = "";
    let miniaturas = "";
    let botones = "";

    // separo los datos segun si es una captacion o una franquicia porque tienen distintos nombres en la bd
    if (tipo === "captacion") {
        id = item.idCaptacion;
        solicitante = item.propietarioNombre || "Usuario";
        // la linea "Imagenes:" se saca del texto y se muestra como miniaturas para que el operador vea las fotos
        const separado = separarImagenes(item.caracteristicas);
        detalle = separado.texto;
        miniaturas = separado.html;
        campoObservaciones = `<textarea id="obs-${id}" placeholder="Observaciones (obligatorias para rechazar, opcionales para aceptar)" maxlength="255"></textarea>`;
        botones = `
            <button class="btn btn-azul" onclick="procesarSolicitud('captacion', ${id}, 'aceptar', this)">Aceptar y publicar</button>
            <button class="btn btn-rechazar" onclick="procesarSolicitud('captacion', ${id}, 'rechazar', this)">Rechazar</button>
        `;
    } else {
        id = item.idSolicitud;
        solicitante = item.solicitante || "Usuario";
        detalle = item.observaciones;
        botones = `
            <button class="btn btn-azul" onclick="procesarSolicitud('franquicia', ${id}, 'aprobar', this)">Aprobar franquicia</button>
            <button class="btn btn-rechazar" onclick="procesarSolicitud('franquicia', ${id}, 'rechazar', this)">Rechazar</button>
        `;
    }

    const estadoClase = String(item.estado || "").toLowerCase();

    return `
        <article class="solicitud">
            <div class="solicitud-cabecera">
                <div>
                    <strong>#${id} · ${limpiarTexto(solicitante)}</strong>
                    <small>${limpiarTexto(item.fecha)}</small>
                </div>
                <span class="estado ${estadoClase}">${limpiarTexto(item.estado)}</span>
            </div>
            <pre class="detalle">${limpiarTexto(detalle)}</pre>
            ${miniaturas}
            ${campoObservaciones}
            <div class="acciones">
                ${botones}
            </div>
        </article>
    `;
}


// 4. ARMAR LA TABLA DE SOLICITUDES YA RESUELTAS
function armarTablaResueltas(tipo, lista) {
    if (lista.length === 0) {
        return '<p class="nota">Todavía no hay solicitudes resueltas.</p>';
    }

    let filasHTML = "";

    for (let i = 0; i < lista.length; i++) {
        const item = lista[i];
        let id = "";
        let solicitante = "";
        let observaciones = "";

        if (tipo === "captacion") {
            id = item.idCaptacion;
            solicitante = item.propietarioNombre || "";
            observaciones = item.observaciones || "";
        } else {
            id = item.idSolicitud;
            solicitante = item.solicitante || "";
        }

        const estadoClase = String(item.estado || "").toLowerCase();

        filasHTML += `
            <tr>
                <td>#${id}</td>
                <td>${limpiarTexto(item.fecha)}</td>
                <td>${limpiarTexto(solicitante)}</td>
                <td><span class="estado ${estadoClase}">${limpiarTexto(item.estado)}</span></td>
                <td>${limpiarTexto(observaciones)}</td>
            </tr>
        `;
    }

    return `
        <table class="tabla">
            <thead>
                <tr>
                    <th>N°</th>
                    <th>Fecha</th>
                    <th>Solicitante</th>
                    <th>Estado</th>
                    <th>Observaciones</th>
                </tr>
            </thead>
            <tbody>${filasHTML}</tbody>
        </table>
    `;
}


// 5. PEDIR LOS DATOS AL BACKEND Y DIBUJARLOS EN PANTALLA
async function cargarSeccion(rutaAPI, tipo, idCajaPendientes, idCajaResueltas) {
    const cajaPendientes = document.getElementById(idCajaPendientes);
    const cajaResueltas = document.getElementById(idCajaResueltas);

    try {
        // llamarAPI viene del archivo comun.js
        const respuesta = await llamarAPI(rutaAPI);

        if (!respuesta.ok) {
            cajaPendientes.innerHTML = '<p class="nota">No se pudo cargar la lista.</p>';
            return;
        }

        const pendientes = [];
        const resueltas = [];

        // separo las pendientes de las que ya estan aprobadas o rechazadas
        for (let i = 0; i < respuesta.datos.length; i++) {
            const solicitud = respuesta.datos[i];
            if (String(solicitud.estado).toLowerCase() === "pendiente") {
                pendientes.push(solicitud);
            } else {
                resueltas.push(solicitud);
            }
        }

        // dibujo las tarjetas pendientes
        if (pendientes.length === 0) {
            cajaPendientes.innerHTML = '<p class="nota">No hay solicitudes pendientes.</p>';
        } else {
            let tarjetasHTML = "";
            for (let i = 0; i < pendientes.length; i++) {
                tarjetasHTML += armarTarjeta(tipo, pendientes[i]);
            }
            cajaPendientes.innerHTML = tarjetasHTML;
        }

        // dibujo la tabla de resueltas
        cajaResueltas.innerHTML = armarTablaResueltas(tipo, resueltas);

    } catch (error) {
        cajaPendientes.innerHTML = '<p class="nota">Error de conexión con el servidor.</p>';
        cajaResueltas.innerHTML = "";
    }
}


// 6. APROBAR O RECHAZAR AL HACER CLIC EN UN BOTON
async function procesarSolicitud(tipo, id, accion, boton) {
    let rutaBase = "/api/solicitudes-franquicia/";
    let datosEnviar = null;

    if (tipo === "captacion") {
        rutaBase = "/api/captaciones/";
        // busco lo que escribio el empleado en el textarea de esa tarjeta
        const cajaTexto = document.getElementById("obs-" + id);
        const textoObs = cajaTexto ? cajaTexto.value.trim() : "";

        // valido que si rechaza una captacion ponga si o si el motivo
        if (accion === "rechazar" && textoObs === "") {
            mostrarMensaje(msgPanel, "Para rechazar una captación escribí el motivo en observaciones.", "error");
            return;
        }
        datosEnviar = { observaciones: textoObs };
    }

    // desactivo el boton un segundo para que no hagan doble clic por accidente
    boton.disabled = true;

    try {
        const rutaFinal = rutaBase + id + "/" + accion;
        const respuesta = await llamarAPI(rutaFinal, "PATCH", datosEnviar);

        if (respuesta.ok) {
            const textoOk = (respuesta.datos && respuesta.datos.mensaje) ? respuesta.datos.mensaje : "Solicitud actualizada.";
            mostrarMensaje(msgPanel, textoOk, "ok");
            recargarTodo();
        } else {
            const textoError = (respuesta.datos && respuesta.datos.error) ? respuesta.datos.error : "No se pudo procesar.";
            mostrarMensaje(msgPanel, "Error: " + textoError, "error");
            boton.disabled = false;
        }
    } catch (error) {
        mostrarMensaje(msgPanel, "No se pudo conectar con el servidor.", "error");
        boton.disabled = false;
    }
}


// 7. FUNCION QUE REFRESCA LAS LISTAS
function recargarTodo() {
    if (puedeVerCaptaciones) {
        cargarSeccion("/api/captaciones", "captacion", "captaciones-pendientes", "captaciones-resueltas");
    }
    if (puedeVerFranquicias) {
        cargarSeccion("/api/solicitudes-franquicia", "franquicia", "franquicias-pendientes", "franquicias-resueltas");
    }
}


// 8. ARRANQUE DE LA PAGINA
// obtenerToken() viene de comun.js. Si no hay sesion o no es empleado, muestro el cartel de aviso
if (!obtenerToken() || !puedeVerCaptaciones) {
    document.getElementById("aviso-acceso").style.display = "block";
} else {
    document.getElementById("contenido-gestion").style.display = "block";
    
    // si es agente solo ve captaciones, le escondo el panel de franquicias
    if (!puedeVerFranquicias) {
        document.getElementById("panel-franquicias").style.display = "none";
    }
    
    recargarTodo();
}
