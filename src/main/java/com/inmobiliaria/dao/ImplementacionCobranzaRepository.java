package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Cobranza;

@Repository
public class ImplementacionCobranzaRepository extends ImplementacionGenericDao<Cobranza, Integer> implements CobranzaRepository {
    public ImplementacionCobranzaRepository() { super(Cobranza.class, "cobranzas", "id_cobranza"); }
}