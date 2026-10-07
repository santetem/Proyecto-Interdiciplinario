package com.inmobiliaria.dao;

import com.inmobiliaria.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    @Query(value = "CALL sp_General_Listar('mensajes')", nativeQuery = true)
    List<Mensaje> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('mensajes', 'id_mensaje', :id)", nativeQuery = true)
    Mensaje obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('mensajes', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('mensajes', :setValores, 'id_mensaje', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('mensajes', 'id_mensaje', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
