package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "visitas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visita")
    private Integer idVisita;

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

    public Visita() {
    }

    public Visita(Inmueble inmueble, Usuario usuario, LocalDateTime fecha, String estado, String observaciones) {
        this.inmueble = inmueble;
        this.usuario = usuario;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public Visita(Integer idVisita, Inmueble inmueble, Usuario usuario, LocalDateTime fecha, String estado, String observaciones) {
        this.idVisita = idVisita;
        this.inmueble = inmueble;
        this.usuario = usuario;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdVisita() { return idVisita; }
    public void setIdVisita(Integer idVisita) { this.idVisita = idVisita; }

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
        Visita visita = (Visita) o;
        return Objects.equals(idVisita, visita.idVisita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVisita);
    }

    @Override
    public String toString() {
        return "Visita{" +
                "idVisita=" + idVisita +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                '}';
    }
}