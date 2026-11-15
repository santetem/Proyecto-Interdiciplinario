package com.inmobiliaria.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.inmobiliaria.dao.CaptacionRepository;
import com.inmobiliaria.dao.InmuebleRepository;
import com.inmobiliaria.dao.UsuarioRepository;
import com.inmobiliaria.dto.InmuebleDTO;
import com.inmobiliaria.exception.RecursoNoEncontradoException;
import com.inmobiliaria.factory.IDaoFactory;
import com.inmobiliaria.model.Inmueble;
import com.inmobiliaria.util.SqlUtil;

/**
 * Lógica de negocio de Inmueble. Las validaciones usan "throw" (se rechaza la operación),
 * y el GlobalExceptionHandler las convierte en 400 / 404.
 * Los DAOs se obtienen de la FACTORY, no se instancian ni se acoplan a la clase concreta.
 */
@Service
public class ImplementacionInmuebleService implements IInmuebleService {

    private final InmuebleRepository inmuebleDao;
    private final UsuarioRepository usuarioDao;
    private final CaptacionRepository captacionDao;

    public ImplementacionInmuebleService(IDaoFactory factory) {
        this.inmuebleDao = factory.getInmuebleDao();
        this.usuarioDao = factory.getUsuarioDao();
        this.captacionDao = factory.getCaptacionDao();
    }

    @Override
    public List<InmuebleDTO> listarTodo() {
        List<InmuebleDTO> resultado = new ArrayList<>();
        for (Inmueble i : inmuebleDao.listarTodosSP()) {
            resultado.add(InmuebleDTO.desde(i));
        }
        return resultado;
    }

    @Override
    public InmuebleDTO buscarPorId(int id) {
        return InmuebleDTO.desde(obtenerExistente(id));
    }

    @Override
    public void registrar(InmuebleDTO d) {
        validarCompleto(d);
        verificarReferencias(d);
        String columnas = "direccion, tipo_inmueble, superficie_cubierta, superficie_total, cantidad_habitaciones, "
                + "cantidad_banos, cantidad_cocheras, antiguedad_estado, localidad, id_propietario, id_captacion";
        String valores = String.join(", ",
                SqlUtil.texto(d.getDireccion()),
                SqlUtil.texto(d.getTipoInmueble()),
                SqlUtil.numero(d.getSuperficieCubierta()),
                SqlUtil.numero(d.getSuperficieTotal()),
                SqlUtil.numero(d.getCantidadHabitaciones()),
                SqlUtil.numero(d.getCantidadBanos()),
                SqlUtil.numero(d.getCantidadCocheras()),
                SqlUtil.texto(d.getAntiguedadEstado()),
                SqlUtil.texto(d.getLocalidad()),
                SqlUtil.numero(d.getIdPropietario()),
                SqlUtil.numero(d.getIdCaptacion()));
        inmuebleDao.insertarSP(columnas, valores);
    }

    @Override
    public void modificar(int id, InmuebleDTO d) {
        obtenerExistente(id);
        validarCompleto(d);
        verificarReferencias(d);
        String set = String.join(", ",
                "direccion = " + SqlUtil.texto(d.getDireccion()),
                "tipo_inmueble = " + SqlUtil.texto(d.getTipoInmueble()),
                "superficie_cubierta = " + SqlUtil.numero(d.getSuperficieCubierta()),
                "superficie_total = " + SqlUtil.numero(d.getSuperficieTotal()),
                "cantidad_habitaciones = " + SqlUtil.numero(d.getCantidadHabitaciones()),
                "cantidad_banos = " + SqlUtil.numero(d.getCantidadBanos()),
                "cantidad_cocheras = " + SqlUtil.numero(d.getCantidadCocheras()),
                "antiguedad_estado = " + SqlUtil.texto(d.getAntiguedadEstado()),
                "localidad = " + SqlUtil.texto(d.getLocalidad()),
                "id_propietario = " + SqlUtil.numero(d.getIdPropietario()),
                "id_captacion = " + SqlUtil.numero(d.getIdCaptacion()));
        inmuebleDao.actualizarSP(set, id);
    }

