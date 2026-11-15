package com.inmobiliaria.service;

import java.util.List;
import com.inmobiliaria.dto.AlquilerDTO;

public interface IAlquilerService {
    List<AlquilerDTO> listarTodo();
    AlquilerDTO buscarPorId(int id);
    void registrar(AlquilerDTO dto);
    void modificar(int id, AlquilerDTO dto);
    void actualizarParcial(int id, AlquilerDTO dto);
    void eliminar(int id);
}
