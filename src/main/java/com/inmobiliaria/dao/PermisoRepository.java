package com.inmobiliaria.dao;

import com.inmobiliaria.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {

    @Query(value = "CALL sp_General_Listar('permisos')", nativeQuery = true)
    List<Permiso> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('permisos', 'id_permiso', :id)", nativeQuery = true)
    Permiso obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('permisos', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('permisos', :setValores, 'id_permiso', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('permisos', 'id_permiso', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
