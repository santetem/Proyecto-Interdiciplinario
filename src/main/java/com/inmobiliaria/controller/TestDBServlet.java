package com.inmobiliaria.controller;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
@WebServlet("/testdb")
public class TestDBServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
IOException {
	Connection conn = com.inmobiliaria.config.Conexion.getInstance().getConnection();if (conn != null) {
resp.getWriter().println("¡Conexión con la DB exitosa!");
} else {
resp.getWriter().println("No se pudo conectar con la DB.");
}
}
}