
//5. ContratoRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Contrato;

@Repository
public interface ImplementacionContratoRepository extends JpaRepository<Contrato, Long> {}
