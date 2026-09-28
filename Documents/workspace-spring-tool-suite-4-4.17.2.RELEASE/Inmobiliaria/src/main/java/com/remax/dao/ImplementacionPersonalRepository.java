//16. PersonalRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Personal;

@Repository
public interface ImplementacionPersonalRepository extends JpaRepository<Personal, Long> {}

