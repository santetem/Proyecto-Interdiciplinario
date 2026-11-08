package com.inmobiliaria.model;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
=======
import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f

@Entity
@Table(name = "publicaciones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicacion")
    private Integer idPublicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inmueble", nullable = false)
<<<<<<< HEAD
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
=======
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
    private Inmueble inmueble;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agente", nullable = false)
<<<<<<< HEAD
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario agente;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    public Publicacion() {
    }

    public Publicacion(Inmueble inmueble, Usuario agente, LocalDate fecha) {
        this.inmueble = inmueble;
        this.agente = agente;
        this.fecha = fecha;
    }

    public Publicacion(Integer idPublicacion, Inmueble inmueble, Usuario agente, LocalDate fecha) {
        this.idPublicacion = idPublicacion;
        this.inmueble = inmueble;
        this.agente = agente;
        this.fecha = fecha;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdPublicacion() { return idPublicacion; }
    public void setIdPublicacion(Integer idPublicacion) { this.idPublicacion = idPublicacion; }

    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }

    public Usuario getAgente() { return agente; }
    public void setAgente(Usuario agente) { this.agente = agente; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publicacion that = (Publicacion) o;
        return Objects.equals(idPublicacion, that.idPublicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPublicacion);
    }

    @Override
    public String toString() {
        return "Publicacion{" +
                "idPublicacion=" + idPublicacion +
                ", fecha=" + fecha +
                '}';
    }
}
=======
    private Usuario agente;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    // Getters y Setters
    public Integer getIdPublicacion() { return idPublicacion; }
    public void setIdPublicacion(Integer idPublicacion) { this.idPublicacion = idPublicacion; }
    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }
    public Usuario getAgente() { return agente; }
    public void setAgente(Usuario agente) { this.agente = agente; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
