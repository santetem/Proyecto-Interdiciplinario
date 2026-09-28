//12. MultimediaRepository.java
package com.remax.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.remax.model.Multimedia;

@Repository
public interface ImplementacionMultimediaRepository extends JpaRepository<Multimedia, Long> {}

