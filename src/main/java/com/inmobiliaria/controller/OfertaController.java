package com.inmobiliaria.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inmobiliaria.dto.OfertaDTO;
import com.inmobiliaria.security.AuthInterceptor;
import com.inmobiliaria.security.RequiereRol;
import com.inmobiliaria.security.Sesion;
import com.inmobiliaria.service.IOfertaService;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    private final IOfertaService ofertaService;

    public OfertaController(IOfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    @PostMapping
    @RequiereRol
    public ResponseEntity<Map<String, String>> registrar(@RequestBody OfertaDTO dto, HttpServletRequest request) {
        Sesion sesion = (Sesion) request.getAttribute(AuthInterceptor.ATRIBUTO_SESION);
        ofertaService.registrar(sesion.getIdUsuario(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensaje", "Oferta enviada correctamente."));
    }
}
