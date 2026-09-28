//10. InmuebleRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Inmueble;

@Repository
public interface ImplementacionInmuebleRepository extends JpaRepository<Inmueble, Long> {}

