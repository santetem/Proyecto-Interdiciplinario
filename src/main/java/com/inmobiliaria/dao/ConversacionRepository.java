package com.inmobiliaria.dao;

import com.inmobiliaria.model.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConversacionRepository extends JpaRepository<Conversacion, Integer> {

    @Query(value = "CALL sp_General_Listar('conversaciones')", nativeQuery = true)
    List<Conversacion> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('conversaciones', 'id_conversacion', :id)", nativeQuery = true)
    Conversacion obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('conversaciones', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('conversaciones', :setValores, 'id_conversacion', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('conversaciones', 'id_conversacion', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
