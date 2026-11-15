package com.inmobiliaria.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.inmobiliaria.model.Inmueble;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;

/**
 * Prueba los métodos REALES del DAO genérico (usado por ImplementacionInmuebleRepository)
 * con un EntityManager simulado: no hace falta MySQL.
 */
class ImplementacionGenericDaoTest {

    private ImplementacionInmuebleRepository dao;
    private EntityManager em;
    private StoredProcedureQuery sp;

    @BeforeEach
    void setUp() {
        dao = new ImplementacionInmuebleRepository();
        em = mock(EntityManager.class);
        sp = mock(StoredProcedureQuery.class);
        dao.entityManager = em; // campo protected: mismo paquete
    }

    @Test
    void listarTodosSP_llamaAlProcedimientoConLaTablaCorrecta() {
        Inmueble i = new Inmueble();
        i.setIdInmueble(1);
        when(em.createStoredProcedureQuery("sp_General_Listar", Inmueble.class)).thenReturn(sp);
        when(sp.getResultList()).thenReturn(List.of(i));

        List<Inmueble> resultado = dao.listarTodosSP();

        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getIdInmueble());
        verify(sp).setParameter("p_nombre_tabla", "inmuebles");
    }

    @Test
    void buscarPorIdSP_devuelveLaEntidadCuandoExiste() {
        Inmueble i = new Inmueble();
        i.setIdInmueble(7);
        Query q = mock(Query.class, RETURNS_SELF);
        doReturn(List.of(i)).when(q).getResultList();
        when(em.createNativeQuery(anyString(), eq(Inmueble.class))).thenReturn(q);

        Inmueble resultado = dao.buscarPorIdSP(7);

        assertNotNull(resultado);
        assertEquals(7, resultado.getIdInmueble());
        verify(q).setParameter("tabla", "inmuebles");
        verify(q).setParameter("campoId", "id_inmueble");
        verify(q).setParameter("id", 7);
    }

    @Test
    void buscarPorIdSP_devuelveNullCuandoNoExiste() {
        Query q = mock(Query.class, RETURNS_SELF);
        doReturn(List.of()).when(q).getResultList();
        when(em.createNativeQuery(anyString(), eq(Inmueble.class))).thenReturn(q);

        assertNull(dao.buscarPorIdSP(99));
    }

    @Test
    void insertarSP_enviaTablaColumnasYValores() {
        when(em.createStoredProcedureQuery("sp_General_Insertar")).thenReturn(sp);

        dao.insertarSP("direccion, localidad", "'Calle 1', 'Rosario'");

        verify(sp).setParameter("p_nombre_tabla", "inmuebles");
        verify(sp).setParameter("p_columnas", "direccion, localidad");
        verify(sp).setParameter("p_valores", "'Calle 1', 'Rosario'");
        verify(sp).execute();
    }

    @Test
    void actualizarSP_enviaSetYClavePrimaria() {
        when(em.createStoredProcedureQuery("sp_General_Actualizar")).thenReturn(sp);

        dao.actualizarSP("localidad = 'Rosario'", 5);

        verify(sp).setParameter("p_nombre_tabla", "inmuebles");
        verify(sp).setParameter("p_set_valores", "localidad = 'Rosario'");
        verify(sp).setParameter("p_nombre_id", "id_inmueble");
        verify(sp).setParameter("p_id", 5);
        verify(sp).execute();
    }

    @Test
    void eliminarPorIdSP_enviaTablaEId() {
        when(em.createStoredProcedureQuery("sp_General_Eliminar")).thenReturn(sp);

        dao.eliminarPorIdSP(3);

        verify(sp).setParameter("p_nombre_tabla", "inmuebles");
        verify(sp).setParameter("p_nombre_id", "id_inmueble");
        verify(sp).setParameter("p_id", 3);
        verify(sp).execute();
    }
}
