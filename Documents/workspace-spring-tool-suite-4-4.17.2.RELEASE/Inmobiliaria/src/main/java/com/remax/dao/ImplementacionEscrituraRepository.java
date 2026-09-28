package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Escritura;

@Repository
public interface ImplementacionEscrituraRepository extends JpaRepository<Escritura, Long> {}

