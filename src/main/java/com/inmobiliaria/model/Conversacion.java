package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

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
    private Inmueble inmueble;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    // Getters y Setters
    public Integer getIdConversacion() { return idConversacion; }
    public void setIdConversacion(Integer idConversacion) { this.idConversacion = idConversacion; }
    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
