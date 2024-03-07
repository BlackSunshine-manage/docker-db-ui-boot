package com.company.project.employes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StandartProfile implements Profile {
    private final String firstName;
    private final String surname;
    private final int age;

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public int getAge() {
        return age;
    }
}