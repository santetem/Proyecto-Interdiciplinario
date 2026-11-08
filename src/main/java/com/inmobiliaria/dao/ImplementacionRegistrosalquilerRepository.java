package com.inmobiliaria.dao;

import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Registrosalquiler;

@Repository
public class ImplementacionRegistrosalquilerRepository extends ImplementacionGenericDao<Registrosalquiler, Integer> implements RegistrosalquilerRepository {

    public ImplementacionRegistrosalquilerRepository() {
        super(Registrosalquiler.class, "registrosalquiler", "id_registro");
    }
}