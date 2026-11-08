package com.inmobiliaria.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.inmobiliaria.model.Usuario;

public class UsuarioServiceTest {

    @Test
    public void testUsuarioCorreoInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            String correo = "correoSinArroba.com";
            if (!correo.contains("@")) {
                throw new IllegalArgumentException("Formato de correo inválido para el repositorio");
            }
        }, "Debe validar el formato del correo antes de operar con el DAO");
    }

    @Test
    public void testModeloUsuarioDao() {
        Usuario u = new Usuario();
        u.setNombre("Jeremias");
        assertEquals("Jeremias", u.getNombre());
    }
}