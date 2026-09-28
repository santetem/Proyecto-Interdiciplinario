package com.remax.dao;

import com.remax.model.Alquiler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImplementacionAlquilerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String TABLA = "Alquileres";
    private static final String CAMPO_ID = "id_alquiler";

    @SuppressWarnings("unchecked")
    public List<Alquiler> listarTodosSP() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_ObtenerTodos", Alquiler.class);
        query.registerStoredProcedureParameter("p_tabla", String.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("p_tabla", TABLA);
        return query.getResultList();
    }

    public Alquiler buscarPorIdSP(Integer id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_ObtenerPorId", Alquiler.class);
        query.registerStoredProcedureParameter("p_tabla", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_campo_id", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id", Integer.class, jakarta.persistence.ParameterMode.IN);

        query.setParameter("p_tabla", TABLA);
        query.setParameter("p_campo_id", CAMPO_ID);
        query.setParameter("p_id", id);

        return (Alquiler) query.getSingleResult();
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
