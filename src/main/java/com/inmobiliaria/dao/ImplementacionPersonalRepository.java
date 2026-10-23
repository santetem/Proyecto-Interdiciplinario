// ImplementacionPersonalRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Personal;
@Repository
public class ImplementacionPersonalRepository extends ImplementacionGenericDao<Personal, Integer> implements PersonalRepository {
    public ImplementacionPersonalRepository() { super(Personal.class, "personales", "id_personal"); }
}