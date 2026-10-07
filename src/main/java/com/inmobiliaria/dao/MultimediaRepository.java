package com.inmobiliaria.dao;

import com.inmobiliaria.model.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Integer> {

    @Query(value = "CALL sp_General_Listar('multimedia')", nativeQuery = true)
    List<Multimedia> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('multimedia', 'id_multimedia', :id)", nativeQuery = true)
    Multimedia obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('multimedia', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('multimedia', :setValores, 'id_multimedia', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('multimedia', 'id_multimedia', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
