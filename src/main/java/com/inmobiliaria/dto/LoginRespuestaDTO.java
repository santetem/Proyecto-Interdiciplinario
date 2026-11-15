package com.inmobiliaria.dto;

import java.util.List;

/** Respuesta del login: token de sesión, datos del usuario y sus roles. */
public class LoginRespuestaDTO {
    private String mensaje;
    private String token;
    private UsuarioDTO usuario;
    private List<String> roles;

    public LoginRespuestaDTO() { }

    public LoginRespuestaDTO(String mensaje, String token, UsuarioDTO usuario, List<String> roles) {
        this.mensaje = mensaje;
        this.token = token;
        this.usuario = usuario;
        this.roles = roles;
    }

    public String getMensaje() { return mensaje; }
    public String getToken() { return token; }
    public UsuarioDTO getUsuario() { return usuario; }
    public List<String> getRoles() { return roles; }
}
