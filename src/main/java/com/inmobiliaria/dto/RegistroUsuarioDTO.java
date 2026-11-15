package com.inmobiliaria.dto;

/** Datos que envía el formulario de registro (register.js). */
public class RegistroUsuarioDTO {
    private String nombre;
    private String apellido;
    private String tag;
    private String correo;
    private String upassword;
    private String dni;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getUpassword() { return upassword; }
    public void setUpassword(String upassword) { this.upassword = upassword; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
}
