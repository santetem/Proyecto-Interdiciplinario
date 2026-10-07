package com.inmobiliaria.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inmobiliaria.dao.InmuebleRepository;
import com.inmobiliaria.model.Inmueble;

@Service
public class InmuebleServiceImpl implements IInmuebleService {

    @Autowired
    private InmuebleRepository inmuebleDao;

    @Override
    public List<Inmueble> listerTodo() {
        return inmuebleDao.obtenerTodosSP();
    }

    @Override
    public Inmueble buscarPorId(int id) {
        if (id <= 0) {
            System.err.println("X ID inválido para la búsqueda");
            return null;
        }
        return inmuebleDao.obtenerPorIdSP(id);
    }

    @Override
    public boolean registrar(Inmueble inmueble) {
        if (inmueble.getDireccion() == null || inmueble.getDireccion().trim().isEmpty()) {
            System.err.println("X No se puede registrar un inmueble sin dirección");
            return false;
        }
        
        try {
            // Mapeamos con las columnas y atributos reales de tu modelo Inmueble
            String columnas = "id_captacion, id_propietario, direccion, tipo_inmueble, superficie_total, antiguedad_estado"; 
            
            String valores = inmueble.getCaptacion().getIdCaptacion() + ", "
                    + inmueble.getPropietario().getIdUsuario() + ", '"
                    + inmueble.getDireccion() + "', '"
                    + inmueble.getTipoInmueble() + "', "
                    + inmueble.getSuperficieTotal() + ", '"
                    + inmueble.getAntiguedadEstado() + "'";
            
            inmuebleDao.insertarSP(columnas, valores);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Inmueble inmueble) {
        if (inmueble.getIdInmueble() == null || inmueble.getIdInmueble() <= 0) {
            System.err.println("X No se puede modificar un inmueble con ID inválido");
            return false;
        }
        
        try {
            // Estructura del SET adaptada a las propiedades de tu modelo
            String setValores = "direccion = '" + inmueble.getDireccion() + "', "
                    + "tipo_inmueble = '" + inmueble.getTipoInmueble() + "', "
                    + "superficie_total = " + inmueble.getSuperficieTotal() + ", "
                    + "antiguedad_estado = '" + inmueble.getAntiguedadEstado() + "'";
            
            Integer id = inmueble.getIdInmueble();
            
            inmuebleDao.actualizarSP(setValores, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarInmueble(int id) {
        if (id <= 0) {
            return false;
        }
        
        try {
            inmuebleDao.eliminarPorIdSP(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
