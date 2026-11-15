package com.inmobiliaria.service;

import java.util.List;
import com.inmobiliaria.dto.LoginDTO;
import com.inmobiliaria.dto.LoginRespuestaDTO;
import com.inmobiliaria.dto.RegistroUsuarioDTO;
import com.inmobiliaria.dto.UsuarioDTO;

public interface IUsuarioService {
    void registrar(RegistroUsuarioDTO dto);
    LoginRespuestaDTO login(LoginDTO dto);
    void logout(String token);
    List<UsuarioDTO> listarTodos();
    UsuarioDTO buscarPorId(int id);
}
