package com.inmobiliaria.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publicaciones")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicacion")
    private Integer idPublicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inmueble", nullable = false)
    private Inmueble inmueble;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agente", nullable = false)
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
