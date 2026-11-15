package com.inmobiliaria.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inmobiliaria.dto.ConcretarVentaDTO;
import com.inmobiliaria.dto.VentaDTO;
import com.inmobiliaria.security.AuthInterceptor;
import com.inmobiliaria.security.RequiereRol;
import com.inmobiliaria.security.Sesion;
import com.inmobiliaria.security.Roles;
import com.inmobiliaria.service.IVentaService;
import jakarta.servlet.http.HttpServletRequest;

/** Ventas: uso interno Administrador / Gerente / Agente. */
@RestController
@RequestMapping("/ventas")
@RequiereRol({Roles.ADMINISTRADOR, Roles.GERENTE, Roles.AGENTE})
public class VentaController {

    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> listarTodo() {
        return ResponseEntity.ok(ventaService.listarTodo());
    }

    @PostMapping("/concretar")
    public ResponseEntity<Map<String, String>> concretar(@RequestBody ConcretarVentaDTO dto, HttpServletRequest request) {
        Sesion sesion = (Sesion) request.getAttribute(AuthInterceptor.ATRIBUTO_SESION);
        ventaService.concretarVenta(dto, sesion.getIdUsuario());
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensaje", "Venta concretada correctamente"));
    }
}
