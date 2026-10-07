package com.inmobiliaria.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "relacionroles")
public class RelacionRoles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relrol")
    private Integer idRelRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    // Getters y Setters
    public Integer getIdRelRol() { return idRelRol; }
    public void setIdRelRol(Integer idRelRol) { this.idRelRol = idRelRol; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
}
