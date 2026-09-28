package com.remax.dao;

import com.remax.model.Captacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaptacionRepository extends JpaRepository<Captacion, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Captaciones')", nativeQuery = true)
    List<Captacion> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Captaciones', 'id_captacion', :id)", nativeQuery = true)
    Captacion obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Captaciones', 'id_captacion', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Captaciones')", nativeQuery = true)
    Long contarRegistrosSP();
}
