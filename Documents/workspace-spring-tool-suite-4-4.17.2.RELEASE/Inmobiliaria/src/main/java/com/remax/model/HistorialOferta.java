package com.remax.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historial_oferta")
public class HistorialOferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_histoferta")
    private Integer idHistoferta;

    @ManyToOne
    @JoinColumn(name = "id_oferta", nullable = false)
    private Oferta oferta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false, length = 100)
    private String accion;

    @Column(precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(columnDefinition = "TEXT")
    private String observaciones;
}
