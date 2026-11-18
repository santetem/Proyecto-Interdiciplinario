// ImplementacionCaptacionRepository.java
package com.inmobiliaria.dao;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.inmobiliaria.model.Captacion;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class ImplementacionCaptacionRepository extends ImplementacionGenericDao<Captacion, Integer> implements CaptacionRepository {

    public ImplementacionCaptacionRepository() { super(Captacion.class, "captaciones", "id_captacion"); }

    @Override
    @Transactional
    public void aceptarCaptacionSP(int idCaptacion, int idOperador, String direccion, String tipo,
                                   BigDecimal supCubierta, BigDecimal supTotal, int habitaciones, int banos,
                                   int cocheras, String antiguedad, String localidad, String observaciones) {
        StoredProcedureQuery q = entityManager.createStoredProcedureQuery("sp_AceptarCaptacion");
        q.registerStoredProcedureParameter("p_id_captacion", Integer.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_id_operador", Integer.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_tipo", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_sup_cubierta", BigDecimal.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_sup_total", BigDecimal.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_habitaciones", Integer.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_banos", Integer.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_cocheras", Integer.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_antiguedad", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_localidad", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_observaciones", String.class, ParameterMode.IN);
        q.setParameter("p_id_captacion", idCaptacion);
        q.setParameter("p_id_operador", idOperador);
        q.setParameter("p_direccion", direccion);
        q.setParameter("p_tipo", tipo);
        q.setParameter("p_sup_cubierta", supCubierta);
        q.setParameter("p_sup_total", supTotal);
        q.setParameter("p_habitaciones", habitaciones);
        q.setParameter("p_banos", banos);
        q.setParameter("p_cocheras", cocheras);
        q.setParameter("p_antiguedad", antiguedad);
        q.setParameter("p_localidad", localidad);
        q.setParameter("p_observaciones", observaciones);
        q.execute();
    }
}
