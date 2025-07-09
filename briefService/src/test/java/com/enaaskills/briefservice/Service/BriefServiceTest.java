package com.enaaskills.briefservice.Service;

import com.enaaskills.briefservice.Model.Brief;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BriefServiceTest {

    @Autowired
    private BriefService briefService;
    @Test
    void creatBrief() {
        Brief brief = new Brief();
        brief.setTitle("application crud");
        brief.setDescription("c'est un brief simple");
        //brief.getDateLimit(LocalDate.of(2025,07,09));

        Brief createdBrief = briefService.creatBrief(brief);

        assertNotNull(createdBrief.getId());
        assertEquals(brief.getTitle(), createdBrief.getTitle());

    }

    @Test
    void getAllBriefs() {
        Brief brief = new Brief();
        brief.setTitle("application crud");
        brief.setDescription("c'est un brief simple");
        Brief createdBrief = briefService.creatBrief(brief);


        List<Brief>briefs = briefService.getAllBriefs();
        assertNotNull(briefs);
        assertTrue(briefs.size() > 0);


    }
}