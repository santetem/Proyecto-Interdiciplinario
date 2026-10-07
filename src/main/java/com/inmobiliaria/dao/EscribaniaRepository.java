package com.inmobiliaria.dao;

import com.inmobiliaria.model.Escribania;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EscribaniaRepository extends JpaRepository<Escribania, Integer> {

    @Query(value = "CALL sp_General_Listar('escribanias')", nativeQuery = true)
    List<Escribania> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('escribanias', 'id_escribania', :id)", nativeQuery = true)
    Escribania obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('escribanias', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('escribanias', :setValores, 'id_escribania', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('escribanias', 'id_escribania', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
