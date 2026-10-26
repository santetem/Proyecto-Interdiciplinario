package com.inmobiliaria.model;

import jakarta.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "relacionpermisos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RelacionPermisos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relpermiso")
    private Integer idRelPermiso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permiso", nullable = false)
    private Permiso permiso;

    // Getters y Setters
    public Integer getIdRelPermiso() { return idRelPermiso; }
    public void setIdRelPermiso(Integer idRelPermiso) { this.idRelPermiso = idRelPermiso; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
    public Permiso getPermiso() { return permiso; }
    public void setPermiso(Permiso permiso) { this.permiso = permiso; }
}
