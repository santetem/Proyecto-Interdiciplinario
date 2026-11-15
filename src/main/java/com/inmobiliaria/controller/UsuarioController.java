package com.inmobiliaria.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inmobiliaria.dto.LoginDTO;
import com.inmobiliaria.dto.LoginRespuestaDTO;
import com.inmobiliaria.dto.RegistroUsuarioDTO;
import com.inmobiliaria.dto.UsuarioDTO;
import com.inmobiliaria.security.AuthInterceptor;
import com.inmobiliaria.security.RequiereRol;
import com.inmobiliaria.security.Roles;
import com.inmobiliaria.security.Sesion;
import com.inmobiliaria.service.IUsuarioService;
import jakarta.servlet.http.HttpServletRequest;

/**
 *   POST /api/usuarios/register  201 | 400 | 409   público
 *   POST /api/usuarios/login     200 | 400 | 401   público (devuelve token)
 *   POST /api/usuarios/logout    200                requiere sesión
 *   GET  /api/usuarios/me        200 | 401          requiere sesión
 *   GET  /api/usuarios           200 | 401 | 403    Administrador
 *   GET  /api/usuarios/{id}      200 | 404          Administrador
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registrar(@RequestBody RegistroUsuarioDTO dto) {
        usuarioService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensaje", "¡Usuario registrado con éxito!"));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRespuestaDTO> login(@RequestBody LoginDTO dto) {
        return ResponseEntity.ok(usuarioService.login(dto));
    }

    @PostMapping("/logout")
    @RequiereRol
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request) {
        String cabecera = request.getHeader("Authorization");
        usuarioService.logout(cabecera != null ? cabecera.replace("Bearer ", "").trim() : null);
        return ResponseEntity.ok(Map.of("mensaje", "Sesión cerrada"));
    }

    @GetMapping("/me")
    @RequiereRol
    public ResponseEntity<UsuarioDTO> perfil(HttpServletRequest request) {
        Sesion sesion = (Sesion) request.getAttribute(AuthInterceptor.ATRIBUTO_SESION);
        return ResponseEntity.ok(usuarioService.buscarPorId(sesion.getIdUsuario()));
    }

    @GetMapping
    @RequiereRol({Roles.ADMINISTRADOR})
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    @RequiereRol({Roles.ADMINISTRADOR})
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable("id") int id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }
}
