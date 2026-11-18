package com.inmobiliaria.service;

import com.inmobiliaria.dto.OfertaDTO;

public interface IOfertaService {
    void registrar(int idUsuario, OfertaDTO dto);
}
