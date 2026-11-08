package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ventas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operacion", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Operacion operacion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "monto_final", nullable = false, precision = 15, scale = 2)
    private BigDecimal montoFinal;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Venta() {
    }

    public Venta(Operacion operacion, LocalDate fecha, BigDecimal montoFinal, String estado) {
        this.operacion = operacion;
        this.fecha = fecha;
        this.montoFinal = montoFinal;
        this.estado = estado;
    }

    public Venta(Integer idVenta, Operacion operacion, LocalDate fecha, BigDecimal montoFinal, String estado) {
        this.idVenta = idVenta;
        this.operacion = operacion;
        this.fecha = fecha;
        this.montoFinal = montoFinal;
        this.estado = estado;
    }

    // --- GETTERS Y SETTERS ---
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

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return Objects.equals(idVenta, venta.idVenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenta);
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", fecha=" + fecha +
                ", montoFinal=" + montoFinal +
                ", estado='" + estado + '\'' +
                '}';
    }
}