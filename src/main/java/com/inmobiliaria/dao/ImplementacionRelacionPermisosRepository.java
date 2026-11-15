// ImplementacionRelacionPermisosRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.RelacionPermisos;
@Repository
public class ImplementacionRelacionPermisosRepository extends ImplementacionGenericDao<RelacionPermisos, Integer> implements RelacionPermisosRepository {
    public ImplementacionRelacionPermisosRepository() { super(RelacionPermisos.class, "relacionpermisos", "id_relpermiso"); }
}