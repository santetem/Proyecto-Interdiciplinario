package com.inmobiliaria.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inmobiliaria.dto.InmuebleDTO;
import com.inmobiliaria.security.RequiereRol;
import com.inmobiliaria.security.Roles;
import com.inmobiliaria.service.IInmuebleService;


@RestController
@RequestMapping("/inmuebles")
public class InmuebleController {

    private final IInmuebleService inmuebleService;

    public InmuebleController(IInmuebleService inmuebleService) {
        this.inmuebleService = inmuebleService;
    }

    @GetMapping
    public ResponseEntity<List<InmuebleDTO>> listarTodo() {
        return ResponseEntity.ok(inmuebleService.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InmuebleDTO> buscarPorId(@PathVariable("id") int id) {
        return ResponseEntity.ok(inmuebleService.buscarPorId(id));
    }

    @PostMapping
    @RequiereRol({Roles.ADMINISTRADOR, Roles.GERENTE, Roles.AGENTE})
    public ResponseEntity<Map<String, String>> registrar(@RequestBody InmuebleDTO dto) {
        inmuebleService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensaje", "Inmueble registrado correctamente"));
    }

    @PutMapping("/{id}")
    @RequiereRol({Roles.ADMINISTRADOR, Roles.GERENTE, Roles.AGENTE})
    public ResponseEntity<Map<String, String>> modificar(@PathVariable("id") int id, @RequestBody InmuebleDTO dto) {
        inmuebleService.modificar(id, dto);
        return ResponseEntity.ok(Map.of("mensaje", "Inmueble modificado correctamente"));
    }

    @PatchMapping("/{id}")
    @RequiereRol({Roles.ADMINISTRADOR, Roles.GERENTE, Roles.AGENTE})
    public ResponseEntity<Map<String, String>> actualizarParcial(@PathVariable("id") int id, @RequestBody InmuebleDTO dto) {
        inmuebleService.actualizarParcial(id, dto);
        return ResponseEntity.ok(Map.of("mensaje", "Inmueble actualizado parcialmente"));
    }

    @DeleteMapping("/{id}")
    @RequiereRol({Roles.ADMINISTRADOR})
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable("id") int id) {
        inmuebleService.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Inmueble eliminado correctamente"));
    }
}
