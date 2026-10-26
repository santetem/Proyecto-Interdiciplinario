package com.inmobiliaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan; // 1. Importá esto

@SpringBootApplication
@ServletComponentScan // 2. Agregá esta anotación acá arriba
public class InmobiliariaAplicacion {
    public static void main(String[] args) {
        SpringApplication.run(InmobiliariaAplicacion.class, args);
    }
}