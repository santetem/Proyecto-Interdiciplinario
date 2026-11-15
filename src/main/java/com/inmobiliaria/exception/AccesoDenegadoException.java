package com.inmobiliaria.exception;

/** El usuario está autenticado pero su rol no alcanza (HTTP 403). */
public class AccesoDenegadoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public AccesoDenegadoException(String mensaje) { super(mensaje); }
}
