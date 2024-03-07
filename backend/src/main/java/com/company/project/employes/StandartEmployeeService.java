package com.company.project.employes;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@Component("standartEmployeeService")
class StandartEmployeeService implements EmployeeService {
    private final EmployeeService cache = new StandartEmployeeCache();

    public void supplyEmployees(Consumer<List<Employee>> usedEmployees) {
        cache.supplyEmployees(usedEmployees);
    }

    @Override
    public synchronized void addEmployees(List<Employee> puttedEmployees) {
        cache.addEmployees(puttedEmployees);
    }

    @Override
    public void deleteEmployees(List<Employee> deletedEmployees) {
        cache.deleteEmployees(deletedEmployees);
    }

    public synchronized void putEmployee(Employee employee, AtomicReference<ResponseEntity<Employee>> response) {
        cache.putEmployee(employee, response);
    }
}