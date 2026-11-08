// ImplementacionInmuebleRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Inmueble;
@Repository
public class ImplementacionInmuebleRepository extends ImplementacionGenericDao<Inmueble, Integer> implements InmuebleRepository {
    public ImplementacionInmuebleRepository() { super(Inmueble.class, "inmuebles", "id_inmueble"); }
}