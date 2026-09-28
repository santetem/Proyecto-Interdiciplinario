package com.remax.dao;

import com.remax.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Alquileres')", nativeQuery = true)
    List<Alquiler> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Alquileres', 'id_alquiler', :id)", nativeQuery = true)
    Alquiler obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Alquileres', 'id_alquiler', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Alquileres')", nativeQuery = true)
    Long contarRegistrosSP();
}
