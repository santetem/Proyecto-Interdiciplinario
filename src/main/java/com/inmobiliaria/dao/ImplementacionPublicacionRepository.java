// ImplementacionPublicacionRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Publicacion;
@Repository
public class ImplementacionPublicacionRepository extends ImplementacionGenericDao<Publicacion, Integer> implements PublicacionRepository {
    public ImplementacionPublicacionRepository() { super(Publicacion.class, "publicaciones", "id_publicacion"); }
}