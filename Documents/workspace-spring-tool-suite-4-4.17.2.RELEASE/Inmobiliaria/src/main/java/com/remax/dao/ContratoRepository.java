package com.remax.dao;

import com.remax.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Contratos')", nativeQuery = true)
    List<Contrato> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Contratos', 'id_contrato', :id)", nativeQuery = true)
    Contrato obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Contratos', 'id_contrato', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Contratos')", nativeQuery = true)
    Long contarRegistrosSP();
}
