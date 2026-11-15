package com.inmobiliaria.security;

import java.time.Instant;
import java.util.List;

/** Sesión en memoria de un usuario logueado. */
public class Sesion {
    private final Integer idUsuario;
    private final String correo;
    private final List<String> roles;
    private final Instant vence;

    public Sesion(Integer idUsuario, String correo, List<String> roles, Instant vence) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.roles = roles;
        this.vence = vence;
    }

    public Integer getIdUsuario() { return idUsuario; }
    public String getCorreo() { return correo; }
    public List<String> getRoles() { return roles; }
    public boolean vencida() { return Instant.now().isAfter(vence); }

    public boolean tieneAlgunRol(String[] requeridos) {
        for (String requerido : requeridos) {
            for (String rol : roles) {
                if (rol.equalsIgnoreCase(requerido)) {
                    return true;
                }
            }
        }
        return false;
    }
}
