package com.remax.dao;

import com.remax.model.SoliFranquicia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoliFranquiciaRepository extends JpaRepository<SoliFranquicia, Integer> {
}
