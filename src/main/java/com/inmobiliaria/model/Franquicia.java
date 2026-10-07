package com.inmobiliaria.model;

import javax.persistence.*;

@Entity
@Table(name = "franquicias")
public class Franquicia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_franquicia")
    private Integer idFranquicia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SoliFranquicias solicitud;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    // Getters y Setters
    public Integer getIdFranquicia() { return idFranquicia; }
    public void setIdFranquicia(Integer idFranquicia) { this.idFranquicia = idFranquicia; }
    public SoliFranquicias getSolicitud() { return solicitud; }
    public void setSolicitud(SoliFranquicias solicitud) { this.solicitud = solicitud; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
