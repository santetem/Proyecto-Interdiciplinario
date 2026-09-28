
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Auditoria;

@Repository
public interface ImplementacionAuditoriaRepository extends JpaRepository<Auditoria, Long> {}
