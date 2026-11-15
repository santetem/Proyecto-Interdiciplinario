package com.inmobiliaria.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.inmobiliaria.dao.OfertaRepository;
import com.inmobiliaria.dao.UsuarioRepository;
import com.inmobiliaria.dao.VentaRepository;
import com.inmobiliaria.dto.ConcretarVentaDTO;
import com.inmobiliaria.dto.VentaDTO;
import com.inmobiliaria.exception.ConflictoException;
import com.inmobiliaria.exception.RecursoNoEncontradoException;
import com.inmobiliaria.factory.IDaoFactory;
import com.inmobiliaria.model.Oferta;
import com.inmobiliaria.model.Venta;

/**
 * Concretar una venta = oferta aceptada + operación + venta + comisión.
 * Todo eso lo hace atómicamente el stored procedure sp_ConcretarVenta (con ROLLBACK si falla);
 * el Service valida los datos antes de llamarlo.
 */
@Service
public class ImplementacionVentaService implements IVentaService {

    private final OfertaRepository ofertaDao;
    private final VentaRepository ventaDao;
    private final UsuarioRepository usuarioDao;

    public ImplementacionVentaService(IDaoFactory factory) {
        this.ofertaDao = factory.getOfertaDao();
        this.ventaDao = factory.getVentaDao();
        this.usuarioDao = factory.getUsuarioDao();
    }

    @Override
    public void concretarVenta(ConcretarVentaDTO d, Integer idUsuarioSesion) {
        if (d == null || d.getIdOferta() == null || d.getIdOferta() <= 0) {
            throw new IllegalArgumentException("idOferta es obligatorio");
        }
        if (d.getMontoFinal() == null || d.getMontoFinal().signum() <= 0) {
            throw new IllegalArgumentException("El monto final debe ser mayor a 0");
        }
        BigDecimal porcentaje = d.getPorcentajeComision();
        if (porcentaje == null || porcentaje.signum() < 0 || porcentaje.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("porcentajeComision debe estar entre 0 y 100");
        }
        Integer idComision = d.getIdUsuarioComision() != null ? d.getIdUsuarioComision() : idUsuarioSesion;
        if (idComision == null || idComision <= 0) {
            throw new IllegalArgumentException("idUsuarioComision es obligatorio");
        }
        if (usuarioDao.buscarPorIdSP(idComision) == null) {
            throw new RecursoNoEncontradoException("No existe el usuario de la comisión con id " + idComision);
        }
        Oferta oferta = ofertaDao.buscarPorIdSP(d.getIdOferta());
        if (oferta == null) {
            throw new RecursoNoEncontradoException("No existe la oferta con id " + d.getIdOferta());
        }
        if ("Aceptada".equalsIgnoreCase(oferta.getEstado())) {
            throw new ConflictoException("La oferta ya fue aceptada anteriormente");
        }
        ventaDao.concretarVentaSP(d.getIdOferta(), d.getMontoFinal(), idComision, porcentaje);
    }

    @Override
    public List<VentaDTO> listarTodo() {
        List<VentaDTO> resultado = new ArrayList<>();
        for (Venta v : ventaDao.listarTodosSP()) {
            resultado.add(VentaDTO.desde(v));
        }
        return resultado;
    }
}
