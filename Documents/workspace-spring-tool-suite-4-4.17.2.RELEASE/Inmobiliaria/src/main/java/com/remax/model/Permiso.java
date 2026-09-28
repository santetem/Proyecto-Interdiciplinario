package com.remax.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Permisos")
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Integer idPermiso;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    public Permiso() {}

    public Integer getIdPermiso() { return idPermiso; }
    public void setIdPermiso(Integer idPermiso) { this.idPermiso = idPermiso; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
