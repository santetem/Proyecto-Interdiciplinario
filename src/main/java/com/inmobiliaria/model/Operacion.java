package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "operaciones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacion")
    private Integer idOperacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oferta", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Oferta oferta;

    @Column(name = "tipo_operacion", nullable = false, length = 50)
    private String tipoOperacion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Operacion() {
    }

    public Operacion(Oferta oferta, String tipoOperacion, LocalDate fecha, String estado) {
        this.oferta = oferta;
        this.tipoOperacion = tipoOperacion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Operacion(Integer idOperacion, Oferta oferta, String tipoOperacion, LocalDate fecha, String estado) {
        this.idOperacion = idOperacion;
        this.oferta = oferta;
        this.tipoOperacion = tipoOperacion;
        this.fecha = fecha;
        this.estado = estado;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdOperacion() { return idOperacion; }
    public void setIdOperacion(Integer idOperacion) { this.idOperacion = idOperacion; }

    public Oferta getOferta() { return oferta; }
    public void setOferta(Oferta oferta) { this.oferta = oferta; }

    public String getTipoOperacion() { return tipoOperacion; }
    public void setTipoOperacion(String tipoOperacion) { this.tipoOperacion = tipoOperacion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operacion operacion = (Operacion) o;
        return Objects.equals(idOperacion, operacion.idOperacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOperacion);
    }

    @Override
    public String toString() {
        return "Operacion{" +
                "idOperacion=" + idOperacion +
                ", tipoOperacion='" + tipoOperacion + '\'' +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                '}';
    }
}