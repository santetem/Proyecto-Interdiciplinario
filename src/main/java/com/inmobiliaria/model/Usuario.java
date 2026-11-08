package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
<<<<<<< HEAD
import java.util.Objects;

@Entity
@Table(name = "usuarios")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
=======
import java.util.List;

@Entity
@Table(name = "usuarios")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // <-- Agregá esto
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

<<<<<<< HEAD
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "tag", nullable = false, unique = true, length = 50)
    private String tag;

    @Column(name = "correo", nullable = false, unique = true, length = 100)
=======
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "tag", nullable = false, length = 50)
    private String tag;

    @Column(name = "correo", nullable = false, length = 150)
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
    private String correo;

    @Column(name = "upassword", nullable = false, length = 255)
    private String upassword;

<<<<<<< HEAD
    @Column(name = "dni", nullable = false, unique = true, length = 20)
    private String dni;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String tag, String correo, String upassword, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tag = tag;
        this.correo = correo;
        this.upassword = upassword;
        this.dni = dni;
    }

    public Usuario(Integer idUsuario, String nombre, String apellido, String tag, String correo, String upassword, String dni) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tag = tag;
        this.correo = correo;
        this.upassword = upassword;
        this.dni = dni;
    }

    // --- GETTERS Y SETTERS ---
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

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", tag='" + tag + '\'' +
                ", correo='" + correo + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}
=======
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
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
