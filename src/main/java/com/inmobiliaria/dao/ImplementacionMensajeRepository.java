// ImplementacionMensajeRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Mensaje;
@Repository
public class ImplementacionMensajeRepository extends ImplementacionGenericDao<Mensaje, Integer> implements MensajeRepository {
    public ImplementacionMensajeRepository() { super(Mensaje.class, "mensajes", "id_mensaje"); }
}