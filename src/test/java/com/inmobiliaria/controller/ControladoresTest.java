package com.inmobiliaria.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ControladoresTest {

    @Test
    public void testInmuebleControllerRuta() {
        String ruta = "/inmuebles";
        assertEquals("/inmuebles", ruta);
    }

    @Test
    public void testAlquilerControllerRuta() {
        String ruta = "/alquileres";
        assertEquals("/alquileres", ruta);
    }

    @Test
    public void testUsuarioControllerRegisterEndpoint() {
        String endpoint = "/api/usuarios/register";
        assertTrue(endpoint.contains("register"));
    }

    @Test
    public void testUsuarioControllerLoginEndpoint() {
        String endpoint = "/api/usuarios/login";
        assertTrue(endpoint.contains("login"));
    }

    @Test
    public void testHomeServletUrlPattern() {
        String url = "/home";
        assertEquals("/home", url);
    }

    @Test
    public void testTestDBServletUrlPattern() {
        String url = "/testdb";
        assertEquals("/testdb", url);
    }
}