//22. TasacionRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Tasacion;

@Repository
public interface ImplementacionTasacionRepository extends JpaRepository<Tasacion, Long> {}

