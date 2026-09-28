package com.remax.dao;

import com.remax.model.Cobranza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CobranzaRepository extends JpaRepository<Cobranza, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Cobranzas')", nativeQuery = true)
    List<Cobranza> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Cobranzas', 'id_cobranza', :id)", nativeQuery = true)
    Cobranza obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Cobranzas', 'id_cobranza', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Cobranzas')", nativeQuery = true)
    Long contarRegistrosSP();
}
