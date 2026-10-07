package com.inmobiliaria.dao;

import com.inmobiliaria.model.SoliFranquicias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SoliFranquiciasRepository extends JpaRepository<SoliFranquicias, Integer> {

    @Query(value = "CALL sp_General_Listar('solifranquicias')", nativeQuery = true)
    List<SoliFranquicias> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('solifranquicias', 'id_solicitud', :id)", nativeQuery = true)
    SoliFranquicias obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('solifranquicias', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('solifranquicias', :setValores, 'id_solicitud', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('solifranquicias', 'id_solicitud', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
