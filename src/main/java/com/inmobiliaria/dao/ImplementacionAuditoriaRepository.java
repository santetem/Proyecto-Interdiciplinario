// ImplementacionAuditoriaRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Auditoria;
@Repository
public class ImplementacionAuditoriaRepository extends ImplementacionGenericDao<Auditoria, Integer> implements AuditoriaRepository {
    public ImplementacionAuditoriaRepository() { super(Auditoria.class, "auditorias", "id_auditoria"); }
}