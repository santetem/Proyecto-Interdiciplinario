package com.inmobiliaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Clase principal.
 *  - Run As > Spring Boot App (STS): usa el Tomcat 10.1 EMBEBIDO, no hace falta instalar Tomcat.
 *  - Desplegar el .war en un Tomcat 10.1 externo: funciona gracias a SpringBootServletInitializer.
 */
@SpringBootApplication
@ServletComponentScan
public class InmobiliariaAplicacion extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(InmobiliariaAplicacion.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(InmobiliariaAplicacion.class, args);
    }
}
