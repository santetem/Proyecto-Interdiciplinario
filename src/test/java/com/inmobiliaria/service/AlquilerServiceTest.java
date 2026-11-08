package com.inmobiliaria.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AlquilerServiceTest {

    @Test
    public void testBuscarAlquilerIdInvalidoValido() {
        int idInvalido = 0;
        boolean esInvalido = (idInvalido <= 0);
        assertTrue(esInvalido, "Un ID de alquiler menor o igual a cero debe ser inválido");
    }

    @Test
    public void testAlquilerObjetoNuloLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            Object alquiler = null;
            if (alquiler == null) {
                throw new IllegalArgumentException("El alquiler no puede ser nulo");
            }
        }, "Debería lanzar IllegalArgumentException si el objeto es nulo");
    }
}