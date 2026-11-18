// ==========================================================
// register.js: validación y envío del formulario de registro
// ==========================================================
// IMPORTANTE: estas validaciones mejoran la experiencia (avisan rápido), pero NO son
// la seguridad real: alguien puede saltearlas desde la consola del navegador.
// Por eso Java (ImplementacionUsuarioService) las repite. La defensa real está en el servidor.

// Espera a que el navegador termine de leer el HTML antes de buscar elementos
document.addEventListener("DOMContentLoaded", () => {

    // ---------- 1. Referencias a los elementos del HTML (por su id) ----------
    const form = document.getElementById("registerForm");
    const mensajeP = document.getElementById("mensaje");
    const campoDni = document.getElementById("dni");
    const campoFecha = document.getElementById("fechaNacimiento");
    const campoTelefono = document.getElementById("telefono");
    const campoPassword = document.getElementById("upassword");
    const campoCorreo = document.getElementById("correo");

    if (!form) { return; } // si la página no tiene el formulario, no hace nada

    // ---------- 2. Reglas (constantes: se cambian en un solo lugar) ----------
    const DNI_DIGITOS = 8;     // el DNI tiene exactamente 8 números
    const TEL_MIN = 8;         // teléfono: mínimo de dígitos
    const TEL_MAX = 15;        // teléfono: máximo de dígitos
    const EDAD_MINIMA = 18;    // para registrarse hay que ser mayor de edad
    const EDAD_MAXIMA = 120;   // tope razonable (evita fechas absurdas como 1850)
    const PASSWORD_MIN = 6;    // igual que la validación de Java

    // ---------- 3. Funciones de fechas ----------
    // Convierte un objeto Date en texto "AAAA-MM-DD" (el formato que usa <input type="date">)
    function aTexto(fecha) {
        const mes = String(fecha.getMonth() + 1).padStart(2, "0"); // getMonth() va de 0 a 11, por eso +1
        const dia = String(fecha.getDate()).padStart(2, "0");      // padStart: 5 -> "05"
        return fecha.getFullYear() + "-" + mes + "-" + dia;
    }

    // Devuelve la fecha de hoy menos N años
    function fechaHaceAnios(anios) {
        const hoy = new Date();
        return new Date(hoy.getFullYear() - anios, hoy.getMonth(), hoy.getDate());
    }

    // Calcula la edad a partir de un texto "AAAA-MM-DD"
    function calcularEdad(texto) {
        // Se separa el texto a mano: new Date("2000-01-01") se interpreta en UTC y en
        // Argentina (UTC-3) podría correrse un día hacia atrás y fallar el cálculo.
        const partes = texto.split("-").map(Number); // "2000-05-20" -> [2000, 5, 20]
        const anio = partes[0], mes = partes[1], dia = partes[2];
        const hoy = new Date();
        let edad = hoy.getFullYear() - anio;
        // ¿ya cumplió años este año? Si todavía no, se resta 1
        const hoyMes = hoy.getMonth() + 1;
        const yaCumplio = hoyMes > mes || (hoyMes === mes && hoy.getDate() >= dia);
        if (!yaCumplio) { edad = edad - 1; }
        return edad;
    }

    // ---------- 4. Límites del calendario ----------
    // max: la fecha más reciente permitida (hoy - 18 años). El calendario bloquea las posteriores.
    // min: la más antigua permitida (hoy - 120 años).
    campoFecha.max = aTexto(fechaHaceAnios(EDAD_MINIMA));
    campoFecha.min = aTexto(fechaHaceAnios(EDAD_MAXIMA));

    // ---------- 5. Solo números en DNI y teléfono ----------
    // Cada vez que el usuario escribe o pega algo (evento "input"), se borra todo lo que no sea dígito.
    // /\D/g es una expresión regular: \D = "cualquier carácter que NO es dígito", g = "en todo el texto".
    function soloNumeros(campo) {
        campo.addEventListener("input", () => {
            campo.value = campo.value.replace(/\D/g, "");
        });
    }
    soloNumeros(campoDni);
    soloNumeros(campoTelefono);

    // ---------- 6. Marcar y limpiar errores visuales ----------
    // Pinta el borde del campo en rojo (clase "invalido" definida en register.css) y lo selecciona
    function marcarInvalido(campo) {
        campo.classList.add("invalido");
        campo.focus();
    }

    // Al empezar a corregir un campo, se le saca el borde rojo
    form.querySelectorAll("input").forEach((campo) => {
        campo.addEventListener("input", () => campo.classList.remove("invalido"));
    });

    // ---------- 7. Validación completa ----------
    // Devuelve { campo, texto } con el primer error encontrado, o null si todo está bien.
    // Se revisa en el mismo orden en que aparecen los campos.
    function validar() {
        // ^ y $ = el texto completo debe cumplir; \d{8} = exactamente 8 dígitos
        const regexDni = new RegExp("^\\d{" + DNI_DIGITOS + "}$");
        if (!regexDni.test(campoDni.value)) {
            return { campo: campoDni, texto: "El DNI debe tener exactamente " + DNI_DIGITOS + " números." };
        }

        if (campoFecha.value === "") {
            return { campo: campoFecha, texto: "Ingresá tu fecha de nacimiento." };
        }
        const edad = calcularEdad(campoFecha.value);
        if (edad < 0) {
            return { campo: campoFecha, texto: "La fecha de nacimiento no puede ser futura." };
        }
        if (edad < EDAD_MINIMA) {
            return { campo: campoFecha, texto: "Tenés que tener al menos " + EDAD_MINIMA + " años para registrarte." };
        }
        if (edad > EDAD_MAXIMA) {
            return { campo: campoFecha, texto: "La fecha de nacimiento no es válida." };
        }

        // \d{8,15} = entre 8 y 15 dígitos
        const regexTel = new RegExp("^\\d{" + TEL_MIN + "," + TEL_MAX + "}$");
        if (!regexTel.test(campoTelefono.value)) {
            return { campo: campoTelefono, texto: "El teléfono debe tener solo números (entre " + TEL_MIN + " y " + TEL_MAX + " dígitos)." };
        }

        // Correo: algo@algo.algo (sin espacios). Es la misma regla que usa Java.
        const regexCorreo = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
        if (!regexCorreo.test(campoCorreo.value.trim())) {
            return { campo: campoCorreo, texto: "El correo no tiene un formato válido." };
        }

        if (campoPassword.value.length < PASSWORD_MIN) {
            return { campo: campoPassword, texto: "La contraseña debe tener al menos " + PASSWORD_MIN + " caracteres." };
        }
        return null;
    }

    // ---------- 8. Envío del formulario ----------
    form.addEventListener("submit", async function (event) {
        event.preventDefault(); // evita que el formulario recargue la página

        // Se limpian los errores visuales y el mensaje anterior
        form.querySelectorAll(".invalido").forEach((c) => c.classList.remove("invalido"));

        // 8.a) Campos obligatorios vacíos (reemplaza al "required" nativo, que acá está desactivado con novalidate)
        const obligatorios = form.querySelectorAll("input[required]");
        for (const campo of obligatorios) {
            if (campo.value.trim() === "") {
                mensajeP.style.color = "red";
                mensajeP.textContent = "Completá todos los campos.";
                marcarInvalido(campo);
                return; // corta: no se envía nada
            }
        }

        // 8.b) Reglas de DNI, fecha, teléfono y contraseña
        const error = validar();
        if (error) {
            mensajeP.style.color = "red";
            mensajeP.textContent = error.texto;
            marcarInvalido(error.campo);
            return;
        }

        // 8.c) Se arma el objeto con los datos. Los nombres de las claves deben ser
        // idénticos a los atributos de RegistroUsuarioDTO en Java.
        const usuarioObj = {
            nombre: document.getElementById("nombre").value.trim(),
            apellido: document.getElementById("apellido").value.trim(),
            tag: document.getElementById("tag").value.trim(),
            correo: document.getElementById("correo").value.trim(),
            upassword: campoPassword.value, // la contraseña NO se recorta: los espacios pueden ser parte de ella
            dni: campoDni.value,
            telefono: campoTelefono.value,
            fechaNacimiento: campoFecha.value // formato "AAAA-MM-DD"
        };

        // Aviso inmediato de que se está procesando
        mensajeP.textContent = "Procesando registro...";
        mensajeP.style.color = "#002244";

        try {
            // POST: envía datos al backend en formato JSON
            const response = await fetch("http://localhost:8086/api/usuarios/register", {
                method: "POST",
                headers: { "Content-Type": "application/json" }, // avisa que el cuerpo es JSON
                body: JSON.stringify(usuarioObj)                 // objeto -> texto JSON
            });

            const datos = await response.json(); // lee la respuesta de Java

            if (response.ok) { // códigos 200-299
                mensajeP.style.color = "green";
                mensajeP.textContent = datos.mensaje;
                form.reset(); // vacía todos los campos
            } else {
                // Java devuelve { "error": "..." } cuando rechaza los datos o hay duplicados
                mensajeP.style.color = "red";
                mensajeP.textContent = "Error: " + (datos.error || "no se pudo registrar");
            }

        } catch (error) {
            // Se llega acá si no hay conexión con el servidor (Spring apagado, red caída)
            console.error("Error de conexión:", error);
            mensajeP.style.color = "red";
            mensajeP.textContent = "No se pudo conectar con el servidor.";
        }
    });
});
