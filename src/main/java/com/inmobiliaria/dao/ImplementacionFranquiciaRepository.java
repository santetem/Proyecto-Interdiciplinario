// ImplementacionFranquiciaRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Franquicia;
@Repository
public class ImplementacionFranquiciaRepository extends ImplementacionGenericDao<Franquicia, Integer> implements FranquiciaRepository {
    public ImplementacionFranquiciaRepository() { super(Franquicia.class, "franquicias", "id_franquicia"); }
}