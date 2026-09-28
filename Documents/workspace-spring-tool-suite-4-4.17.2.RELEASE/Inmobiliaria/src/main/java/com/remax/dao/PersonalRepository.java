package com.remax.dao;

import com.remax.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Personales')", nativeQuery = true)
    List<Personal> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Personales', 'id_personal', :id)", nativeQuery = true)
    Personal obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Personales', 'id_personal', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Personales')", nativeQuery = true)
    Long contarRegistrosSP();
}
