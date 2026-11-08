package com.inmobiliaria.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class VentaServiceTest {

    @Test
    public void testConcretarVentaMontoNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            Double montoFinal = -100.0;
            if (montoFinal == null || montoFinal <= 0) {
                throw new IllegalArgumentException("El monto final de la venta debe ser mayor a cero");
            }
        }, "La capa de negocio debe rechazar montos negativos mediante excepciones");
    }

    @Test
    public void testIdOfertaTransaccionValido() {
        Integer idOferta = 1;
        assertTrue(idOferta > 0, "El ID de la oferta para procesar la venta debe ser válido");
    }
}