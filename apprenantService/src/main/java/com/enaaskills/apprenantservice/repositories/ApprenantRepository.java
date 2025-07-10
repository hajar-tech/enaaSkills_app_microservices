package com.enaaskills.apprenantservice.repositories;

import com.enaaskills.apprenantservice.models.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprenantRepository extends JpaRepository<Apprenant , Long> {
}
