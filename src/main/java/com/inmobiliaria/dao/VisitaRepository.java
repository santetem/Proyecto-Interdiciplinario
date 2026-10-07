package com.inmobiliaria.dao;

import com.inmobiliaria.model.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Integer> {

    @Query(value = "CALL sp_General_Listar('visitas')", nativeQuery = true)
    List<Visita> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('visitas', 'id_visita', :id)", nativeQuery = true)
    Visita obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('visitas', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('visitas', :setValores, 'id_visita', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('visitas', 'id_visita', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
