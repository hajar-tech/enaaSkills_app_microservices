package com.enaaskills.validationservice.repository;

import com.enaaskills.validationservice.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ValidationRepository extends JpaRepository<Validation, Long> {
    List<Validation> findByApprenantId(Long apprenantId);
    List<Validation> findByBriefId(Long briefId);
    List<Validation> findByCompetenceId(Long competenceId);
}
