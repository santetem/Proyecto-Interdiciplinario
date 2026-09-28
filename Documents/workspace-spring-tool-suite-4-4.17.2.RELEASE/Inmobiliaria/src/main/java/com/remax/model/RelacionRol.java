package com.remax.model;

import jakarta.persistence.*;

@Entity
@Table(name = "RelacionRoles")
public class RelacionRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relrol")
    private Integer idRelrol;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    public RelacionRol() {}

    public Integer getIdRelrol() { return idRelrol; }
    public void setIdRelrol(Integer idRelrol) { this.idRelrol = idRelrol; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
}
