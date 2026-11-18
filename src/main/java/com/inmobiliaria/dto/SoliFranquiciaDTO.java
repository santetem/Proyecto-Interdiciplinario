package com.inmobiliaria.dto;

import java.time.LocalDate;
import com.inmobiliaria.model.SoliFranquicias;

/** DTO de SoliFranquicias (solicitud para abrir una franquicia). */
public class SoliFranquiciaDTO {
    private Integer idSolicitud;
    private Integer idUsuario;
    private LocalDate fecha;
    private String estado;
    private String observaciones;
    /** Nombre y correo de quien se postula (solo lectura, para el panel de gestión). */
    private String solicitante;

    public SoliFranquiciaDTO() { }

    public static SoliFranquiciaDTO desde(SoliFranquicias s) {
        SoliFranquiciaDTO d = new SoliFranquiciaDTO();
        d.idSolicitud = s.getIdSolicitud();
        d.idUsuario = s.getUsuario() != null ? s.getUsuario().getIdUsuario() : null;
        d.fecha = s.getFecha();
        d.estado = s.getEstado();
        d.observaciones = s.getObservaciones();
        if (s.getUsuario() != null) {
            d.solicitante = s.getUsuario().getNombre() + " " + s.getUsuario().getApellido()
                    + " (" + s.getUsuario().getCorreo() + ")";
        }
        return d;
    }

    public Integer getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(Integer idSolicitud) { this.idSolicitud = idSolicitud; }
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public String getSolicitante() { return solicitante; }
    public void setSolicitante(String solicitante) { this.solicitante = solicitante; }
}
