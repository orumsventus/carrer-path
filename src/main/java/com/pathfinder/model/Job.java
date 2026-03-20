package com.pathfinder.model;

import java.util.List;

public class Job {
    public String name;
    public List<Skill> requiredSkills;

    public Job(String name, List<Skill> requiredSkills) {
        this.name = name;
        this.requiredSkills = requiredSkills;
    }
}