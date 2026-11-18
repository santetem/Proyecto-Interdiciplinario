// agarro los elementos principales del formulario de captacion
const formularioCaptacion = document.getElementById("form-captacion");
const cartelMensaje = document.getElementById("mensaje");
const inputArchivos = document.getElementById("multimedia");


// 1. MOSTRAR LA LISTA DE FOTOS O VIDEOS QUE ELIGIO EL USUARIO
inputArchivos.addEventListener("change", function() {
    const listaUL = document.getElementById("lista-archivos");
    listaUL.innerHTML = "";

    // recorro los archivos seleccionados y muestro el nombre y cuanto pesan en MB
    for (let i = 0; i < inputArchivos.files.length; i++) {
        const archivo = inputArchivos.files[i];
        const pesoEnMB = (archivo.size / 1048576).toFixed(1);

        const itemLista = document.createElement("li");
        itemLista.textContent = archivo.name + " (" + pesoEnMB + " MB)";
        listaUL.appendChild(itemLista);
    }
});


// 2. ENVIAR LA SOLICITUD DE CAPTACION AL HACER SUBMIT
formularioCaptacion.addEventListener("submit", async function(evento) {
    evento.preventDefault(); // evito que se recargue la pagina

    // valido que haya iniciado sesion
    if (!obtenerToken()) {
        mostrarMensaje(cartelMensaje, "Tenés que iniciar sesión para enviar tu inmueble.", "error");
        return;
    }

    // junto todas las casillas tildadas (ej: Pileta, Parrilla, Balcon)
    const checkboxesMarcados = document.querySelectorAll("#caracteristicas input:checked");
    const listaExtras = [];
    for (let i = 0; i < checkboxesMarcados.length; i++) {
        listaExtras.push(checkboxesMarcados[i].value);
    }
    const textoExtras = listaExtras.join(", ");

    // leo los valores de cada caja del formulario
    const operacion = document.getElementById("operacion").value.trim();
    const tipo = document.getElementById("tipo").value.trim();
    const direccion = document.getElementById("direccion").value.trim();
    const localidad = document.getElementById("localidad").value.trim();
    const valor = document.getElementById("valor").value.trim();
    const supCubierta = document.getElementById("sup-cubierta").value.trim();
    const supTotal = document.getElementById("sup-total").value.trim();
    const habitaciones = document.getElementById("habitaciones").value.trim();
    const banos = document.getElementById("banos").value.trim();
    const cocheras = document.getElementById("cocheras").value.trim();
    const antiguedad = document.getElementById("antiguedad").value.trim();
    const observaciones = document.getElementById("observaciones").value.trim();

    // armo el texto completo renglon por renglon para guardarlo en el campo "caracteristicas" de la bd
    const lineas = [];
    lineas.push("Operación: " + operacion);
    lineas.push("Tipo: " + tipo);
    lineas.push("Dirección: " + direccion + ", " + localidad);

    if (valor !== "") {
        lineas.push("Valor pretendido: USD " + valor);
    }
    if (supCubierta !== "") {
        lineas.push("Sup. cubierta: " + supCubierta + " m²");
    }
    if (supTotal !== "") {
        lineas.push("Sup. total: " + supTotal + " m²");
    }

    lineas.push("Habitaciones: " + habitaciones + " | Baños: " + banos + " | Cocheras: " + cocheras);

    if (antiguedad !== "") {
        lineas.push("Antigüedad/estado: " + antiguedad);
    }
    if (textoExtras !== "") {
        lineas.push("Extras: " + textoExtras);
    }
    if (observaciones !== "") {
        lineas.push("Comentarios: " + observaciones);
    }

    mostrarMensaje(cartelMensaje, "Enviando solicitud...", "");

    try {
        // PASO 1: subo las fotos una por una. Cada una devuelve su URL (ej: /uploads/abc.jpg)
        const urlsImagenes = await subirImagenes(inputArchivos.files);

        // PASO 2: agrego las URLs como un renglon mas del texto "caracteristicas" (java las lee al aceptar)
        if (urlsImagenes.length > 0) {
            lineas.push("Imagenes: " + urlsImagenes.join(", "));
        }

        const datosEnviar = {
            caracteristicas: lineas.join("\n")
        };

        // mando la solicitud al backend en java (java saca el id del propietario desde el token)
        const respuesta = await llamarAPI("/api/captaciones", "POST", datosEnviar);

        if (respuesta.ok) {
            const textoOk = (respuesta.datos && respuesta.datos.mensaje) ? respuesta.datos.mensaje : "Solicitud enviada. Un operador se pondrá en contacto con vos.";
            mostrarMensaje(cartelMensaje, textoOk, "ok");

            // vacio el formulario, limpio la lista de archivos y actualizo la tabla de abajo
            formularioCaptacion.reset();
            document.getElementById("lista-archivos").innerHTML = "";
            cargarMisCaptaciones();
        } else {
            const textoError = (respuesta.datos && respuesta.datos.error) ? respuesta.datos.error : "No se pudo enviar.";
            mostrarMensaje(cartelMensaje, "Error: " + textoError, "error");
        }
    } catch (error) {
        // si el error trae un mensaje pensado para el usuario (ej: "la foto pesa mas de 5 MB") lo muestro
        const textoCatch = error.mensajeUsuario ? error.mensajeUsuario : "No se pudo conectar con el servidor.";
        mostrarMensaje(cartelMensaje, textoCatch, "error");
    }
});


// 2.1 SUBIR LAS FOTOS AL SERVIDOR
const MAX_FOTOS = 5;
const MAX_MB_POR_FOTO = 5;
const TIPOS_PERMITIDOS = ["image/jpeg", "image/png", "image/webp", "image/gif"];

