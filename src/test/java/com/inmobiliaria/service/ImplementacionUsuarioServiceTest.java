package com.inmobiliaria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.inmobiliaria.dao.RelacionRolesRepository;
import com.inmobiliaria.dao.RolRepository;
import com.inmobiliaria.dao.UsuarioRepository;
import com.inmobiliaria.dto.LoginDTO;
import com.inmobiliaria.dto.LoginRespuestaDTO;
import com.inmobiliaria.dto.RegistroUsuarioDTO;
import com.inmobiliaria.exception.ConflictoException;
import com.inmobiliaria.exception.NoAutenticadoException;
import com.inmobiliaria.factory.IDaoFactory;
import com.inmobiliaria.model.RelacionRoles;
import com.inmobiliaria.model.Rol;
import com.inmobiliaria.model.Usuario;
import com.inmobiliaria.security.Sesion;
import com.inmobiliaria.security.SesionService;

class ImplementacionUsuarioServiceTest {

    private UsuarioRepository usuarioDao;
    private RolRepository rolDao;
    private RelacionRolesRepository relacionDao;
    private SesionService sesiones;
    private ImplementacionUsuarioService service;

    @BeforeEach
    void setUp() {
        IDaoFactory factory = mock(IDaoFactory.class);
        usuarioDao = mock(UsuarioRepository.class);
        rolDao = mock(RolRepository.class);
        relacionDao = mock(RelacionRolesRepository.class);
        sesiones = new SesionService();
        when(factory.getUsuarioDao()).thenReturn(usuarioDao);
        when(factory.getRolDao()).thenReturn(rolDao);
        when(factory.getRelacionRolesDao()).thenReturn(relacionDao);
        service = new ImplementacionUsuarioService(factory, sesiones);
    }

    private RegistroUsuarioDTO registroValido() {
        RegistroUsuarioDTO d = new RegistroUsuarioDTO();
        d.setNombre("Ana");
        d.setApellido("Pérez");
        d.setTag("anap");
        d.setCorreo("ana@mail.com");
        d.setUpassword("secreto1");
        d.setDni("30111222");
        d.setTelefono("1155667788");
        d.setFechaNacimiento("1990-05-20");
        return d;
    }

    private Usuario usuario(int id, String correo, String pass) {
        Usuario u = new Usuario(id, "Ana", "Pérez", "tag" + id, correo, pass, "dni" + id);
        return u;
    }

    @Test
    void registrar_valido_usaSpAltaConRolClienteYGuardaHashBCrypt() {
        when(usuarioDao.listarTodosSP()).thenReturn(List.of());
        Rol cliente = new Rol();
        cliente.setIdRol(1);
        cliente.setNombre("Cliente");
        when(rolDao.listarTodosSP()).thenReturn(List.of(cliente));

        service.registrar(registroValido());

        ArgumentCaptor<String> pass = ArgumentCaptor.forClass(String.class);
        verify(usuarioDao).altaUsuarioConRol(eq("Ana"), eq("Pérez"), eq("anap"), eq("ana@mail.com"),
                pass.capture(), eq("30111222"), eq("1155667788"), eq(LocalDate.of(1990, 5, 20)), eq(1));
        assertNotEquals("secreto1", pass.getValue());
        assertTrue(pass.getValue().startsWith("$2"));
        assertTrue(new BCryptPasswordEncoder().matches("secreto1", pass.getValue()));
    }

    @Test
    void registrar_sinRolClienteEnLaBase_insertaUsuarioIgual() {
        when(usuarioDao.listarTodosSP()).thenReturn(List.of());
        when(rolDao.listarTodosSP()).thenReturn(List.of());

        service.registrar(registroValido());

        verify(usuarioDao).insertarSP(eq("nombre, apellido, tag, correo, upassword, dni, telefono, fecha_nacimiento"), contains("'ana@mail.com'"));
        verify(usuarioDao, never()).altaUsuarioConRol(any(), any(), any(), any(), any(), any(), any(), any(), anyInt());
    }

    @Test
    void registrar_correoDuplicado_lanzaConflicto() {
        when(usuarioDao.listarTodosSP()).thenReturn(List.of(usuario(1, "ANA@mail.com", "x")));
        assertThrows(ConflictoException.class, () -> service.registrar(registroValido()));
        verify(usuarioDao, never()).insertarSP(anyString(), anyString());
    }

    @Test
    void registrar_correoInvalido_lanzaIllegalArgument() {
        RegistroUsuarioDTO d = registroValido();
        d.setCorreo("no-es-un-mail");
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
    }

    @Test
    void registrar_passwordCorta_lanzaIllegalArgument() {
        RegistroUsuarioDTO d = registroValido();
        d.setUpassword("123");
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
    }

