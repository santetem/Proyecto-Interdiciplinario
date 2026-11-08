package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "registrosalquiler")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Registrosalquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro")
    private Integer idRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alquiler", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Alquiler alquiler;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tiporegistro tipoRegistro;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "monto", nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "observaciones", length = 255)
    private String observaciones;

    public Registrosalquiler() {
    }

    public Registrosalquiler(Alquiler alquiler, Tiporegistro tipoRegistro, LocalDate fecha, BigDecimal monto, String estado, String observaciones) {
        this.alquiler = alquiler;
        this.tipoRegistro = tipoRegistro;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public Registrosalquiler(Integer idRegistro, Alquiler alquiler, Tiporegistro tipoRegistro, LocalDate fecha, BigDecimal monto, String estado, String observaciones) {
        this.idRegistro = idRegistro;
        this.alquiler = alquiler;
        this.tipoRegistro = tipoRegistro;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdRegistro() { return idRegistro; }
    public void setIdRegistro(Integer idRegistro) { this.idRegistro = idRegistro; }

    public Alquiler getAlquiler() { return alquiler; }
    public void setAlquiler(Alquiler alquiler) { this.alquiler = alquiler; }

    public Tiporegistro getTipoRegistro() { return tipoRegistro; }
    public void setTipoRegistro(Tiporegistro tipoRegistro) { this.tipoRegistro = tipoRegistro; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registrosalquiler that = (Registrosalquiler) o;
        return Objects.equals(idRegistro, that.idRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRegistro);
    }

    @Override
    public String toString() {
        return "Registrosalquiler{" +
                "idRegistro=" + idRegistro +
                ", fecha=" + fecha +
                ", monto=" + monto +
                ", estado='" + estado + '\'' +
                '}';
    }
}