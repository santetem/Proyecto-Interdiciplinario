package com.remax.dao;

import com.remax.model.Franquicia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FranquiciaRepository extends JpaRepository<Franquicia, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Franquicias')", nativeQuery = true)
    List<Franquicia> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Franquicias', 'id_franquicia', :id)", nativeQuery = true)
    Franquicia obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Franquicias', 'id_franquicia', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Franquicias')", nativeQuery = true)
    Long contarRegistrosSP();
}
