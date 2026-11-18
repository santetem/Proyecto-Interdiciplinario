package com.inmobiliaria.service;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import com.inmobiliaria.dao.CaptacionRepository;
import com.inmobiliaria.dao.InmuebleRepository;
import com.inmobiliaria.dao.MultimediaRepository;
import com.inmobiliaria.dao.UsuarioRepository;
import com.inmobiliaria.dto.CaptacionDTO;
import com.inmobiliaria.exception.ConflictoException;
import com.inmobiliaria.exception.RecursoNoEncontradoException;
import com.inmobiliaria.factory.IDaoFactory;
import com.inmobiliaria.model.Captacion;
import com.inmobiliaria.model.Inmueble;
import com.inmobiliaria.util.SqlUtil;

/**
 * Captación = solicitud del propietario. Nace "Pendiente" y SIN operador:
 * un operador la toma después. Si la acepta, el stored procedure sp_AceptarCaptacion
 * (atómico) la pasa a "Activa", crea el Inmueble y lo publica. Si la rechaza, queda "Rechazada"
 * con el motivo, así el cliente puede verlo en su pantalla.
 */
@Service
public class ImplementacionCaptacionService implements ICaptacionService {

    public static final String ESTADO_INICIAL = "Pendiente";
    public static final String ESTADO_RECHAZADA = "Rechazada";

    private static final Pattern NUMERO = Pattern.compile("\\d+(?:[.,]\\d+)?");
    private static final BigDecimal MAX_SUPERFICIE = new BigDecimal("99999999");
    private static final int MAX_IMAGENES = 5;

    private final CaptacionRepository captacionDao;
    private final UsuarioRepository usuarioDao;
    private final InmuebleRepository inmuebleDao;
    private final MultimediaRepository multimediaDao;

    public ImplementacionCaptacionService(IDaoFactory factory) {
        this.captacionDao = factory.getCaptacionDao();
        this.usuarioDao = factory.getUsuarioDao();
        this.inmuebleDao = factory.getInmuebleDao();
        this.multimediaDao = factory.getMultimediaDao();
    }

    @Override
    public void registrar(int idUsuario, CaptacionDTO d) {
        if (d == null) {
            throw new IllegalArgumentException("Los datos de la captación son obligatorios");
        }
        exigirTexto(d.getCaracteristicas(), "caracteristicas");
        if (d.getCaracteristicas().length() > 5000) {
            throw new IllegalArgumentException("El campo caracteristicas es demasiado largo");
        }
        leerImagenes(parsear(d.getCaracteristicas())); // rechaza URLs inválidas antes de guardar nada
        if (usuarioDao.buscarPorIdSP(idUsuario) == null) {
            throw new RecursoNoEncontradoException("No existe el usuario con id " + idUsuario);
        }
        // id_operador se omite a propósito: queda NULL hasta que un operador la tome
        String columnas = "id_propietario, caracteristicas, fecha, estado";
        String valores = String.join(", ",
                SqlUtil.numero(idUsuario),
                SqlUtil.texto(d.getCaracteristicas().trim()),
                SqlUtil.fecha(LocalDate.now()),
                SqlUtil.texto(ESTADO_INICIAL));
        captacionDao.insertarSP(columnas, valores);
    }

    @Override
    public List<CaptacionDTO> listar(String estado) {
        List<CaptacionDTO> resultado = new ArrayList<>();
        for (Captacion c : captacionDao.listarTodosSP()) {
            if (estado == null || estado.isBlank() || estado.equalsIgnoreCase(c.getEstado())) {
                resultado.add(CaptacionDTO.desde(c));
            }
        }
        resultado.sort(Comparator.comparing(CaptacionDTO::getIdCaptacion).reversed());
        return resultado;
    }

    @Override
    public List<CaptacionDTO> listarPorUsuario(int idUsuario) {
        List<CaptacionDTO> resultado = new ArrayList<>();
        for (Captacion c : captacionDao.listarTodosSP()) {
            if (c.getPropietario() != null && c.getPropietario().getIdUsuario() == idUsuario) {
                resultado.add(CaptacionDTO.desde(c));
            }
        }
        resultado.sort(Comparator.comparing(CaptacionDTO::getIdCaptacion).reversed());
        return resultado;
    }

    @Override
    public void aceptar(int idCaptacion, int idOperador, String observaciones) {
        Captacion c = obtenerPendiente(idCaptacion);
        validarObservaciones(observaciones);

        // El formulario del cliente empaqueta todo en el texto "caracteristicas": lo desarmamos acá.
        Map<String, String> datos = parsear(c.getCaracteristicas());

        String direccionYLocalidad = datos.get("direccion");
        exigirTexto(direccionYLocalidad, "dirección (en las características de la captación)");
        int coma = direccionYLocalidad.lastIndexOf(',');
        String direccion = coma > 0 ? direccionYLocalidad.substring(0, coma).trim() : direccionYLocalidad.trim();
        String localidad = coma > 0 ? direccionYLocalidad.substring(coma + 1).trim() : "";
        if (direccion.isEmpty() || localidad.isEmpty()) {
            throw new IllegalArgumentException("La captación debe tener dirección y localidad (formato \"calle, localidad\")");
        }
        String tipo = datos.get("tipo");
        exigirTexto(tipo, "tipo de inmueble (en las características de la captación)");

        BigDecimal cubierta = decimal(datos.get("sup. cubierta"));
        BigDecimal total = decimal(datos.get("sup. total"));
        validarSuperficie(cubierta, "superficie cubierta");
        validarSuperficie(total, "superficie total");
        if (cubierta != null && total != null && cubierta.compareTo(total) > 0) {
            throw new IllegalArgumentException("La superficie cubierta no puede ser mayor que la superficie total");
        }

        List<String> imagenes = leerImagenes(datos); // se validan ANTES de aceptar

        captacionDao.aceptarCaptacionSP(idCaptacion, idOperador,
                recortar(direccion, 255), recortar(tipo, 50), cubierta, total,
                entero(datos.get("habitaciones")), entero(datos.get("banos")), entero(datos.get("cocheras")),
                recortar(datos.get("antiguedad/estado"), 100), recortar(localidad, 100),
                limpiar(observaciones));

        // El SP ya creó el inmueble: ahora se guardan sus fotos en la tabla Multimedia
        guardarImagenes(idCaptacion, imagenes);
    }

