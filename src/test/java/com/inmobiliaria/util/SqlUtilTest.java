package com.inmobiliaria.util;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SqlUtilTest {

    @Test
    void textoEscapaComillasSimples() {
        assertEquals("'O''Brien'", SqlUtil.texto("O'Brien"));
    }

    @Test
    void textoNeutralizaIntentoDeSqlInjection() {
        assertEquals("'x''); DROP TABLE inmuebles;--'", SqlUtil.texto("x'); DROP TABLE inmuebles;--"));
    }

    @Test
    void textoEscapaBarraInvertida() {
        assertEquals("'a\\\\'", SqlUtil.texto("a\\"));
    }

    @Test
    void textoNullDevuelveNULL() {
        assertEquals("NULL", SqlUtil.texto(null));
    }

    @Test
    void numeroFormateaBigDecimalSinNotacionCientifica() {
        assertEquals("1500000.50", SqlUtil.numero(new BigDecimal("1500000.50")));
        assertEquals("3", SqlUtil.numero(3));
        assertEquals("NULL", SqlUtil.numero(null));
    }

    @Test
    void numeroRechazaNaN() {
        assertThrows(IllegalArgumentException.class, () -> SqlUtil.numero(Double.NaN));
    }

    @Test
    void fechaUsaFormatoIso() {
        assertEquals("'2026-11-14'", SqlUtil.fecha(LocalDate.of(2026, 11, 14)));
        assertEquals("NULL", SqlUtil.fecha(null));
    }
}
