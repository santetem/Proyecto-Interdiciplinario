
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Franquicia;

@Repository
public interface ImplementacionFranquiciaRepository extends JpaRepository<Franquicia, Long> {}

