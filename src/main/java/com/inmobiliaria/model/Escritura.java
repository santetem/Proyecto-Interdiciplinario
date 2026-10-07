package com.inmobiliaria.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "escrituras")
public class Escritura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escritura")
    private Integer idEscritura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_escribania", nullable = false)
    private Escribania escribania;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    // Getters y Setters
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
}
