package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "caracteristicas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caracteristica")
    private Integer idCaracteristica;

    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;

    public Caracteristica() {
    }

    public Caracteristica(String categoria, String nombre) {
        this.categoria = categoria;
        this.nombre = nombre;
    }

    public Caracteristica(Integer idCaracteristica, String categoria, String nombre) {
        this.idCaracteristica = idCaracteristica;
        this.categoria = categoria;
        this.nombre = nombre;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdCaracteristica() { return idCaracteristica; }
    public void setIdCaracteristica(Integer idCaracteristica) { this.idCaracteristica = idCaracteristica; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caracteristica that = (Caracteristica) o;
        return Objects.equals(idCaracteristica, that.idCaracteristica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCaracteristica);
    }

    @Override
    public String toString() {
        return "Caracteristica{" +
                "idCaracteristica=" + idCaracteristica +
                ", categoria='" + categoria + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}