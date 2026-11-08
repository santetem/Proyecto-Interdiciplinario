package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "inmueble_caracteristicas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InmuebleCaracteristicas implements Serializable {

    @EmbeddedId
    private InmuebleCaracteristicasId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idInmueble")
    @JoinColumn(name = "id_inmueble")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Inmueble inmueble;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCaracteristica")
    @JoinColumn(name = "id_caracteristica")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Caracteristica caracteristica;

    public InmuebleCaracteristicas() {
    }

    public InmuebleCaracteristicas(Inmueble inmueble, Caracteristica caracteristica) {
        this.inmueble = inmueble;
        this.caracteristica = caracteristica;
        this.id = new InmuebleCaracteristicasId(
            inmueble != null ? inmueble.getIdInmueble() : null, 
            caracteristica != null ? caracteristica.getIdCaracteristica() : null
        );
    }

    // --- GETTERS Y SETTERS ---
    public InmuebleCaracteristicasId getId() { return id; }
    public void setId(InmuebleCaracteristicasId id) { this.id = id; }

    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }

    public Caracteristica getCaracteristica() { return caracteristica; }
    public void setCaracteristica(Caracteristica caracteristica) { this.caracteristica = caracteristica; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InmuebleCaracteristicas that = (InmuebleCaracteristicas) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "InmuebleCaracteristicas{" +
                "id=" + id +
                '}';
    }

    // --- CLASE EMBEDDED ID AUXILIAR ---
    @Embeddable
    public static class InmuebleCaracteristicasId implements Serializable {
        @Column(name = "id_inmueble")
        private Integer idInmueble;

        @Column(name = "id_caracteristica")
        private Integer idCaracteristica;

        public InmuebleCaracteristicasId() {}

        public InmuebleCaracteristicasId(Integer idInmueble, Integer idCaracteristica) {
            this.idInmueble = idInmueble;
            this.idCaracteristica = idCaracteristica;
        }

        public Integer getIdInmueble() { return idInmueble; }
        public void setIdInmueble(Integer idInmueble) { this.idInmueble = idInmueble; }

        public Integer getIdCaracteristica() { return idCaracteristica; }
        public void setIdCaracteristica(Integer idCaracteristica) { this.idCaracteristica = idCaracteristica; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            InmuebleCaracteristicasId that = (InmuebleCaracteristicasId) o;
            return Objects.equals(idInmueble, that.idInmueble) && Objects.equals(idCaracteristica, that.idCaracteristica);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idInmueble, idCaracteristica);
        }
    }
}