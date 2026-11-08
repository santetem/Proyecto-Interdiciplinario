// ImplementacionConversacionRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Conversacion;
@Repository
public class ImplementacionConversacionRepository extends ImplementacionGenericDao<Conversacion, Integer> implements ConversacionRepository {
    public ImplementacionConversacionRepository() { super(Conversacion.class, "conversaciones", "id_conversacion"); }
}