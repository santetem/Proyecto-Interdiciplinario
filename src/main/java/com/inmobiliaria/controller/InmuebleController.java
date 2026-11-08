package com.inmobiliaria.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.inmobiliaria.model.Inmueble;
import com.inmobiliaria.service.IInmuebleService;

@RestController
@RequestMapping("/inmuebles")
public class InmuebleController {

    private final IInmuebleService inmuebleService;

    public InmuebleController(IInmuebleService inmuebleService) {
        this.inmuebleService = inmuebleService;
    }

    @GetMapping
    public List<Inmueble> listarTodo() {
        return inmuebleService.listarTodo();
    }

    @GetMapping("/{id}")
    public Inmueble buscarPorId(@PathVariable("id") int id) {
        return inmuebleService.buscarPorId(id);
    }

    @PostMapping
    public boolean registrar(@RequestBody Inmueble i) {
        return inmuebleService.registrar(i);
    }

    @PutMapping
    public boolean modificar(@RequestBody Inmueble i) {
        return inmuebleService.modificar(i);
    }

    @PatchMapping("/{id}")
    public boolean actualizarPrecio(@PathVariable("id") int id, @RequestParam("precio") double nuevoPrecio) {
        return inmuebleService.actualizarPrecio(id, nuevoPrecio);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable("id") int id) {
        return inmuebleService.eliminarInmueble(id);
    }
}