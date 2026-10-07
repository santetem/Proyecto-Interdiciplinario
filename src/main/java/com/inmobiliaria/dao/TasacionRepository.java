package com.inmobiliaria.dao;

import com.inmobiliaria.model.Tasacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TasacionRepository extends JpaRepository<Tasacion, Integer> {

    @Query(value = "CALL sp_General_Listar('tasaciones')", nativeQuery = true)
    List<Tasacion> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('tasaciones', 'id_tasacion', :id)", nativeQuery = true)
    Tasacion obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('tasaciones', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('tasaciones', :setValores, 'id_tasacion', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('tasaciones', 'id_tasacion', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
