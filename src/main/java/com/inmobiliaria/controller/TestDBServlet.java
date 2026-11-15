package com.inmobiliaria.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import com.inmobiliaria.config.Conexion;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** Servlet de diagnóstico: GET /testdb comprueba que la base de datos responde. */
@WebServlet("/testdb")
public class TestDBServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection conn = Conexion.getInstancia().getConexion()) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("¡Conexión con la DB exitosa!");
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            resp.getWriter().println("No se pudo conectar con la DB.");
        }
    }
}
