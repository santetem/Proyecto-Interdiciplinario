package com.inmobiliaria.factory;

import com.inmobiliaria.dao.AlquilerRepository;
import com.inmobiliaria.dao.AuditoriaRepository;
import com.inmobiliaria.dao.CaptacionRepository;
import com.inmobiliaria.dao.CaracteristicaRepository;
import com.inmobiliaria.dao.ComisionRepository;
import com.inmobiliaria.dao.ConversacionRepository;
import com.inmobiliaria.dao.EscribaniaRepository;
import com.inmobiliaria.dao.EscrituraRepository;
import com.inmobiliaria.dao.FranquiciaRepository;
import com.inmobiliaria.dao.HistorialOfertaRepository;
import com.inmobiliaria.dao.InmuebleCaracteristicasRepository;
import com.inmobiliaria.dao.InmuebleRepository;
import com.inmobiliaria.dao.MensajeRepository;
import com.inmobiliaria.dao.MultimediaRepository;
import com.inmobiliaria.dao.OfertaRepository;
import com.inmobiliaria.dao.OperacionRepository;
import com.inmobiliaria.dao.PermisoRepository;
import com.inmobiliaria.dao.PersonalRepository;
import com.inmobiliaria.dao.PublicacionRepository;
import com.inmobiliaria.dao.RegistrosalquilerRepository;
import com.inmobiliaria.dao.RelacionPermisosRepository;
import com.inmobiliaria.dao.RelacionRolesRepository;
import com.inmobiliaria.dao.RolRepository;
import com.inmobiliaria.dao.SoliFranquiciasRepository;
import com.inmobiliaria.dao.TasacionRepository;
import com.inmobiliaria.dao.TiporegistroRepository;
import com.inmobiliaria.dao.UsuarioRepository;
import com.inmobiliaria.dao.VentaRepository;
import com.inmobiliaria.dao.VisitaRepository;

/**
 * Patrón FACTORY (Factory Method) - Fábrica abstracta de DAOs.
 * Los Services piden los DAOs a esta interfaz y nunca conocen la clase concreta
 * (ImplementacionXRepository). Si mañana cambia la tecnología de acceso a datos,
 * solo se crea otra fábrica (por ejemplo MockDaoFactory) sin tocar los Services.
 */
public interface IDaoFactory {
    AlquilerRepository getAlquilerDao();
    AuditoriaRepository getAuditoriaDao();
    CaptacionRepository getCaptacionDao();
    CaracteristicaRepository getCaracteristicaDao();
    ComisionRepository getComisionDao();
    ConversacionRepository getConversacionDao();
    EscribaniaRepository getEscribaniaDao();
    EscrituraRepository getEscrituraDao();
    FranquiciaRepository getFranquiciaDao();
    HistorialOfertaRepository getHistorialOfertaDao();
    InmuebleCaracteristicasRepository getInmuebleCaracteristicasDao();
    InmuebleRepository getInmuebleDao();
    MensajeRepository getMensajeDao();
    MultimediaRepository getMultimediaDao();
    OfertaRepository getOfertaDao();
    OperacionRepository getOperacionDao();
    PermisoRepository getPermisoDao();
    PersonalRepository getPersonalDao();
    PublicacionRepository getPublicacionDao();
    RegistrosalquilerRepository getRegistrosalquilerDao();
    RelacionPermisosRepository getRelacionPermisosDao();
    RelacionRolesRepository getRelacionRolesDao();
    RolRepository getRolDao();
    SoliFranquiciasRepository getSoliFranquiciasDao();
    TasacionRepository getTasacionDao();
    TiporegistroRepository getTiporegistroDao();
    UsuarioRepository getUsuarioDao();
    VentaRepository getVentaDao();
    VisitaRepository getVisitaDao();
}
