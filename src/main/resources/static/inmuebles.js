// propiedades de prueba por si todavia no tengo levantado el servidor en java
const INMUEBLES_DEMO = [
    { id_inmueble: 1, tipo_inmueble: "Departamento", operacion: "Venta", direccion: "Av. Santa Fe 3200", localidad: "Palermo", precio: 145000, cantidad_habitaciones: 2, cantidad_banos: 1, cantidad_cocheras: 0, superficie_total: 62, antiguedad_estado: "10 años, buen estado", caracteristicas: ["Balcón", "Ascensor"] },
    { id_inmueble: 2, tipo_inmueble: "Casa", operacion: "Venta", direccion: "Los Aromos 455", localidad: "Ciudad del Libertador", precio: 230000, cantidad_habitaciones: 4, cantidad_banos: 2, cantidad_cocheras: 2, superficie_total: 320, antiguedad_estado: "5 años, excelente", caracteristicas: ["Jardín", "Pileta", "Parrilla"] },
    { id_inmueble: 3, tipo_inmueble: "PH", operacion: "Alquiler", direccion: "Thames 1850", localidad: "Villa Crespo", precio: 650, cantidad_habitaciones: 3, cantidad_banos: 1, cantidad_cocheras: 0, superficie_total: 90, antiguedad_estado: "20 años, a refaccionar", caracteristicas: ["Terraza"] },
    { id_inmueble: 4, tipo_inmueble: "Terreno", operacion: "Venta", direccion: "Ruta 8 km 52", localidad: "Pilar", precio: 80000, cantidad_habitaciones: 0, cantidad_banos: 0, cantidad_cocheras: 0, superficie_total: 600, antiguedad_estado: "A estrenar", caracteristicas: ["Barrio cerrado"] },
    { id_inmueble: 5, tipo_inmueble: "Departamento", operacion: "Alquiler", direccion: "Corrientes 4100", localidad: "Almagro", precio: 480, cantidad_habitaciones: 1, cantidad_banos: 1, cantidad_cocheras: 0, superficie_total: 38, antiguedad_estado: "15 años, bueno", caracteristicas: ["Amoblado"] }
];

// variables globales para guardar las propiedades y saber cual abrio el usuario
let todosLosInmuebles = [];
let inmuebleSeleccionado = null;

// agarro los elementos del html que voy a ir modificando
const cajaListado = document.getElementById("listado");
const textoContador = document.getElementById("contador");
const ventanaDetalle = document.getElementById("detalle");


// 1. EMPROLIJAR LOS DATOS QUE VIENEN DEL BACKEND
// como java manda nombres en camelCase (idInmueble) y los de prueba tienen guion bajo (id_inmueble), aca los unifico
function emprolijarDatos(item) {
    return {
        id: item.idInmueble || item.id_inmueble,
        tipo: item.tipoInmueble || item.tipo_inmueble,
        operacion: item.operacion || "",
        direccion: item.direccion,
        localidad: item.localidad,
        precio: item.precio || null,
        habitaciones: item.cantidadHabitaciones || item.cantidad_habitaciones || 0,
        banos: item.cantidadBanos || item.cantidad_banos || 0,
        cocheras: item.cantidadCocheras || item.cantidad_cocheras || 0,
        superficie: item.superficieTotal || item.superficie_total || "-",
        antiguedad: item.antiguedadEstado || item.antiguedad_estado || "-",
        extras: item.caracteristicas || [],
        imagenes: item.imagenes || [] // URLs de las fotos que manda java
    };
}


// 2. PEDIR LAS PROPIEDADES A MI API EN JAVA
async function cargarInmuebles() {
    todosLosInmuebles = [];

    try {
        // llamarAPI viene de comun.js
        const respuesta = await llamarAPI("/inmuebles");
        
        if (respuesta.ok) {
            for (let i = 0; i < respuesta.datos.length; i++) {
                todosLosInmuebles.push(emprolijarDatos(respuesta.datos[i]));
            }
        } else {
            throw new Error("Error en la respuesta");
        }
    } catch (error) {
        // si el backend esta apagado, cargo los 5 ejemplos de arriba para que la pagina no quede vacia
        for (let i = 0; i < INMUEBLES_DEMO.length; i++) {
            todosLosInmuebles.push(emprolijarDatos(INMUEBLES_DEMO[i]));
        }
    }

    mostrarPropiedades();
}


// 3. APLICAR LOS FILTROS DE BUSQUEDA
function obtenerFiltrados() {
    // leo lo que el usuario escribio o selecciono en los filtros
    const filtroLocalidad = document.getElementById("f-localidad").value.trim().toLowerCase();
    const filtroTipo = document.getElementById("f-tipo").value;
    const filtroOperacion = document.getElementById("f-operacion").value;
    const filtroHabitaciones = Number(document.getElementById("f-habitaciones").value) || 0;
    const filtroPrecioMax = Number(document.getElementById("f-precio").value) || 999999999;

    const resultado = [];

    // recorro todas las propiedades y voy descartando las que no cumplen
    for (let i = 0; i < todosLosInmuebles.length; i++) {
        const casa = todosLosInmuebles[i];

        if (filtroLocalidad !== "" && !casa.localidad.toLowerCase().includes(filtroLocalidad)) {
            continue;
        }
        if (filtroTipo !== "" && casa.tipo !== filtroTipo) {
            continue;
        }
        if (filtroOperacion !== "" && casa.operacion !== filtroOperacion) {
            continue;
        }
        if (casa.habitaciones < filtroHabitaciones) {
            continue;
        }
        if (casa.precio !== null && casa.precio > filtroPrecioMax) {
            continue;
        }

        // si paso todos los filtros, la agrego a la lista que se va a mostrar
        resultado.push(casa);
    }

    return resultado;
}


