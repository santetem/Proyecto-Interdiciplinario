// ImplementacionTasacionRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Tasacion;
@Repository
public class ImplementacionTasacionRepository extends ImplementacionGenericDao<Tasacion, Integer> implements TasacionRepository {
    public ImplementacionTasacionRepository() { super(Tasacion.class, "tasaciones", "id_tasacion"); }
}