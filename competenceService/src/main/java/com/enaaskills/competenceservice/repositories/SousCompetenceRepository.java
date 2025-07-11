package com.enaaskills.competenceservice.repositories;


import com.enaaskills.competenceservice.models.SousCompetences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SousCompetenceRepository  extends JpaRepository<SousCompetences, Long> {
    List<SousCompetences> findByCompetenceId (Long competenceId);
}
