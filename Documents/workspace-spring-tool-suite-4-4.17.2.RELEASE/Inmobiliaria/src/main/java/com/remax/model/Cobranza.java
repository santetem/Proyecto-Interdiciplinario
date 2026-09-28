package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cobranzas")
public class Cobranza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cobranza")
    private Integer idCobranza;

    @ManyToOne
    @JoinColumn(name = "id_alquiler", nullable = false)
    private Alquiler alquiler;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false, length = 50)
    private String estado;
}
