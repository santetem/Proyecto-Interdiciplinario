package com.inmobiliaria.dao;

import com.inmobiliaria.model.HistorialOferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistorialOfertaRepository extends JpaRepository<HistorialOferta, Integer> {

    @Query(value = "CALL sp_General_Listar('historial_oferta')", nativeQuery = true)
    List<HistorialOferta> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('historial_oferta', 'id_histoferta', :id)", nativeQuery = true)
    HistorialOferta obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('historial_oferta', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('historial_oferta', :setValores, 'id_histoferta', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('historial_oferta', 'id_histoferta', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
