//13. OfertaRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Oferta;

@Repository
public interface ImplementacionOfertaRepository extends JpaRepository<Oferta, Long> {}

