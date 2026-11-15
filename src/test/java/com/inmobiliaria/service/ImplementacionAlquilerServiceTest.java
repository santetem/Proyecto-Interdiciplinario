package com.inmobiliaria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.inmobiliaria.dao.AlquilerRepository;
import com.inmobiliaria.dao.OperacionRepository;
import com.inmobiliaria.dto.AlquilerDTO;
import com.inmobiliaria.exception.RecursoNoEncontradoException;
import com.inmobiliaria.factory.IDaoFactory;
import com.inmobiliaria.model.Alquiler;
import com.inmobiliaria.model.Operacion;

class ImplementacionAlquilerServiceTest {

    private AlquilerRepository alquilerDao;
    private OperacionRepository operacionDao;
    private ImplementacionAlquilerService service;

    @BeforeEach
    void setUp() {
        IDaoFactory factory = mock(IDaoFactory.class);
        alquilerDao = mock(AlquilerRepository.class);
        operacionDao = mock(OperacionRepository.class);
        when(factory.getAlquilerDao()).thenReturn(alquilerDao);
        when(factory.getOperacionDao()).thenReturn(operacionDao);
        service = new ImplementacionAlquilerService(factory);
    }

    private AlquilerDTO dtoValido() {
        AlquilerDTO d = new AlquilerDTO();
        d.setIdOperacion(3);
        d.setFechaInicio(LocalDate.of(2026, 1, 1));
        d.setFechaFin(LocalDate.of(2027, 1, 1));
        d.setMonto(new BigDecimal("450000"));
        d.setEstado("Activo");
        return d;
    }

    private Alquiler alquilerExistente() {
        Alquiler a = new Alquiler();
        a.setIdAlquiler(1);
        a.setFechaInicio(LocalDate.of(2026, 1, 1));
        a.setMonto(new BigDecimal("1000"));
        a.setEstado("Activo");
        return a;
    }

    @Test
    void registrar_valido_insertaConFechasYMontoFormateados() {
        when(operacionDao.buscarPorIdSP(3)).thenReturn(new Operacion());

        service.registrar(dtoValido());

        verify(alquilerDao).insertarSP("id_operacion, fecha_inicio, fecha_fin, monto, estado",
                "3, '2026-01-01', '2027-01-01', 450000, 'Activo'");
    }

    @Test
    void registrar_sinFechaFin_enviaNULL() {
        when(operacionDao.buscarPorIdSP(3)).thenReturn(new Operacion());
        AlquilerDTO d = dtoValido();
        d.setFechaFin(null);

        service.registrar(d);

        verify(alquilerDao).insertarSP(anyString(), eq("3, '2026-01-01', NULL, 450000, 'Activo'"));
    }

    @Test
    void registrar_montoCero_lanzaIllegalArgument() {
        AlquilerDTO d = dtoValido();
        d.setMonto(BigDecimal.ZERO);
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
        verifyNoInteractions(alquilerDao);
    }

    @Test
    void registrar_fechaFinAnterior_lanzaIllegalArgument() {
        AlquilerDTO d = dtoValido();
        d.setFechaFin(LocalDate.of(2025, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
    }

    @Test
    void registrar_operacionInexistente_lanzaNoEncontrado() {
        when(operacionDao.buscarPorIdSP(3)).thenReturn(null);
        assertThrows(RecursoNoEncontradoException.class, () -> service.registrar(dtoValido()));
    }

    @Test
    void actualizarParcial_monto_soloActualizaMonto() {
        when(alquilerDao.buscarPorIdSP(1)).thenReturn(alquilerExistente());
        AlquilerDTO d = new AlquilerDTO();
        d.setMonto(new BigDecimal("2000"));

        service.actualizarParcial(1, d);

        verify(alquilerDao).actualizarSP("monto = 2000", 1);
    }

    @Test
    void actualizarParcial_sinCampos_lanzaIllegalArgument() {
        when(alquilerDao.buscarPorIdSP(1)).thenReturn(alquilerExistente());
        assertThrows(IllegalArgumentException.class, () -> service.actualizarParcial(1, new AlquilerDTO()));
    }

    @Test
    void buscarPorId_noExiste_lanzaNoEncontrado() {
        when(alquilerDao.buscarPorIdSP(5)).thenReturn(null);
        assertThrows(RecursoNoEncontradoException.class, () -> service.buscarPorId(5));
    }

    @Test
    void eliminar_idInvalido_lanzaIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> service.eliminar(0));
    }

    @Test
    void eliminar_existente_llamaAlDao() {
        when(alquilerDao.buscarPorIdSP(1)).thenReturn(alquilerExistente());
        service.eliminar(1);
        verify(alquilerDao).eliminarPorIdSP(1);
    }
}
