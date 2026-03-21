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

    private List<Transition> buildTransitionsFromDevelopers() {
        Map<String, Transition> map = new HashMap<>();

        for (Developer dev : DataStore.developers) {

            List<Skill> skills = dev.skills;

            for (int i = 0; i < skills.size() - 1; i++) {
                String from = skills.get(i).name;
                String to = skills.get(i + 1).name;

                String key = from + "->" + to;

                if (!map.containsKey(key)) {
                    map.put(key, new Transition(from, to, 1, 0.5));
                } else {
                    map.get(key).weight++;
                }
            }
        }

        return new ArrayList<>(map.values());
    }

    public List<Transition> getAllTransitions() {
        return buildTransitionsFromDevelopers();
    }

    public Map<String, Integer> buildTransitionWeights() {
        Map<String, Integer> weights = new HashMap<>();

        for (Developer dev : DataStore.developers) {
            List<Skill> skills = dev.skills;

            for (int i = 0; i < skills.size() - 1; i++) {
                String key = skills.get(i).name + "->" + skills.get(i + 1).name;
                weights.merge(key, 1, Integer::sum);
            }
        }

        return weights;
    }

    public List<String> getBestPath(Developer dev, Job job) {

        if (dev.skills.isEmpty()) {
            return List.of("No tienes skills aún");
        }

        Map<String, Integer> weights = buildTransitionWeights();
        List<String> path = new ArrayList<>();

        // empieza desde último skill del usuario
        String current = dev.skills.get(dev.skills.size() - 1).name;
        path.add(current);

        for (Skill target : job.requiredSkills) {

            if (dev.skills.stream().anyMatch(s -> s.name.equals(target.name))) {
                continue;
            }

            String bestNext = null;
            int bestScore = -1;

            for (String key : weights.keySet()) {
                String[] parts = key.split("->");

                if (parts[0].equals(current)) {
                    int score = weights.get(key);

                    if (score > bestScore) {
                        bestScore = score;
                        bestNext = parts[1];
                    }
                }
            }

            /**
             * Esto hace que:
             *
             * Nunca se quede “atascado”
             * Siempre llegue a la meta
             */
            if (bestNext != null) {
                path.add(bestNext);
                current = bestNext;
            } else {
                // fallback: usa skill objetivo directamente
                path.add(target.name);
                current = target.name;
            }
        }

        return path;
    }

    public double calculateProbability(List<String> path) {
        if (path.size() < 2) return 0;

        Map<String, Integer> weights = buildTransitionWeights();

        int total = 0;
        int count = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            String key = path.get(i) + "->" + path.get(i + 1);
            int w = weights.getOrDefault(key, 1);

            total += w;
            count++;
        }

        return (double) total / count / 10;
    }


}