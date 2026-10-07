package com.inmobiliaria.controller;

import java.util.List;
import com.inmobiliaria.model.Inmueble;

public interface IInmuebleService {

    List<Inmueble> listerTodo();

    Inmueble buscarPorId(int id);

    boolean registrar(Inmueble inmueble);

    boolean modificar(Inmueble inmueble);

    boolean eliminarInmueble(int id);
}
