package com.enaaskills.briefservice.Repository;

import com.enaaskills.briefservice.Model.Brief;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BriefRepository extends JpaRepository<Brief, Long> {

}
