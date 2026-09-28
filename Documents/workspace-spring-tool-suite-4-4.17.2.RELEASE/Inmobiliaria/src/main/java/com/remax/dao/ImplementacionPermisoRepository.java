//15. PermisoRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Permiso;

@Repository
public interface ImplementacionPermisoRepository extends JpaRepository<Permiso, Long> {}

