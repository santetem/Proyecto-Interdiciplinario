package com.inmobiliaria.dao;

import com.inmobiliaria.model.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Integer> {

    @Query(value = "CALL sp_General_Listar('caracteristicas')", nativeQuery = true)
    List<Caracteristica> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('caracteristicas', 'id_caracteristica', :id)", nativeQuery = true)
    Caracteristica obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('caracteristicas', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('caracteristicas', :setValores, 'id_caracteristica', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('caracteristicas', 'id_caracteristica', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
