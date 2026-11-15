package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tasaciones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tasacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tasacion")
    private Integer idTasacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inmueble", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Inmueble inmueble;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_encargado", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario encargado;

    @Column(name = "valor_estimado", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorEstimado;

    @Column(name = "valor_final", precision = 15, scale = 2)
    private BigDecimal valorFinal;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Tasacion() {
    }

    public Tasacion(Inmueble inmueble, Usuario encargado, BigDecimal valorEstimado, BigDecimal valorFinal, LocalDate fecha, String estado) {
        this.inmueble = inmueble;
        this.encargado = encargado;
        this.valorEstimado = valorEstimado;
        this.valorFinal = valorFinal;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Tasacion(Integer idTasacion, Inmueble inmueble, Usuario encargado, BigDecimal valorEstimado, BigDecimal valorFinal, LocalDate fecha, String estado) {
        this.idTasacion = idTasacion;
        this.inmueble = inmueble;
        this.encargado = encargado;
        this.valorEstimado = valorEstimado;
        this.valorFinal = valorFinal;
        this.fecha = fecha;
        this.estado = estado;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdTasacion() { return idTasacion; }
    public void setIdTasacion(Integer idTasacion) { this.idTasacion = idTasacion; }

    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }

    public Usuario getEncargado() { return encargado; }
    public void setEncargado(Usuario encargado) { this.encargado = encargado; }

    public BigDecimal getValorEstimado() { return valorEstimado; }
    public void setValorEstimado(BigDecimal valorEstimado) { this.valorEstimado = valorEstimado; }

    public BigDecimal getValorFinal() { return valorFinal; }
    public void setValorFinal(BigDecimal valorFinal) { this.valorFinal = valorFinal; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tasacion tasacion = (Tasacion) o;
        return Objects.equals(idTasacion, tasacion.idTasacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTasacion);
    }

    @Override
    public String toString() {
        return "Tasacion{" +
                "idTasacion=" + idTasacion +
                ", valorEstimado=" + valorEstimado +
                ", valorFinal=" + valorFinal +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                '}';
    }
}