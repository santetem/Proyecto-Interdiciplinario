// ImplementacionInmuebleRepository.java
package com.inmobiliaria.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.inmobiliaria.model.Inmueble;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class ImplementacionInmuebleRepository extends ImplementacionGenericDao<Inmueble, Integer> implements InmuebleRepository {

    public ImplementacionInmuebleRepository() {
        super(Inmueble.class, "inmuebles", "id_inmueble");
    }

    @Override
    @Transactional
    public void eliminarCompletoSP(int idInmueble) {
        StoredProcedureQuery q = entityManager.createStoredProcedureQuery("sp_EliminarInmueble");
        q.registerStoredProcedureParameter("p_id_inmueble", Integer.class, ParameterMode.IN);
        q.setParameter("p_id_inmueble", idInmueble);
        q.execute();
    }
}
