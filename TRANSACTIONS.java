package com.inmobiliaria.controller; // o com.inmobiliaria.service según tu estructura

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmobiliaria.dao.OfertaRepository;
import com.inmobiliaria.dao.OperacionRepository;
import com.inmobiliaria.dao.VentaRepository;
import com.inmobiliaria.model.Oferta;
import com.inmobiliaria.model.Operacion;
import com.inmobiliaria.model.Venta;

import java.time.LocalDate;

@Service
public class VentaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private VentaRepository ventaRepository;

    /**
     * Transacción completa para concretar una venta a partir de una oferta aceptada.
     * Si ocurre cualquier error de base de datos, se ejecuta un rollback automático.
     */
    @Transactional(rollbackFor = Exception.class)
    public void concretarVenta(Long idOferta, Double montoFinal) {
        // 1. Buscar y actualizar el estado de la oferta a "Aceptada"
        Oferta oferta = ofertaRepository.findById(idOferta)
                .orElseThrow(() -> new RuntimeException("No se encontró la oferta con ID: " + idOferta));
        oferta.setEstado("Aceptada");
        ofertaRepository.save(oferta);

        // 2. Registrar la Operación vinculada a esa oferta
        Operacion operacion = new Operacion();
        operacion.setOferta(oferta);
        operacion.setTipoOperacion("VENTA");
        operacion.setFecha(LocalDate.now());
        operacion.setEstado("Completada");
        operacionRepository.save(operacion);

        // 3. Registrar formalmente la Venta
        Venta venta = new Venta();
        venta.setOperacion(operacion);
        venta.setFecha(LocalDate.now());
        venta.setMontoFinal(montoFinal);
        venta.setEstado("Finalizada");
        ventaRepository.save(venta);
        
        // Si todo sale bien, la transacción realiza un commit automático de los 3 cambios.
    }
}