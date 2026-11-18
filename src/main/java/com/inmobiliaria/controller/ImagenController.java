package com.inmobiliaria.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.inmobiliaria.security.RequiereRol;
import com.inmobiliaria.service.AlmacenImagenes;

/** Recibe UNA imagen por petición y devuelve la URL con la que después se muestra. */
@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {

    private final AlmacenImagenes almacen;

    public ImagenController(AlmacenImagenes almacen) {
        this.almacen = almacen;
    }

    // consumes: este endpoint solo acepta formularios con archivos (multipart/form-data)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RequiereRol // hace falta haber iniciado sesión (token)
    public ResponseEntity<Map<String, String>> subir(@RequestParam("archivo") MultipartFile archivo) {
        String url = almacen.guardar(archivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("url", url));
    }
}
