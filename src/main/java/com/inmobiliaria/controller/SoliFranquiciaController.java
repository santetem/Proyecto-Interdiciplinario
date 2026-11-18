package com.inmobiliaria.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inmobiliaria.dto.SoliFranquiciaDTO;
import com.inmobiliaria.security.AuthInterceptor;
import com.inmobiliaria.security.RequiereRol;
import com.inmobiliaria.security.Roles;
import com.inmobiliaria.security.Sesion;
import com.inmobiliaria.service.ISoliFranquiciaService;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/solicitudes-franquicia")
public class SoliFranquiciaController {

    private final ISoliFranquiciaService soliService;

    public SoliFranquiciaController(ISoliFranquiciaService soliService) {
        this.soliService = soliService;
    }

    @PostMapping
    @RequiereRol
    public ResponseEntity<Map<String, String>> registrar(@RequestBody SoliFranquiciaDTO dto, HttpServletRequest request) {
        Sesion sesion = (Sesion) request.getAttribute(AuthInterceptor.ATRIBUTO_SESION);
        soliService.registrar(sesion.getIdUsuario(), dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("mensaje", "Postulación enviada. Te avisaremos cuando sea revisada."));
    }

    /** Las solicitudes del usuario logueado. */
    @GetMapping("/mias")
    @RequiereRol
    public ResponseEntity<List<SoliFranquiciaDTO>> mias(HttpServletRequest request) {
        Sesion sesion = (Sesion) request.getAttribute(AuthInterceptor.ATRIBUTO_SESION);
        return ResponseEntity.ok(soliService.listarPorUsuario(sesion.getIdUsuario()));
    }

    /** Sector administrativo: todas las solicitudes, o solo las de un estado (?estado=Pendiente). */
    @GetMapping
    @RequiereRol({Roles.ADMINISTRADOR, Roles.GERENTE})
    public ResponseEntity<List<SoliFranquiciaDTO>> listar(@RequestParam(value = "estado", required = false) String estado) {
        return ResponseEntity.ok(soliService.listar(estado));
    }

    /** Sector administrativo: aprueba la solicitud y crea la franquicia. */
    @PatchMapping("/{id}/aprobar")
    @RequiereRol({Roles.ADMINISTRADOR, Roles.GERENTE})
    public ResponseEntity<Map<String, String>> aprobar(@PathVariable("id") int id) {
        soliService.aprobar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Solicitud aprobada: la franquicia quedó dada de alta"));
    }

    /** Sector administrativo: rechaza la solicitud. */
    @PatchMapping("/{id}/rechazar")
    @RequiereRol({Roles.ADMINISTRADOR, Roles.GERENTE})
    public ResponseEntity<Map<String, String>> rechazar(@PathVariable("id") int id) {
        soliService.rechazar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Solicitud rechazada"));
    }
}
