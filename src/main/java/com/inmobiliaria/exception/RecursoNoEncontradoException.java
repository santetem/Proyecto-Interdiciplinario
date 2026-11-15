package com.inmobiliaria.exception;

/** Se lanza cuando el registro pedido no existe (HTTP 404). */
public class RecursoNoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public RecursoNoEncontradoException(String mensaje) { super(mensaje); }
}
