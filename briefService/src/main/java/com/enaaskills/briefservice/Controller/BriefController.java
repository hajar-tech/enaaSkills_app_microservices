package com.enaaskills.briefservice.Controller;

import com.enaaskills.briefservice.DTO.BriefDTO;
import com.enaaskills.briefservice.DTO.BriefResponseDTO;
import com.enaaskills.briefservice.DTO.CreateBriefRequestDTO;
import com.enaaskills.briefservice.Mapper.BriefMapper;
import com.enaaskills.briefservice.Model.Brief;
import com.enaaskills.briefservice.Model.BriefCompetence;
import com.enaaskills.briefservice.Service.BriefService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Briefs")
public class BriefController {

    private final BriefService briefService;

    public BriefController(BriefService briefService){
        this.briefService=briefService;
    }

    @PostMapping("/add")
    public ResponseEntity<Brief> createBrief(@RequestBody Brief brief){
        return ResponseEntity.ok(briefService.creatBrief(brief));
    }

    @GetMapping
    public ResponseEntity<List<Brief>> getAllBriefs(){
        return ResponseEntity.ok(briefService.getAllBriefs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brief> getBriefById(@PathVariable Long id){
        return ResponseEntity.ok(briefService.getBrief(id));

    }

    //getAllBriefs
    @GetMapping("/dto")
    public ResponseEntity<List<BriefResponseDTO>> getAllBriefsDTO(){
        List<Brief> briefs=briefService.getAllBriefs();
        List<BriefResponseDTO> response = briefs.stream()
                .map(BriefMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }


    @PostMapping("/{id}/add-competence")
    public ResponseEntity<BriefCompetence> addCompetence(@PathVariable Long id, @RequestBody Map<String, Long> body){
        Long competenceId = body.get("competenceId");
        return ResponseEntity.ok(briefService.associeCompetence(id, competenceId));
    }

    @PostMapping("/addBriefDto")
    public ResponseEntity<BriefDTO> createBriefDTO(@RequestBody BriefDTO briefDTO){
        Brief entity = BriefMapper.toEntity(briefDTO);
        Brief saved = briefService.creatBrief(entity);
        return ResponseEntity.ok(BriefMapper.toDTO(saved));
    }

    //post brief with skills

    @PostMapping("/with-competences")
    public ResponseEntity<BriefResponseDTO> createWithCompetences(@RequestBody CreateBriefRequestDTO briefDTO){
        Brief saved = briefService.createBriefWithCompetences(briefDTO);
        return ResponseEntity.ok(BriefMapper.toResponseDTO(saved));
    }
}
