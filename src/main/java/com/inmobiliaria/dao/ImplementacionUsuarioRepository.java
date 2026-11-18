package com.inmobiliaria.dao;

import java.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.inmobiliaria.model.Usuario;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class ImplementacionUsuarioRepository
        extends ImplementacionGenericDao<Usuario, Integer>
        implements UsuarioRepository {

    public ImplementacionUsuarioRepository() {
        super(Usuario.class, "usuarios", "id_usuario");
    }

    @Override
    @Transactional
    public void altaUsuarioConRol(String nombre, String apellido, String tag, String correo,
                                  String upassword, String dni, String telefono,
                                  LocalDate fechaNacimiento, int idRol) {
        StoredProcedureQuery q = entityManager.createStoredProcedureQuery("sp_AltaUsuarioConRol");
        q.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_tag", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_correo", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_upassword", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_dni", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_telefono", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_fecha_nacimiento", java.sql.Date.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_id_rol", Integer.class, ParameterMode.IN);
        q.setParameter("p_nombre", nombre);
        q.setParameter("p_apellido", apellido);
        q.setParameter("p_tag", tag);
        q.setParameter("p_correo", correo);
        q.setParameter("p_upassword", upassword);
        q.setParameter("p_dni", dni);
        q.setParameter("p_telefono", telefono);
        q.setParameter("p_fecha_nacimiento", java.sql.Date.valueOf(fechaNacimiento)); // LocalDate -> DATE de MySQL
        q.setParameter("p_id_rol", idRol);
        q.execute();
    }
}
