// ImplementacionCaracteristicaRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Caracteristica;
@Repository
public class ImplementacionCaracteristicaRepository extends ImplementacionGenericDao<Caracteristica, Integer> implements CaracteristicaRepository {
    public ImplementacionCaracteristicaRepository() { super(Caracteristica.class, "caracteristicas", "id_caracteristica"); }
}