// crea un error con un texto listo para mostrarle al usuario
function errorParaUsuario(texto) {
    const error = new Error(texto);
    error.mensajeUsuario = texto;
    return error;
}

// valida las fotos elegidas y las sube; devuelve la lista de URLs
// (java vuelve a validar todo: estas comprobaciones solo evitan esperar para enterarse del error)
async function subirImagenes(archivos) {
    const urls = [];

    if (archivos.length > MAX_FOTOS) {
        throw errorParaUsuario("Podés subir hasta " + MAX_FOTOS + " fotos.");
    }
    for (let i = 0; i < archivos.length; i++) {
        if (!TIPOS_PERMITIDOS.includes(archivos[i].type)) {
            throw errorParaUsuario("\"" + archivos[i].name + "\" no es una imagen válida (usá JPG, PNG, WEBP o GIF).");
        }
        if (archivos[i].size > MAX_MB_POR_FOTO * 1048576) {
            throw errorParaUsuario("\"" + archivos[i].name + "\" pesa más de " + MAX_MB_POR_FOTO + " MB.");
        }
    }

    for (let i = 0; i < archivos.length; i++) {
        // FormData arma un formulario con archivos (multipart). El campo se llama "archivo" igual que en java
        const formulario = new FormData();
        formulario.append("archivo", archivos[i]);

        // OJO: aca no uso llamarAPI porque esa funcion manda JSON. Para archivos NO se pone Content-Type:
        // el navegador lo completa solo (con el "boundary" que separa las partes del formulario)
        const respuesta = await fetch(API_BASE + "/api/imagenes", {
            method: "POST",
            headers: { "Authorization": "Bearer " + obtenerToken() },
            body: formulario
        });

        let datos = null;
        try {
            datos = await respuesta.json();
        } catch (e) {
            datos = null;
        }

        if (!respuesta.ok) {
            const motivo = (datos && datos.error) ? datos.error : "no se pudo subir";
            throw errorParaUsuario("Foto \"" + archivos[i].name + "\": " + motivo);
        }
        urls.push(datos.url);
    }
    return urls;
}


// 3. FUNCIONES AUXILIARES PARA ARMAR LA TABLA DE "MIS CAPTACIONES"
// limpia caracteres raros antes de meter texto en el html
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

// busca un renglon especifico adentro del texto guardado (ej: busca la linea que empieza con "Tipo:")
function sacarDatoDeLinea(textoCompleto, palabraClave) {
    if (!textoCompleto) {
        return "";
    }
    const renglones = textoCompleto.split("\n");
    for (let i = 0; i < renglones.length; i++) {
        const linea = renglones[i].trim();
        if (linea.startsWith(palabraClave + ":")) {
            return linea.replace(palabraClave + ":", "").trim();
        }
    }
    return "";
}


// 4. TRAER LAS CAPTACIONES QUE MANDO ESTE CLIENTE Y MOSTRARLAS EN TABLA
async function cargarMisCaptaciones() {
    if (!obtenerToken()) {
        return;
    }

    const cajaTabla = document.getElementById("mis-captaciones");

    try {
        const respuesta = await llamarAPI("/api/captaciones/mias");

        if (!respuesta.ok) {
            cajaTabla.innerHTML = '<p class="nota">No se pudieron cargar tus solicitudes.</p>';
            return;
        }

        if (respuesta.datos.length === 0) {
            cajaTabla.innerHTML = '<p class="nota">Todavía no enviaste ninguna solicitud.</p>';
            return;
        }

        let filasHTML = "";

        for (let i = 0; i < respuesta.datos.length; i++) {
            const cap = respuesta.datos[i];

            // saco los 3 datos principales del texto para armar un resumen corto en la tabla
            const op = sacarDatoDeLinea(cap.caracteristicas, "Operación");
            const tipo = sacarDatoDeLinea(cap.caracteristicas, "Tipo");
            const dir = sacarDatoDeLinea(cap.caracteristicas, "Dirección");
            const resumen = op + " · " + tipo + " · " + dir;

            const estado = String(cap.estado);
            let detalleEstado = limpiarTexto(cap.observaciones || "");

            // segun el estado de la solicitud, muestro un mensaje claro al cliente
            if (estado === "Pendiente") {
                detalleEstado = "Un operador la está revisando.";
            } else if (estado === "Activa") {
                detalleEstado = 'Aceptada: tu inmueble ya está publicado. <a href="inmuebles.html">Verlo en Inmuebles</a>';
                if (cap.observaciones) {
                    detalleEstado += "<br>" + limpiarTexto(cap.observaciones);
                }
            }

            filasHTML += `
                <tr>
                    <td>${limpiarTexto(cap.fecha)}</td>
                    <td>${limpiarTexto(resumen)}</td>
                    <td><span class="estado ${estado.toLowerCase()}">${limpiarTexto(estado)}</span></td>
                    <td>${detalleEstado}</td>
                </tr>
            `;
        }

        cajaTabla.innerHTML = `
            <table class="tabla">
                <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>Inmueble</th>
                        <th>Estado</th>
                        <th>Detalle</th>
                    </tr>
                </thead>
                <tbody>
                    ${filasHTML}
                </tbody>
            </table>
        `;

    } catch (error) {
        cajaTabla.innerHTML = '<p class="nota">No se pudieron cargar tus solicitudes.</p>';
    }
}


// apenas entra a la pagina, cargo el historial de captaciones del usuario
cargarMisCaptaciones();
