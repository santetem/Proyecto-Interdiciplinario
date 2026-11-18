document.addEventListener("DOMContentLoaded", () => {
    const formLogin = document.getElementById("form-login");
    const mensajeRespuesta = document.getElementById("mensaje-respuesta");

    if (formLogin) {
        formLogin.addEventListener("submit", async function (event) {
            event.preventDefault();

            // Capturamos los valores usando los IDs exactos de tu HTML
            const correo = document.getElementById("correo").value;
            const upassword = document.getElementById("contrasena").value;

            // Si había otra sesión abierta (ej. un cliente), se cierra antes de entrar con la nueva cuenta.
            const tokenViejo = localStorage.getItem("token");
            if (tokenViejo) {
                fetch("http://localhost:8086/api/usuarios/logout", {
                    method: "POST",
                    headers: { "Authorization": "Bearer " + tokenViejo }
                }).catch(() => { /* sin conexión: no importa */ });
            }
            ["token", "usuario", "roles"].forEach(k => localStorage.removeItem(k));

            const datosLogin = {
                correo: correo,
                upassword: upassword
            };

            try {
                // Apuntando al puerto 8086 como pediste
                const respuesta = await fetch("http://localhost:8086/api/usuarios/login", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(datosLogin)
                });

                const datos = await respuesta.json();

                if (respuesta.ok) {
                    // Guardamos la sesión: el token se manda luego en "Authorization: Bearer <token>"
                    localStorage.setItem("token", datos.token);
                    localStorage.setItem("usuario", JSON.stringify(datos.usuario));
                    localStorage.setItem("roles", JSON.stringify(datos.roles));

                    mensajeRespuesta.textContent = datos.mensaje;
                    mensajeRespuesta.style.color = "#2e7d32"; // Verde éxito
                    setTimeout(() => {
                        window.location.href = "principal.html"; // Cambia por tu vista principal si difiere
                    }, 1500);
                } else {
                    mensajeRespuesta.textContent = datos.error || "No se pudo iniciar sesión.";
                    mensajeRespuesta.style.color = "#d32f2f"; // Rojo error
                }
            } catch (error) {
                console.error("Error de conexión:", error);
                mensajeRespuesta.textContent = "No se pudo conectar con el servidor.";
                mensajeRespuesta.style.color = "#d32f2f";
            }
        });
    }
});