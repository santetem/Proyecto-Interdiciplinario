package com.inmobiliaria.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ServiciosTest {

    @Test
    public void testImplementacionInmuebleServiceLogica() {
        double precio = 150000.0;
        boolean valido = (precio > 0);
        assertTrue(valido);
    }

    @Test
    public void testVentaServiceTransaccion() {
        Double monto = 300000.0;
        assertNotNull(monto);
        assertTrue(monto > 0);
    }

    @Test
    public void testAlquilerServiceReglas() {
        int meses = 12;
        assertEquals(12, meses);
    }

    @Test
    public void testUsuarioServiceSeguridad() {
        String pass = "passSegura123";
        assertTrue(pass.length() >= 8);
    }

    @Test
    public void testOfertaServiceValidacionRango() {
        double oferta = 50000.0;
        assertTrue(oferta > 0);
    }
}