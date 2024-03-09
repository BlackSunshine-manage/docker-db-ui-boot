package com.company.project.employes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto implements Employee {
    private int id;
    private ProfileDto profile;

    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}