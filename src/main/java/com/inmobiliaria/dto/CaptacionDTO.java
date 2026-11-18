package com.inmobiliaria.dto;

import java.time.LocalDate;
import com.inmobiliaria.model.Captacion;

/** DTO de Captacion: lo que viaja por la API (ids en lugar de entidades anidadas). */
public class CaptacionDTO {
    private Integer idCaptacion;
    private Integer idPropietario;
    private Integer idOperador;
    private String caracteristicas;
    private LocalDate fecha;
    private String estado;
    private String observaciones;
    /** Nombre y correo del propietario (solo lectura, para el panel de gestión). */
    private String propietarioNombre;

    public CaptacionDTO() { }

    public static CaptacionDTO desde(Captacion c) {
        CaptacionDTO d = new CaptacionDTO();
        d.idCaptacion = c.getIdCaptacion();
        d.idPropietario = c.getPropietario() != null ? c.getPropietario().getIdUsuario() : null;
        d.idOperador = c.getOperador() != null ? c.getOperador().getIdUsuario() : null;
        d.caracteristicas = c.getCaracteristicas();
        d.fecha = c.getFecha();
        d.estado = c.getEstado();
        d.observaciones = c.getObservaciones();
        if (c.getPropietario() != null) {
            d.propietarioNombre = c.getPropietario().getNombre() + " " + c.getPropietario().getApellido()
                    + " (" + c.getPropietario().getCorreo() + ")";
        }
        return d;
    }

    public Integer getIdCaptacion() { return idCaptacion; }
    public void setIdCaptacion(Integer idCaptacion) { this.idCaptacion = idCaptacion; }
    public Integer getIdPropietario() { return idPropietario; }
    public void setIdPropietario(Integer idPropietario) { this.idPropietario = idPropietario; }
    public Integer getIdOperador() { return idOperador; }
    public void setIdOperador(Integer idOperador) { this.idOperador = idOperador; }
    public String getCaracteristicas() { return caracteristicas; }
    public void setCaracteristicas(String caracteristicas) { this.caracteristicas = caracteristicas; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public String getPropietarioNombre() { return propietarioNombre; }
    public void setPropietarioNombre(String propietarioNombre) { this.propietarioNombre = propietarioNombre; }
}
