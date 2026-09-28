
//9. HistorialOfertaRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.HistorialOferta;

@Repository
public interface ImplementacionHistorialOfertasRepository extends JpaRepository<HistorialOferta, Long> {}

