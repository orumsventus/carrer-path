package com.pathfinder.model;

import java.util.List;

public class Developer {
    public Long id;
    public String name;
    public List<Skill> skills;

    public Developer(Long id, String name, List<Skill> skills) {
        this.id = id;
        this.name = name;
        this.skills = skills;
    }
}
