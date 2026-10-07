package com.inmobiliaria.dao;

import com.inmobiliaria.model.Captacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CaptacionRepository extends JpaRepository<Captacion, Integer> {

    @Query(value = "CALL sp_General_Listar('captaciones')", nativeQuery = true)
    List<Captacion> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('captaciones', 'id_captacion', :id)", nativeQuery = true)
    Captacion obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('captaciones', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('captaciones', :setValores, 'id_captacion', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('captaciones', 'id_captacion', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
