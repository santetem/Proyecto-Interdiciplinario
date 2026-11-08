package com.inmobiliaria.model;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "caracteristicas")
=======
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "caracteristicas", uniqueConstraints = {@UniqueConstraint(columnNames = "nombre")})
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caracteristica")
    private Integer idCaracteristica;

<<<<<<< HEAD
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
=======
    @Column(name = "categoria", nullable = false, length = 100)
    private String categoria;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @ManyToMany(mappedBy = "caracteristicas")
    private List<Inmueble> inmuebles;

    // Getters y Setters
    public Integer getIdCaracteristica() { return idCaracteristica; }
    public void setIdCaracteristica(Integer idCaracteristica) { this.idCaracteristica = idCaracteristica; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Inmueble> getInmuebles() { return inmuebles; }
    public void setInmuebles(List<Inmueble> inmuebles) { this.inmuebles = inmuebles; }
}
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
