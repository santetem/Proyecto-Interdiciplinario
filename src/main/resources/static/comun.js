// Configuración compartida con tu backend de Spring (puerto 8086, igual que login.js)
const API_BASE = "http://localhost:8086";

// Datos de la sesión que guarda login.js
function obtenerToken() { return localStorage.getItem("token"); }
function usuarioActual() {
    try { return JSON.parse(localStorage.getItem("usuario")); } catch { return null; }
}

// Llama al backend con el token (Authorization: Bearer ...). Devuelve { ok, status, datos }.
// Tus errores llegan como {"error": "..."} y los éxitos como {"mensaje": "..."}.
async function llamarAPI(ruta, metodo = "GET", cuerpo = null) {
    const cabeceras = { "Content-Type": "application/json" };
    const token = obtenerToken();
    if (token) cabeceras["Authorization"] = "Bearer " + token;
    const r = await fetch(API_BASE + ruta, {
        method: metodo,
        headers: cabeceras,
        body: cuerpo ? JSON.stringify(cuerpo) : undefined
    });
    let datos = null;
    try { datos = await r.json(); } catch { /* respuesta sin cuerpo */ }
    return { ok: r.ok, status: r.status, datos };
}

function mostrarMensaje(el, texto, tipo) {
    el.textContent = texto;
    el.className = "mensaje " + tipo;
}

// ---------- Sesión y barra de navegación (se aplica en todas las páginas que cargan comun.js) ----------
function cerrarSesionLocal() {
    ["token", "usuario", "roles"].forEach(k => localStorage.removeItem(k));
}

async function cerrarSesion() {
    // Avisa al backend para que invalide el token; si no hay conexión igual se cierra acá.
    try { await llamarAPI("/api/usuarios/logout", "POST"); } catch { /* sin conexión */ }
    cerrarSesionLocal();
    window.location.href = "login.html";
}

// Ajusta la barra según la sesión:
//  - sin sesión: botón "Iniciar Sesión"
//  - con sesión: botón "Cerrar sesión"
//  - enlace "Gestión" solo para personal (Administrador, Gerente o Agente; el backend igual valida el rol)
function armarBarra() {
    const nav = document.querySelector(".nav-links");
    if (!nav) return;
    const logueado = !!obtenerToken();
    let roles = [];
    try { roles = (JSON.parse(localStorage.getItem("roles")) || []).map(r => String(r).toLowerCase()); } catch { /* sin roles */ }
    const esPersonal = logueado && roles.some(r => ["administrador", "gerente", "agente"].includes(r));
    const boton = nav.querySelector(".btn-login-nav");

    let enlace = document.getElementById("link-panel");
    if (esPersonal && !enlace) {
        enlace = document.createElement("a");
        enlace.href = "solicitudes.html";
        enlace.id = "link-panel";
        enlace.textContent = "Gestión";
        nav.insertBefore(enlace, boton);
    } else if (!esPersonal && enlace) {
        enlace.remove();
    }

    if (boton) {
        if (logueado) {
            boton.textContent = "Cerrar sesión";
            boton.href = "#";
            boton.onclick = e => { e.preventDefault(); cerrarSesion(); };
        } else {
            boton.textContent = "Iniciar Sesión";
            boton.href = "login.html";
            boton.onclick = null;
        }
    }
}

// Si el token guardado ya no sirve (por ejemplo, reiniciaste la app y las sesiones en memoria se perdieron),
// se limpia la sesión local para no mostrar al usuario como logueado.
async function verificarSesion() {
    if (!obtenerToken()) return;
    try {
        const r = await llamarAPI("/api/usuarios/me");
        if (r.status === 401) {
            cerrarSesionLocal();
            armarBarra();
        }
    } catch { /* sin conexión: se deja como está */ }
}

document.addEventListener("DOMContentLoaded", () => {
    armarBarra();
    verificarSesion();
});
