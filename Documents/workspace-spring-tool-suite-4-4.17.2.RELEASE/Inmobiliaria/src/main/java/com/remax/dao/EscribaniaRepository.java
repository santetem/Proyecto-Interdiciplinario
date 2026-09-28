package com.remax.dao;

import com.remax.model.Escribania;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscribaniaRepository extends JpaRepository<Escribania, Integer> {
}
