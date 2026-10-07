package com.inmobiliaria.dao;

import com.inmobiliaria.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Integer> {

    @Query(value = "CALL sp_General_Listar('personales')", nativeQuery = true)
    List<Personal> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('personales', 'id_personal', :id)", nativeQuery = true)
    Personal obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('personales', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('personales', :setValores, 'id_personal', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('personales', 'id_personal', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
