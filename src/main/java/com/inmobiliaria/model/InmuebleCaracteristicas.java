package com.inmobiliaria.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inmueble_caracteristicas")
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
