package com.inmobiliaria.dto;

/** Credenciales de login. */
public class LoginDTO {
    private String correo;
    private String upassword;

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getUpassword() { return upassword; }
    public void setUpassword(String upassword) { this.upassword = upassword; }
}
