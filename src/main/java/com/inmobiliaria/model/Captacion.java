package com.inmobiliaria.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "captaciones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // <-- Agregá esto
public class Captacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_captacion")
    private Integer idCaptacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operador")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario operador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario", nullable = false)
    private Usuario propietario;

    @Column(name = "caracteristicas", columnDefinition = "TEXT")
    private String caracteristicas;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    // Getters y Setters
    public Integer getIdCaptacion() { return idCaptacion; }
    public void setIdCaptacion(Integer idCaptacion) { this.idCaptacion = idCaptacion; }
    public Usuario getOperador() { return operador; }
    public void setOperador(Usuario operador) { this.operador = operador; }
    public Usuario getPropietario() { return propietario; }
    public void setPropietario(Usuario propietario) { this.propietario = propietario; }
    public String getCaracteristicas() { return caracteristicas; }
    public void setCaracteristicas(String caracteristicas) { this.caracteristicas = caracteristicas; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
