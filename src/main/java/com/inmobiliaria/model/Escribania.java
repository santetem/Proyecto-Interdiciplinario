package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
<<<<<<< HEAD
import jakarta.persistence.*;
import java.util.Objects;
=======

import jakarta.persistence.*;
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f

@Entity
@Table(name = "escribanias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Escribania {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escribania")
    private Integer idEscribania;

<<<<<<< HEAD
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "telefono", length = 30)
    private String telefono;

    @Column(name = "correo", length = 100)
    private String correo;

    public Escribania() {
    }

    public Escribania(String nombre, String telefono, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Escribania(Integer idEscribania, String nombre, String telefono, String correo) {
        this.idEscribania = idEscribania;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdEscribania() { return idEscribania; }
    public void setIdEscribania(Integer idEscribania) { this.idEscribania = idEscribania; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Escribania that = (Escribania) o;
        return Objects.equals(idEscribania, that.idEscribania);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEscribania);
    }

    @Override
    public String toString() {
        return "Escribania{" +
                "idEscribania=" + idEscribania +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
=======
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "correo", length = 150)
    private String correo;

    // Getters y Setters
    public Integer getIdEscribania() { return idEscribania; }
    public void setIdEscribania(Integer idEscribania) { this.idEscribania = idEscribania; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
