// ImplementacionInmuebleCaracteristicasRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.InmuebleCaracteristicas;
@Repository
public class ImplementacionInmuebleCaracteristicasRepository extends ImplementacionGenericDao<InmuebleCaracteristicas, Integer> implements InmuebleCaracteristicasRepository {
    public ImplementacionInmuebleCaracteristicasRepository() { super(InmuebleCaracteristicas.class, "inmueble_caracteristicas", "id_inmueble"); }
}