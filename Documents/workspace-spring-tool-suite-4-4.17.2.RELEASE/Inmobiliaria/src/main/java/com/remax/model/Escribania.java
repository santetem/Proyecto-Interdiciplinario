package com.remax.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Escribanias")
public class Escribania {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escribania")
    private Integer idEscribania;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "correo", length = 150)
    private String correo;

    public Escribania() {}

    public Integer getIdEscribania() { return idEscribania; }
    public void setIdEscribania(Integer idEscribania) { this.idEscribania = idEscribania; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}
