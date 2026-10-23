package com.inmobiliaria.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // 1. Atributos estáticos para el patrón Singleton
    private static Conexion instance = null;
    private static Connection conexion = null;

    // 2. Parámetros de la base de datos (Ajusta los valores según tu configuración)
    private static final String URL = "jdbc:mysql://localhost:3306/remax";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // 3. Constructor privado para evitar instanciación externa
    private Conexion() {
        try {
            // Cargar el driver de MySQL explicitamente
            Class.forName(DRIVER);
            // Establecer la conexión física
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el Driver de MySQL. " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // 4. Método público y estático para obtener la INSTANCIA de la clase
    public static synchronized Conexion getInstance() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }

    // 5. Método de instancia para obtener el objeto Connection
    public Connection getConnection() {
        try {
            // Verificación por seguridad: si se cerró la conexión por timeout, la vuelve a abrir
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("Error al reabrir la conexión: " + e.getMessage());
        }
        return conexion;
    }

    // 6. Método opcional pero recomendado para cerrar la conexión limpiamente
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
