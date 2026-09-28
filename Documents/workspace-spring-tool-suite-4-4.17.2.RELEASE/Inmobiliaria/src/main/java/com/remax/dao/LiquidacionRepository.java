package com.remax.dao;

import com.remax.model.Liquidacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiquidacionRepository extends JpaRepository<Liquidacion, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Liquidaciones')", nativeQuery = true)
    List<Liquidacion> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Liquidaciones', 'id_liquidacion', :id)", nativeQuery = true)
    Liquidacion obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Liquidaciones', 'id_liquidacion', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Liquidaciones')", nativeQuery = true)
    Long contarRegistrosSP();
}
