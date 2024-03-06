package com.company.project.employes;

import lombok.Getter;

@Getter
public class StandartEmployee implements Employee {
    private final int id;
    private final Profile profile;

    public StandartEmployee(int id,
                            Profile profile) {
        this.id = id;
        this.profile = new StandartProfile(profile.getFirstName(), profile.getSurname(), profile.getAge());
    }

    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}