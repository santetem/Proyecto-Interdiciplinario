package com.inmobiliaria.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "liquidaciones")
public class Liquidacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_liquidacion")
    private Integer idLiquidacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alquiler", nullable = false)
    private Alquiler alquiler;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "monto", nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    // Getters y Setters
    public Integer getIdLiquidacion() { return idLiquidacion; }
    public void setIdLiquidacion(Integer idLiquidacion) { this.idLiquidacion = idLiquidacion; }
    public Alquiler getAlquiler() { return alquiler; }
    public void setAlquiler(Alquiler alquiler) { this.alquiler = alquiler; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
}
