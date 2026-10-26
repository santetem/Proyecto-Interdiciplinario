// ImplementacionOfertaRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Oferta;
@Repository
public class ImplementacionOfertaRepository extends ImplementacionGenericDao<Oferta, Integer> implements OfertaRepository {
    public ImplementacionOfertaRepository() { super(Oferta.class, "ofertas", "id_oferta"); }
}