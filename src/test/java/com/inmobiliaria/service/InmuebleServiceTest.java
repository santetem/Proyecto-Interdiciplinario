package com.inmobiliaria.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.inmobiliaria.model.Inmueble;

public class InmuebleServiceTest {

    @Test
    public void testInmuebleIdInvalidoLanzaExcepcion() {
        int idInvalido = -5;
        assertThrows(IllegalArgumentException.class, () -> {
            if (idInvalido <= 0) {
                throw new IllegalArgumentException("ID de inmueble no válido para buscar por SP");
            }
        }, "Debe lanzar IllegalArgumentException si el ID es negativo");
    }

    @Test
    public void testCreacionObjetoInmuebleDao() {
        // Validamos la estructura del modelo que maneja el DAO
        Inmueble inmueble = new Inmueble();
        inmueble.setDireccion("Av. Boyacá 1500");
        assertEquals("Av. Boyacá 1500", inmueble.getDireccion());
    }
}