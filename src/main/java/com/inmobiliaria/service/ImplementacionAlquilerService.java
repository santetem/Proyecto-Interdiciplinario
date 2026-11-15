package com.inmobiliaria.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.inmobiliaria.dao.AlquilerRepository;
import com.inmobiliaria.dao.OperacionRepository;
import com.inmobiliaria.dto.AlquilerDTO;
import com.inmobiliaria.exception.RecursoNoEncontradoException;
import com.inmobiliaria.factory.IDaoFactory;
import com.inmobiliaria.model.Alquiler;
import com.inmobiliaria.util.SqlUtil;

/** Lógica de negocio de Alquiler (antes estaba mezclada dentro del Controller). */
@Service
public class ImplementacionAlquilerService implements IAlquilerService {

    private final AlquilerRepository alquilerDao;
    private final OperacionRepository operacionDao;

    public ImplementacionAlquilerService(IDaoFactory factory) {
        this.alquilerDao = factory.getAlquilerDao();
        this.operacionDao = factory.getOperacionDao();
    }

    @Override
    public List<AlquilerDTO> listarTodo() {
        List<AlquilerDTO> resultado = new ArrayList<>();
        for (Alquiler a : alquilerDao.listarTodosSP()) {
            resultado.add(AlquilerDTO.desde(a));
        }
        return resultado;
    }

    @Override
    public AlquilerDTO buscarPorId(int id) {
        return AlquilerDTO.desde(obtenerExistente(id));
    }

    @Override
    public void registrar(AlquilerDTO d) {
        validarCompleto(d);
        if (operacionDao.buscarPorIdSP(d.getIdOperacion()) == null) {
            throw new RecursoNoEncontradoException("No existe la operación con id " + d.getIdOperacion());
        }
        String columnas = "id_operacion, fecha_inicio, fecha_fin, monto, estado";
        String valores = String.join(", ",
                SqlUtil.numero(d.getIdOperacion()),
                SqlUtil.fecha(d.getFechaInicio()),
                SqlUtil.fecha(d.getFechaFin()),
                SqlUtil.numero(d.getMonto()),
                SqlUtil.texto(d.getEstado()));
        alquilerDao.insertarSP(columnas, valores);
    }

    @Override
    public void modificar(int id, AlquilerDTO d) {
        obtenerExistente(id);
        validarCompleto(d);
        if (operacionDao.buscarPorIdSP(d.getIdOperacion()) == null) {
            throw new RecursoNoEncontradoException("No existe la operación con id " + d.getIdOperacion());
        }
        String set = String.join(", ",
                "id_operacion = " + SqlUtil.numero(d.getIdOperacion()),
                "fecha_inicio = " + SqlUtil.fecha(d.getFechaInicio()),
                "fecha_fin = " + SqlUtil.fecha(d.getFechaFin()),
                "monto = " + SqlUtil.numero(d.getMonto()),
                "estado = " + SqlUtil.texto(d.getEstado()));
        alquilerDao.actualizarSP(set, id);
    }

    /** PATCH: modifica solo monto, estado y/o fechaFin. */
    @Override
    public void actualizarParcial(int id, AlquilerDTO d) {
        Alquiler actual = obtenerExistente(id);
        if (d == null) {
            throw new IllegalArgumentException("Debe enviar al menos un campo para modificar");
        }
        List<String> partes = new ArrayList<>();
        if (d.getMonto() != null) {
            if (d.getMonto().signum() <= 0) {
                throw new IllegalArgumentException("El monto debe ser mayor a 0");
            }
            partes.add("monto = " + SqlUtil.numero(d.getMonto()));
        }
        if (d.getEstado() != null) {
            if (d.getEstado().trim().isEmpty()) {
                throw new IllegalArgumentException("El estado no puede estar vacío");
            }
            partes.add("estado = " + SqlUtil.texto(d.getEstado()));
        }
        if (d.getFechaFin() != null) {
            if (actual.getFechaInicio() != null && d.getFechaFin().isBefore(actual.getFechaInicio())) {
                throw new IllegalArgumentException("fechaFin no puede ser anterior a fechaInicio");
            }
            partes.add("fecha_fin = " + SqlUtil.fecha(d.getFechaFin()));
        }
        if (partes.isEmpty()) {
            throw new IllegalArgumentException("Debe enviar al menos un campo para modificar (monto, estado o fechaFin)");
        }
        alquilerDao.actualizarSP(String.join(", ", partes), id);
    }

    @Override
    public void eliminar(int id) {
        obtenerExistente(id);
        alquilerDao.eliminarPorIdSP(id);
    }

    private Alquiler obtenerExistente(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id del alquiler debe ser mayor a 0");
        }
        Alquiler a = alquilerDao.buscarPorIdSP(id);
        if (a == null) {
            throw new RecursoNoEncontradoException("No existe el alquiler con id " + id);
        }
        return a;
    }

    private void validarCompleto(AlquilerDTO d) {
        if (d == null) {
            throw new IllegalArgumentException("Los datos del alquiler son obligatorios");
        }
        if (d.getIdOperacion() == null || d.getIdOperacion() <= 0) {
            throw new IllegalArgumentException("idOperacion es obligatorio");
        }
        if (d.getFechaInicio() == null) {
            throw new IllegalArgumentException("fechaInicio es obligatoria");
        }
        if (d.getFechaFin() != null && d.getFechaFin().isBefore(d.getFechaInicio())) {
            throw new IllegalArgumentException("fechaFin no puede ser anterior a fechaInicio");
        }
        if (d.getMonto() == null || d.getMonto().signum() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }
        if (d.getEstado() == null || d.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }
    }
}
