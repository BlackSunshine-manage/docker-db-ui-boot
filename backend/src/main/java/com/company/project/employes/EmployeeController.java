package com.company.project.employes;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeController {
    ResponseEntity<List<Employee>> getEmployees();

    ResponseEntity<List<Employee>> pushEmployees(Employee ... employees);

    ResponseEntity<Employee> deleteEmployee(Employee ... employees);

    ResponseEntity<Employee> putEmployee(Employee employee);

    ResponseEntity<List<Employee>> getEmployees(Integer ... employeeIds);
}