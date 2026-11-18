// SoliFranquicias solo tiene fecha, estado y observaciones, así que los datos
// del local se guardan juntos en "observaciones".
const formFran = document.getElementById("form-franquicia");
const msgFran = document.getElementById("mensaje");

formFran.addEventListener("submit", async e => {
    e.preventDefault();
    if (!obtenerToken()) {
        mostrarMensaje(msgFran, "Tenés que iniciar sesión para postularte.", "error");
        return;
    }
    const v = id => document.getElementById(id).value.trim();
    const texto = ["Local: " + v("nombre-local"), "Dirección: " + v("direccion-local"), "Teléfono: " + v("telefono"),
                   v("observaciones") && "Motivo: " + v("observaciones")].filter(Boolean).join(" | ");
    mostrarMensaje(msgFran, "Enviando postulación...", "");
    try {
        // El usuario sale del token; el backend pone fecha de hoy y estado "Pendiente".
        const r = await llamarAPI("/api/solicitudes-franquicia", "POST", { observaciones: texto });
        if (r.ok) {
            mostrarMensaje(msgFran, (r.datos && r.datos.mensaje) || "Postulación enviada.", "ok");
            formFran.reset();
            cargarMisSolicitudes();
        } else {
            mostrarMensaje(msgFran, "Error: " + ((r.datos && r.datos.error) || r.status), "error");
        }
    } catch {
        mostrarMensaje(msgFran, "No se pudo conectar con el servidor.", "error");
    }
});

async function cargarMisSolicitudes() {
    if (!obtenerToken()) return;
    const cont = document.getElementById("mis-solicitudes");
    try {
        const r = await llamarAPI("/api/solicitudes-franquicia/mias");
        if (!r.ok) throw new Error();
        if (!r.datos.length) {
            cont.innerHTML = '<p class="nota">Todavía no enviaste ninguna postulación.</p>';
            return;
        }
        cont.innerHTML = '<table class="tabla"><thead><tr><th>Fecha</th><th>Detalle</th><th>Estado</th></tr></thead><tbody>' +
            r.datos.map(s => `<tr><td>${s.fecha}</td><td>${s.observaciones || ""}</td><td><span class="estado ${String(s.estado).toLowerCase()}">${s.estado}</span></td></tr>`).join("") +
            "</tbody></table>";
    } catch {
        cont.innerHTML = '<p class="nota">No se pudieron cargar tus solicitudes.</p>';
    }
}
cargarMisSolicitudes();
