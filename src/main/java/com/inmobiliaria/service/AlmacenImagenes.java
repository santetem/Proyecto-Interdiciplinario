package com.inmobiliaria.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Guarda las imágenes en una carpeta del disco (NO en la base de datos).
 * En la base solo se guarda la URL, por ejemplo "/uploads/3f2a...-....jpg".
 */
@Component
public class AlmacenImagenes {

    public static final long MAX_BYTES = 5L * 1024 * 1024; // 5 MB por imagen

    // Única forma de URL que aceptamos: /uploads/<uuid>.<extensión permitida>
    private static final Pattern URL_VALIDA =
            Pattern.compile("^/uploads/[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}\\.(jpg|png|gif|webp)$");

    private final Path directorio;

    // app.uploads.dir viene de application.properties; si no está, usa la carpeta "uploads"
    public AlmacenImagenes(@Value("${app.uploads.dir:uploads}") String ruta) {
        this.directorio = Paths.get(ruta).toAbsolutePath().normalize();
    }

    public Path getDirectorio() {
        return directorio;
    }

    /** ¿La URL tiene exactamente el formato que genera este almacén? (evita rutas raras como ../) */
    public static boolean esUrlValida(String url) {
        return url != null && URL_VALIDA.matcher(url).matches();
    }

    /** Valida y guarda la imagen. Devuelve la URL pública (ej: "/uploads/abc.jpg"). */
    public String guardar(MultipartFile archivo) {
        if (archivo == null || archivo.isEmpty()) {
            throw new IllegalArgumentException("No se recibió ninguna imagen");
        }
        if (archivo.getSize() > MAX_BYTES) {
            throw new IllegalArgumentException("La imagen supera el máximo de 5 MB");
        }
        // Se mira el CONTENIDO real del archivo (primeros bytes), no el nombre ni el tipo que declara el cliente,
        // porque esos dos datos se pueden falsificar fácilmente.
        String extension = detectarExtension(leerCabecera(archivo));
        if (extension == null) {
            throw new IllegalArgumentException("Formato no permitido: solo JPG, PNG, GIF o WEBP");
        }
        // El nombre original se descarta: se genera uno aleatorio (evita sobrescribir archivos e intentos de ../)
        String nombre = UUID.randomUUID() + "." + extension;
        try {
            Files.createDirectories(directorio);
            try (InputStream in = archivo.getInputStream()) {
                Files.copy(in, directorio.resolve(nombre));
            }
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo guardar la imagen en el servidor", e);
        }
        return "/uploads/" + nombre;
    }

    /** Borra del disco la imagen de esa URL. Si no existe o no se puede borrar, no hace nada (no es crítico). */
    public void eliminar(String url) {
        if (!esUrlValida(url)) {
            return; // nunca se borra nada con una URL que no tenga el formato exacto (evita ../ y rutas raras)
        }
        try {
            Files.deleteIfExists(directorio.resolve(url.substring("/uploads/".length())));
        } catch (IOException e) {
            // se ignora: el inmueble ya se eliminó de la base y un archivo suelto no rompe nada
        }
    }

    private byte[] leerCabecera(MultipartFile archivo) {
        try (InputStream in = archivo.getInputStream()) {
            return in.readNBytes(12);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer la imagen", e);
        }
    }

    /** "Firma" de cada formato: los primeros bytes identifican qué tipo de archivo es. */
    private String detectarExtension(byte[] c) {
        if (c.length >= 3 && (c[0] & 0xFF) == 0xFF && (c[1] & 0xFF) == 0xD8 && (c[2] & 0xFF) == 0xFF) {
            return "jpg";
        }
        if (c.length >= 4 && (c[0] & 0xFF) == 0x89 && c[1] == 'P' && c[2] == 'N' && c[3] == 'G') {
            return "png";
        }
        if (c.length >= 4 && c[0] == 'G' && c[1] == 'I' && c[2] == 'F' && c[3] == '8') {
            return "gif";
        }
        if (c.length >= 12 && c[0] == 'R' && c[1] == 'I' && c[2] == 'F' && c[3] == 'F'
                && c[8] == 'W' && c[9] == 'E' && c[10] == 'B' && c[11] == 'P') {
            return "webp";
        }
        return null;
    }
}
