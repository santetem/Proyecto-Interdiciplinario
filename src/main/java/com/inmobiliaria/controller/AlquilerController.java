package com.inmobiliaria.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.inmobiliaria.dao.AlquilerRepository;
import com.inmobiliaria.model.Alquiler;

@RestController
@RequestMapping("/alquileres")
public class AlquilerController {

    // Se declara usando la INTERFAZ (como pide tu profe)
    private final AlquilerRepository alquilerDao;

    // Spring inyecta la implementación automáticamente mediante el constructor
    public AlquilerController(AlquilerRepository alquilerDao) {
        this.alquilerDao = alquilerDao;
    }

    @GetMapping
    public List<Alquiler> listarTodo() {
        return alquilerDao.listarTodosSP();
    }

    @GetMapping("/{id}")
    public Alquiler buscarPorId(@PathVariable("id") int id) {
        if (id <= 0) return null;
        try {
            return alquilerDao.buscarPorIdSP(id);
        } catch (Exception e) {
            System.err.println("Alquiler no encontrado con ID: " + id);
            return null;
        }
    }

    @PostMapping
    public boolean registrar(@RequestBody Alquiler a) {
        if (a == null || a.getOperacion() == null) return false;
        try {
            String columnas = "id_operacion, fecha_inicio, fecha_fin, monto, estado";
            String fechaFinStr = (a.getFechaFin() != null) ? "'" + a.getFechaFin() + "'" : "NULL";
            String valores = a.getOperacion().getIdOperacion() + ", '" 
                           + a.getFechaInicio() + "', " 
                           + fechaFinStr + ", " 
                           + a.getMonto() + ", '" 
                           + a.getEstado() + "'";
            
            alquilerDao.insertarSP(columnas, valores);
            return true;
        } catch (Exception e) {
            System.err.println("Error al insertar alquiler: " + e.getMessage());
            return false;
        }
    }

    @PutMapping
    public boolean modificar(@RequestBody Alquiler a) {
        if (a == null || a.getIdAlquiler() == null || a.getIdAlquiler() <= 0) return false;
        try {
            String fechaFinStr = (a.getFechaFin() != null) ? "'" + a.getFechaFin() + "'" : "NULL";
            String setValores = "id_operacion = " + a.getOperacion().getIdOperacion() 
                              + ", fecha_inicio = '" + a.getFechaInicio() + "'"
                              + ", fecha_fin = " + fechaFinStr
                              + ", monto = " + a.getMonto()
                              + ", estado = '" + a.getEstado() + "'";
            
            alquilerDao.actualizarSP(setValores, a.getIdAlquiler());
            return true;
        } catch (Exception e) {
            System.err.println("Error al modificar alquiler: " + e.getMessage());
            return false;
        }
    }

    @PatchMapping("/{id}")
    public boolean actualizarMonto(@PathVariable("id") int id, @RequestBody Alquiler cuerpoParcial) {
        if (id <= 0 || cuerpoParcial.getMonto() == null) return false;
        try {
            String setValores = "monto = " + cuerpoParcial.getMonto();
            alquilerDao.actualizarSP(setValores, id);
            return true;
        } catch (Exception e) {
            System.err.println("Error al aplicar PATCH en alquiler: " + e.getMessage());
            return false;
        }
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable("id") int id) {
        if (id <= 0) return false;
        try {
            alquilerDao.eliminarPorIdSP(id);
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar alquiler: " + e.getMessage());
            return false;
        }
    }
}