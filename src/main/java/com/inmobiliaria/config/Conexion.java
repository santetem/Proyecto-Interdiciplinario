package com.inmobiliaria.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Patrón SINGLETON - administrador de conexiones.
 * Hay UNA sola instancia de Conexion, pero NO guarda un Connection global:
 * cada llamada a getConexion() entrega una conexión nueva, para poder usarla
 * con try-with-resources sin cerrarle la conexión a otros.
 * (La API Spring usa su propio pool configurado en application.properties; esta clase
 * se usa para el chequeo de conexión /testdb.)
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/remax?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    private static Conexion instancia;

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL no encontrado: " + e.getMessage());
        }
    }

    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
