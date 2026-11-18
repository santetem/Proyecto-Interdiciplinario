package com.inmobiliaria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.inmobiliaria.security.AuthInterceptor;
import com.inmobiliaria.service.AlmacenImagenes;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final AlmacenImagenes almacenImagenes;

    public WebConfig(AuthInterceptor authInterceptor, AlmacenImagenes almacenImagenes) {
        this.authInterceptor = authInterceptor;
        this.almacenImagenes = almacenImagenes;
    }

    /**
     * Las imágenes subidas viven en una carpeta del disco, fuera de "static".
     * Este método le dice a Spring: "lo que pidan en /uploads/... buscalo en esa carpeta".
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String ubicacion = almacenImagenes.getDirectorio().toUri().toString(); // file:///C:/.../uploads
        if (!ubicacion.endsWith("/")) {
            ubicacion = ubicacion + "/"; // Spring exige la barra final
        }
        registry.addResourceHandler("/uploads/**").addResourceLocations(ubicacion);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
