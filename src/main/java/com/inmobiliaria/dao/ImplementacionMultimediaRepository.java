// ImplementacionMultimediaRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Multimedia;
@Repository
public class ImplementacionMultimediaRepository extends ImplementacionGenericDao<Multimedia, Integer> implements MultimediaRepository {
    public ImplementacionMultimediaRepository() { super(Multimedia.class, "multimedia", "id_multimedia"); }
}