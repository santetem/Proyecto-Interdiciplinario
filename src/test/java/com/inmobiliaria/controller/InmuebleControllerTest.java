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
import com.inmobiliaria.dto.InmuebleDTO;
import com.inmobiliaria.exception.GlobalExceptionHandler;
import com.inmobiliaria.exception.RecursoNoEncontradoException;
import com.inmobiliaria.security.AuthInterceptor;
import com.inmobiliaria.security.SesionService;
import com.inmobiliaria.service.IInmuebleService;

/**
 * Prueba el Controller REAL con MockMvc (sin levantar Spring ni MySQL):
 * códigos HTTP + control de roles (AuthInterceptor) + manejo de errores.
 */
class InmuebleControllerTest {

    private static final String JSON = "{\"direccion\":\"Calle 1\",\"tipoInmueble\":\"Casa\",\"localidad\":\"Rosario\",\"idPropietario\":1,\"idCaptacion\":2}";

    private IInmuebleService service;
    private SesionService sesiones;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        service = mock(IInmuebleService.class);
        sesiones = new SesionService();
        mvc = MockMvcBuilders.standaloneSetup(new InmuebleController(service))
                .setControllerAdvice(new GlobalExceptionHandler())
                .addInterceptors(new AuthInterceptor(sesiones))
                .build();
    }

    private String bearer(String... roles) {
        return "Bearer " + sesiones.crear(1, "a@a.com", List.of(roles));
    }

    @Test
    void get_listado_esPublico_y_devuelve200() throws Exception {
        when(service.listarTodo()).thenReturn(List.of(new InmuebleDTO()));
        mvc.perform(get("/inmuebles")).andExpect(status().isOk());
    }

    @Test
    void get_porId_noExiste_devuelve404ConMensaje() throws Exception {
        when(service.buscarPorId(99)).thenThrow(new RecursoNoEncontradoException("No existe el inmueble con id 99"));
        mvc.perform(get("/inmuebles/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No existe el inmueble con id 99"));
    }

    @Test
    void post_sinToken_devuelve401() throws Exception {
        mvc.perform(post("/inmuebles").contentType(MediaType.APPLICATION_JSON).content(JSON))
                .andExpect(status().isUnauthorized());
        verifyNoInteractions(service);
    }

    @Test
    void post_conRolCliente_devuelve403() throws Exception {
        mvc.perform(post("/inmuebles").header("Authorization", bearer("Cliente"))
                .contentType(MediaType.APPLICATION_JSON).content(JSON))
                .andExpect(status().isForbidden());
        verifyNoInteractions(service);
    }

    @Test
    void post_conRolAgente_devuelve201() throws Exception {
        mvc.perform(post("/inmuebles").header("Authorization", bearer("Agente"))
                .contentType(MediaType.APPLICATION_JSON).content(JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensaje").exists());
        verify(service).registrar(any(InmuebleDTO.class));
    }

    @Test
    void post_datosInvalidos_devuelve400() throws Exception {
        doThrow(new IllegalArgumentException("El campo direccion es obligatorio")).when(service).registrar(any());
        mvc.perform(post("/inmuebles").header("Authorization", bearer("Administrador"))
                .contentType(MediaType.APPLICATION_JSON).content(JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("El campo direccion es obligatorio"));
    }

    @Test
    void post_jsonMalFormado_devuelve400() throws Exception {
        mvc.perform(post("/inmuebles").header("Authorization", bearer("Administrador"))
                .contentType(MediaType.APPLICATION_JSON).content("{no es json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void patch_conRolAgente_devuelve200() throws Exception {
        mvc.perform(patch("/inmuebles/5").header("Authorization", bearer("Agente"))
                .contentType(MediaType.APPLICATION_JSON).content("{\"localidad\":\"Funes\"}"))
                .andExpect(status().isOk());
        verify(service).actualizarParcial(eq(5), any(InmuebleDTO.class));
    }

    @Test
    void delete_conRolAgente_devuelve403_soloAdminPuedeEliminar() throws Exception {
        mvc.perform(delete("/inmuebles/5").header("Authorization", bearer("Agente")))
                .andExpect(status().isForbidden());
        verify(service, never()).eliminar(anyInt());
    }

    @Test
    void delete_conRolAdmin_devuelve200() throws Exception {
        mvc.perform(delete("/inmuebles/5").header("Authorization", bearer("Administrador")))
                .andExpect(status().isOk());
        verify(service).eliminar(5);
    }

    @Test
    void tokenInventado_devuelve401() throws Exception {
        mvc.perform(delete("/inmuebles/5").header("Authorization", "Bearer token-falso"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void idNoNumerico_devuelve400() throws Exception {
        mvc.perform(get("/inmuebles/abc")).andExpect(status().isBadRequest());
    }
}
