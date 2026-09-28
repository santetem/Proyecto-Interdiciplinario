//11. LiquidacionRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Liquidacion;

@Repository
public interface ImplementacionLiquidacionRepository extends JpaRepository<Liquidacion, Long> {}

