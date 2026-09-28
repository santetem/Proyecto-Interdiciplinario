//17. PublicacionRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Publicacion;

@Repository
public interface ImplementacionPublicacionRepositoy extends JpaRepository<Publicacion, Long> {}

