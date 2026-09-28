package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Alquiler;

@Repository
public interface ImplementacionAlquilerRepository extends JpaRepository<Alquiler, Long> {}
