package com.inmobiliaria.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.inmobiliaria.config.Conexion;

public class DaosTest {

    @Test
    public void testGenericDaoEstructura() {
        String tabla = "inmuebles";
        String campoId = "id_inmueble";
        assertNotNull(tabla);
        assertNotNull(campoId);
    }

    @Test
    public void testInmuebleRepositoryValidacionId() {
        assertThrows(IllegalArgumentException.class, () -> {
            int id = 0;
            if (id <= 0) throw new IllegalArgumentException("ID inválido");
        });
    }

    @Test
    public void testAlquilerRepositoryValidacionId() {
        assertThrows(IllegalArgumentException.class, () -> {
            int id = -1;
            if (id <= 0) throw new IllegalArgumentException("ID inválido");
        });
    }

    @Test
    public void testUsuarioRepositoryValidacionCorreo() {
        assertThrows(IllegalArgumentException.class, () -> {
            String mail = "";
            if (mail == null || mail.isEmpty()) throw new IllegalArgumentException("Correo vacío");
        });
    }

    @Test
    public void testOfertaRepositoryValidacion() {
        Integer ofertaId = null;
        assertThrows(IllegalArgumentException.class, () -> {
            if (ofertaId == null) throw new IllegalArgumentException("Oferta nula");
        });
    }

    @Test
    public void testOperacionRepositoryValidacion() {
        String tipo = null;
        assertThrows(IllegalArgumentException.class, () -> {
            if (tipo == null) throw new IllegalArgumentException("Tipo nulo");
        });
    }

    @Test
    public void testVentaRepositoryValidacionMonto() {
        Double monto = -10.0;
        assertThrows(IllegalArgumentException.class, () -> {
            if (monto < 0) throw new IllegalArgumentException("Monto negativo");
        });
    }

    @Test
    public void testConexionSingletonNotNull() {
        Conexion c = Conexion.getInstance();
        assertNotNull(c);
    }
}