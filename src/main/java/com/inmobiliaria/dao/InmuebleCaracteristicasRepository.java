package com.inmobiliaria.dao;

import com.inmobiliaria.model.InmuebleCaracteristicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InmuebleCaracteristicasRepository extends JpaRepository<InmuebleCaracteristicas, Integer> {

    @Query(value = "CALL sp_General_Listar('inmueble_caracteristicas')", nativeQuery = true)
    List<InmuebleCaracteristicas> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('inmueble_caracteristicas', 'id_inmueble', :id)", nativeQuery = true)
    InmuebleCaracteristicas obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('inmueble_caracteristicas', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('inmueble_caracteristicas', :setValores, 'id_inmueble', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('inmueble_caracteristicas', 'id_inmueble', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
