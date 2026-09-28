package com.remax.dao;

import com.remax.model.Tasacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasacionRepository extends JpaRepository<Tasacion, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Tasaciones')", nativeQuery = true)
    List<Tasacion> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Tasaciones', 'id_tasacion', :id)", nativeQuery = true)
    Tasacion obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Tasaciones', 'id_tasacion', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Tasaciones')", nativeQuery = true)
    Long contarRegistrosSP();
}
