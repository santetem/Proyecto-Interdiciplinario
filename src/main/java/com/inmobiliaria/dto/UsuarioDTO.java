package com.inmobiliaria.dto;

import com.inmobiliaria.model.Usuario;

/** DTO de salida de Usuario: NUNCA incluye la contraseña. */
public class UsuarioDTO {
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String tag;
    private String correo;
    private String dni;

    public UsuarioDTO() { }

    public static UsuarioDTO desde(Usuario u) {
        UsuarioDTO d = new UsuarioDTO();
        d.idUsuario = u.getIdUsuario();
        d.nombre = u.getNombre();
        d.apellido = u.getApellido();
        d.tag = u.getTag();
        d.correo = u.getCorreo();
        d.dni = u.getDni();
        return d;
    }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
}
