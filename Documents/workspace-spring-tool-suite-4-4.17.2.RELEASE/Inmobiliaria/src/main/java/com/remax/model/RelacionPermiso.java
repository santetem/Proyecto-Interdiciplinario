package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "relacionpermisos")
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
}