// 4. DIBUJAR LAS TARJETAS EN PANTALLA
function mostrarPropiedades() {
    const listaFiltrada = obtenerFiltrados();

    // actualizo el texto de arriba que dice cuantas se encontraron
    if (listaFiltrada.length === 1) {
        textoContador.textContent = "1 propiedad encontrada";
    } else {
        textoContador.textContent = listaFiltrada.length + " propiedades encontradas";
    }

    // limpio el contenedor antes de volver a dibujar
    cajaListado.innerHTML = "";

    if (listaFiltrada.length === 0) {
        cajaListado.innerHTML = '<div class="vacio">No hay propiedades con esos filtros. Probá ampliar la zona o quitar el precio máximo.</div>';
        return;
    }

    // armo una tarjeta por cada propiedad
    for (let i = 0; i < listaFiltrada.length; i++) {
        const casa = listaFiltrada[i];

        // armo la etiqueta de Venta o Alquiler solo si viene el dato
        let etiquetaOperacion = "";
        if (casa.operacion !== "") {
            etiquetaOperacion = `<span class="etiqueta">${casa.operacion}</span>`;
        }

        // formateo el precio en dolares
        let textoPrecio = "Consultar precio";
        if (casa.precio !== null) {
            textoPrecio = "USD " + casa.precio.toLocaleString("es-AR");
            if (casa.operacion === "Alquiler") {
                textoPrecio += " /mes";
            }
        }

        // el boton Eliminar solo se dibuja si el usuario es administrador
        let botonEliminar = "";
        if (esAdministrador()) {
            botonEliminar = `<button class="btn btn-peligro" onclick="eliminarInmueble(${casa.id})">Eliminar</button>`;
        }

        // si tiene fotos, dibujo la primera tapando el fondo azul. Si la imagen no carga (onerror),
        // se borra la etiqueta <img> y queda visible el fondo azul con el tipo de propiedad
        let htmlImagen = "";
        if (casa.imagenes.length > 0) {
            htmlImagen = `<img src="${API_BASE}${casa.imagenes[0]}" alt="${casa.tipo} en ${casa.localidad}" onerror="this.remove()">`;
        }

        cajaListado.innerHTML += `
            <article class="tarjeta-inmueble">
                <div class="foto">${etiquetaOperacion}<span>${casa.tipo}</span>${htmlImagen}</div>
                <div class="cuerpo">
                    <div class="precio">${textoPrecio}</div>
                    <div class="direccion">${casa.direccion}, ${casa.localidad}</div>
                    <div class="datos">
                        <span>${casa.superficie} m²</span>
                        <span>${casa.habitaciones} hab.</span>
                        <span>${casa.banos} baño(s)</span>
                        <span>${casa.cocheras} cochera(s)</span>
                    </div>
                    <button class="btn btn-azul" onclick="abrirDetalle(${casa.id})">Ver detalle</button>
                    ${botonEliminar}
                </div>
            </article>
        `;
    }
}


// 5. ABRIR LA VENTANA EMERGENTE CON EL DETALLE Y EL FORMULARIO DE OFERTA
function abrirDetalle(idInmueble) {
    // busco la propiedad en mi lista usando su id
    inmuebleSeleccionado = null;
    for (let i = 0; i < todosLosInmuebles.length; i++) {
        if (todosLosInmuebles[i].id === idInmueble) {
            inmuebleSeleccionado = todosLosInmuebles[i];
            break;
        }
    }

    if (!inmuebleSeleccionado) {
        return;
    }

    // junto las caracteristicas extras separadas por coma (ej: "Balcon, Ascensor")
    let textoExtras = "—";
    if (inmuebleSeleccionado.extras.length > 0) {
        textoExtras = inmuebleSeleccionado.extras.join(", ");
    }

    let subtitulo = inmuebleSeleccionado.direccion;
    if (inmuebleSeleccionado.operacion !== "") {
        subtitulo += " · " + inmuebleSeleccionado.operacion;
    }

    // si tiene fotos, las muestro todas en una tira que se desplaza hacia el costado
    let htmlGaleria = "";
    if (inmuebleSeleccionado.imagenes.length > 0) {
        htmlGaleria = '<div class="galeria">';
        for (let i = 0; i < inmuebleSeleccionado.imagenes.length; i++) {
            htmlGaleria += `<img src="${API_BASE}${inmuebleSeleccionado.imagenes[i]}" alt="Foto ${i + 1}" onerror="this.remove()">`;
        }
        htmlGaleria += "</div>";
    }

    // relleno el contenido del modal y limpio el formulario de oferta
    document.getElementById("detalle-contenido").innerHTML = htmlGaleria + `
        <h2>${inmuebleSeleccionado.tipo} en ${inmuebleSeleccionado.localidad}</h2>
        <p>${subtitulo}</p>
        <ul>
            <li>Superficie total: ${inmuebleSeleccionado.superficie} m²</li>
            <li>Antigüedad y estado: ${inmuebleSeleccionado.antiguedad}</li>
            <li>Características: ${textoExtras}</li>
        </ul>
    `;

    document.getElementById("msg-oferta").textContent = "";
    document.getElementById("monto").value = "";
    
    // showModal() es una funcion propia de la etiqueta <dialog> de html para abrir la ventana
    ventanaDetalle.showModal();
}


