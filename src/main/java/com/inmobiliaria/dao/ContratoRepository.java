package com.inmobiliaria.dao;

import com.inmobiliaria.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {

    @Query(value = "CALL sp_General_Listar('contratos')", nativeQuery = true)
    List<Contrato> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('contratos', 'id_contrato', :id)", nativeQuery = true)
    Contrato obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('contratos', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('contratos', :setValores, 'id_contrato', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('contratos', 'id_contrato', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
