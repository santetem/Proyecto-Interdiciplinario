package com.inmobiliaria.dao;

import com.inmobiliaria.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {

    @Query(value = "CALL sp_General_Listar('alquileres')", nativeQuery = true)
    List<Alquiler> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('alquileres', 'id_alquiler', :id)", nativeQuery = true)
    Alquiler obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('alquileres', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('alquileres', :setValores, 'id_alquiler', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('alquileres', 'id_alquiler', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
