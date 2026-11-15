package com.inmobiliaria.exception;

/** Se lanza cuando el dato ya existe, por ejemplo correo duplicado (HTTP 409). */
public class ConflictoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ConflictoException(String mensaje) { super(mensaje); }
}
