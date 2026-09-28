package com.remax.dao;

import com.remax.model.Captacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptacionRepository extends JpaRepository<Captacion, Integer> {
}
