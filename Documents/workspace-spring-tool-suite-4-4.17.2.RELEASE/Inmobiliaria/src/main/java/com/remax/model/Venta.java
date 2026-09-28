package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacion")
    private Integer idVenta;

    @ManyToOne
    @JoinColumn(name = "id_operacion", nullable = false)
    private Operacion operacion;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "monto_final", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoFinal;

    @Column(nullable = false, length = 50)
    private String estado;
}
