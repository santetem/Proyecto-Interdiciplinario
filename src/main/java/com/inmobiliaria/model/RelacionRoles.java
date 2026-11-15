package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "relacionroles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RelacionRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relrol")
    private Integer idRelrol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Rol rol;

    public RelacionRoles() {
    }

    public RelacionRoles(Usuario usuario, Rol rol) {
        this.usuario = usuario;
        this.rol = rol;
    }

    public RelacionRoles(Integer idRelrol, Usuario usuario, Rol rol) {
        this.idRelrol = idRelrol;
        this.usuario = usuario;
        this.rol = rol;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdRelrol() { return idRelrol; }
    public void setIdRelrol(Integer idRelrol) { this.idRelrol = idRelrol; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelacionRoles that = (RelacionRoles) o;
        return Objects.equals(idRelrol, that.idRelrol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRelrol);
    }

    @Override
    public String toString() {
        return "RelacionRoles{" +
                "idRelrol=" + idRelrol +
                '}';
    }
}