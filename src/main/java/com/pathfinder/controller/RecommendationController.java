package com.pathfinder.controller;

import com.pathfinder.data.DataStore;
import com.pathfinder.model.Developer;
import com.pathfinder.model.Skill;
import com.pathfinder.model.Transition;
import com.pathfinder.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
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

    @GetMapping("/graph/{id}")
    public Map<String, Object> getGraph(@PathVariable Long id) {

        Developer dev = DataStore.developers.stream()
                .filter(d -> d.id.equals(id))
                .findFirst()
                .orElseThrow();

        List<Map<String, Object>> elements = new ArrayList<>();

        // Nodos actuales (verde)
        for (Skill s : dev.skills) {
            elements.add(Map.of("data", Map.of(
                    "id", s.name,
                    "label", s.name,
                    "color", "green"
            )));
        }

        // Transiciones
        RecommendationService service = new RecommendationService();
        List<Transition> transitions = service.getAllTransitions();

        for (Transition t : transitions) {

            elements.add(Map.of("data", Map.of(
                    "id", t.to,
                    "label", t.to
            )));

            elements.add(Map.of("data", Map.of(
                    "source", t.from,
                    "target", t.to
            )));
        }

        return Map.of("elements", elements);
    }

    @PostMapping("/developer")
    public Developer createDeveloper(@RequestBody Map<String, String> body) {

        Long id = (long) (DataStore.developers.size() + 1);
        String name = body.get("name");

        Developer dev = new Developer(id, name, new ArrayList<>());
        DataStore.developers.add(dev);

        return dev;
    }

    @GetMapping("/developers")
    public List<Developer> getAllDevelopers() {
        return DataStore.developers;
    }
}