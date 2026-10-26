// ImplementacionRelacionRolesRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.RelacionRoles;
@Repository
public class ImplementacionRelacionRolesRepository extends ImplementacionGenericDao<RelacionRoles, Integer> implements RelacionRolesRepository {
    public ImplementacionRelacionRolesRepository() { super(RelacionRoles.class, "relacionroles", "id_relrol"); }
}