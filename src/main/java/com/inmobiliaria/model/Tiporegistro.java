package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
<<<<<<< HEAD
import java.util.Objects;
=======
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f

@Entity
@Table(name = "tiporegistro")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tiporegistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer idTipo;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

<<<<<<< HEAD
    public Tiporegistro() {
    }

    public Tiporegistro(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Tiporegistro(Integer idTipo, String nombre, String descripcion) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // --- GETTERS Y SETTERS ---
=======
    // Getters y Setters
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
    public Integer getIdTipo() { return idTipo; }
    public void setIdTipo(Integer idTipo) { this.idTipo = idTipo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
<<<<<<< HEAD

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tiporegistro that = (Tiporegistro) o;
        return Objects.equals(idTipo, that.idTipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipo);
    }

    @Override
    public String toString() {
        return "Tiporegistro{" +
                "idTipo=" + idTipo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
=======
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
}