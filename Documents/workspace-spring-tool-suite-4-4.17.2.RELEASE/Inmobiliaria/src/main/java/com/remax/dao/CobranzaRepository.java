package com.remax.dao;

import com.remax.model.Cobranza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CobranzaRepository extends JpaRepository<Cobranza, Integer> {
}
