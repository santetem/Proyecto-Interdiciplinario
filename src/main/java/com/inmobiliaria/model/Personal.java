package com.inmobiliaria.model;

import javax.persistence.*;

@Entity
@Table(name = "personales")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal")
    private Integer idPersonal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_franquicia")
    private Franquicia franquicia;

    // Getters y Setters
    public Integer getIdPersonal() { return idPersonal; }
    public void setIdPersonal(Integer idPersonal) { this.idPersonal = idPersonal; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Franquicia getFranquicia() { return franquicia; }
    public void setFranquicia(Franquicia franquicia) { this.franquicia = franquicia; }
}
