package com.inmobiliaria.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.inmobiliaria.model.Oferta;

/** DTO de Oferta. El usuario NO viaja en el JSON de entrada: sale del token de sesión. */
public class OfertaDTO {
    private Integer idOferta;
    private Integer idUsuario;
    private Integer idInmueble;
    private BigDecimal monto;
    private LocalDate fecha;
    private String estado;

    public OfertaDTO() { }

    public static OfertaDTO desde(Oferta o) {
        OfertaDTO d = new OfertaDTO();
        d.idOferta = o.getIdOferta();
        d.idUsuario = o.getUsuario() != null ? o.getUsuario().getIdUsuario() : null;
        d.idInmueble = o.getInmueble() != null ? o.getInmueble().getIdInmueble() : null;
        d.monto = o.getMonto();
        d.fecha = o.getFecha();
        d.estado = o.getEstado();
        return d;
    }

    public Integer getIdOferta() { return idOferta; }
    public void setIdOferta(Integer idOferta) { this.idOferta = idOferta; }
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public Integer getIdInmueble() { return idInmueble; }
    public void setIdInmueble(Integer idInmueble) { this.idInmueble = idInmueble; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
