package com.inmobiliaria.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

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
 * Fábrica concreta para MySQL (stored procedures vía JPA).
 * Spring crea los DAOs (necesitan el EntityManager), la fábrica los entrega
 * como INTERFAZ, ocultando las clases Implementacion... al resto del sistema.
 */
@Component
public class MySQLDaoFactory implements IDaoFactory {

    private final ApplicationContext contexto;

    public MySQLDaoFactory(ApplicationContext contexto) {
        this.contexto = contexto;
    }

    @Override
    public AlquilerRepository getAlquilerDao() {
        return contexto.getBean(AlquilerRepository.class);
    }

    @Override
    public AuditoriaRepository getAuditoriaDao() {
        return contexto.getBean(AuditoriaRepository.class);
    }

    @Override
    public CaptacionRepository getCaptacionDao() {
        return contexto.getBean(CaptacionRepository.class);
    }

    @Override
    public CaracteristicaRepository getCaracteristicaDao() {
        return contexto.getBean(CaracteristicaRepository.class);
    }

    @Override
    public ComisionRepository getComisionDao() {
        return contexto.getBean(ComisionRepository.class);
    }

    @Override
    public ConversacionRepository getConversacionDao() {
        return contexto.getBean(ConversacionRepository.class);
    }

    @Override
    public EscribaniaRepository getEscribaniaDao() {
        return contexto.getBean(EscribaniaRepository.class);
    }

    @Override
    public EscrituraRepository getEscrituraDao() {
        return contexto.getBean(EscrituraRepository.class);
    }

    @Override
    public FranquiciaRepository getFranquiciaDao() {
        return contexto.getBean(FranquiciaRepository.class);
    }

    @Override
    public HistorialOfertaRepository getHistorialOfertaDao() {
        return contexto.getBean(HistorialOfertaRepository.class);
    }

    @Override
    public InmuebleCaracteristicasRepository getInmuebleCaracteristicasDao() {
        return contexto.getBean(InmuebleCaracteristicasRepository.class);
    }

    @Override
    public InmuebleRepository getInmuebleDao() {
        return contexto.getBean(InmuebleRepository.class);
    }

    @Override
    public MensajeRepository getMensajeDao() {
        return contexto.getBean(MensajeRepository.class);
    }

    @Override
    public MultimediaRepository getMultimediaDao() {
        return contexto.getBean(MultimediaRepository.class);
    }

    @Override
    public OfertaRepository getOfertaDao() {
        return contexto.getBean(OfertaRepository.class);
    }

    @Override
    public OperacionRepository getOperacionDao() {
        return contexto.getBean(OperacionRepository.class);
    }

    @Override
    public PermisoRepository getPermisoDao() {
        return contexto.getBean(PermisoRepository.class);
    }

    @Override
    public PersonalRepository getPersonalDao() {
        return contexto.getBean(PersonalRepository.class);
    }

    @Override
    public PublicacionRepository getPublicacionDao() {
        return contexto.getBean(PublicacionRepository.class);
    }

    @Override
    public RegistrosalquilerRepository getRegistrosalquilerDao() {
        return contexto.getBean(RegistrosalquilerRepository.class);
    }

    @Override
    public RelacionPermisosRepository getRelacionPermisosDao() {
        return contexto.getBean(RelacionPermisosRepository.class);
    }

    @Override
    public RelacionRolesRepository getRelacionRolesDao() {
        return contexto.getBean(RelacionRolesRepository.class);
    }

    @Override
    public RolRepository getRolDao() {
        return contexto.getBean(RolRepository.class);
    }

    @Override
    public SoliFranquiciasRepository getSoliFranquiciasDao() {
        return contexto.getBean(SoliFranquiciasRepository.class);
    }

    @Override
    public TasacionRepository getTasacionDao() {
        return contexto.getBean(TasacionRepository.class);
    }

    @Override
    public TiporegistroRepository getTiporegistroDao() {
        return contexto.getBean(TiporegistroRepository.class);
    }

    @Override
    public UsuarioRepository getUsuarioDao() {
        return contexto.getBean(UsuarioRepository.class);
    }

    @Override
    public VentaRepository getVentaDao() {
        return contexto.getBean(VentaRepository.class);
    }

    @Override
    public VisitaRepository getVisitaDao() {
        return contexto.getBean(VisitaRepository.class);
    }
}
