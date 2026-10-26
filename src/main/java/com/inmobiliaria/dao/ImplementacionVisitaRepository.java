// ImplementacionVisitaRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Visita;
@Repository
public class ImplementacionVisitaRepository extends ImplementacionGenericDao<Visita, Integer> implements VisitaRepository {
    public ImplementacionVisitaRepository() { super(Visita.class, "visitas", "id_visita"); }
}