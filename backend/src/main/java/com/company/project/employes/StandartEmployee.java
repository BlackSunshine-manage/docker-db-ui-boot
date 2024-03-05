package com.company.project.employes;

public class StandartEmployee implements Employee {
    private final int id;

    public StandartEmployee(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}