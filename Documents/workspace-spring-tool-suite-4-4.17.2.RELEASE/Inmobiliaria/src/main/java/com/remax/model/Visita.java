package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "visitas")
public class Visita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visita")
    private Integer idVisita;

    @ManyToOne
    @JoinColumn(name = "id_inmueble", nullable = false)
    private Inmueble inmueble;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(columnDefinition = "TEXT")
    private String observaciones;
}
