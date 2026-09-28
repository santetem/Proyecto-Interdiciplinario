package com.remax.dao;

import com.remax.model.RelacionRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelacionRolRepository extends JpaRepository<RelacionRol, Integer> {
}
