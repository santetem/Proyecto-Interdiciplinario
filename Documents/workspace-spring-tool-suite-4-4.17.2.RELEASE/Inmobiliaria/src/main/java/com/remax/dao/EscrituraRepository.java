package com.remax.dao;

import com.remax.model.Escritura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EscrituraRepository extends JpaRepository<Escritura, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Escrituras')", nativeQuery = true)
    List<Escritura> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Escrituras', 'id_escritura', :id)", nativeQuery = true)
    Escritura obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Escrituras', 'id_escritura', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Escrituras')", nativeQuery = true)
    Long contarRegistrosSP();
}
