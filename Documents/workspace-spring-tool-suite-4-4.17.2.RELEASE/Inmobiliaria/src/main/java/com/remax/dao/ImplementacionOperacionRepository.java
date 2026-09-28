//14. OperacionRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Operacion;

@Repository
public interface ImplementacionOperacionRepository extends JpaRepository<Operacion, Long> {}

