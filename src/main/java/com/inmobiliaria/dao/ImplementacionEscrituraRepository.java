// ImplementacionEscrituraRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Escritura;
@Repository
public class ImplementacionEscrituraRepository extends ImplementacionGenericDao<Escritura, Integer> implements EscrituraRepository {
    public ImplementacionEscrituraRepository() { super(Escritura.class, "escrituras", "id_escritura"); }
}