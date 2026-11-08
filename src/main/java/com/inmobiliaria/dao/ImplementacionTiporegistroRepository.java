package com.inmobiliaria.dao;

import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Tiporegistro;

@Repository
public class ImplementacionTiporegistroRepository extends ImplementacionGenericDao<Tiporegistro, Integer> implements TiporegistroRepository {

    public ImplementacionTiporegistroRepository() {
        super(Tiporegistro.class, "tiporegistro", "id_tipo");
    }
}