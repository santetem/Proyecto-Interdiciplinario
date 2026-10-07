package com.inmobiliaria.dao;

import com.inmobiliaria.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query(value = "CALL sp_General_Listar('ventas')", nativeQuery = true)
    List<Venta> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('ventas', 'id_venta', :id)", nativeQuery = true)
    Venta obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('ventas', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('ventas', :setValores, 'id_venta', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('ventas', 'id_venta', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
