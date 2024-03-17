package com.company.project.employes;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto implements Employee {
    private Integer id;
    private String firstName;
    private String surname;
    private int age;

    public EmployeeDto(Integer id, Profile profile) {
        this.id = id;
        this.firstName = profile.getFirstName();
        this.surname = profile.getSurname();
        this.age = profile.getAge();
    }

    @Override
    public Profile getProfile() {
        return new ProfileDto(firstName, surname, age);
    }

    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}