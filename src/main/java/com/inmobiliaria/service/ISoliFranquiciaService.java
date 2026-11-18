package com.inmobiliaria.service;

import java.util.List;
import com.inmobiliaria.dto.SoliFranquiciaDTO;

public interface ISoliFranquiciaService {
    void registrar(int idUsuario, SoliFranquiciaDTO dto);
    List<SoliFranquiciaDTO> listarPorUsuario(int idUsuario);

    /** Todas las solicitudes (o solo las de un estado, ej. "Pendiente"). Uso interno del personal. */
    List<SoliFranquiciaDTO> listar(String estado);

    /** Aprueba: la solicitud queda "Aprobada" y se crea la franquicia "Activa". */
    void aprobar(int idSolicitud);

    /** Rechaza: la solicitud queda "Rechazada". */
    void rechazar(int idSolicitud);
}
