package com.example.demo.application.core.locales;

public class Municipality {
    public Municipality(String name, String state, String region) {
        this.name = name;
        this.state = state;
        this.region = region;
    }

    private String name;
    private String state;
    private String region;

    public Municipality(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Municipality{" +
                "name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
