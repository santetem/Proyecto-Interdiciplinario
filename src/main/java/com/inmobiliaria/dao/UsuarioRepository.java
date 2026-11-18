package com.inmobiliaria.dao;

import java.time.LocalDate;
import com.inmobiliaria.model.Usuario;

public interface UsuarioRepository extends IGenericDao<Usuario, Integer> {

    /** Llama a sp_AltaUsuarioConRol: crea el usuario y su rol en UNA transacción. */
    void altaUsuarioConRol(String nombre, String apellido, String tag, String correo,
                           String upassword, String dni, String telefono,
                           LocalDate fechaNacimiento, int idRol);
}
