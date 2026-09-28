package com.remax.dao;

import com.remax.model.RelacionPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelacionPermisoRepository extends JpaRepository<RelacionPermiso, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('RelacionPermisos')", nativeQuery = true)
    List<RelacionPermiso> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('RelacionPermisos', 'id_relpermiso', :id)", nativeQuery = true)
    RelacionPermiso obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('RelacionPermisos', 'id_relpermiso', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('RelacionPermisos')", nativeQuery = true)
    Long contarRegistrosSP();
}
