package com.inmobiliaria.service;

import java.util.List;
import com.inmobiliaria.dto.CaptacionDTO;

public interface ICaptacionService {
    /** Crea una solicitud de captación en estado "Pendiente" para el usuario logueado. */
    void registrar(int idUsuario, CaptacionDTO dto);

    /** Todas las captaciones (o solo las de un estado, ej. "Pendiente"). Uso interno del personal. */
    List<CaptacionDTO> listar(String estado);

    /** Las captaciones del usuario logueado. */
    List<CaptacionDTO> listarPorUsuario(int idUsuario);

    /** Acepta: la captación queda "Activa", se crea el inmueble y se publica. */
    void aceptar(int idCaptacion, int idOperador, String observaciones);

    /** Rechaza: la captación queda "Rechazada" con el motivo en observaciones. */
    void rechazar(int idCaptacion, int idOperador, String motivo);
}
