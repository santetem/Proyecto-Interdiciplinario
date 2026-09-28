//18. RelacionPermisoRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.RelacionPermiso;

@Repository
public interface ImplementacionRelacionPermisoRepository extends JpaRepository<RelacionPermiso, Long> {}

