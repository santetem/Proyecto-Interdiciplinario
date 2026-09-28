package com.remax.dao;

import com.remax.model.Caracteristica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImplementacionCaracteristicaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String TABLA = "Caracteristicas";
    private static final String CAMPO_ID = "id_caracteristica";

    @SuppressWarnings("unchecked")
    public List<Caracteristica> listarTodosSP() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_ObtenerTodos", Caracteristica.class);
        query.registerStoredProcedureParameter("p_tabla", String.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("p_tabla", TABLA);
        return query.getResultList();
    }

    public Caracteristica buscarPorIdSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_ObtenerPorId", Caracteristica.class);
        query.registerStoredProcedureParameter("p_tabla", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_campo_id", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("p_tabla", TABLA);
        query.setParameter("p_campo_id", CAMPO_ID);
        query.setParameter("p_id", id);
        return (Caracteristica) query.getSingleResult();
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
