//20. RolRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Rol;

@Repository
public interface ImplementacionRolRepository extends JpaRepository<Rol, Long> {}

