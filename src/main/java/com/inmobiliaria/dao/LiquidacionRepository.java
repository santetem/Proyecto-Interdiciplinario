package com.inmobiliaria.dao;

import com.inmobiliaria.model.Liquidacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LiquidacionRepository extends JpaRepository<Liquidacion, Integer> {

    @Query(value = "CALL sp_General_Listar('liquidaciones')", nativeQuery = true)
    List<Liquidacion> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('liquidaciones', 'id_liquidacion', :id)", nativeQuery = true)
    Liquidacion obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('liquidaciones', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('liquidaciones', :setValores, 'id_liquidacion', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('liquidaciones', 'id_liquidacion', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
