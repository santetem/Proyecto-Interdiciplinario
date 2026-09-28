package com.remax.dao;

import com.remax.model.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperacionRepository extends JpaRepository<Operacion, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Operaciones')", nativeQuery = true)
    List<Operacion> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Operaciones', 'id_operacion', :id)", nativeQuery = true)
    Operacion obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Operaciones', 'id_operacion', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Operaciones')", nativeQuery = true)
    Long contarRegistrosSP();
}
