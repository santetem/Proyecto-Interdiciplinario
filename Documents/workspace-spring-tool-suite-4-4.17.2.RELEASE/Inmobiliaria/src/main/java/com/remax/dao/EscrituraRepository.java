package com.remax.dao;

import com.remax.model.Escritura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscrituraRepository extends JpaRepository<Escritura, Integer> {
}
