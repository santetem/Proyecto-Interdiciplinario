// ImplementacionAlquilerRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Alquiler;
@Repository
public class ImplementacionAlquilerRepository extends ImplementacionGenericDao<Alquiler, Integer> implements AlquilerRepository {
    public ImplementacionAlquilerRepository() { super(Alquiler.class, "alquileres", "id_alquiler"); }
}