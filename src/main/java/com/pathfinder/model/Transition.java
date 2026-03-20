package com.pathfinder.model;

public class Transition {
    public String from;
    public String to;
    public int weight;
    public double successRate;

    public Transition(String from, String to, int weight, double successRate) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.successRate = successRate;
    }
}