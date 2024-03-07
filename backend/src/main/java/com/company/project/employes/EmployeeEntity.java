package com.company.project.employes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class EmployeeEntity implements Employee {
    @Id
    private int id;
    private short age;
    private String firstName;
    private String surname;

    @Override
    public Profile getProfile() {
        return new StandartProfile(firstName, surname, age);
    }

    public EmployeeEntity(int id) {
        this.id = id;
    }

    public EmployeeEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return id == id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}