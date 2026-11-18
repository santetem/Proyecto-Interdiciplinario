package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "tag", nullable = false, unique = true, length = 50)
    private String tag;

    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // nunca se devuelve en el JSON
    @Column(name = "upassword", nullable = false, length = 255)
    private String upassword;

    @Column(name = "dni", nullable = false, unique = true, length = 20)
    private String dni;

    // Nuevos: son NULL en la base para los usuarios viejos (por eso sin nullable=false)
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

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

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

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