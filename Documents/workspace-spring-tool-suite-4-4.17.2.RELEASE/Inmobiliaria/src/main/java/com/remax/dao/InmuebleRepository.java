package com.remax.dao;

import com.remax.model.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Inmuebles')", nativeQuery = true)
    List<Inmueble> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Inmuebles', 'id_inmueble', :id)", nativeQuery = true)
    Inmueble obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Inmuebles', 'id_inmueble', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Inmuebles')", nativeQuery = true)
    Long contarRegistrosSP();
}
