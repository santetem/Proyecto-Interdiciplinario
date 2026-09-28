package com.remax.dao;

import com.remax.model.Personal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImplementacionPersonalRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String TABLA = "Personales";
    private static final String CAMPO_ID = "id_personal";

    @SuppressWarnings("unchecked")
    public List<Personal> listarTodosSP() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_ObtenerTodos", Personal.class);
        query.registerStoredProcedureParameter("p_tabla", String.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("p_tabla", TABLA);
        return query.getResultList();
    }

    public Personal buscarPorIdSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_ObtenerPorId", Personal.class);
        query.registerStoredProcedureParameter("p_tabla", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_campo_id", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("p_tabla", TABLA);
        query.setParameter("p_campo_id", CAMPO_ID);
        query.setParameter("p_id", id);
        return (Personal) query.getSingleResult();
    }

    public void eliminarPorIdSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_EliminarPorId");
        query.registerStoredProcedureParameter("p_tabla", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_campo_id", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("p_tabla", TABLA);
        query.setParameter("p_campo_id", CAMPO_ID);
        query.setParameter("p_id", id);
        query.execute();
    }

    public Long contarTotalSP() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_ContarRegistros");
        query.registerStoredProcedureParameter("p_tabla", String.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("p_tabla", TABLA);
        Object result = query.getSingleResult();
        return ((Number) result).longValue();
    }
}
