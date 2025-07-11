package com.enaaskills.competenceservice.repositories;


import com.enaaskills.competenceservice.models.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
            Optional<Competence> findByCode(String code);

            @Query(value = "select count(competencedata.sous_competences.statut =\"VALIDE\") \n" +
                    "from competencedata.sous_competences inner join competencedata.competence \n" +
                    "on competencedata.sous_competences.competence_id = competencedata.competence.id \n" +
                    "where competencedata.competence.code = :code" , nativeQuery = true)
    int countNumberValideParCode(@Param("code") String code);
}
