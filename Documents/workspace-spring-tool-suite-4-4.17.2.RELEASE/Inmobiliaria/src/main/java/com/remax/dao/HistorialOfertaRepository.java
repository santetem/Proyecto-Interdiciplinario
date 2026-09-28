package com.remax.dao;

import com.remax.model.HistorialOferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialOfertaRepository extends JpaRepository<HistorialOferta, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Historial_oferta')", nativeQuery = true)
    List<HistorialOferta> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Historial_oferta', 'id_histoferta', :id)", nativeQuery = true)
    HistorialOferta obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Historial_oferta', 'id_histoferta', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Historial_oferta')", nativeQuery = true)
    Long contarRegistrosSP();
}
