// ImplementacionPermisoRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Permiso;
@Repository
public class ImplementacionPermisoRepository extends ImplementacionGenericDao<Permiso, Integer> implements PermisoRepository {
    public ImplementacionPermisoRepository() { super(Permiso.class, "permisos", "id_permiso"); }
}