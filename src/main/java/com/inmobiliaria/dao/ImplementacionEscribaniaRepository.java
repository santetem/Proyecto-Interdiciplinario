// ImplementacionEscribaniaRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Escribania;
@Repository
public class ImplementacionEscribaniaRepository extends ImplementacionGenericDao<Escribania, Integer> implements EscribaniaRepository {
    public ImplementacionEscribaniaRepository() { super(Escribania.class, "escribanias", "id_escribania"); }
}