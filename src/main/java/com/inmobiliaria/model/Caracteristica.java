package com.inmobiliaria.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "caracteristicas", uniqueConstraints = {@UniqueConstraint(columnNames = "nombre")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caracteristica")
    private Integer idCaracteristica;

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
