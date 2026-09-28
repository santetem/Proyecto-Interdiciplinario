//25. VisitaRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Visita;

@Repository
public interface ImplementacionVisitaRepository extends JpaRepository<Visita, Long> {}
