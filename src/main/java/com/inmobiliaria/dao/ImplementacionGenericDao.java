package com.inmobiliaria.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.transaction.annotation.Transactional;

public abstract class ImplementacionGenericDao<T, ID> implements IGenericDao<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<T> entityClass;
    private final String nombreTabla;
    private final String nombreCampoId;

    public ImplementacionGenericDao(Class<T> entityClass, String nombreTabla, String nombreCampoId) {
        this.entityClass = entityClass;
        this.nombreTabla = nombreTabla;
        this.nombreCampoId = nombreCampoId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> listarTodosSP() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_General_Listar", entityClass);
        query.registerStoredProcedureParameter("p_nombre_tabla", String.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("p_nombre_tabla", nombreTabla);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T buscarPorIdSP(ID id) {
        String sql = "CALL sp_General_BuscarPorId(:tabla, :campoId, :id)";
        List<T> resultados = entityManager.createNativeQuery(sql, entityClass)
                .setParameter("tabla", nombreTabla)
                .setParameter("campoId", nombreCampoId)
                .setParameter("id", id)
                .getResultList();
        
        if (resultados.isEmpty()) {
            return null;
        }
        
        T entidad = resultados.get(0);
        // Forzamos a Hibernate a inicializar la entidad si viene como proxy
        org.hibernate.Hibernate.initialize(entidad);
        if (entidad instanceof org.hibernate.proxy.HibernateProxy) {
            entidad = (T) ((org.hibernate.proxy.HibernateProxy) entidad)
                    .getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entidad;
    }

    @Override
    @Transactional
    public void insertarSP(String columnas, String valores) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_General_Insertar");
        query.registerStoredProcedureParameter("p_nombre_tabla", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_columnas", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_valores", String.class, jakarta.persistence.ParameterMode.IN);

        query.setParameter("p_nombre_tabla", nombreTabla);
        query.setParameter("p_columnas", columnas);
        query.setParameter("p_valores", valores);
        query.execute();
    }

    @Override
    @Transactional
    public void actualizarSP(String setValores, ID id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_General_Actualizar");
        query.registerStoredProcedureParameter("p_nombre_tabla", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_set_valores", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre_id", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id", Integer.class, jakarta.persistence.ParameterMode.IN);

        query.setParameter("p_nombre_tabla", nombreTabla);
        query.setParameter("p_set_valores", setValores);
        query.setParameter("p_nombre_id", nombreCampoId);
        query.setParameter("p_id", id);
        query.execute();
    }

    @Override
    @Transactional
    public void eliminarPorIdSP(ID id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_General_Eliminar");
        query.registerStoredProcedureParameter("p_nombre_tabla", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_nombre_id", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id", Integer.class, jakarta.persistence.ParameterMode.IN);

        query.setParameter("p_nombre_tabla", tableNameFormatter(nombreTabla));
        query.setParameter("p_nombre_id", nombreCampoId);
        query.setParameter("p_id", id);
        query.execute();
    }
    
    private String tableNameFormatter(String name) {
        return name;
    }
}