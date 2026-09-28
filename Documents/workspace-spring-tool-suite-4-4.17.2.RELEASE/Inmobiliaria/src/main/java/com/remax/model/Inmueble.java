package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inmuebles")
public class Inmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inmueble")
    private Integer idInmueble;

    @ManyToOne
    @JoinColumn(name = "id_captacion", nullable = false)
    private Captacion captacion;

    @ManyToOne
    @JoinColumn(name = "id_propietario", nullable = false)
    private Usuario propietario;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(name = "tipo_inmueble", nullable = false, length = 100)
    private String tipoInmueble;

    @Column(columnDefinition = "TEXT")
    private String caracteristicas;
}
