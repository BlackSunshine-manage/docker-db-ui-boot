package com.company.project.employes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class ProfileDto implements Profile {
    private String firstName;
    private String surname;
    private int age;
}