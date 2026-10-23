package com.inmobiliaria.model;

import jakarta.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "inmueble_caracteristicas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InmuebleCaracteristicas implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inmueble", nullable = false)
    private Inmueble inmueble;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_caracteristica", nullable = false)
    private Caracteristica caracteristica;

    // Getters y Setters
    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }
    public Caracteristica getCaracteristica() { return caracteristica; }
    public void setCaracteristica(Caracteristica caracteristica) { this.caracteristica = caracteristica; }
}
