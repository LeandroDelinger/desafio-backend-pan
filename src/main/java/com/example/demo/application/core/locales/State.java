package com.example.demo.application.core.locales;

public class State {
    private int id;
    private String acronym;
    private String name;

    public State(int id, String acronym, String name) {
        this.id = id;
        this.acronym = acronym;
        this.name = name;
    }

    public State() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", acronym='" + acronym + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
