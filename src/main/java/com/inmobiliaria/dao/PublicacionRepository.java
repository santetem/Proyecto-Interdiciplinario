package com.inmobiliaria.dao;

import com.inmobiliaria.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {

    @Query(value = "CALL sp_General_Listar('publicaciones')", nativeQuery = true)
    List<Publicacion> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('publicaciones', 'id_publicacion', :id)", nativeQuery = true)
    Publicacion obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('publicaciones', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('publicaciones', :setValores, 'id_publicacion', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('publicaciones', 'id_publicacion', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
