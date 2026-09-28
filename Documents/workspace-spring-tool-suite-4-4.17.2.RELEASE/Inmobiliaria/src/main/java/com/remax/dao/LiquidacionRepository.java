package com.remax.dao;

import com.remax.model.Liquidacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquidacionRepository extends JpaRepository<Liquidacion, Integer> {
}
