package com.inmobiliaria.dao;

import java.util.List;

public interface IGenericDao<T, ID> {
    List<T> listarTodosSP();
    T buscarPorIdSP(ID id);
    void insertarSP(String columnas, String valores);
    void actualizarSP(String setValores, ID id);
    void eliminarPorIdSP(ID id);
}