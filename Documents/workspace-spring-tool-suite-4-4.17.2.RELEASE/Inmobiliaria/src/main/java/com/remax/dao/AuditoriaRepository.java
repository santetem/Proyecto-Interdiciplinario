package com.remax.dao;

import com.remax.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Auditorias')", nativeQuery = true)
    List<Auditoria> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Auditorias', 'id_auditoria', :id)", nativeQuery = true)
    Auditoria obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Auditorias', 'id_auditoria', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Auditorias')", nativeQuery = true)
    Long contarRegistrosSP();
}
