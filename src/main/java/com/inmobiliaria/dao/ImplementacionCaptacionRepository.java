// ImplementacionCaptacionRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Captacion;
@Repository
public class ImplementacionCaptacionRepository extends ImplementacionGenericDao<Captacion, Integer> implements CaptacionRepository {
    public ImplementacionCaptacionRepository() { super(Captacion.class, "captaciones", "id_captacion"); }
}