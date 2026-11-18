package com.inmobiliaria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import com.inmobiliaria.dao.CaptacionRepository;
import com.inmobiliaria.dao.MultimediaRepository;
import com.inmobiliaria.dao.OperacionRepository;
import com.inmobiliaria.dao.InmuebleRepository;
import com.inmobiliaria.dao.UsuarioRepository;
import com.inmobiliaria.dto.InmuebleDTO;
import com.inmobiliaria.exception.ConflictoException;
import com.inmobiliaria.exception.RecursoNoEncontradoException;
import com.inmobiliaria.factory.IDaoFactory;
import com.inmobiliaria.model.Captacion;
import com.inmobiliaria.model.Inmueble;
import com.inmobiliaria.model.Multimedia;
import com.inmobiliaria.model.Oferta;
import com.inmobiliaria.model.Operacion;
import com.inmobiliaria.model.Usuario;

/** Prueba la lógica REAL del Service; los DAOs se simulan a través de la Factory. */
class ImplementacionInmuebleServiceTest {

    private InmuebleRepository inmuebleDao;
    private UsuarioRepository usuarioDao;
    private CaptacionRepository captacionDao;
    private MultimediaRepository multimediaDao;
    private OperacionRepository operacionDao;
    private AlmacenImagenes almacen;
    private ImplementacionInmuebleService service;

    @BeforeEach
    void setUp() {
        IDaoFactory factory = mock(IDaoFactory.class);
        inmuebleDao = mock(InmuebleRepository.class);
        usuarioDao = mock(UsuarioRepository.class);
        captacionDao = mock(CaptacionRepository.class);
        multimediaDao = mock(MultimediaRepository.class);
        operacionDao = mock(OperacionRepository.class);
        almacen = mock(AlmacenImagenes.class);
        when(factory.getInmuebleDao()).thenReturn(inmuebleDao);
        when(factory.getUsuarioDao()).thenReturn(usuarioDao);
        when(factory.getCaptacionDao()).thenReturn(captacionDao);
        when(factory.getMultimediaDao()).thenReturn(multimediaDao);
        when(factory.getOperacionDao()).thenReturn(operacionDao);
        service = new ImplementacionInmuebleService(factory, almacen);
    }

    private InmuebleDTO dtoValido() {
        InmuebleDTO d = new InmuebleDTO();
        d.setDireccion("Av. Siempre Viva 742");
        d.setTipoInmueble("Casa");
        d.setLocalidad("Rosario");
        d.setSuperficieCubierta(new BigDecimal("120.50"));
        d.setSuperficieTotal(new BigDecimal("300"));
        d.setCantidadHabitaciones(3);
        d.setCantidadBanos(2);
        d.setCantidadCocheras(1);
        d.setAntiguedadEstado("A estrenar");
        d.setIdPropietario(1);
        d.setIdCaptacion(2);
        return d;
    }

    private Inmueble inmuebleExistente(int id) {
        Inmueble i = new Inmueble();
        i.setIdInmueble(id);
        i.setDireccion("Calle 1");
        i.setLocalidad("Rosario");
        Usuario u = new Usuario();
        u.setIdUsuario(1);
        i.setPropietario(u);
        Captacion c = new Captacion();
        c.setIdCaptacion(2);
        i.setCaptacion(c);
        return i;
    }

    private void propietarioYCaptacionExisten() {
        when(usuarioDao.buscarPorIdSP(1)).thenReturn(new Usuario());
        when(captacionDao.buscarPorIdSP(2)).thenReturn(new Captacion());
    }

    @Test
    void listarTodo_incluyeLasImagenesDelInmueble() {
        Inmueble i = inmuebleExistente(1);
        when(inmuebleDao.listarTodosSP()).thenReturn(List.of(i));
        when(multimediaDao.listarTodosSP()).thenReturn(List.of(new Multimedia(i, "imagen", "/uploads/a.jpg")));

        List<InmuebleDTO> lista = service.listarTodo();

        assertEquals(List.of("/uploads/a.jpg"), lista.get(0).getImagenes());
    }

    @Test
    void listarTodo_convierteEntidadesADTO() {
        when(inmuebleDao.listarTodosSP()).thenReturn(List.of(inmuebleExistente(1), inmuebleExistente(2)));

        List<InmuebleDTO> lista = service.listarTodo();

        assertEquals(2, lista.size());
        assertEquals(1, lista.get(0).getIdPropietario());
        assertEquals(2, lista.get(0).getIdCaptacion());
    }

