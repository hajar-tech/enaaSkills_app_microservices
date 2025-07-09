package com.enaaskills.briefservice.Controller;

import com.enaaskills.briefservice.Model.Brief;
import com.enaaskills.briefservice.Service.BriefService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Briefs")
public class BriefController {

    private final BriefService briefService;

    public BriefController(BriefService briefService){
        this.briefService=briefService;
    }

    @PostMapping
    public ResponseEntity<Brief> createBrief(@RequestBody Brief brief){
        return ResponseEntity.ok(briefService.creatBrief(brief));
    }

    @GetMapping
    public ResponseEntity<List<Brief>> getAllBriefs(){
        return ResponseEntity.ok(briefService.getAllBriefs());
    }
}
