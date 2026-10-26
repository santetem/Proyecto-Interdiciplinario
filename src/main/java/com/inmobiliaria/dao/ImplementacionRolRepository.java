// ImplementacionRolRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Rol;
@Repository
public class ImplementacionRolRepository extends ImplementacionGenericDao<Rol, Integer> implements RolRepository {
    public ImplementacionRolRepository() { super(Rol.class, "roles", "id_rol"); }
}