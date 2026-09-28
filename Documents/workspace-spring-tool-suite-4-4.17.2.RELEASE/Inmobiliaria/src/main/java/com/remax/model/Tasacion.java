package com.remax.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Tasaciones")
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

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Tasacion() {}

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
}
