package com.inmobiliaria.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inmobiliaria.dto.CaptacionDTO;
import com.inmobiliaria.security.AuthInterceptor;
import com.inmobiliaria.security.RequiereRol;
import com.inmobiliaria.security.Roles;
import com.inmobiliaria.security.Sesion;
import com.inmobiliaria.service.ICaptacionService;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/captaciones")
public class CaptacionController {

    private final ICaptacionService captacionService;

    public CaptacionController(ICaptacionService captacionService) {
        this.captacionService = captacionService;
    }

    /** Cualquier usuario con sesión iniciada puede enviar su inmueble para captación. */
    @PostMapping
    @RequiereRol
    public ResponseEntity<Map<String, String>> registrar(@RequestBody CaptacionDTO dto, HttpServletRequest request) {
        Sesion sesion = (Sesion) request.getAttribute(AuthInterceptor.ATRIBUTO_SESION);
        captacionService.registrar(sesion.getIdUsuario(), dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("mensaje", "Solicitud enviada. Un operador se pondrá en contacto con vos."));
    }

    /** Las captaciones del usuario logueado (para que vea en qué estado están). */
    @GetMapping("/mias")
    @RequiereRol
    public ResponseEntity<List<CaptacionDTO>> mias(HttpServletRequest request) {
        Sesion sesion = (Sesion) request.getAttribute(AuthInterceptor.ATRIBUTO_SESION);
        return ResponseEntity.ok(captacionService.listarPorUsuario(sesion.getIdUsuario()));
    }

    /** Uso interno: todas las captaciones, o solo las de un estado (?estado=Pendiente). */
    @GetMapping
    @RequiereRol({Roles.ADMINISTRADOR, Roles.GERENTE, Roles.AGENTE})
    public ResponseEntity<List<CaptacionDTO>> listar(@RequestParam(value = "estado", required = false) String estado) {
        return ResponseEntity.ok(captacionService.listar(estado));
    }

    /** Uso interno: acepta la captación (crea el inmueble y lo publica). Body opcional: {"observaciones": "..."} */
    @PatchMapping("/{id}/aceptar")
    @RequiereRol({Roles.ADMINISTRADOR, Roles.GERENTE, Roles.AGENTE})
    public ResponseEntity<Map<String, String>> aceptar(@PathVariable("id") int id,
            @RequestBody(required = false) CaptacionDTO dto, HttpServletRequest request) {
        Sesion sesion = (Sesion) request.getAttribute(AuthInterceptor.ATRIBUTO_SESION);
        captacionService.aceptar(id, sesion.getIdUsuario(), dto != null ? dto.getObservaciones() : null);
        return ResponseEntity.ok(Map.of("mensaje", "Captación aceptada: el inmueble ya está publicado"));
    }

    /** Uso interno: rechaza la captación. Body: {"observaciones": "motivo"} (obligatorio). */
    @PatchMapping("/{id}/rechazar")
    @RequiereRol({Roles.ADMINISTRADOR, Roles.GERENTE, Roles.AGENTE})
    public ResponseEntity<Map<String, String>> rechazar(@PathVariable("id") int id,
            @RequestBody(required = false) CaptacionDTO dto, HttpServletRequest request) {
        Sesion sesion = (Sesion) request.getAttribute(AuthInterceptor.ATRIBUTO_SESION);
        captacionService.rechazar(id, sesion.getIdUsuario(), dto != null ? dto.getObservaciones() : null);
        return ResponseEntity.ok(Map.of("mensaje", "Captación rechazada"));
    }
}
