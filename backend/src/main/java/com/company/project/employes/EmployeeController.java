package com.company.project.employes;

import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author snd00 created at 11.02.2024
 * @project docker-db-ui-boot
 */
public interface EmployeeController {
    ResponseEntity<List<Employee>> getEmployees();

    ResponseEntity<List<Employee>> pushEmployees(Employee ... employees);

    ResponseEntity<Employee> deleteEmployee(Employee ... employees);

    ResponseEntity<Employee> putEmployee(Employee employee);
}