package com.inmobiliaria.dao;

import com.inmobiliaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "CALL sp_General_Listar('usuarios')", nativeQuery = true)
    List<Usuario> obtenerTodosSP();

    @Query(value = "CALL sp_General_BuscarPorId('usuarios', 'id_usuario', :id)", nativeQuery = true)
    Usuario obtenerPorIdSP(@Param("id") Integer id);

    @Query(value = "CALL sp_General_Insertar('usuarios', :columnas, :valores)", nativeQuery = true)
    void insertarSP(@Param("columnas") String columnas, @Param("valores") String valores);

    @Query(value = "CALL sp_General_Actualizar('usuarios', :setValores, 'id_usuario', :id)", nativeQuery = true)
    void actualizarSP(@Param("setValores") String setValores, @Param("id") Integer id);

    @Query(value = "CALL sp_General_Eliminar('usuarios', 'id_usuario', :id)", nativeQuery = true)
    void eliminarPorIdSP(@Param("id") Integer id);
}
