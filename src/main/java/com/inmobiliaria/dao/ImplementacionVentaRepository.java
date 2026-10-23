// ImplementacionVentaRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Venta;
@Repository
public class ImplementacionVentaRepository extends ImplementacionGenericDao<Venta, Integer> implements VentaRepository {
    public ImplementacionVentaRepository() { super(Venta.class, "ventas", "id_venta"); }
}