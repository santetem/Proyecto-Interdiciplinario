package com.inmobiliaria.controller;

import com.inmobiliaria.model.Usuario;
import com.inmobiliaria.dao.ImplementacionUsuarioRepository;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
import java.util.List;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

<<<<<<< HEAD
    private final ImplementacionUsuarioRepository usuarioDao;

    // Inyección de dependencias por constructor (Altamente recomendado en Spring)
    public UsuarioController(ImplementacionUsuarioRepository usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioDao.listarTodosSP();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable("id") int id) {
        return usuarioDao.buscarPorIdSP(id);
    }
=======
    @Autowired
    private ImplementacionUsuarioRepository usuarioDao;
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f

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