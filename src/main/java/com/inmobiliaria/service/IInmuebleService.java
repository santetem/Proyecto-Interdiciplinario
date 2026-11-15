package com.inmobiliaria.service;

import java.util.List;
import com.inmobiliaria.dto.InmuebleDTO;

public interface IInmuebleService {
    List<InmuebleDTO> listarTodo();
    InmuebleDTO buscarPorId(int id);
    void registrar(InmuebleDTO dto);
    void modificar(int id, InmuebleDTO dto);
    void actualizarParcial(int id, InmuebleDTO dto);
    void eliminar(int id);
}
