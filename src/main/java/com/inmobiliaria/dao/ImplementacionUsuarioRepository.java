package com.inmobiliaria.dao;

import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Usuario;

@Repository
public class ImplementacionUsuarioRepository 
        extends ImplementacionGenericDao<Usuario, Integer> 
        implements UsuarioRepository {

    public ImplementacionUsuarioRepository() {
        super(Usuario.class, "usuarios", "id_usuario");
    }
}