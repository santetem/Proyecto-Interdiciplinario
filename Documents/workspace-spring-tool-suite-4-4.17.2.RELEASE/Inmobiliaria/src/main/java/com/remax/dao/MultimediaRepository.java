package com.remax.dao;

import com.remax.model.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Multimedia')", nativeQuery = true)
    List<Multimedia> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Multimedia', 'id_multimedia', :id)", nativeQuery = true)
    Multimedia obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Multimedia', 'id_multimedia', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Multimedia')", nativeQuery = true)
    Long contarRegistrosSP();
}
