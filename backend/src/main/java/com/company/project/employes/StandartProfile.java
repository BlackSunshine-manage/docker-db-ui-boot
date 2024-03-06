package com.company.project.employes;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
public class StandartProfile implements Profile {
    private final String firstName;
    private final String surname;
    private final int age;
    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getSurname() {
        return null;
    }

    @Override
    public int getAge() {
        return 0;
    }
}
