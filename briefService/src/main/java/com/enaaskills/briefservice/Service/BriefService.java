package com.enaaskills.briefservice.Service;


import com.enaaskills.briefservice.Model.Brief;
import com.enaaskills.briefservice.Repository.BriefRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BriefService {

    private final BriefRepository briefRepository;

    public BriefService(BriefRepository briefRepository) {
        this.briefRepository = briefRepository;
    }

    public Brief creatBrief(Brief brief) {
        return briefRepository.save(brief);
    }

    public List<Brief> getAllBriefs() {
        return briefRepository.findAll();
    }


}
