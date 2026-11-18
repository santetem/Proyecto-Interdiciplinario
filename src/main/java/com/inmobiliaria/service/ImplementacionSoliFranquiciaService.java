package com.inmobiliaria.service;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.inmobiliaria.dao.SoliFranquiciasRepository;
import com.inmobiliaria.dao.UsuarioRepository;
import com.inmobiliaria.dto.SoliFranquiciaDTO;
import com.inmobiliaria.exception.ConflictoException;
import com.inmobiliaria.exception.RecursoNoEncontradoException;
import com.inmobiliaria.factory.IDaoFactory;
import com.inmobiliaria.model.SoliFranquicias;
import com.inmobiliaria.util.SqlUtil;

/**
 * Solicitud para abrir una franquicia. Nace "Pendiente"; el sector administrativo la
 * verifica y, recién cuando la aprueba, se crea la fila de Franquicias (atómico, con el
 * stored procedure sp_AprobarSolicitudFranquicia).
 */
@Service
public class ImplementacionSoliFranquiciaService implements ISoliFranquiciaService {

    public static final String ESTADO_INICIAL = "Pendiente";
    public static final String ESTADO_RECHAZADA = "Rechazada";

    private final SoliFranquiciasRepository soliDao;
    private final UsuarioRepository usuarioDao;

    public ImplementacionSoliFranquiciaService(IDaoFactory factory) {
        this.soliDao = factory.getSoliFranquiciasDao();
        this.usuarioDao = factory.getUsuarioDao();
    }

    @Override
    public void registrar(int idUsuario, SoliFranquiciaDTO d) {
        if (d == null || d.getObservaciones() == null || d.getObservaciones().trim().isEmpty()) {
            throw new IllegalArgumentException("El campo observaciones es obligatorio (datos del local)");
        }
        if (d.getObservaciones().length() > 255) {
            throw new IllegalArgumentException("Las observaciones no pueden superar los 255 caracteres");
        }
        if (usuarioDao.buscarPorIdSP(idUsuario) == null) {
            throw new RecursoNoEncontradoException("No existe el usuario con id " + idUsuario);
        }
        String columnas = "id_usuario, fecha, estado, observaciones";
        String valores = String.join(", ",
                SqlUtil.numero(idUsuario),
                SqlUtil.fecha(LocalDate.now()),
                SqlUtil.texto(ESTADO_INICIAL),
                SqlUtil.texto(d.getObservaciones().trim()));
        soliDao.insertarSP(columnas, valores);
    }

    @Override
    public List<SoliFranquiciaDTO> listarPorUsuario(int idUsuario) {
        List<SoliFranquiciaDTO> resultado = new ArrayList<>();
        for (SoliFranquicias s : soliDao.listarTodosSP()) {
            if (s.getUsuario() != null && s.getUsuario().getIdUsuario() == idUsuario) {
                resultado.add(SoliFranquiciaDTO.desde(s));
            }
        }
        return resultado;
    }

    @Override
    public List<SoliFranquiciaDTO> listar(String estado) {
        List<SoliFranquiciaDTO> resultado = new ArrayList<>();
        for (SoliFranquicias s : soliDao.listarTodosSP()) {
            if (estado == null || estado.isBlank() || estado.equalsIgnoreCase(s.getEstado())) {
                resultado.add(SoliFranquiciaDTO.desde(s));
            }
        }
        resultado.sort(Comparator.comparing(SoliFranquiciaDTO::getIdSolicitud).reversed());
        return resultado;
    }

    @Override
    public void aprobar(int idSolicitud) {
        SoliFranquicias s = obtenerPendiente(idSolicitud);
        // El formulario guarda: "Local: ... | Dirección: ... | Teléfono: ... | Motivo: ..."
        Map<String, String> datos = parsear(s.getObservaciones());
        String nombre = datos.get("local");
        String direccion = datos.get("direccion");
        if (nombre == null || nombre.isBlank() || direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("La solicitud no tiene nombre y dirección del local (formato \"Local: ... | Dirección: ...\")");
        }
        soliDao.aprobarSolicitudSP(idSolicitud, recortar(nombre, 100), recortar(direccion, 255));
    }

    @Override
    public void rechazar(int idSolicitud) {
        obtenerPendiente(idSolicitud);
        // Solo cambia el estado: las observaciones conservan los datos que cargó el postulante.
        soliDao.actualizarSP("estado = " + SqlUtil.texto(ESTADO_RECHAZADA), idSolicitud);
    }

    // ---------- auxiliares ----------

    private SoliFranquicias obtenerPendiente(int id) {
        SoliFranquicias s = soliDao.buscarPorIdSP(id);
        if (s == null) {
            throw new RecursoNoEncontradoException("No existe la solicitud con id " + id);
        }
        if (!ESTADO_INICIAL.equalsIgnoreCase(s.getEstado())) {
            throw new ConflictoException("La solicitud ya fue procesada (estado: " + s.getEstado() + ")");
        }
        return s;
    }

    /** "Clave: valor | Clave: valor" -> mapa (claves en minúscula y sin tildes; gana la primera repetida). */
    static Map<String, String> parsear(String texto) {
        Map<String, String> datos = new HashMap<>();
        if (texto == null) {
            return datos;
        }
        for (String trozo : texto.split("\\s\\|\\s")) {
            int i = trozo.indexOf(':');
            if (i > 0) {
                String clave = Normalizer.normalize(trozo.substring(0, i).trim(), Normalizer.Form.NFD)
                        .replaceAll("\\p{M}", "").toLowerCase();
                datos.putIfAbsent(clave, trozo.substring(i + 1).trim());
            }
        }
        return datos;
    }

    private static String recortar(String s, int max) {
        String t = s.trim();
        return t.length() > max ? t.substring(0, max) : t;
    }
}
