package com.inmobiliaria.exception;

/** Falta el token o es inválido/vencido (HTTP 401). */
public class NoAutenticadoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public NoAutenticadoException(String mensaje) { super(mensaje); }
}
