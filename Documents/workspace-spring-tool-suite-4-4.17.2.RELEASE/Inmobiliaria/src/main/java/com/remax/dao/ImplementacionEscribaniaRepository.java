
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Escribania;

@Repository
public interface ImplementacionEscribaniaRepository extends JpaRepository<Escribania, Long> {}

