package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "historial_oferta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HistorialOferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_histoferta")
    private Integer idHistoferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oferta", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Oferta oferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "accion", nullable = false, length = 100)
    private String accion;

    @Column(name = "monto", precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(name = "observaciones", length = 255)
    private String observaciones;

    public HistorialOferta() {
    }

    public HistorialOferta(Oferta oferta, Usuario usuario, LocalDateTime fecha, String accion, BigDecimal monto, String observaciones) {
        this.oferta = oferta;
        this.usuario = usuario;
        this.fecha = fecha;
        this.accion = accion;
        this.monto = monto;
        this.observaciones = observaciones;
    }

    public HistorialOferta(Integer idHistoferta, Oferta oferta, Usuario usuario, LocalDateTime fecha, String accion, BigDecimal monto, String observaciones) {
        this.idHistoferta = idHistoferta;
        this.oferta = oferta;
        this.usuario = usuario;
        this.fecha = fecha;
        this.accion = accion;
        this.monto = monto;
        this.observaciones = observaciones;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdHistoferta() { return idHistoferta; }
    public void setIdHistoferta(Integer idHistoferta) { this.idHistoferta = idHistoferta; }

    public Oferta getOferta() { return oferta; }
    public void setOferta(Oferta oferta) { this.oferta = oferta; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialOferta that = (HistorialOferta) o;
        return Objects.equals(idHistoferta, that.idHistoferta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistoferta);
    }

    @Override
    public String toString() {
        return "HistorialOferta{" +
                "idHistoferta=" + idHistoferta +
                ", fecha=" + fecha +
                ", accion='" + accion + '\'' +
                ", monto=" + monto +
                '}';
    }
}