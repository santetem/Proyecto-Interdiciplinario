package com.remax.dao;

import com.remax.model.RelacionPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelacionPermisoRepository extends JpaRepository<RelacionPermiso, Integer> {
}
