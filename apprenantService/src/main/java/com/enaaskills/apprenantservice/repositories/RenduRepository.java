package com.enaaskills.apprenantservice.repositories;

import com.enaaskills.apprenantservice.models.Rendu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RenduRepository extends JpaRepository<Rendu , Long> {
    List<Rendu> findByApprenantId(Long apprenantId);
}
