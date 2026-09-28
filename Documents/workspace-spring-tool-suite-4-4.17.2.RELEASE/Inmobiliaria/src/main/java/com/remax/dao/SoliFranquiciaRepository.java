package com.remax.dao;

import com.remax.model.SoliFranquicia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoliFranquiciaRepository extends JpaRepository<SoliFranquicia, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('SoliFranquicias')", nativeQuery = true)
    List<SoliFranquicia> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('SoliFranquicias', 'id_solicitud', :id)", nativeQuery = true)
    SoliFranquicia obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('SoliFranquicias', 'id_solicitud', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('SoliFranquicias')", nativeQuery = true)
    Long contarRegistrosSP();
}
