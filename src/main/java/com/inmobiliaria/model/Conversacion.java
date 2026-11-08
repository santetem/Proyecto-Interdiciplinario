package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "conversaciones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Conversacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conversacion")
    private Integer idConversacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inmueble")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Inmueble inmueble;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Conversacion() {
    }

    public Conversacion(Inmueble inmueble, String estado) {
        this.inmueble = inmueble;
        this.estado = estado;
    }

    public Conversacion(Integer idConversacion, Inmueble inmueble, String estado) {
        this.idConversacion = idConversacion;
        this.inmueble = inmueble;
        this.estado = estado;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdConversacion() { return idConversacion; }
    public void setIdConversacion(Integer idConversacion) { this.idConversacion = idConversacion; }

    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversacion that = (Conversacion) o;
        return Objects.equals(idConversacion, that.idConversacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConversacion);
    }

    @Override
    public String toString() {
        return "Conversacion{" +
                "idConversacion=" + idConversacion +
                ", estado='" + estado + '\'' +
                '}';
    }
}