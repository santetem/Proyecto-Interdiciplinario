package com.inmobiliaria.dao;

import com.inmobiliaria.model.RelacionPermisos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RelacionPermisosRepository extends JpaRepository<RelacionPermisos, Integer> {

    @Query(value = "CALL sp_General_Listar('relacionpermisos')", nativeQuery = true)
    List<RelacionPermisos> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('relacionpermisos', 'id_relpermiso', :id)", nativeQuery = true)
    RelacionPermisos obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('relacionpermisos', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('relacionpermisos', :setValores, 'id_relpermiso', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('relacionpermisos', 'id_relpermiso', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
