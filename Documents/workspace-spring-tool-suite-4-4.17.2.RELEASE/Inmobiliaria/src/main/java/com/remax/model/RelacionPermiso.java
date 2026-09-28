package com.remax.model;

import jakarta.persistence.*;

@Entity
@Table(name = "RelacionPermisos")
public class RelacionPermiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relpermiso")
    private Integer idRelpermiso;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "id_permiso", nullable = false)
    private Permiso permiso;

    public RelacionPermiso() {}

    public Integer getIdRelpermiso() { return idRelpermiso; }
    public void setIdRelpermiso(Integer idRelpermiso) { this.idRelpermiso = idRelpermiso; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public Permiso getPermiso() { return permiso; }
    public void setPermiso(Permiso permiso) { this.permiso = permiso; }
}
