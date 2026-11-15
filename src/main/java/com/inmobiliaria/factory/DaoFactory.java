package com.inmobiliaria.factory;

/**
 * Punto de acceso al proveedor de DAOs.
 * Se usa solo cuando NO hay inyección de Spring (por ejemplo en una clase de prueba manual):
 *
 *     IDaoFactory f = DaoFactory.getFactory(contexto);
 *
 * Dentro de Spring, los Services reciben directamente la IDaoFactory por constructor.
 */
public class DaoFactory {

    private DaoFactory() { }

    public static IDaoFactory getFactory(org.springframework.context.ApplicationContext contexto) {
        return new MySQLDaoFactory(contexto); // Cambiar de proveedor = cambiar esta línea
    }
}
