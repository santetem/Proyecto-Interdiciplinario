package com.inmobiliaria.dao;

import com.inmobiliaria.model.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble, Integer> {

    @Query(value = "CALL sp_General_Listar('inmuebles')", nativeQuery = true)
    List<Inmueble> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('inmuebles', 'id_inmueble', :id)", nativeQuery = true)
    Inmueble obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('inmuebles', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('inmuebles', :setValores, 'id_inmueble', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('inmuebles', 'id_inmueble', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
