package com.inmobiliaria.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.inmobiliaria.dto.LoginDTO;
import com.inmobiliaria.dto.LoginRespuestaDTO;
import com.inmobiliaria.dto.UsuarioDTO;
import com.inmobiliaria.exception.ConflictoException;
import com.inmobiliaria.exception.GlobalExceptionHandler;
import com.inmobiliaria.exception.NoAutenticadoException;
import com.inmobiliaria.security.AuthInterceptor;
import com.inmobiliaria.security.SesionService;
import com.inmobiliaria.service.IUsuarioService;

class UsuarioControllerTest {

    private IUsuarioService service;
    private SesionService sesiones;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        service = mock(IUsuarioService.class);
        sesiones = new SesionService();
        mvc = MockMvcBuilders.standaloneSetup(new UsuarioController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .addInterceptors(new AuthInterceptor(sesiones))
                .build();
    }

    @Test
    void register_ok_devuelve201() throws Exception {
        mvc.perform(post("/api/usuarios/register").contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Ana\",\"apellido\":\"P\",\"tag\":\"a\",\"correo\":\"a@a.com\",\"upassword\":\"123456\",\"dni\":\"1\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void register_correoDuplicado_devuelve409() throws Exception {
        doThrow(new ConflictoException("Ya existe un usuario con ese correo")).when(service).registrar(any());
        mvc.perform(post("/api/usuarios/register").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("Ya existe un usuario con ese correo"));
    }

    @Test
    void login_ok_devuelve200ConToken() throws Exception {
        when(service.login(any(LoginDTO.class))).thenReturn(
                new LoginRespuestaDTO("ok", "tok123", new UsuarioDTO(), List.of("Agente")));
        mvc.perform(post("/api/usuarios/login").contentType(MediaType.APPLICATION_JSON)
                .content("{\"correo\":\"a@a.com\",\"upassword\":\"123456\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("tok123"))
                .andExpect(jsonPath("$.roles[0]").value("Agente"));
    }

    @Test
    void login_incorrecto_devuelve401() throws Exception {
        when(service.login(any())).thenThrow(new NoAutenticadoException("Correo o contraseña incorrectos"));
        mvc.perform(post("/api/usuarios/login").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void listarUsuarios_sinToken_devuelve401() throws Exception {
        mvc.perform(get("/api/usuarios")).andExpect(status().isUnauthorized());
    }

    @Test
    void listarUsuarios_comoAgente_devuelve403() throws Exception {
        String t = sesiones.crear(1, "a@a.com", List.of("Agente"));
        mvc.perform(get("/api/usuarios").header("Authorization", "Bearer " + t)).andExpect(status().isForbidden());
    }

    @Test
    void listarUsuarios_comoAdmin_devuelve200() throws Exception {
        String t = sesiones.crear(1, "a@a.com", List.of("Administrador"));
        mvc.perform(get("/api/usuarios").header("Authorization", "Bearer " + t)).andExpect(status().isOk());
    }

    @Test
    void me_devuelveElUsuarioDeLaSesion() throws Exception {
        String t = sesiones.crear(4, "a@a.com", List.of("Cliente"));
        when(service.buscarPorId(4)).thenReturn(new UsuarioDTO());
        mvc.perform(get("/api/usuarios/me").header("Authorization", "Bearer " + t)).andExpect(status().isOk());
        verify(service).buscarPorId(4);
    }
}