// 5.1 ELIMINAR UN INMUEBLE (solo administrador)
// dice si el usuario logueado tiene el rol Administrador (los roles los guardo login.js en localStorage).
// OJO: esto solo decide si se DIBUJA el boton. La proteccion real esta en java: el endpoint
// DELETE /inmuebles/{id} rechaza con 403 a cualquiera que no sea administrador.
function esAdministrador() {
    try {
        const roles = JSON.parse(localStorage.getItem("roles")) || [];
        for (let i = 0; i < roles.length; i++) {
            if (String(roles[i]).toLowerCase() === "administrador") {
                return true;
            }
        }
        return false;
    } catch (error) {
        return false;
    }
}

async function eliminarInmueble(idInmueble) {
    // busco el inmueble para nombrarlo en la pregunta de confirmacion
    let nombre = "este inmueble";
    for (let i = 0; i < todosLosInmuebles.length; i++) {
        if (todosLosInmuebles[i].id === idInmueble) {
            nombre = todosLosInmuebles[i].direccion + ", " + todosLosInmuebles[i].localidad;
        }
    }

    // confirm() muestra un cuadro Aceptar / Cancelar; si cancela, no se hace nada
    const confirmado = confirm("¿Eliminar definitivamente " + nombre + "?\n\nSe borran también sus fotos, ofertas y visitas. No se puede deshacer.");
    if (!confirmado) {
        return;
    }

    const aviso = document.getElementById("aviso");
    try {
        const respuesta = await llamarAPI("/inmuebles/" + idInmueble, "DELETE");
        if (respuesta.ok) {
            mostrarMensaje(aviso, (respuesta.datos && respuesta.datos.mensaje) ? respuesta.datos.mensaje : "Inmueble eliminado.", "ok");
            await cargarInmuebles(); // vuelvo a pedir la lista para que desaparezca la tarjeta
        } else {
            const motivo = (respuesta.datos && respuesta.datos.error) ? respuesta.datos.error : "código " + respuesta.status;
            mostrarMensaje(aviso, "No se pudo eliminar: " + motivo, "error");
        }
    } catch (error) {
        mostrarMensaje(aviso, "No se pudo conectar con el servidor.", "error");
    }
}


// 6. EVENTOS DE BOTONES Y FILTROS
// boton X para cerrar la ventana del detalle
document.getElementById("cerrar").addEventListener("click", function() {
    ventanaDetalle.close();
});

// cada vez que escribo o cambio algo en los filtros, vuelvo a dibujar las tarjetas
document.getElementById("filtros").addEventListener("input", mostrarPropiedades);

// boton para resetear los filtros y mostrar todas de nuevo
document.getElementById("btn-limpiar").addEventListener("click", function() {
    document.getElementById("filtros").reset();
    mostrarPropiedades();
});


// 7. ENVIAR UNA OFERTA AL BACKEND
document.getElementById("form-oferta").addEventListener("submit", async function(evento) {
    evento.preventDefault(); // evito que se recargue la pagina
    
    const cartelMensaje = document.getElementById("msg-oferta");

    // verifico que haya iniciado sesion antes de ofertar
    if (!obtenerToken()) {
        mostrarMensaje(cartelMensaje, "Tenés que iniciar sesión para ofertar.", "error");
        return;
    }

    const datosOferta = {
idInmueble: inmuebleSeleccionado.id,
monto: Number(document.getElementById("monto").value)
};
try {
const respuesta = await llamarAPI("/api/ofertas", "POST", datosOferta);
if (respuesta.ok) {
const textoOk = (respuesta.datos && respuesta.datos.mensaje) ? respuesta.datos.mensaje : "Oferta enviada.";
mostrarMensaje(cartelMensaje, textoOk, "ok");
} else {
const textoError = (respuesta.datos && respuesta.datos.error) ? respuesta.datos.error : "No se pudo enviar.";
mostrarMensaje(cartelMensaje, "Error: " + textoError, "error");
}
} catch (error) {
mostrarMensaje(cartelMensaje, "No se pudo conectar con el servidor.", "error");
}
});
// apenas abre la pagina, arranco cargando los inmuebles
cargarInmuebles();