    @Test
    void buscarPorId_idInvalido_lanzaIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> service.buscarPorId(-5));
        verifyNoInteractions(inmuebleDao);
    }

    @Test
    void buscarPorId_noExiste_lanzaNoEncontrado() {
        when(inmuebleDao.buscarPorIdSP(9)).thenReturn(null);
        assertThrows(RecursoNoEncontradoException.class, () -> service.buscarPorId(9));
    }

    @Test
    void buscarPorId_existe_devuelveDTO() {
        when(inmuebleDao.buscarPorIdSP(4)).thenReturn(inmuebleExistente(4));
        assertEquals("Calle 1", service.buscarPorId(4).getDireccion());
    }

    @Test
    void registrar_valido_insertaTodasLasColumnasIncluidaLocalidad() {
        propietarioYCaptacionExisten();

        service.registrar(dtoValido());

        ArgumentCaptor<String> columnas = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> valores = ArgumentCaptor.forClass(String.class);
        verify(inmuebleDao).insertarSP(columnas.capture(), valores.capture());
        assertTrue(columnas.getValue().contains("localidad"));
        assertTrue(valores.getValue().contains("'Av. Siempre Viva 742'"));
        assertTrue(valores.getValue().contains("'Rosario'"));
        assertTrue(valores.getValue().contains("120.50"));
    }

    @Test
    void registrar_conInyeccionSql_escapaLasComillas() {
        propietarioYCaptacionExisten();
        InmuebleDTO d = dtoValido();
        d.setDireccion("x'); DROP TABLE inmuebles;--");

        service.registrar(d);

        ArgumentCaptor<String> valores = ArgumentCaptor.forClass(String.class);
        verify(inmuebleDao).insertarSP(anyString(), valores.capture());
        assertTrue(valores.getValue().contains("'x''); DROP TABLE inmuebles;--'"));
    }

    @Test
    void registrar_sinDireccion_lanzaIllegalArgumentYNoInserta() {
        InmuebleDTO d = dtoValido();
        d.setDireccion("  ");
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
        verify(inmuebleDao, never()).insertarSP(anyString(), anyString());
    }

    @Test
    void registrar_superficieNegativa_lanzaIllegalArgument() {
        InmuebleDTO d = dtoValido();
        d.setSuperficieTotal(new BigDecimal("-1"));
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
    }

    @Test
    void registrar_dtoNull_lanzaIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> service.registrar(null));
    }

    @Test
    void registrar_propietarioInexistente_lanzaNoEncontrado() {
        when(usuarioDao.buscarPorIdSP(1)).thenReturn(null);
        assertThrows(RecursoNoEncontradoException.class, () -> service.registrar(dtoValido()));
        verify(inmuebleDao, never()).insertarSP(anyString(), anyString());
    }

    @Test
    void modificar_valido_actualizaTodosLosCampos() {
        when(inmuebleDao.buscarPorIdSP(5)).thenReturn(inmuebleExistente(5));
        propietarioYCaptacionExisten();

        service.modificar(5, dtoValido());

        ArgumentCaptor<String> set = ArgumentCaptor.forClass(String.class);
        verify(inmuebleDao).actualizarSP(set.capture(), eq(5));
        assertTrue(set.getValue().contains("direccion = 'Av. Siempre Viva 742'"));
        assertTrue(set.getValue().contains("localidad = 'Rosario'"));
    }

    @Test
    void modificar_noExiste_lanzaNoEncontrado() {
        when(inmuebleDao.buscarPorIdSP(5)).thenReturn(null);
        assertThrows(RecursoNoEncontradoException.class, () -> service.modificar(5, dtoValido()));
    }

    @Test
    void actualizarParcial_soloCambiaLosCamposEnviados() {
        when(inmuebleDao.buscarPorIdSP(5)).thenReturn(inmuebleExistente(5));
        InmuebleDTO d = new InmuebleDTO();
        d.setLocalidad("Funes");

        service.actualizarParcial(5, d);

        verify(inmuebleDao).actualizarSP("localidad = 'Funes'", 5);
    }

    @Test
    void actualizarParcial_sinCampos_lanzaIllegalArgument() {
        when(inmuebleDao.buscarPorIdSP(5)).thenReturn(inmuebleExistente(5));
        assertThrows(IllegalArgumentException.class, () -> service.actualizarParcial(5, new InmuebleDTO()));
        verify(inmuebleDao, never()).actualizarSP(anyString(), anyInt());
    }

    @Test
    void eliminar_existente_borraConElSpCompletoYLasFotosDelDisco() {
        Inmueble i = inmuebleExistente(8);
        when(inmuebleDao.buscarPorIdSP(8)).thenReturn(i);
        when(multimediaDao.listarTodosSP()).thenReturn(List.of(new Multimedia(i, "imagen", "/uploads/a.jpg")));

        service.eliminar(8);

        verify(inmuebleDao).eliminarCompletoSP(8);
        verify(almacen).eliminar("/uploads/a.jpg");
    }

    @Test
    void eliminar_conOperaciones_lanzaConflictoYNoElimina() {
        Inmueble i = inmuebleExistente(8);
        when(inmuebleDao.buscarPorIdSP(8)).thenReturn(i);
        Oferta oferta = new Oferta();
        oferta.setInmueble(i);
        Operacion operacion = new Operacion();
        operacion.setOferta(oferta);
        when(operacionDao.listarTodosSP()).thenReturn(List.of(operacion));

        assertThrows(ConflictoException.class, () -> service.eliminar(8));
        verify(inmuebleDao, never()).eliminarCompletoSP(anyInt());
        verify(almacen, never()).eliminar(anyString());
    }

    @Test
    void eliminar_noExiste_lanzaNoEncontradoYNoElimina() {
        when(inmuebleDao.buscarPorIdSP(8)).thenReturn(null);
        assertThrows(RecursoNoEncontradoException.class, () -> service.eliminar(8));
        verify(inmuebleDao, never()).eliminarCompletoSP(anyInt());
    }
}
