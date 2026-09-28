package com.remax.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Caracteristicas")
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caracteristica")
    private Integer idCaracteristica;

    @Column(name = "categoria", nullable = false, length = 100)
    private String categoria;

    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;

    public Caracteristica() {}

    public Integer getIdCaracteristica() { return idCaracteristica; }
    public void setIdCaracteristica(Integer idCaracteristica) { this.idCaracteristica = idCaracteristica; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
