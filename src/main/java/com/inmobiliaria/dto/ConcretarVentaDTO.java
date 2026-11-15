package com.inmobiliaria.dto;

import java.math.BigDecimal;

/** Pedido para concretar una venta a partir de una oferta. */
public class ConcretarVentaDTO {
    private Integer idOferta;
    private BigDecimal montoFinal;
    /** Agente que cobra la comisión. Si no se envía, se usa el usuario logueado. */
    private Integer idUsuarioComision;
    /** Porcentaje de comisión (0 a 100). */
    private BigDecimal porcentajeComision;

    public Integer getIdOferta() { return idOferta; }
    public void setIdOferta(Integer idOferta) { this.idOferta = idOferta; }
    public BigDecimal getMontoFinal() { return montoFinal; }
    public void setMontoFinal(BigDecimal montoFinal) { this.montoFinal = montoFinal; }
    public Integer getIdUsuarioComision() { return idUsuarioComision; }
    public void setIdUsuarioComision(Integer idUsuarioComision) { this.idUsuarioComision = idUsuarioComision; }
    public BigDecimal getPorcentajeComision() { return porcentajeComision; }
    public void setPorcentajeComision(BigDecimal porcentajeComision) { this.porcentajeComision = porcentajeComision; }
}
