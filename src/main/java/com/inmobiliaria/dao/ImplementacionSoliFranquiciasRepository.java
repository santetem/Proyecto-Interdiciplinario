// ImplementacionSoliFranquiciasRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.SoliFranquicias;
@Repository
public class ImplementacionSoliFranquiciasRepository extends ImplementacionGenericDao<SoliFranquicias, Integer> implements SoliFranquiciasRepository {
    public ImplementacionSoliFranquiciasRepository() { super(SoliFranquicias.class, "solifranquicias", "id_solicitud"); }
}