package com.inmobiliaria.service; 

import java.util.List;
import org.springframework.stereotype.Service;

import com.inmobiliaria.dao.InmuebleRepository;
import com.inmobiliaria.model.Inmueble;

@Service
public class ImplementacionInmuebleService implements IInmuebleService {

    private final InmuebleRepository inmuebleDao;

    // Inyección de la interfaz por constructor
    public ImplementacionInmuebleService(InmuebleRepository inmuebleDao) {
        this.inmuebleDao = inmuebleDao;
    }

    @Override
    public List<Inmueble> listarTodo() {
        return inmuebleDao.listarTodosSP(); 
    }

    @Override
    public Inmueble buscarPorId(int id) {
        if (id <= 0) {
            System.err.println("ID inválido para la búsqueda");
            return null;
        }
        return inmuebleDao.buscarPorIdSP(id);
    }

    @Override
    public boolean registrar(Inmueble i) {
        if (i == null || i.getPropietario() == null || i.getCaptacion() == null) return false;
        try {
            String columnas = "direccion, tipo_inmueble, superficie_cubierta, superficie_total, cantidad_habitaciones, cantidad_banos, cantidad_cocheras, antiguedad_estado, id_propietario, id_captacion";
            
            String valores = "'" + i.getDireccion() + "', '" + 
                             i.getTipoInmueble() + "', " + 
                             i.getSuperficieCubierta() + ", " + 
                             i.getSuperficieTotal() + ", " + 
                             i.getCantidadHabitaciones() + ", " + 
                             i.getCantidadBanos() + ", " + 
                             i.getCantidadCocheras() + ", '" + 
                             i.getAntiguedadEstado() + "', " + 
                             i.getPropietario().getIdUsuario() + ", " + 
                             i.getCaptacion().getIdCaptacion();
            
            inmuebleDao.insertarSP(columnas, valores);
            return true;
        } catch (Exception e) {
            System.err.println("Error al registrar inmueble: " + e.getMessage());
            return false;
        }
    }
    @Override
    public boolean modificar(Inmueble i) {
        if (i == null || i.getIdInmueble() == null || i.getIdInmueble() <= 0) return false;
        try {
            String setValores = "direccion = '" + i.getDireccion() + "'";
            inmuebleDao.actualizarSP(setValores, i.getIdInmueble());
            return true;
        } catch (Exception e) {
            System.err.println("Error al modificar inmueble: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarPrecio(int id, double nuevoPrecio) {
        if (id <= 0) return false;
        try {
            
            String setValores = "antiguedad_estado = 'Actualizado " + nuevoPrecio + "'"; 
            inmuebleDao.actualizarSP(setValores, id);
            return true;
        } catch (Exception e) {
            System.err.println("Error en PATCH: " + e.getMessage());
            return false;
        }
    }
    @Override
    public boolean eliminarInmueble(int id) {
        if (id <= 0) return false;
        try {
            inmuebleDao.eliminarPorIdSP(id);
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar inmueble: " + e.getMessage());
            return false;
        }
    }
}