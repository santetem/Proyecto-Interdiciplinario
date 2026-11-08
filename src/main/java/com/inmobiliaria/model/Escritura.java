package com.inmobiliaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "escrituras")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Escritura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escritura")
    private Integer idEscritura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_escribania", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Escribania escribania;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Escritura() {
    }

    public Escritura(Venta venta, Escribania escribania, LocalDate fecha, String estado) {
        this.venta = venta;
        this.escribania = escribania;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Escritura(Integer idEscritura, Venta venta, Escribania escribania, LocalDate fecha, String estado) {
        this.idEscritura = idEscritura;
        this.venta = venta;
        this.escribania = escribania;
        this.fecha = fecha;
        this.estado = estado;
    }

    // --- GETTERS Y SETTERS ---
    public Integer getIdEscritura() { return idEscritura; }
    public void setIdEscritura(Integer idEscritura) { this.idEscritura = idEscritura; }

    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }

    public Escribania getEscribania() { return escribania; }
    public void setEscribania(Escribania escribania) { this.escribania = escribania; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // --- MÉTODOS OBJECT ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Escritura escritura = (Escritura) o;
        return Objects.equals(idEscritura, escritura.idEscritura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEscritura);
    }

    @Override
    public String toString() {
        return "Escritura{" +
                "idEscritura=" + idEscritura +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                '}';
    }
}