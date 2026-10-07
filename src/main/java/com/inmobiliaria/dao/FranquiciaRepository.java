package com.inmobiliaria.dao;

import com.inmobiliaria.model.Franquicia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FranquiciaRepository extends JpaRepository<Franquicia, Integer> {

    @Query(value = "CALL sp_General_Listar('franquicias')", nativeQuery = true)
    List<Franquicia> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('franquicias', 'id_franquicia', :id)", nativeQuery = true)
    Franquicia obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('franquicias', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('franquicias', :setValores, 'id_franquicia', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('franquicias', 'id_franquicia', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
