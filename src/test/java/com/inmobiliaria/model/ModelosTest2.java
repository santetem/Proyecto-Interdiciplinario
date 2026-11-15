package com.inmobiliaria.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

public class ModelosTest2 {

    @Test
    public void testTodasLas29ClasesModel() {
        // 1. Alquiler
        Alquiler alquiler = new Alquiler();
        alquiler.setEstado("Activo");
        alquiler.setMonto(new BigDecimal("75000.00"));
        assertEquals("Activo", alquiler.getEstado());

        // 2. Auditoria
        Auditoria auditoria = new Auditoria();
        assertNotNull(auditoria);

        // 3. Captacion
        Captacion captacion = new Captacion();
        assertNotNull(captacion);

        // 4. Caracteristica
        Caracteristica caracteristica = new Caracteristica();
        assertNotNull(caracteristica);

        // 5. Comision
        Comision comision = new Comision();
        assertNotNull(comision);

        // 6. Conversacion
        Conversacion conversacion = new Conversacion();
        assertNotNull(conversacion);

        // 7. Escribania
        Escribania escribania = new Escribania();
        assertNotNull(escribania);

        // 8. Escritura
        Escritura escritura = new Escritura();
        assertNotNull(escritura);

        // 9. Franquicia
        Franquicia franquicia = new Franquicia();
        assertNotNull(franquicia);

        // 10. HistorialOferta
        HistorialOferta historialOferta = new HistorialOferta();
        assertNotNull(historialOferta);

        // 11. Inmueble
        Inmueble inmueble = new Inmueble();
        inmueble.setDireccion("Av. Boyacá 1500");
        assertEquals("Av. Boyacá 1500", inmueble.getDireccion());

        // 12. InmuebleCaracteristicas
        InmuebleCaracteristicas inmuebleCaracteristicas = new InmuebleCaracteristicas();
        assertNotNull(inmuebleCaracteristicas);

        // 13. Mensaje
        Mensaje mensaje = new Mensaje();
        assertNotNull(mensaje);

        // 14. Multimedia
        Multimedia multimedia = new Multimedia();
        assertNotNull(multimedia);

        // 15. Oferta
        Oferta oferta = new Oferta();
        oferta.setEstado("Pendiente");
        assertEquals("Pendiente", oferta.getEstado());

        // 16. Operacion
        Operacion operacion = new Operacion();
        operacion.setTipoOperacion("VENTA");
        assertEquals("VENTA", operacion.getTipoOperacion());

        // 17. Permiso
        Permiso permiso = new Permiso();
        assertNotNull(permiso);

        // 18. Personal
        Personal personal = new Personal();
        assertNotNull(personal);

        // 19. Publicacion
        Publicacion publicacion = new Publicacion();
        assertNotNull(publicacion);

        // 20. Registrosalquiler
        Registrosalquiler registrosalquiler = new Registrosalquiler();
        assertNotNull(registrosalquiler);

        // 21. RelacionPermisos
        RelacionPermisos relacionPermisos = new RelacionPermisos();
        assertNotNull(relacionPermisos);

        // 22. RelacionRoles
        RelacionRoles relacionRoles = new RelacionRoles();
        assertNotNull(relacionRoles);

        // 23. Rol
        Rol rol = new Rol();
        assertNotNull(rol);

        // 24. SoliFranquicias
        SoliFranquicias soliFranquicias = new SoliFranquicias();
        assertNotNull(soliFranquicias);

        // 25. Tasacion
        Tasacion tasacion = new Tasacion();
        assertNotNull(tasacion);

        // 26. Tiporegistro
        Tiporegistro tiporegistro = new Tiporegistro();
        assertNotNull(tiporegistro);

        // 27. Usuario
        Usuario usuario = new Usuario();
        usuario.setCorreo("jeremias@remax.com");
        assertEquals("jeremias@remax.com", usuario.getCorreo());

        // 28. Venta 
        Venta venta = new Venta();
        venta.setIdVenta(1);
        venta.setEstado("Finalizada");
        venta.setMontoFinal(new BigDecimal("200000.00"));
        assertEquals(1, venta.getIdVenta());
        assertEquals("Finalizada", venta.getEstado());
        assertEquals(new BigDecimal("200000.00"), venta.getMontoFinal());

        // 29. Visita
        Visita visita = new Visita();
        assertNotNull(visita);
    }
}