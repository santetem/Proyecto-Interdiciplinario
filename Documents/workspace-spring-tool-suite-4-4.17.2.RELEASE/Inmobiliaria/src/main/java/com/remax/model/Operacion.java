package com.remax.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Operaciones")
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacion")
    private Integer idOperacion;

    @ManyToOne
    @JoinColumn(name = "id_oferta", nullable = false)
    private Oferta oferta;

    @Column(name = "tipo_operacion", nullable = false, length = 50)
    private String tipoOperacion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Operacion() {}

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
}
