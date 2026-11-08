package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
<<<<<<< HEAD
import jakarta.persistence.*;
import java.util.Objects;
=======

import jakarta.persistence.*;
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f

@Entity
@Table(name = "franquicias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Franquicia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_franquicia")
    private Integer idFranquicia;

<<<<<<< HEAD
    @Column(name = "id_solicitud", nullable = false)
    private Integer idSolicitud; // O bien mapeada a SoliFranquicias si prefieres objeto

    @Column(name = "nombre", nullable = false, length = 100)
=======
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SoliFranquicias solicitud;

    @Column(name = "nombre", nullable = false, length = 150)
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

<<<<<<< HEAD
    public Franquicia() {
    }

    public Franquicia(Integer idSolicitud, String nombre, String direccion, String estado) {
        this.idSolicitud = idSolicitud;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Franquicia(Integer idFranquicia, Integer idSolicitud, String nombre, String direccion, String estado) {
        this.idFranquicia = idFranquicia;
        this.idSolicitud = idSolicitud;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado = estado;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdFranquicia() { return idFranquicia; }
    public void setIdFranquicia(Integer idFranquicia) { this.idFranquicia = idFranquicia; }

    public Integer getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(Integer idSolicitud) { this.idSolicitud = idSolicitud; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Franquicia that = (Franquicia) o;
        return Objects.equals(idFranquicia, that.idFranquicia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFranquicia);
    }

    @Override
    public String toString() {
        return "Franquicia{" +
                "idFranquicia=" + idFranquicia +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
=======
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
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
