package com.remax.dao;

import com.remax.model.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Visitas')", nativeQuery = true)
    List<Visita> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Visitas', 'id_visita', :id)", nativeQuery = true)
    Visita obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Visitas', 'id_visita', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Visitas')", nativeQuery = true)
    Long contarRegistrosSP();
}
