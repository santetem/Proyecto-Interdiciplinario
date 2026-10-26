package com.inmobiliaria.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.inmobiliaria.model.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ModelosTest {

    @Test
    public void testInmuebleModel() {
        Inmueble i = new Inmueble();
        i.setIdInmueble(1);
        i.setDireccion("Av. Corrientes 1234");
        assertEquals(1, i.getIdInmueble());
        assertEquals("Av. Corrientes 1234", i.getDireccion());
    }

    @Test
    public void testAlquilerModel() {
        Alquiler a = new Alquiler();
        a.setIdAlquiler(10);
        a.setEstado("Activo");
        a.setMonto(new BigDecimal("75000.00"));
        assertEquals(10, a.getIdAlquiler());
        assertEquals("Activo", a.getEstado());
        assertEquals(new BigDecimal("75000.00"), a.getMonto());
    }

    @Test
    public void testUsuarioModel() {
        Usuario u = new Usuario();
        u.setCorreo("jeremias@remax.com");
        u.setNombre("Jeremías");
        assertEquals("jeremias@remax.com", u.getCorreo());
        assertEquals("Jeremías", u.getNombre());
    }

    @Test
    public void testOfertaModel() {
        Oferta o = new Oferta();
        o.setIdOferta(5);
        o.setEstado("Pendiente");
        assertEquals(5, o.getIdOferta());
        assertEquals("Pendiente", o.getEstado());
    }

    @Test
    public void testOperacionModel() {
        Operacion op = new Operacion();
        op.setIdOperacion(3);
        op.setTipoOperacion("VENTA");
        assertEquals(3, op.getIdOperacion());
        assertEquals("VENTA", op.getTipoOperacion());
    }

    @Test
    public void testVentaModel() {
        Venta v = new Venta();
        v.setIdVenta(1);
        v.setEstado("Finalizada");
        // Corregido: BigDecimal en lugar de Double
        v.setMontoFinal(new BigDecimal("200000.00"));
        assertEquals(1, v.getIdVenta());
        assertEquals("Finalizada", v.getEstado());
        assertEquals(new BigDecimal("200000.00"), v.getMontoFinal());
    }

    @Test
    public void testRestoDeModelosSecundarios() {
        // Validación de existencia de las demás entidades del paquete model
        assertNotNull(new Auditoria());
        assertNotNull(new Captacion());
        assertNotNull(new Caracteristica());
        assertNotNull(new Comision());
        assertNotNull(new Conversacion());
        assertNotNull(new Escribania());
        assertNotNull(new Escritura());
        assertNotNull(new Franquicia());
        assertNotNull(new HistorialOferta());
        assertNotNull(new InmuebleCaracteristicas());
        assertNotNull(new Mensaje());
        assertNotNull(new Multimedia());
        assertNotNull(new Permiso());
        assertNotNull(new Personal());
        assertNotNull(new Publicacion());
        assertNotNull(new Registrosalquiler());
        assertNotNull(new RelacionPermisos());
        assertNotNull(new RelacionRoles());
        assertNotNull(new Rol());
        assertNotNull(new SoliFranquicias());
        assertNotNull(new Tasacion());
        assertNotNull(new Tiporegistro());
        assertNotNull(new Visita());
    }
}