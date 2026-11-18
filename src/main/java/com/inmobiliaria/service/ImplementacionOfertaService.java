package com.inmobiliaria.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import com.inmobiliaria.dao.InmuebleRepository;
import com.inmobiliaria.dao.OfertaRepository;
import com.inmobiliaria.dao.UsuarioRepository;
import com.inmobiliaria.dto.OfertaDTO;
import com.inmobiliaria.exception.ConflictoException;
import com.inmobiliaria.exception.RecursoNoEncontradoException;
import com.inmobiliaria.factory.IDaoFactory;
import com.inmobiliaria.model.Inmueble;
import com.inmobiliaria.util.SqlUtil;

/** Cualquier usuario registrado puede ofertar (salvo sobre su propio inmueble). */
@Service
public class ImplementacionOfertaService implements IOfertaService {

    public static final String ESTADO_INICIAL = "Pendiente";

    private final OfertaRepository ofertaDao;
    private final InmuebleRepository inmuebleDao;
    private final UsuarioRepository usuarioDao;

    public ImplementacionOfertaService(IDaoFactory factory) {
        this.ofertaDao = factory.getOfertaDao();
        this.inmuebleDao = factory.getInmuebleDao();
        this.usuarioDao = factory.getUsuarioDao();
    }

    @Override
    public void registrar(int idUsuario, OfertaDTO d) {
        if (d == null) {
            throw new IllegalArgumentException("Los datos de la oferta son obligatorios");
        }
        if (d.getIdInmueble() == null || d.getIdInmueble() <= 0) {
            throw new IllegalArgumentException("idInmueble es obligatorio");
        }
        if (d.getMonto() == null || d.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }
        if (usuarioDao.buscarPorIdSP(idUsuario) == null) {
            throw new RecursoNoEncontradoException("No existe el usuario con id " + idUsuario);
        }
        Inmueble inmueble = inmuebleDao.buscarPorIdSP(d.getIdInmueble());
        if (inmueble == null) {
            throw new RecursoNoEncontradoException("No existe el inmueble con id " + d.getIdInmueble());
        }
        if (inmueble.getPropietario() != null && inmueble.getPropietario().getIdUsuario() == idUsuario) {
            throw new ConflictoException("No podés ofertar por tu propio inmueble");
        }
        String columnas = "id_usuario, id_inmueble, monto, fecha, estado";
        String valores = String.join(", ",
                SqlUtil.numero(idUsuario),
                SqlUtil.numero(d.getIdInmueble()),
                SqlUtil.numero(d.getMonto()),
                SqlUtil.fecha(LocalDate.now()),
                SqlUtil.texto(ESTADO_INICIAL));
        ofertaDao.insertarSP(columnas, valores);
    }
}
