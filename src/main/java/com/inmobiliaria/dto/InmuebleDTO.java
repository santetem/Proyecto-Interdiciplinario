package com.inmobiliaria.dto;

import java.math.BigDecimal;
import com.inmobiliaria.model.Inmueble;

/** DTO de Inmueble: lo que viaja por la API (ids en lugar de entidades anidadas). */
public class InmuebleDTO {
    private Integer idInmueble;
    private Integer idPropietario;
    private Integer idCaptacion;
    private String direccion;
    private String tipoInmueble;
    private BigDecimal superficieCubierta;
    private BigDecimal superficieTotal;
    private Integer cantidadHabitaciones;
    private Integer cantidadBanos;
    private Integer cantidadCocheras;
    private String antiguedadEstado;
    private String localidad;

    public InmuebleDTO() { }

    public static InmuebleDTO desde(Inmueble i) {
        InmuebleDTO d = new InmuebleDTO();
        d.idInmueble = i.getIdInmueble();
        d.idPropietario = i.getPropietario() != null ? i.getPropietario().getIdUsuario() : null;
        d.idCaptacion = i.getCaptacion() != null ? i.getCaptacion().getIdCaptacion() : null;
        d.direccion = i.getDireccion();
        d.tipoInmueble = i.getTipoInmueble();
        d.superficieCubierta = i.getSuperficieCubierta();
        d.superficieTotal = i.getSuperficieTotal();
        d.cantidadHabitaciones = i.getCantidadHabitaciones();
        d.cantidadBanos = i.getCantidadBanos();
        d.cantidadCocheras = i.getCantidadCocheras();
        d.antiguedadEstado = i.getAntiguedadEstado();
        d.localidad = i.getLocalidad();
        return d;
    }

    public Integer getIdInmueble() { return idInmueble; }
    public void setIdInmueble(Integer idInmueble) { this.idInmueble = idInmueble; }
    public Integer getIdPropietario() { return idPropietario; }
    public void setIdPropietario(Integer idPropietario) { this.idPropietario = idPropietario; }
    public Integer getIdCaptacion() { return idCaptacion; }
    public void setIdCaptacion(Integer idCaptacion) { this.idCaptacion = idCaptacion; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTipoInmueble() { return tipoInmueble; }
    public void setTipoInmueble(String tipoInmueble) { this.tipoInmueble = tipoInmueble; }
    public BigDecimal getSuperficieCubierta() { return superficieCubierta; }
    public void setSuperficieCubierta(BigDecimal superficieCubierta) { this.superficieCubierta = superficieCubierta; }
    public BigDecimal getSuperficieTotal() { return superficieTotal; }
    public void setSuperficieTotal(BigDecimal superficieTotal) { this.superficieTotal = superficieTotal; }
    public Integer getCantidadHabitaciones() { return cantidadHabitaciones; }
    public void setCantidadHabitaciones(Integer cantidadHabitaciones) { this.cantidadHabitaciones = cantidadHabitaciones; }
    public Integer getCantidadBanos() { return cantidadBanos; }
    public void setCantidadBanos(Integer cantidadBanos) { this.cantidadBanos = cantidadBanos; }
    public Integer getCantidadCocheras() { return cantidadCocheras; }
    public void setCantidadCocheras(Integer cantidadCocheras) { this.cantidadCocheras = cantidadCocheras; }
    public String getAntiguedadEstado() { return antiguedadEstado; }
    public void setAntiguedadEstado(String antiguedadEstado) { this.antiguedadEstado = antiguedadEstado; }
    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }
}
