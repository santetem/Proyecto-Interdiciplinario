
//4. CobranzaRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Cobranza;

@Repository
public interface ImplementacionCobranzaRepository extends JpaRepository<Cobranza, Long> {}
