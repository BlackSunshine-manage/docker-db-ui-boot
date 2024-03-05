package com.company.project.employes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


/**
 * @author snd00 created at 01.03.2024
 * @project backend
 */
@Entity
@Table(name = "employee")
public class EmployeeEntity implements Employee {
    @Id
    private int id;

    public EmployeeEntity(int id) {
        this.id = id;
    }

    public EmployeeEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        com.company.project.entity.Greeting greeting = (com.company.project.entity.Greeting) o;

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