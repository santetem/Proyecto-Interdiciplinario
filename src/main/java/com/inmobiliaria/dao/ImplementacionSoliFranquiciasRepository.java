// ImplementacionSoliFranquiciasRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.inmobiliaria.model.SoliFranquicias;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class ImplementacionSoliFranquiciasRepository extends ImplementacionGenericDao<SoliFranquicias, Integer> implements SoliFranquiciasRepository {

    public ImplementacionSoliFranquiciasRepository() { super(SoliFranquicias.class, "solifranquicias", "id_solicitud"); }

    @Override
    @Transactional
    public void aprobarSolicitudSP(int idSolicitud, String nombre, String direccion) {
        StoredProcedureQuery q = entityManager.createStoredProcedureQuery("sp_AprobarSolicitudFranquicia");
        q.registerStoredProcedureParameter("p_id_solicitud", Integer.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN);
        q.setParameter("p_id_solicitud", idSolicitud);
        q.setParameter("p_nombre", nombre);
        q.setParameter("p_direccion", direccion);
        q.execute();
    }
}
