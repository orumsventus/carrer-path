package com.pathfinder.controller;

import com.pathfinder.data.DataStore;
import com.pathfinder.model.Developer;
import com.pathfinder.model.Skill;
import com.pathfinder.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecommendationController {

    private final RecommendationService service = new RecommendationService();

    @GetMapping("/recommendations/{id}")
    public List<String> getRecommendations(@PathVariable Long id) {
        return service.recommendNextSkills(id);
    }

    @PostMapping("/developer/{id}/skill")
    public String addSkill(@PathVariable Long id, @RequestBody String skillName) {
        Developer dev = DataStore.developers.stream()
                .filter(d -> d.id.equals(id))
                .findFirst()
                .orElseThrow();

        dev.skills.add(new Skill(skillName));

        return "Skill added: " + skillName;
    }
}