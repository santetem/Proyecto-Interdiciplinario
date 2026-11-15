package com.inmobiliaria.factory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import com.inmobiliaria.dao.InmuebleRepository;
import com.inmobiliaria.dao.UsuarioRepository;

/** La Factory entrega los DAOs como interfaz, pidiéndolos al contexto de Spring. */
class MySQLDaoFactoryTest {

    @Test
    void getInmuebleDao_devuelveElBeanDelContexto() {
        ApplicationContext ctx = mock(ApplicationContext.class);
        InmuebleRepository esperado = mock(InmuebleRepository.class);
        when(ctx.getBean(InmuebleRepository.class)).thenReturn(esperado);

        IDaoFactory factory = new MySQLDaoFactory(ctx);

        assertSame(esperado, factory.getInmuebleDao());
    }

    @Test
    void daoFactory_getFactory_devuelveLaFabricaMySQL() {
        ApplicationContext ctx = mock(ApplicationContext.class);
        UsuarioRepository esperado = mock(UsuarioRepository.class);
        when(ctx.getBean(UsuarioRepository.class)).thenReturn(esperado);

        assertSame(esperado, DaoFactory.getFactory(ctx).getUsuarioDao());
    }
}
