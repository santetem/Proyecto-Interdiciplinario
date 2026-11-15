package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "personales")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal")
    private Integer idPersonal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_franquicia")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Franquicia franquicia;

    public Personal() {
    }

    public Personal(Usuario usuario, Franquicia franquicia) {
        this.usuario = usuario;
        this.franquicia = franquicia;
    }

    public Personal(Integer idPersonal, Usuario usuario, Franquicia franquicia) {
        this.idPersonal = idPersonal;
        this.usuario = usuario;
        this.franquicia = franquicia;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdPersonal() { return idPersonal; }
    public void setIdPersonal(Integer idPersonal) { this.idPersonal = idPersonal; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Franquicia getFranquicia() { return franquicia; }
    public void setFranquicia(Franquicia franquicia) { this.franquicia = franquicia; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personal personal = (Personal) o;
        return Objects.equals(idPersonal, personal.idPersonal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersonal);
    }

    @Override
    public String toString() {
        return "Personal{" +
                "idPersonal=" + idPersonal +
                '}';
    }
}