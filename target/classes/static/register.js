document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('registerForm');
    
    if (form) {
        form.addEventListener('submit', async function(event) {
            event.preventDefault();

            // Mapeamos los datos incluyendo el tag
            const usuarioObj = {
                nombre: document.getElementById('nombre').value,
                apellido: document.getElementById('apellido').value,
                tag: document.getElementById('tag').value,
                correo: document.getElementById('correo').value,
                upassword: document.getElementById('upassword').value,
                dni: document.getElementById('dni').value
            };

            const mensajeP = document.getElementById('mensaje');
            mensajeP.textContent = "Procesando registro...";
            mensajeP.style.color = "#002244";

            try {
                const response = await fetch('http://localhost:8086/api/usuarios/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(usuarioObj)
                });

                const respuestaTexto = await response.text();

                if (response.ok) {
                    mensajeP.style.color = "green";
                    mensajeP.textContent = respuestaTexto;
                    form.reset();
                } else {
                    mensajeP.style.color = "red";
                    mensajeP.textContent = "Error: " + respuestaTexto;
                }

            } catch (error) {
                console.error("Error de conexión:", error);
                mensajeP.style.color = "red";
                mensajeP.textContent = "No se pudo conectar con el servidor.";
            }
        });
    }
});