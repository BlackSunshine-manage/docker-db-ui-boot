package com.company.project.employes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeMapper {
    final private Employee employee;
    EmployeeEntity mapToEntity() {
        final var employee = new EmployeeEntity();
        employee.setId(this.employee.getId());
        return employee;
    }

    StandartEmployee mapToBusiness() {
        return new StandartEmployee(this.employee.getId());
    }

    EmployeeDto mapToDto() {
        return new EmployeeDto(this.employee.getId());
    }
}
