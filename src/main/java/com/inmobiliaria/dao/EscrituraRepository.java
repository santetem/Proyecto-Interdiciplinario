package com.inmobiliaria.dao;

import com.inmobiliaria.model.Escritura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EscrituraRepository extends JpaRepository<Escritura, Integer> {

    @Query(value = "CALL sp_General_Listar('escrituras')", nativeQuery = true)
    List<Escritura> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('escrituras', 'id_escritura', :id)", nativeQuery = true)
    Escritura obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('escrituras', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('escrituras', :setValores, 'id_escritura', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('escrituras', 'id_escritura', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
