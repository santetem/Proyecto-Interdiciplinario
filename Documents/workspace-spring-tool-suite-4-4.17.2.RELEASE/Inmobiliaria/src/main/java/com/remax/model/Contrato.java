package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "contratos")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private Integer idContrato;

    @ManyToOne
    @JoinColumn(name = "id_alquiler", nullable = false)
    private Alquiler alquiler;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "monto_final", precision = 15, scale = 2)
    private BigDecimal montoFinal;

    @Column(nullable = false, length = 50)
    private String estado;
}