    @Override
    public void rechazar(int idCaptacion, int idOperador, String motivo) {
        obtenerPendiente(idCaptacion);
        exigirTexto(motivo, "motivo del rechazo");
        validarObservaciones(motivo);
        String set = String.join(", ",
                "estado = " + SqlUtil.texto(ESTADO_RECHAZADA),
                "id_operador = " + SqlUtil.numero(idOperador),
                "observaciones = " + SqlUtil.texto(motivo.trim()));
        captacionDao.actualizarSP(set, idCaptacion);
    }

    // ---------- auxiliares ----------

    private Captacion obtenerPendiente(int id) {
        Captacion c = captacionDao.buscarPorIdSP(id);
        if (c == null) {
            throw new RecursoNoEncontradoException("No existe la captación con id " + id);
        }
        if (!ESTADO_INICIAL.equalsIgnoreCase(c.getEstado())) {
            throw new ConflictoException("La captación ya fue procesada (estado: " + c.getEstado() + ")");
        }
        return c;
    }

    /**
     * Convierte el texto del formulario ("Clave: valor", una por línea; algunas líneas traen varias
     * separadas por " | ") en un mapa clave -> valor. Las claves se pasan a minúsculas y sin tildes.
     * Si una clave se repite (ej. alguien escribió "Tipo:" en los comentarios) gana la primera.
     */
    static Map<String, String> parsear(String texto) {
        Map<String, String> datos = new HashMap<>();
        if (texto == null) {
            return datos;
        }
        for (String linea : texto.split("\\r?\\n")) {
            for (String trozo : linea.split("\\s\\|\\s")) {
                int i = trozo.indexOf(':');
                if (i > 0) {
                    datos.putIfAbsent(normalizar(trozo.substring(0, i)), trozo.substring(i + 1).trim());
                }
            }
        }
        return datos;
    }

    private static String normalizar(String s) {
        return Normalizer.normalize(s.trim(), Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase();
    }

    private static BigDecimal decimal(String s) {
        if (s == null) {
            return null;
        }
        Matcher m = NUMERO.matcher(s);
        return m.find() ? new BigDecimal(m.group().replace(',', '.')) : null;
    }

    private static int entero(String s) {
        BigDecimal d = decimal(s);
        return d == null ? 0 : d.intValue();
    }

    private static String recortar(String s, int max) {
        if (s == null) {
            return null;
        }
        String t = s.trim();
        return t.length() > max ? t.substring(0, max) : t;
    }

    private static String limpiar(String s) {
        return s == null || s.trim().isEmpty() ? null : s.trim();
    }

    /** Saca del texto de la captación la línea "Imagenes: url1, url2" y valida cada URL. */
    private List<String> leerImagenes(Map<String, String> datos) {
        List<String> urls = new ArrayList<>();
        String texto = datos.get("imagenes");
        if (texto == null || texto.isBlank()) {
            return urls;
        }
        for (String parte : texto.split(",")) {
            String url = parte.trim();
            if (url.isEmpty()) {
                continue;
            }
            if (!AlmacenImagenes.esUrlValida(url)) {
                throw new IllegalArgumentException("Una de las imágenes de la captación no es válida");
            }
            urls.add(url);
        }
        if (urls.size() > MAX_IMAGENES) {
            throw new IllegalArgumentException("Se permiten hasta " + MAX_IMAGENES + " imágenes por captación");
        }
        return urls;
    }

    /** Busca el inmueble que acaba de crear el SP (por id_captacion) y le guarda cada foto en Multimedia. */
    private void guardarImagenes(int idCaptacion, List<String> urls) {
        if (urls.isEmpty()) {
            return;
        }
        Integer idInmueble = null;
        for (Inmueble i : inmuebleDao.listarTodosSP()) {
            if (i.getCaptacion() != null && i.getCaptacion().getIdCaptacion() == idCaptacion) {
                idInmueble = i.getIdInmueble();
                break;
            }
        }
        if (idInmueble == null) {
            throw new RecursoNoEncontradoException("No se encontró el inmueble creado para la captación " + idCaptacion);
        }
        for (String url : urls) {
            multimediaDao.insertarSP("id_inmueble, tipo, url",
                    String.join(", ", SqlUtil.numero(idInmueble), SqlUtil.texto("imagen"), SqlUtil.texto(url)));
        }
    }

    private void validarSuperficie(BigDecimal valor, String campo) {
        if (valor != null && valor.compareTo(MAX_SUPERFICIE) > 0) {
            throw new IllegalArgumentException("La " + campo + " es demasiado grande");
        }
    }

    private void validarObservaciones(String s) {
        if (s != null && s.trim().length() > 255) {
            throw new IllegalArgumentException("Las observaciones no pueden superar los 255 caracteres");
        }
    }

    private void exigirTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio");
        }
    }
}
