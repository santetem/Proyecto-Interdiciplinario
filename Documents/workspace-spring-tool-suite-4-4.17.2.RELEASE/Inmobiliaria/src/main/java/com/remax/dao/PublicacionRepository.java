package com.remax.dao;

import com.remax.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Publicaciones')", nativeQuery = true)
    List<Publicacion> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Publicaciones', 'id_publicacion', :id)", nativeQuery = true)
    Publicacion obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Publicaciones', 'id_publicacion', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Publicaciones')", nativeQuery = true)
    Long contarRegistrosSP();
}
