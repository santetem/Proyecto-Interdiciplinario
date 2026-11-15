package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "relacionpermisos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RelacionPermisos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relpermiso")
    private Integer idRelpermiso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permiso", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Permiso permiso;

    public RelacionPermisos() {
    }

    public RelacionPermisos(Rol rol, Permiso permiso) {
        this.rol = rol;
        this.permiso = permiso;
    }

    public RelacionPermisos(Integer idRelpermiso, Rol rol, Permiso permiso) {
        this.idRelpermiso = idRelpermiso;
        this.rol = rol;
        this.permiso = permiso;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdRelpermiso() { return idRelpermiso; }
    public void setIdRelpermiso(Integer idRelpermiso) { this.idRelpermiso = idRelpermiso; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public Permiso getPermiso() { return permiso; }
    public void setPermiso(Permiso permiso) { this.permiso = permiso; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelacionPermisos that = (RelacionPermisos) o;
        return Objects.equals(idRelpermiso, that.idRelpermiso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRelpermiso);
    }

    @Override
    public String toString() {
        return "RelacionPermisos{" +
                "idRelpermiso=" + idRelpermiso +
                '}';
    }
}