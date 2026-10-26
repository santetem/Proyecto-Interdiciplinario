package com.inmobiliaria.controller;

import com.inmobiliaria.model.Usuario;
import com.inmobiliaria.dao.ImplementacionUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private ImplementacionUsuarioRepository usuarioDao;

    @PostMapping("/register")
    public String registrarUsuario(@RequestBody Usuario u) {
        try {
            String columnas = "nombre, apellido, tag, correo, upassword, dni";
            String valores = "'" + u.getNombre() + "', '" + 
                             u.getApellido() + "', '" + 
                             u.getTag() + "', '" + 
                             u.getCorreo() + "', '" + 
                             u.getUpassword() + "', '" + 
                             u.getDni() + "'";

            usuarioDao.insertarSP(columnas, valores);
            return "¡Usuario registrado con éxito!";
        } catch (Exception e) {
            return "Error al registrar: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestBody Usuario u) {
        try {
            for (Usuario user : usuarioDao.listarTodosSP()) {
                if (user.getCorreo().equals(u.getCorreo()) && user.getUpassword().equals(u.getUpassword())) {
                    return "¡Login exitoso! Bienvenido " + user.getNombre();
                }
            }
            return "Error: Correo o contraseña incorrectos.";
        } catch (Exception e) {
            return "Error en el servidor: " + e.getMessage();
        }
    }
}