// ImplementacionComisionRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Comision;
@Repository
public class ImplementacionComisionRepository extends ImplementacionGenericDao<Comision, Integer> implements ComisionRepository {
    public ImplementacionComisionRepository() { super(Comision.class, "comisiones", "id_comision"); }
}