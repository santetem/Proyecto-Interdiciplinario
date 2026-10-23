// ImplementacionLiquidacionRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Liquidacion;
@Repository
public class ImplementacionLiquidacionRepository extends ImplementacionGenericDao<Liquidacion, Integer> implements LiquidacionRepository {
    public ImplementacionLiquidacionRepository() { super(Liquidacion.class, "liquidaciones", "id_liquidacion"); }
}