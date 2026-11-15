package com.inmobiliaria.dao;

import java.math.BigDecimal;
import com.inmobiliaria.model.Venta;

public interface VentaRepository extends IGenericDao<Venta, Integer> {

    /**
     * Llama a sp_ConcretarVenta: en una sola transacción acepta la oferta, crea la operación,
     * crea la venta y registra la comisión del agente. Si algo falla hace ROLLBACK.
     */
    void concretarVentaSP(int idOferta, BigDecimal montoFinal, int idUsuarioComision, BigDecimal porcentajeComision);
}
