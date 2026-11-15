package com.inmobiliaria.security;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 * Guarda las sesiones activas: token -> Sesion.
 * El cliente manda el token en la cabecera  Authorization: Bearer <token>.
 */
@Service
public class SesionService {

    private static final long HORAS_DE_VIDA = 8;

    private final Map<String, Sesion> sesiones = new ConcurrentHashMap<>();

    public String crear(Integer idUsuario, String correo, List<String> roles) {
        String token = UUID.randomUUID().toString();
        sesiones.put(token, new Sesion(idUsuario, correo, roles, Instant.now().plusSeconds(HORAS_DE_VIDA * 3600)));
        return token;
    }

    /** Devuelve la sesión vigente o null si no existe / venció. */
    public Sesion obtener(String token) {
        if (token == null) {
            return null;
        }
        Sesion s = sesiones.get(token);
        if (s != null && s.vencida()) {
            sesiones.remove(token);
            return null;
        }
        return s;
    }

    public void cerrar(String token) {
        if (token != null) {
            sesiones.remove(token);
        }
    }
}
