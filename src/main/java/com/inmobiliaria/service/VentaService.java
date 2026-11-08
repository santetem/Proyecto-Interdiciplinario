package com.inmobiliaria.service; 

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
    public void concretarVenta(Integer idOferta, Double montoFinal) {
        // 1. Buscar la oferta usando método de Procedimiento Almacenado (buscarPorIdSP)
        Oferta oferta = ofertaRepository.buscarPorIdSP(idOferta);
        if (oferta == null) {
            throw new RuntimeException("No se encontró la oferta con ID: " + idOferta);
        }
        
        // Actualizar el estado de la oferta usando actualizarSP
        oferta.setEstado("Aceptada");
        String setValoresOferta = "estado = 'Aceptada'";
        ofertaRepository.actualizarSP(setValoresOferta, idOferta);

        // 2. Registrar la Operación vinculada a esa oferta usando insertarSP
        
        String columnasOperacion = "id_oferta, tipo_operacion, fecha, estado";
        String valoresOperacion = idOferta + ", 'VENTA', '" + LocalDate.now() + "', 'Completada'";
        operacionRepository.insertarSP(columnasOperacion, valoresOperacion);

        // Si se necesita recuperar el ID de la operación recién insertada para vincularla a la venta, 
        // puede hacerlistando las operaciones o ajustando la inserción. 
        // Por ahora, registramos formalmente la venta con tu método insertarSP:
        String columnasVenta = "fecha, monto_final, estado";
        String valoresVenta = "'" + LocalDate.now() + "', " + montoFinal + ", 'Finalizada'";
        ventaRepository.insertarSP(columnasVenta, valoresVenta);
        
        
    }
}