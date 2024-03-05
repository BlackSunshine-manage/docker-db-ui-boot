package com.company.project.employes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto implements Employee {
    private int id;

    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}
