package com.remax.dao;

import com.remax.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "CALL sp_ObtenerTodos('Usuarios')", nativeQuery = true)
    List<Usuario> obtenerTodosSP();

    @Query(value = "CALL sp_ObtenerPorId('Usuarios', 'id_usuario', :id)", nativeQuery = true)
    Usuario obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_EliminarPorId('Usuarios', 'id_usuario', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_ContarRegistros('Usuarios')", nativeQuery = true)
    Long contarRegistrosSP();
}
