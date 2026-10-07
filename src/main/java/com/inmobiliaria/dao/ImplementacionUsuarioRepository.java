package com.inmobiliaria.dao;

import com.inmobiliaria.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class ImplementacionUsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String TABLA = "usuarios";
    private static final String CAMPO_ID = "id_usuario";

    @SuppressWarnings("unchecked")
    public List<Usuario> listarTodosSP() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_General_Listar", Usuario.class);
        query.registerStoredProcedureParameter("p_nombre_tabla", String.class, javax.persistence.ParameterMode.IN);
        query.setParameter("p_nombre_tabla", TABLA);
        return query.getResultList();
    }

    public Usuario buscarPorIdSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_General_BuscarPorId", Usuario.class);
        query.registerStoredProcedureParameter("p_nombre_tabla", String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre_id", String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_valor_id", String.class, javax.persistence.ParameterMode.IN);

        query.setParameter("p_nombre_tabla", TABLA);
        query.setParameter("p_nombre_id", CAMPO_ID);
        query.setParameter("p_valor_id", String.valueOf(id));

        return (Usuario) query.getSingleResult();
    }

    @Transactional
    public void insertarSP(String columnas, String valores) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_General_Insertar");
        query.registerStoredProcedureParameter("p_nombre_tabla", String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_columnas", String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_valores", String.class, javax.persistence.ParameterMode.IN);

        query.setParameter("p_nombre_tabla", TABLA);
        query.setParameter("p_columnas", columnas);
        query.setParameter("p_valores", valores);
        query.execute();
    }

    @Transactional
    public void actualizarSP(String setValores, Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_General_Actualizar");
        query.registerStoredProcedureParameter("p_nombre_tabla", String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_set_valores", String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre_id", String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_valor_id", String.class, javax.persistence.ParameterMode.IN);

        query.setParameter("p_nombre_tabla", TABLA);
        query.setParameter("p_set_valores", setValores);
        query.setParameter("p_nombre_id", CAMPO_ID);
        query.setParameter("p_valor_id", String.valueOf(id));
        query.execute();
    }

    @Transactional
    public void eliminarPorIdSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_General_Eliminar");
        query.registerStoredProcedureParameter("p_nombre_tabla", String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre_id", String.class, javax.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_valor_id", String.class, javax.persistence.ParameterMode.IN);

        query.setParameter("p_nombre_tabla", TABLA);
        query.setParameter("p_nombre_id", CAMPO_ID);
        query.setParameter("p_valor_id", String.valueOf(id));
        query.execute();
    }
}