    /** PATCH: solo cambia los campos que vienen en el JSON (los null se ignoran). */
    @Override
    public void actualizarParcial(int id, InmuebleDTO d) {
        obtenerExistente(id);
        if (d == null) {
            throw new IllegalArgumentException("Debe enviar al menos un campo para modificar");
        }
        List<String> partes = new ArrayList<>();
        if (d.getDireccion() != null) {
            exigirTexto(d.getDireccion(), "direccion");
            partes.add("direccion = " + SqlUtil.texto(d.getDireccion()));
        }
        if (d.getTipoInmueble() != null) {
            exigirTexto(d.getTipoInmueble(), "tipoInmueble");
            partes.add("tipo_inmueble = " + SqlUtil.texto(d.getTipoInmueble()));
        }
        if (d.getLocalidad() != null) {
            exigirTexto(d.getLocalidad(), "localidad");
            partes.add("localidad = " + SqlUtil.texto(d.getLocalidad()));
        }
        if (d.getAntiguedadEstado() != null) {
            partes.add("antiguedad_estado = " + SqlUtil.texto(d.getAntiguedadEstado()));
        }
        if (d.getSuperficieCubierta() != null) {
            exigirNoNegativo(d.getSuperficieCubierta(), "superficieCubierta");
            partes.add("superficie_cubierta = " + SqlUtil.numero(d.getSuperficieCubierta()));
        }
        if (d.getSuperficieTotal() != null) {
            exigirNoNegativo(d.getSuperficieTotal(), "superficieTotal");
            partes.add("superficie_total = " + SqlUtil.numero(d.getSuperficieTotal()));
        }
        if (d.getCantidadHabitaciones() != null) {
            exigirNoNegativo(d.getCantidadHabitaciones(), "cantidadHabitaciones");
            partes.add("cantidad_habitaciones = " + SqlUtil.numero(d.getCantidadHabitaciones()));
        }
        if (d.getCantidadBanos() != null) {
            exigirNoNegativo(d.getCantidadBanos(), "cantidadBanos");
            partes.add("cantidad_banos = " + SqlUtil.numero(d.getCantidadBanos()));
        }
        if (d.getCantidadCocheras() != null) {
            exigirNoNegativo(d.getCantidadCocheras(), "cantidadCocheras");
            partes.add("cantidad_cocheras = " + SqlUtil.numero(d.getCantidadCocheras()));
        }
        if (partes.isEmpty()) {
            throw new IllegalArgumentException("Debe enviar al menos un campo para modificar");
        }
        inmuebleDao.actualizarSP(String.join(", ", partes), id);
    }

    @Override
    public void eliminar(int id) {
        obtenerExistente(id);
        inmuebleDao.eliminarPorIdSP(id);
    }

    // ---------- métodos auxiliares ----------

    private Inmueble obtenerExistente(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id del inmueble debe ser mayor a 0");
        }
        Inmueble i = inmuebleDao.buscarPorIdSP(id);
        if (i == null) {
            throw new RecursoNoEncontradoException("No existe el inmueble con id " + id);
        }
        return i;
    }

    private void validarCompleto(InmuebleDTO d) {
        if (d == null) {
            throw new IllegalArgumentException("Los datos del inmueble son obligatorios");
        }
        exigirTexto(d.getDireccion(), "direccion");
        exigirTexto(d.getTipoInmueble(), "tipoInmueble");
        exigirTexto(d.getLocalidad(), "localidad");
        if (d.getIdPropietario() == null || d.getIdPropietario() <= 0) {
            throw new IllegalArgumentException("idPropietario es obligatorio");
        }
        if (d.getIdCaptacion() == null || d.getIdCaptacion() <= 0) {
            throw new IllegalArgumentException("idCaptacion es obligatorio");
        }
        if (d.getSuperficieCubierta() != null) exigirNoNegativo(d.getSuperficieCubierta(), "superficieCubierta");
        if (d.getSuperficieTotal() != null) exigirNoNegativo(d.getSuperficieTotal(), "superficieTotal");
        if (d.getCantidadHabitaciones() != null) exigirNoNegativo(d.getCantidadHabitaciones(), "cantidadHabitaciones");
        if (d.getCantidadBanos() != null) exigirNoNegativo(d.getCantidadBanos(), "cantidadBanos");
        if (d.getCantidadCocheras() != null) exigirNoNegativo(d.getCantidadCocheras(), "cantidadCocheras");
        // misma regla que el trigger trg_before_insert_inmueble de la base
        if (d.getSuperficieCubierta() != null && d.getSuperficieTotal() != null
                && d.getSuperficieCubierta().compareTo(d.getSuperficieTotal()) > 0) {
            throw new IllegalArgumentException("La superficie cubierta no puede ser mayor que la superficie total");
        }
    }

    private void verificarReferencias(InmuebleDTO d) {
        if (usuarioDao.buscarPorIdSP(d.getIdPropietario()) == null) {
            throw new RecursoNoEncontradoException("No existe el propietario con id " + d.getIdPropietario());
        }
        if (captacionDao.buscarPorIdSP(d.getIdCaptacion()) == null) {
            throw new RecursoNoEncontradoException("No existe la captación con id " + d.getIdCaptacion());
        }
    }

    private void exigirTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio");
        }
    }

    private void exigirNoNegativo(Number valor, String campo) {
        if (new BigDecimal(valor.toString()).signum() < 0) {
            throw new IllegalArgumentException("El campo " + campo + " no puede ser negativo");
        }
    }
}
