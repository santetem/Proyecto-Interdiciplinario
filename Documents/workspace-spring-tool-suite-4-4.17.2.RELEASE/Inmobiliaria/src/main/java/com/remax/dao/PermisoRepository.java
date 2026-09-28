package com.remax.dao;

import com.remax.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Permisos')", nativeQuery = true)
    List<Permiso> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Permisos', 'id_permiso', :id)", nativeQuery = true)
    Permiso obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Permisos', 'id_permiso', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Permisos')", nativeQuery = true)
    Long contarRegistrosSP();
}
