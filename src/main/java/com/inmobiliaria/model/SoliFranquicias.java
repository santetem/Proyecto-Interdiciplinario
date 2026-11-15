package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "solifranquicias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SoliFranquicias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "observaciones", length = 255)
    private String observaciones;

    public SoliFranquicias() {
    }

    public SoliFranquicias(Usuario usuario, LocalDate fecha, String estado, String observaciones) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public SoliFranquicias(Integer idSolicitud, Usuario usuario, LocalDate fecha, String estado, String observaciones) {
        this.idSolicitud = idSolicitud;
        this.usuario = usuario;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(Integer idSolicitud) { this.idSolicitud = idSolicitud; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

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
        SoliFranquicias that = (SoliFranquicias) o;
        return Objects.equals(idSolicitud, that.idSolicitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSolicitud);
    }

    @Override
    public String toString() {
        return "SoliFranquicias{" +
                "idSolicitud=" + idSolicitud +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                '}';
    }
}