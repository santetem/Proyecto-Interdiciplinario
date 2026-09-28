package com.remax.dao;

import com.remax.model.HistorialOferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialOfertaRepository extends JpaRepository<HistorialOferta, Integer> {
}
