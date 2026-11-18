// CaptacionRepository.java
package com.inmobiliaria.dao;
import java.math.BigDecimal;
import com.inmobiliaria.model.Captacion;
public interface CaptacionRepository extends IGenericDao<Captacion, Integer> {

    /**
     * Llama a sp_AceptarCaptacion: en una sola transacción pasa la captación a "Activa" (con su operador),
     * crea el inmueble y lo publica. Si algo falla hace ROLLBACK.
     */
    void aceptarCaptacionSP(int idCaptacion, int idOperador, String direccion, String tipo,
                            BigDecimal supCubierta, BigDecimal supTotal, int habitaciones, int banos,
                            int cocheras, String antiguedad, String localidad, String observaciones);
}
