package com.inmobiliaria.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_oferta")
public class HistorialOferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_histoferta")
    private Integer idHistOferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oferta", nullable = false)
    private Oferta oferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "accion", nullable = false, length = 100)
    private String accion;

    @Column(name = "monto", precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    // Getters y Setters
    public Integer getIdHistOferta() { return idHistOferta; }
    public void setIdHistOferta(Integer idHistOferta) { this.idHistOferta = idHistOferta; }
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
    public String getObservations() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
