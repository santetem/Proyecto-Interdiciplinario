//24. VentaRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Venta;

@Repository
public interface ImplementacionVentaRepository extends JpaRepository<Venta, Long> {}