    @Test
    void registrar_conInyeccionEnNombre_escapaLaComilla_cuandoNoHayRol() {
        when(usuarioDao.listarTodosSP()).thenReturn(List.of());
        RegistroUsuarioDTO d = registroValido();
        d.setNombre("Ana'); DROP TABLE usuarios;--");

        service.registrar(d);

        verify(usuarioDao).insertarSP(anyString(), contains("'Ana''); DROP TABLE usuarios;--'"));
    }

    @Test
    void registrar_dniConLetras_lanzaIllegalArgument() {
        RegistroUsuarioDTO d = registroValido();
        d.setDni("3011122A");
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
    }

    @Test
    void registrar_dniCon7Digitos_lanzaIllegalArgument() {
        RegistroUsuarioDTO d = registroValido();
        d.setDni("3011122");
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
    }

    @Test
    void registrar_dniCon9Digitos_lanzaIllegalArgument() {
        RegistroUsuarioDTO d = registroValido();
        d.setDni("301112222");
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
    }

    @Test
    void registrar_telefonoConLetras_lanzaIllegalArgument() {
        RegistroUsuarioDTO d = registroValido();
        d.setTelefono("11556abc78");
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
    }

    @Test
    void registrar_menorDe18_lanzaIllegalArgument() {
        RegistroUsuarioDTO d = registroValido();
        d.setFechaNacimiento(LocalDate.now().minusYears(17).toString());
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
    }

    @Test
    void registrar_fechaFutura_lanzaIllegalArgument() {
        RegistroUsuarioDTO d = registroValido();
        d.setFechaNacimiento(LocalDate.now().plusDays(1).toString());
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
    }

    @Test
    void registrar_fechaConFormatoInvalido_lanzaIllegalArgument() {
        RegistroUsuarioDTO d = registroValido();
        d.setFechaNacimiento("20/05/1990");
        assertThrows(IllegalArgumentException.class, () -> service.registrar(d));
    }

    @Test
    void login_correcto_devuelveTokenYRoles() {
        Usuario u = usuario(1, "ana@mail.com", "secreto1");
        when(usuarioDao.listarTodosSP()).thenReturn(List.of(u));
        Rol admin = new Rol();
        admin.setNombre("Administrador");
        RelacionRoles rr = new RelacionRoles();
        rr.setUsuario(u);
        rr.setRol(admin);
        when(relacionDao.listarTodosSP()).thenReturn(List.of(rr));
        LoginDTO l = new LoginDTO();
        l.setCorreo("ANA@mail.com");
        l.setUpassword("secreto1");

        LoginRespuestaDTO r = service.login(l);

        assertNotNull(r.getToken());
        assertEquals(List.of("Administrador"), r.getRoles());
        assertEquals("ana@mail.com", r.getUsuario().getCorreo());
        Sesion s = sesiones.obtener(r.getToken());
        assertNotNull(s);
        assertTrue(s.tieneAlgunRol(new String[] {"Administrador"}));
    }

    @Test
    void login_conPasswordHasheadaConBCrypt_funciona() {
        String hash = new BCryptPasswordEncoder().encode("miClave1");
        when(usuarioDao.listarTodosSP()).thenReturn(List.of(usuario(2, "bcrypt@mail.com", hash)));
        LoginDTO l = new LoginDTO();
        l.setCorreo("bcrypt@mail.com");
        l.setUpassword("miClave1");
        assertNotNull(service.login(l).getToken());

        l.setUpassword("otraClave");
        assertThrows(NoAutenticadoException.class, () -> service.login(l));
    }

    @Test
    void login_passwordIncorrecta_lanzaNoAutenticado() {
        when(usuarioDao.listarTodosSP()).thenReturn(List.of(usuario(1, "ana@mail.com", "secreto1")));
        LoginDTO l = new LoginDTO();
        l.setCorreo("ana@mail.com");
        l.setUpassword("otra");
        assertThrows(NoAutenticadoException.class, () -> service.login(l));
    }

    @Test
    void login_correoInexistente_lanzaNoAutenticado() {
        when(usuarioDao.listarTodosSP()).thenReturn(List.of());
        LoginDTO l = new LoginDTO();
        l.setCorreo("nadie@mail.com");
        l.setUpassword("x");
        assertThrows(NoAutenticadoException.class, () -> service.login(l));
    }

    @Test
    void login_sinDatos_lanzaIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> service.login(new LoginDTO()));
    }

    @Test
    void logout_invalidaElToken() {
        String token = sesiones.crear(1, "a@a.com", List.of("Cliente"));
        service.logout(token);
        assertNull(sesiones.obtener(token));
    }
}
