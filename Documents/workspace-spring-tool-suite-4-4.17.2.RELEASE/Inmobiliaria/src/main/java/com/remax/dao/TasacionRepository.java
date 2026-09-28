package com.remax.dao;

import com.remax.model.Tasacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasacionRepository extends JpaRepository<Tasacion, Integer> {
}
