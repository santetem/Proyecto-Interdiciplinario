// ImplementacionHistorialOfertaRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.HistorialOferta;
@Repository
public class ImplementacionHistorialOfertaRepository extends ImplementacionGenericDao<HistorialOferta, Integer> implements HistorialOfertaRepository {
    public ImplementacionHistorialOfertaRepository() { super(HistorialOferta.class, "historial_oferta", "id_histoferta"); }
}