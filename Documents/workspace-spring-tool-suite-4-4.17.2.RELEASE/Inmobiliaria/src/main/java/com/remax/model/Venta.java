package com.remax.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @ManyToOne
    @JoinColumn(name = "id_operacion", nullable = false)
    private Operacion operacion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "monto_final", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoFinal;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Venta() {}

    public Integer getIdVenta() { return idVenta; }
    public void setIdVenta(Integer idVenta) { this.idVenta = idVenta; }

    public Operacion getOperacion() { return operacion; }
    public void setOperacion(Operacion operacion) { this.operacion = operacion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public BigDecimal getMontoFinal() { return montoFinal; }
    public void setMontoFinal(BigDecimal montoFinal) { this.montoFinal = montoFinal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
