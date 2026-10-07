package com.inmobiliaria.dao;

import com.inmobiliaria.model.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OperacionRepository extends JpaRepository<Operacion, Integer> {

    @Query(value = "CALL sp_General_Listar('operaciones')", nativeQuery = true)
    List<Operacion> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('operaciones', 'id_operacion', :id)", nativeQuery = true)
    Operacion obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('operaciones', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('operaciones', :setValores, 'id_operacion', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('operaciones', 'id_operacion', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
