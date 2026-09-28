package com.remax.dao;

import com.remax.model.Escribania;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EscribaniaRepository extends JpaRepository<Escribania, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Escribanias')", nativeQuery = true)
    List<Escribania> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Escribanias', 'id_escribania', :id)", nativeQuery = true)
    Escribania obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Escribanias', 'id_escribania', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Escribanias')", nativeQuery = true)
    Long contarRegistrosSP();
}
