package com.inmobiliaria.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.inmobiliaria.dao.RelacionRolesRepository;
import com.inmobiliaria.dao.RolRepository;
import com.inmobiliaria.dao.UsuarioRepository;
import com.inmobiliaria.dto.LoginDTO;
import com.inmobiliaria.dto.LoginRespuestaDTO;
import com.inmobiliaria.dto.RegistroUsuarioDTO;
import com.inmobiliaria.dto.UsuarioDTO;
import com.inmobiliaria.exception.ConflictoException;
import com.inmobiliaria.exception.NoAutenticadoException;
import com.inmobiliaria.exception.RecursoNoEncontradoException;
import com.inmobiliaria.factory.IDaoFactory;
import com.inmobiliaria.model.RelacionRoles;
import com.inmobiliaria.model.Rol;
import com.inmobiliaria.model.Usuario;
import com.inmobiliaria.security.Roles;
import com.inmobiliaria.security.SesionService;
import com.inmobiliaria.util.SqlUtil;

/** Registro, login (con token y roles) y consulta de usuarios. */
@Service
public class ImplementacionUsuarioService implements IUsuarioService {

    /** Rol que se asigna a todo usuario que se registra desde el frontend. */
    public static final String ROL_POR_DEFECTO = Roles.CLIENTE;

    private final UsuarioRepository usuarioDao;
    private final RolRepository rolDao;
    private final RelacionRolesRepository relacionRolesDao;
    private final SesionService sesionService;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public ImplementacionUsuarioService(IDaoFactory factory, SesionService sesionService) {
        this.usuarioDao = factory.getUsuarioDao();
        this.rolDao = factory.getRolDao();
        this.relacionRolesDao = factory.getRelacionRolesDao();
        this.sesionService = sesionService;
    }

    @Override
    public void registrar(RegistroUsuarioDTO d) {
        validarRegistro(d);
        for (Usuario existente : usuarioDao.listarTodosSP()) {
            if (d.getCorreo().equalsIgnoreCase(existente.getCorreo())) {
                throw new ConflictoException("Ya existe un usuario con ese correo");
            }
            if (d.getTag().equals(existente.getTag())) {
                throw new ConflictoException("Ya existe un usuario con ese tag");
            }
            if (d.getDni().equals(existente.getDni())) {
                throw new ConflictoException("Ya existe un usuario con ese DNI");
            }
        }
        String hash = encoder.encode(d.getUpassword()); // la contraseña nunca se guarda en texto plano
        Integer idRol = buscarIdRolPorDefecto();
        if (idRol != null) {
            // sp_AltaUsuarioConRol: crea usuario + rol en una sola transacción
            usuarioDao.altaUsuarioConRol(d.getNombre().trim(), d.getApellido().trim(), d.getTag().trim(),
                    d.getCorreo().trim(), hash, d.getDni().trim(), idRol);
        } else {
            String columnas = "nombre, apellido, tag, correo, upassword, dni";
            String valores = String.join(", ",
                    SqlUtil.texto(d.getNombre().trim()),
                    SqlUtil.texto(d.getApellido().trim()),
                    SqlUtil.texto(d.getTag().trim()),
                    SqlUtil.texto(d.getCorreo().trim()),
                    SqlUtil.texto(hash),
                    SqlUtil.texto(d.getDni().trim()));
            usuarioDao.insertarSP(columnas, valores);
        }
    }

    @Override
    public LoginRespuestaDTO login(LoginDTO d) {
        if (d == null || vacio(d.getCorreo()) || vacio(d.getUpassword())) {
            throw new IllegalArgumentException("Correo y contraseña son obligatorios");
        }
        Usuario encontrado = null;
        for (Usuario u : usuarioDao.listarTodosSP()) {
            if (d.getCorreo().trim().equalsIgnoreCase(u.getCorreo())) {
                encontrado = u;
                break;
            }
        }
        if (encontrado == null || !iguales(d.getUpassword(), encontrado.getUpassword())) {
            // mismo mensaje para ambos casos: no revela si el correo existe
            throw new NoAutenticadoException("Correo o contraseña incorrectos");
        }
        List<String> roles = rolesDe(encontrado.getIdUsuario());
        String token = sesionService.crear(encontrado.getIdUsuario(), encontrado.getCorreo(), roles);
        return new LoginRespuestaDTO("¡Login exitoso! Bienvenido " + encontrado.getNombre(),
                token, UsuarioDTO.desde(encontrado), roles);
    }

    @Override
    public void logout(String token) {
        sesionService.cerrar(token);
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        List<UsuarioDTO> resultado = new ArrayList<>();
        for (Usuario u : usuarioDao.listarTodosSP()) {
            resultado.add(UsuarioDTO.desde(u));
        }
        return resultado;
    }

    @Override
    public UsuarioDTO buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id del usuario debe ser mayor a 0");
        }
        Usuario u = usuarioDao.buscarPorIdSP(id);
        if (u == null) {
            throw new RecursoNoEncontradoException("No existe el usuario con id " + id);
        }
        return UsuarioDTO.desde(u);
    }

    // ---------- métodos auxiliares ----------

    private List<String> rolesDe(Integer idUsuario) {
        List<String> roles = new ArrayList<>();
        for (RelacionRoles rr : relacionRolesDao.listarTodosSP()) {
            if (rr.getUsuario() != null && rr.getRol() != null
                    && idUsuario.equals(rr.getUsuario().getIdUsuario())) {
                roles.add(rr.getRol().getNombre());
            }
        }
        return roles;
    }

    /** Busca en la tabla roles el id del rol "Cliente" (null si no existe). */
    private Integer buscarIdRolPorDefecto() {
        for (Rol r : rolDao.listarTodosSP()) {
            if (ROL_POR_DEFECTO.equalsIgnoreCase(r.getNombre())) {
                return r.getIdRol();
            }
        }
        return null;
    }

    private void validarRegistro(RegistroUsuarioDTO d) {
        if (d == null) {
            throw new IllegalArgumentException("Los datos de registro son obligatorios");
        }
        if (vacio(d.getNombre()) || vacio(d.getApellido()) || vacio(d.getTag())
                || vacio(d.getCorreo()) || vacio(d.getUpassword()) || vacio(d.getDni())) {
            throw new IllegalArgumentException("Nombre, apellido, tag, correo, contraseña y DNI son obligatorios");
        }
        if (!d.getCorreo().trim().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("El correo no tiene un formato válido");
        }
        if (d.getUpassword().length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }
    }

    private boolean vacio(String s) {
        return s == null || s.trim().isEmpty();
    }

    /**
     * Compara la contraseña ingresada con la guardada.
     * Las contraseñas nuevas están hasheadas con BCrypt ($2a$...); las de los datos de prueba
     * viejos de la base (ej. "123456") están en texto plano, por eso se aceptan ambas.
     */
    private boolean iguales(String ingresada, String guardada) {
        if (guardada == null) {
            return false;
        }
        if (guardada.startsWith("$2a$") || guardada.startsWith("$2b$") || guardada.startsWith("$2y$")) {
            return encoder.matches(ingresada, guardada);
        }
        return MessageDigest.isEqual(ingresada.getBytes(StandardCharsets.UTF_8), guardada.getBytes(StandardCharsets.UTF_8));
    }
}
