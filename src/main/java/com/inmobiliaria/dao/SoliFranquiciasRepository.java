// SoliFranquiciasRepository.java
package com.inmobiliaria.dao;
import com.inmobiliaria.model.SoliFranquicias;
public interface SoliFranquiciasRepository extends IGenericDao<SoliFranquicias, Integer> {

    /**
     * Llama a sp_AprobarSolicitudFranquicia: en una sola transacción pasa la solicitud a "Aprobada"
     * y crea la fila de Franquicias (estado "Activa"). Si algo falla hace ROLLBACK.
     */
    void aprobarSolicitudSP(int idSolicitud, String nombre, String direccion);
}
