package com.remax.dao;

import com.remax.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Ventas')", nativeQuery = true)
    List<Venta> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Ventas', 'id_venta', :id)", nativeQuery = true)
    Venta obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Ventas', 'id_venta', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Ventas')", nativeQuery = true)
    Long contarRegistrosSP();
}
