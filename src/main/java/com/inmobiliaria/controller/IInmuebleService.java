package com.inmobiliaria.controller;

import java.util.List;
import com.inmobiliaria.model.Inmueble; // Reemplaza por tu entidad real

public interface IInmuebleService {
    boolean registrar(Inmueble i);
    Inmueble buscarPorId(int id);
    List<Inmueble> listarTodo();
    boolean modificar(Inmueble i);
    boolean eliminarInmueble(int id);
    boolean actualizarPrecio(int id, double nuevoPrecio);


}
