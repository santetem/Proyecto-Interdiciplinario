package com.remax.dao;

import com.remax.model.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Ofertas')", nativeQuery = true)
    List<Oferta> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Ofertas', 'id_oferta', :id)", nativeQuery = true)
    Oferta obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Ofertas', 'id_oferta', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Ofertas')", nativeQuery = true)
    Long contarRegistrosSP();
}
