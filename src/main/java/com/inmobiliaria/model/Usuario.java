package com.inmobiliaria.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuarios", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tag"}),
    @UniqueConstraint(columnNames = {"correo"}),
    @UniqueConstraint(columnNames = {"dni"})
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "tag", nullable = false, length = 50)
    private String tag;

    @Column(name = "correo", nullable = false, length = 150)
    private String correo;

    @Column(name = "upassword", nullable = false, length = 255)
    private String upassword;

    @Column(name = "dni", nullable = false, length = 20)
    private String dni;

    @ManyToMany
    @JoinTable(
        name = "relacionroles",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private List<Rol> roles;

    // Getters y Setters
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
    public String getUpassword() { return upassword; }
    public void setUpassword(String upassword) { this.upassword = upassword; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public List<Rol> getRoles() { return roles; }
    public void setRoles(List<Rol> roles) { this.roles = roles; }
}
