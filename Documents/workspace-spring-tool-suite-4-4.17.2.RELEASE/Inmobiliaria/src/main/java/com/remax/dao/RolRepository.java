package com.remax.dao;

import com.remax.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Roles')", nativeQuery = true)
    List<Rol> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Roles', 'id_rol', :id)", nativeQuery = true)
    Rol obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Roles', 'id_rol', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Roles')", nativeQuery = true)
    Long contarRegistrosSP();
}
