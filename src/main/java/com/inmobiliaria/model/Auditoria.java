package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "auditorias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Integer idAuditoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inmueble", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Inmueble inmueble;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "observaciones", length = 255)
    private String observaciones;

    public Auditoria() {
    }

    public Auditoria(Inmueble inmueble, Usuario usuario, LocalDateTime fecha, String estado, String observaciones) {
        this.inmueble = inmueble;
        this.usuario = usuario;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public Auditoria(Integer idAuditoria, Inmueble inmueble, Usuario usuario, LocalDateTime fecha, String estado, String observaciones) {
        this.idAuditoria = idAuditoria;
        this.inmueble = inmueble;
        this.usuario = usuario;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdAuditoria() { return idAuditoria; }
    public void setIdAuditoria(Integer idAuditoria) { this.idAuditoria = idAuditoria; }

    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auditoria auditoria = (Auditoria) o;
        return Objects.equals(idAuditoria, auditoria.idAuditoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAuditoria);
    }

    @Override
    public String toString() {
        return "Auditoria{" +
                "idAuditoria=" + idAuditoria +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                '}';
    }
}