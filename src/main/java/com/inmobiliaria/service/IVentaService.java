package com.inmobiliaria.service;

import java.util.List;
import com.inmobiliaria.dto.ConcretarVentaDTO;
import com.inmobiliaria.dto.VentaDTO;

public interface IVentaService {
    /** @param idUsuarioSesion usuario logueado; cobra la comisión si el DTO no indica otro. */
    void concretarVenta(ConcretarVentaDTO dto, Integer idUsuarioSesion);
    List<VentaDTO> listarTodo();
}
