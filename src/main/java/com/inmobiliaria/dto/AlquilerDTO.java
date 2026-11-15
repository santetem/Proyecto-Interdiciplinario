package com.inmobiliaria.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.inmobiliaria.model.Alquiler;

/** DTO de Alquiler. */
public class AlquilerDTO {
    private Integer idAlquiler;
    private Integer idOperacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal monto;
    private String estado;

    public AlquilerDTO() { }

    public static AlquilerDTO desde(Alquiler a) {
        AlquilerDTO d = new AlquilerDTO();
        d.idAlquiler = a.getIdAlquiler();
        d.idOperacion = a.getOperacion() != null ? a.getOperacion().getIdOperacion() : null;
        d.fechaInicio = a.getFechaInicio();
        d.fechaFin = a.getFechaFin();
        d.monto = a.getMonto();
        d.estado = a.getEstado();
        return d;
    }

    public Integer getIdAlquiler() { return idAlquiler; }
    public void setIdAlquiler(Integer idAlquiler) { this.idAlquiler = idAlquiler; }
    public Integer getIdOperacion() { return idOperacion; }
    public void setIdOperacion(Integer idOperacion) { this.idOperacion = idOperacion; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
