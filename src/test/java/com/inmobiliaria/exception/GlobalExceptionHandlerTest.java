package com.inmobiliaria.exception;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void errorDeTriggerSqlState45000_seTraduceA400ConElMensaje() {
        SQLException sql = new SQLException("Error: La superficie cubierta no puede ser mayor que la superficie total.", "45000");
        ResponseEntity<Map<String, String>> r = handler.baseDeDatos(new UncategorizedSQLException("x", "y", sql));
        assertEquals(HttpStatus.BAD_REQUEST, r.getStatusCode());
        assertTrue(r.getBody().get("error").contains("superficie cubierta"));
    }

    @Test
    void otroErrorDeBase_seTraduceA500SinFiltrarDetalles() {
        SQLException sql = new SQLException("Access denied for user root", "28000");
        ResponseEntity<Map<String, String>> r = handler.baseDeDatos(new UncategorizedSQLException("x", "y", sql));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, r.getStatusCode());
        assertFalse(r.getBody().get("error").contains("root"));
    }

    @Test
    void violacionDeIntegridad_seTraduceA409() {
        ResponseEntity<Map<String, String>> r = handler.conflicto(new DataIntegrityViolationException("dup"));
        assertEquals(HttpStatus.CONFLICT, r.getStatusCode());
    }

    @Test
    void noEncontrado_seTraduceA404() {
        assertEquals(HttpStatus.NOT_FOUND, handler.noEncontrado(new RecursoNoEncontradoException("x")).getStatusCode());
    }
}
