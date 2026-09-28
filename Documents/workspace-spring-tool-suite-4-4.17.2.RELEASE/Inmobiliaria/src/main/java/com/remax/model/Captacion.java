package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "captaciones")
public class Captacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_captacion")
    private Integer idCaptacion;

    @ManyToOne
    @JoinColumn(name = "id_operador", nullable = false)
    private Usuario operador;

    @ManyToOne
    @JoinColumn(name = "id_propietario", nullable = false)
    private Usuario propietario;

    @Column(columnDefinition = "TEXT")
    private String caracteristicas;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(columnDefinition = "TEXT")
    private String observaciones;
}
