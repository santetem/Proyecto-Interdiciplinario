document.addEventListener("DOMContentLoaded", () => {
    const formLogin = document.getElementById("form-login");
    const mensajeRespuesta = document.getElementById("mensaje-respuesta");

    if (formLogin) {
        formLogin.addEventListener("submit", async function (event) {
            event.preventDefault();

            // Capturamos los valores usando los IDs exactos de tu HTML
            const correo = document.getElementById("correo").value;
            const upassword = document.getElementById("contrasena").value;

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

                const mensaje = await respuesta.text();

                // Mostramos el mensaje en el párrafo que ya tenías preparado en el HTML
                mensajeRespuesta.textContent = mensaje;

                if (mensaje.includes("exitoso")) {
                    mensajeRespuesta.style.color = "#2e7d32"; // Verde éxito
                    setTimeout(() => {
                        window.location.href = "index.html"; // Cambia por tu vista principal si difiere
                    }, 1500);
                } else {
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