package com.inmobiliaria.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.inmobiliaria.model.Venta;

/** DTO de Venta. */
public class VentaDTO {
    private Integer idVenta;
    private Integer idOperacion;
    private LocalDate fecha;
    private BigDecimal montoFinal;
    private String estado;

    public static VentaDTO desde(Venta v) {
        VentaDTO d = new VentaDTO();
        d.idVenta = v.getIdVenta();
        d.idOperacion = v.getOperacion() != null ? v.getOperacion().getIdOperacion() : null;
        d.fecha = v.getFecha();
        d.montoFinal = v.getMontoFinal();
        d.estado = v.getEstado();
        return d;
    }

    public Integer getIdVenta() { return idVenta; }
    public Integer getIdOperacion() { return idOperacion; }
    public LocalDate getFecha() { return fecha; }
    public BigDecimal getMontoFinal() { return montoFinal; }
    public String getEstado() { return estado; }
}
