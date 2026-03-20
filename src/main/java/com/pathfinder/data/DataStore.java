package com.pathfinder.data;

import com.pathfinder.model.*;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    public static List<Developer> developers = new ArrayList<>(List.of(
            new Developer(1L, "Luis", new ArrayList<>(List.of(
                    new Skill("Java"),
                    new Skill("Spring")
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
            ))
    );
}