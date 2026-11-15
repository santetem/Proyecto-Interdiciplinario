package com.inmobiliaria.security;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;

class SesionServiceTest {

    @Test
    void crearYObtener_devuelveLaSesion() {
        SesionService s = new SesionService();
        String token = s.crear(1, "a@a.com", List.of("Administrador"));
        assertEquals("a@a.com", s.obtener(token).getCorreo());
    }

    @Test
    void obtener_tokenDesconocidoONull_devuelveNull() {
        SesionService s = new SesionService();
        assertNull(s.obtener("xxx"));
        assertNull(s.obtener(null));
    }

    @Test
    void sesionVencida_noSeConsidera() {
        Sesion vencida = new Sesion(1, "a@a.com", List.of("Administrador"), Instant.now().minusSeconds(5));
        assertTrue(vencida.vencida());
    }

    @Test
    void tieneAlgunRol_ignoraMayusculas() {
        Sesion s = new Sesion(1, "a@a.com", List.of("Agente"), Instant.now().plusSeconds(60));
        assertTrue(s.tieneAlgunRol(new String[] {"Administrador", "Agente"}));
        assertFalse(s.tieneAlgunRol(new String[] {"Administrador"}));
    }
}
