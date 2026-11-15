// ImplementacionOperacionRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Operacion;
@Repository
public class ImplementacionOperacionRepository extends ImplementacionGenericDao<Operacion, Integer> implements OperacionRepository {
    public ImplementacionOperacionRepository() { super(Operacion.class, "operaciones", "id_operacion"); }
}