package com.company.project.employes;

import com.company.project.utils.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

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