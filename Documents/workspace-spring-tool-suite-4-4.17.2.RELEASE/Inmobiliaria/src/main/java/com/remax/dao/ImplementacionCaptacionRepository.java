
//3. CaptacionRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Captacion;

@Repository
public interface ImplementacionCaptacionRepository extends JpaRepository<Captacion, Long> {}
