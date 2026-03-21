package com.pathfinder.data;

import com.pathfinder.model.*;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    public static List<Developer> developers = new ArrayList<>(List.of(

            // 🔹 Backend path fuerte
            new Developer(1L, "Luis", new ArrayList<>(List.of(
                    new Skill("Java"),
                    new Skill("Spring"),
                    new Skill("Microservices"),
                    new Skill("Cloud")
            ))),

            new Developer(2L, "Ana", new ArrayList<>(List.of(
                    new Skill("Java"),
                    new Skill("Spring"),
                    new Skill("Microservices")
            ))),

            new Developer(3L, "Carlos", new ArrayList<>(List.of(
                    new Skill("Java"),
                    new Skill("Spring"),
                    new Skill("Microservices"),
                    new Skill("Cloud")
            ))),

            // 🔹 Camino alternativo (menos exitoso)
            new Developer(4L, "Sofia", new ArrayList<>(List.of(
                    new Skill("Java"),
                    new Skill("Spring"),
                    new Skill("Android")
            ))),

            new Developer(5L, "Miguel", new ArrayList<>(List.of(
                    new Skill("Java"),
                    new Skill("Spring"),
                    new Skill("Android")
            ))),

            // 🔹 Fullstack
            new Developer(6L, "Laura", new ArrayList<>(List.of(
                    new Skill("Java"),
                    new Skill("Spring"),
                    new Skill("React"),
                    new Skill("Node")
            ))),

            // 🔹 Frontend puro
            new Developer(7L, "Diego", new ArrayList<>(List.of(
                    new Skill("JavaScript"),
                    new Skill("React"),
                    new Skill("NextJS")
            ))),

            // 🔹 Cloud fuerte
            new Developer(8L, "Andres", new ArrayList<>(List.of(
                    new Skill("Java"),
                    new Skill("Spring"),
                    new Skill("Microservices"),
                    new Skill("Cloud"),
                    new Skill("Kubernetes")
            ))),

            // 🔹 DevOps path
            new Developer(9L, "Fernanda", new ArrayList<>(List.of(
                    new Skill("Linux"),
                    new Skill("Docker"),
                    new Skill("Kubernetes"),
                    new Skill("Cloud")
            ))),

            // 🔹 Caso raro (ruido)
            new Developer(10L, "Pedro", new ArrayList<>(List.of(
                    new Skill("Java"),
                    new Skill("Photoshop")
            )))
    ));

    public static List<Transition> transitions = List.of(
            new Transition("Java", "Spring", 10, 0.9),
            new Transition("Spring", "Microservices", 8, 0.85),
            new Transition("Microservices", "Cloud", 6, 0.8),
            new Transition("Spring", "Android", 3, 0.4)
    );

    public static List<Job> jobs = List.of(
            new Job("Backend Senior", List.of(
                    new Skill("Java"),
                    new Skill("Spring"),
                    new Skill("Microservices"),
                    new Skill("Cloud")
            )),
            new Job("Frontend Developer", List.of(
                    new Skill("JavaScript"),
                    new Skill("React"),
                    new Skill("NextJS")
            )),
            new Job("DevOps Engineer", List.of(
                    new Skill("Docker"),
                    new Skill("Kubernetes"),
                    new Skill("Cloud")
            ))
    );
}