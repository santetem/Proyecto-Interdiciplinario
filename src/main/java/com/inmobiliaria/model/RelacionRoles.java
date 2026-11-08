package com.inmobiliaria.model;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Objects;
=======
import jakarta.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f

@Entity
@Table(name = "relacionroles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
<<<<<<< HEAD
public class RelacionRoles {
=======
public class RelacionRoles implements Serializable {
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relrol")
<<<<<<< HEAD
    private Integer idRelrol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
=======
    private Integer idRelRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
<<<<<<< HEAD
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
=======
    private Rol rol;

    // Getters y Setters
    public Integer getIdRelRol() { return idRelRol; }
    public void setIdRelRol(Integer idRelRol) { this.idRelRol = idRelRol; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
}
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
