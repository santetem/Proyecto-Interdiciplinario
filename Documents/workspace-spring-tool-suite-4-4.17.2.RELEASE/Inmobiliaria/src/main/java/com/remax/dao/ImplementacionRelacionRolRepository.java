//19. RelacionRolRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.RelacionRol;

@Repository
public interface ImplementacionRelacionRolRepository extends JpaRepository<RelacionRol, Long> {}

