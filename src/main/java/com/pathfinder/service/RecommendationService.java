package com.pathfinder.service;

import com.pathfinder.data.DataStore;
import com.pathfinder.model.*;

import java.util.*;

public class RecommendationService {

    public List<String> recommendNextSkills(Long developerId) {
        Developer dev = DataStore.developers.stream()
                .filter(d -> d.id.equals(developerId))
                .findFirst()
                .orElseThrow();

        Map<String, Double> scores = new HashMap<>();

        for (Skill skill : dev.skills) {
            for (Transition t : DataStore.transitions) {
                if (t.from.equals(skill.name)) {
                    double score = t.weight * t.successRate;
                    scores.merge(t.to, score, Double::sum);
                }
            }
        }

        return scores.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }
}