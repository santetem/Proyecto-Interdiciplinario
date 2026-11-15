package com.inmobiliaria.dao;

import java.math.BigDecimal;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.inmobiliaria.model.Venta;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class ImplementacionVentaRepository extends ImplementacionGenericDao<Venta, Integer> implements VentaRepository {

    public ImplementacionVentaRepository() {
        super(Venta.class, "ventas", "id_venta");
    }

    @Override
    @Transactional
    public void concretarVentaSP(int idOferta, BigDecimal montoFinal, int idUsuarioComision, BigDecimal porcentajeComision) {
        StoredProcedureQuery q = entityManager.createStoredProcedureQuery("sp_ConcretarVenta");
        q.registerStoredProcedureParameter("p_id_oferta", Integer.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_monto_final", BigDecimal.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_id_usuario_comision", Integer.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_porcentaje_comision", BigDecimal.class, ParameterMode.IN);
        q.setParameter("p_id_oferta", idOferta);
        q.setParameter("p_monto_final", montoFinal);
        q.setParameter("p_id_usuario_comision", idUsuarioComision);
        q.setParameter("p_porcentaje_comision", porcentajeComision);
        q.execute();
    }
}
