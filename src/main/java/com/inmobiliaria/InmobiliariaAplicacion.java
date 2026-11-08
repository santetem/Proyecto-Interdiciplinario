package com.inmobiliaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan; // 1. Importá esto

@SpringBootApplication
<<<<<<< HEAD
@ServletComponentScan 
=======
@ServletComponentScan // 2. Agregá esta anotación acá arriba
>>>>>>> bf73e566c8a2e2d2c65852ba999fcf15a7fc866f
public class InmobiliariaAplicacion {
    public static void main(String[] args) {
        SpringApplication.run(InmobiliariaAplicacion.class, args);
    }
}