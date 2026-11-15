package com.inmobiliaria.security;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.inmobiliaria.exception.AccesoDenegadoException;
import com.inmobiliaria.exception.NoAutenticadoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Se ejecuta ANTES de cada Controller. Si el método (o la clase) tiene @RequiereRol:
 *   - sin token válido            -> 401 Unauthorized
 *   - token válido pero sin rol   -> 403 Forbidden
 * Si todo está bien deja la sesión en el request para que el Controller la use.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    public static final String ATRIBUTO_SESION = "sesionActual";

    private final SesionService sesionService;

    public AuthInterceptor(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true; // recursos estáticos, etc.
        }
        HandlerMethod metodo = (HandlerMethod) handler;
        RequiereRol regla = metodo.getMethodAnnotation(RequiereRol.class);
        if (regla == null) {
            regla = metodo.getBeanType().getAnnotation(RequiereRol.class);
        }
        if (regla == null) {
            return true; // endpoint público
        }

        Sesion sesion = sesionService.obtener(extraerToken(request));
        if (sesion == null) {
            throw new NoAutenticadoException("Debe iniciar sesión (token ausente, inválido o vencido)");
        }
        if (regla.value().length > 0 && !sesion.tieneAlgunRol(regla.value())) {
            throw new AccesoDenegadoException("Su rol no tiene permiso para esta operación");
        }
        request.setAttribute(ATRIBUTO_SESION, sesion);
        return true;
    }

    static String extraerToken(HttpServletRequest request) {
        String cabecera = request.getHeader("Authorization");
        if (cabecera != null && cabecera.startsWith("Bearer ")) {
            return cabecera.substring(7).trim();
        }
        return null;
    }
}
