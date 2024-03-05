package com.company.project.employes;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public interface EmployeeService {
    void supplyEmployees(Consumer<List<Employee>> usedEmployees);

    void addEmployees(List<Employee> puttedEmployees);

    void deleteEmployees(List<Employee> puttedEmployees);

    void putEmployee(Employee employee, AtomicReference<ResponseEntity<Employee>> response);
}