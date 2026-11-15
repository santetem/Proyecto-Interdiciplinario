package com.inmobiliaria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.inmobiliaria.dao.OfertaRepository;
import com.inmobiliaria.dao.UsuarioRepository;
import com.inmobiliaria.dao.VentaRepository;
import com.inmobiliaria.dto.ConcretarVentaDTO;
import com.inmobiliaria.exception.ConflictoException;
import com.inmobiliaria.exception.RecursoNoEncontradoException;
import com.inmobiliaria.factory.IDaoFactory;
import com.inmobiliaria.model.Oferta;
import com.inmobiliaria.model.Usuario;

class ImplementacionVentaServiceTest {

    private OfertaRepository ofertaDao;
    private VentaRepository ventaDao;
    private UsuarioRepository usuarioDao;
    private ImplementacionVentaService service;

    @BeforeEach
    void setUp() {
        IDaoFactory factory = mock(IDaoFactory.class);
        ofertaDao = mock(OfertaRepository.class);
        ventaDao = mock(VentaRepository.class);
        usuarioDao = mock(UsuarioRepository.class);
        when(factory.getOfertaDao()).thenReturn(ofertaDao);
        when(factory.getVentaDao()).thenReturn(ventaDao);
        when(factory.getUsuarioDao()).thenReturn(usuarioDao);
        service = new ImplementacionVentaService(factory);
    }

    private ConcretarVentaDTO dto() {
        ConcretarVentaDTO d = new ConcretarVentaDTO();
        d.setIdOferta(5);
        d.setMontoFinal(new BigDecimal("250000"));
        d.setPorcentajeComision(new BigDecimal("3"));
        return d;
    }

    private Oferta oferta(String estado) {
        Oferta o = new Oferta();
        o.setIdOferta(5);
        o.setEstado(estado);
        return o;
    }

    @Test
    void concretarVenta_ok_llamaAlStoredProcedureConElAgenteDeLaSesion() {
        when(ofertaDao.buscarPorIdSP(5)).thenReturn(oferta("Pendiente"));
        when(usuarioDao.buscarPorIdSP(7)).thenReturn(new Usuario());

        service.concretarVenta(dto(), 7);

        verify(ventaDao).concretarVentaSP(5, new BigDecimal("250000"), 7, new BigDecimal("3"));
    }

    @Test
    void concretarVenta_usaElAgenteDelDtoSiSeEnvia() {
        when(ofertaDao.buscarPorIdSP(5)).thenReturn(oferta("Pendiente"));
        when(usuarioDao.buscarPorIdSP(9)).thenReturn(new Usuario());
        ConcretarVentaDTO d = dto();
        d.setIdUsuarioComision(9);

        service.concretarVenta(d, 7);

        verify(ventaDao).concretarVentaSP(eq(5), any(), eq(9), any());
    }

    @Test
    void concretarVenta_ofertaInexistente_lanzaNoEncontrado() {
        when(usuarioDao.buscarPorIdSP(7)).thenReturn(new Usuario());
        when(ofertaDao.buscarPorIdSP(5)).thenReturn(null);
        assertThrows(RecursoNoEncontradoException.class, () -> service.concretarVenta(dto(), 7));
        verify(ventaDao, never()).concretarVentaSP(anyInt(), any(), anyInt(), any());
    }

    @Test
    void concretarVenta_ofertaYaAceptada_lanzaConflicto() {
        when(usuarioDao.buscarPorIdSP(7)).thenReturn(new Usuario());
        when(ofertaDao.buscarPorIdSP(5)).thenReturn(oferta("Aceptada"));
        assertThrows(ConflictoException.class, () -> service.concretarVenta(dto(), 7));
    }

    @Test
    void concretarVenta_montoInvalido_lanzaIllegalArgument() {
        ConcretarVentaDTO d = dto();
        d.setMontoFinal(BigDecimal.ZERO);
        assertThrows(IllegalArgumentException.class, () -> service.concretarVenta(d, 7));
    }

    @Test
    void concretarVenta_porcentajeFueraDeRango_lanzaIllegalArgument() {
        ConcretarVentaDTO d = dto();
        d.setPorcentajeComision(new BigDecimal("150"));
        assertThrows(IllegalArgumentException.class, () -> service.concretarVenta(d, 7));
        d.setPorcentajeComision(null);
        assertThrows(IllegalArgumentException.class, () -> service.concretarVenta(d, 7));
    }

    @Test
    void concretarVenta_sinOfertaOSinDto_lanzaIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> service.concretarVenta(null, 7));
        ConcretarVentaDTO d = dto();
        d.setIdOferta(null);
        assertThrows(IllegalArgumentException.class, () -> service.concretarVenta(d, 7));
    }
}
