// InmuebleRepository.java
package com.inmobiliaria.dao;

import com.inmobiliaria.model.Inmueble;

public interface InmuebleRepository extends IGenericDao<Inmueble, Integer> {

    /**
     * Llama a sp_EliminarInmueble: borra el inmueble y todo lo que depende de el
     * (fotos, publicacion, ofertas, visitas...) en UNA transaccion. Si algo falla hace ROLLBACK.
     */
    void eliminarCompletoSP(int idInmueble);
}
