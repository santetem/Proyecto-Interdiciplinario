package com.remax.dao;

import com.remax.model.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Caracteristicas')", nativeQuery = true)
    List<Caracteristica> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Caracteristicas', 'id_caracteristica', :id)", nativeQuery = true)
    Caracteristica obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Caracteristicas', 'id_caracteristica', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Caracteristicas')", nativeQuery = true)
    Long contarRegistrosSP();
}
