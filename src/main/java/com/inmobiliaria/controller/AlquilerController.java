package com.inmobiliaria.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inmobiliaria.dto.AlquilerDTO;
import com.inmobiliaria.security.RequiereRol;
import com.inmobiliaria.security.Roles;
import com.inmobiliaria.service.IAlquilerService;

/** Controller REST de Alquileres. Todo el controller es de uso interno: Administrador / Gerente / Agente (DELETE solo Administrador). */
@RestController
@RequestMapping("/alquileres")
@RequiereRol({Roles.ADMINISTRADOR, Roles.GERENTE, Roles.AGENTE})
public class AlquilerController {

    private final IAlquilerService alquilerService;

    public AlquilerController(IAlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping
    public ResponseEntity<List<AlquilerDTO>> listarTodo() {
        return ResponseEntity.ok(alquilerService.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlquilerDTO> buscarPorId(@PathVariable("id") int id) {
        return ResponseEntity.ok(alquilerService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registrar(@RequestBody AlquilerDTO dto) {
        alquilerService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensaje", "Alquiler registrado correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> modificar(@PathVariable("id") int id, @RequestBody AlquilerDTO dto) {
        alquilerService.modificar(id, dto);
        return ResponseEntity.ok(Map.of("mensaje", "Alquiler modificado correctamente"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, String>> actualizarParcial(@PathVariable("id") int id, @RequestBody AlquilerDTO dto) {
        alquilerService.actualizarParcial(id, dto);
        return ResponseEntity.ok(Map.of("mensaje", "Alquiler actualizado parcialmente"));
    }

    @DeleteMapping("/{id}")
    @RequiereRol({Roles.ADMINISTRADOR})
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable("id") int id) {
        alquilerService.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Alquiler eliminado correctamente"));
    }
}
