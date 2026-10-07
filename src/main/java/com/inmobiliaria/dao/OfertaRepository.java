package com.inmobiliaria.dao;

import com.inmobiliaria.model.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {

    @Query(value = "CALL sp_General_Listar('ofertas')", nativeQuery = true)
    List<Oferta> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('ofertas', 'id_oferta', :id)", nativeQuery = true)
    Oferta obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('ofertas', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('ofertas', :setValores, 'id_oferta', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('ofertas', 'id_oferta', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
