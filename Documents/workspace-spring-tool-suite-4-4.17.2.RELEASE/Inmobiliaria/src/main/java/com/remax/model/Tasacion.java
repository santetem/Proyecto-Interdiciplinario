package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tasaciones")
public class Tasacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tasacion")
    private Integer idTasacion;

    @ManyToOne
    @JoinColumn(name = "id_inmueble", nullable = false)
    private Inmueble inmueble;

    @ManyToOne
    @JoinColumn(name = "id_encargado", nullable = false)
    private Usuario encargado;

    @Column(name = "valor_estimado", precision = 15, scale = 2)
    private BigDecimal valorEstimado;

    @Column(name = "valor_final", precision = 15, scale = 2)
    private BigDecimal valorFinal;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 50)
    private String estado;
}
