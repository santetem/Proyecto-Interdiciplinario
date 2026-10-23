// ImplementacionContratoRepository.java
package com.inmobiliaria.dao;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.model.Contrato;
@Repository
public class ImplementacionContratoRepository extends ImplementacionGenericDao<Contrato, Integer> implements ContratoRepository {
    public ImplementacionContratoRepository() { super(Contrato.class, "contratos", "id_contrato"); }
}