package com.remax.dao;

import com.remax.model.RelacionRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelacionRolRepository extends JpaRepository<RelacionRol, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('RelacionRoles')", nativeQuery = true)
    List<RelacionRol> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('RelacionRoles', 'id_relrol', :id)", nativeQuery = true)
    RelacionRol obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('RelacionRoles', 'id_relrol', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('RelacionRoles')", nativeQuery = true)
    Long contarRegistrosSP();
}
