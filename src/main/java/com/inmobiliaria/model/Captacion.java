package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "captaciones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Captacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_captacion")
    private Integer idCaptacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operador", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario operador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario propietario;

    @Column(name = "caracteristicas", columnDefinition = "TEXT")
    private String caracteristicas;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "observaciones", length = 255)
    private String observaciones;

    public Captacion() {
    }

    public Captacion(Usuario operador, Usuario propietario, String caracteristicas, LocalDate fecha, String estado, String observaciones) {
        this.operador = operador;
        this.propietario = propietario;
        this.caracteristicas = caracteristicas;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public Captacion(Integer idCaptacion, Usuario operador, Usuario propietario, String caracteristicas, LocalDate fecha, String estado, String observaciones) {
        this.idCaptacion = idCaptacion;
        this.operador = operador;
        this.propietario = propietario;
        this.caracteristicas = caracteristicas;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    // --- GETTERS Y SETTERS ---
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

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Captacion captacion = (Captacion) o;
        return Objects.equals(idCaptacion, captacion.idCaptacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCaptacion);
    }

    @Override
    public String toString() {
        return "Captacion{" +
                "idCaptacion=" + idCaptacion +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                '}';
    }
}