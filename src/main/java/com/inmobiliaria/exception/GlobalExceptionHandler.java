package com.inmobiliaria.exception;

import java.sql.SQLException;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Traduce las excepciones de los Services a códigos HTTP.
 * Así los Controllers no devuelven "boolean" sino una respuesta HTTP real:
 * 400 datos inválidos, 401 sin sesión, 403 sin permiso, 404 no existe,
 * 409 duplicado, 500 error interno.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, String>> error(HttpStatus status, String mensaje) {
        return ResponseEntity.status(status).body(Map.of("error", mensaje));
    }

    @ExceptionHandler({IllegalArgumentException.class, HttpMessageNotReadableException.class,
                       MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Map<String, String>> badRequest(Exception e) {
        String msg = (e instanceof IllegalArgumentException) ? e.getMessage() : "Solicitud inválida (JSON o parámetro mal formado)";
        return error(HttpStatus.BAD_REQUEST, msg);
    }

    @ExceptionHandler(NoAutenticadoException.class)
    public ResponseEntity<Map<String, String>> noAutenticado(NoAutenticadoException e) {
        return error(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(AccesoDenegadoException.class)
    public ResponseEntity<Map<String, String>> prohibido(AccesoDenegadoException e) {
        return error(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> noEncontrado(RecursoNoEncontradoException e) {
        return error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler({ConflictoException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Map<String, String>> conflicto(Exception e) {
        String msg = (e instanceof ConflictoException) ? e.getMessage() : "El dato viola una restricción de la base (duplicado o clave inexistente)";
        return error(HttpStatus.CONFLICT, msg);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, String>> baseDeDatos(DataAccessException e) {
        // Los triggers y stored procedures de la base lanzan SQLSTATE 45000 con un mensaje de negocio
        // (ej. "La superficie cubierta no puede ser mayor que la total"): es un error del cliente (400).
        Throwable causa = e;
        while (causa != null) {
            if (causa instanceof SQLException && "45000".equals(((SQLException) causa).getSQLState())) {
                return error(HttpStatus.BAD_REQUEST, causa.getMessage());
            }
            causa = causa.getCause();
        }
        return error(HttpStatus.INTERNAL_SERVER_ERROR, "Error al acceder a la base de datos");
    }
}
