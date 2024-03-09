package com.company.project.employes;

import com.company.project.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Component
@RequiredArgsConstructor
public abstract class StandartEmployeeCache implements EmployeeService {
    protected volatile Set<Employee> employees = new ConcurrentSkipListSet<>();
    protected volatile Set<Employee> bypass = new ConcurrentSkipListSet<>();
    protected final EmployeeRepository employeeRepository;

    public void supplyEmployees(Consumer<List<Employee>> usedEmployees) {
        new CollectionNotEmpty<>(employees,
                () -> usedEmployees.accept(new ArrayList<>(employees)));
    }

    @Override
    public synchronized void addEmployees(List<Employee> puttedEmployees) {
        new CollectionNotEmpty<>(employees,
                () -> employees.addAll(puttedEmployees));
    }

    @Override
    public void deleteEmployees(List<Employee> deletedEmployees) {
        new CollectionNotEmpty<>(employees,
                () -> deletedEmployees.forEach(employees::remove));
    }

    public synchronized void putEmployee(Employee employee, AtomicReference<ResponseEntity<Employee>> response) {
        final Predicate<Employee> filterById = (employeeFromStream) ->
                employeeFromStream.getId() == employee.getId();
        final Action actionOnUpdate = () -> {
            employees.removeIf(employeeInSet -> employeeInSet.equals(employee));
            employees.add(employee);
            response.set(new ResponseEntity<>(NO_CONTENT));
        };
        new ObjectNotEmpty<>(employee, () ->
                new CollectionAnyMatchByPredicate<>(employees, filterById, actionOnUpdate));
    }

    protected abstract void updateDataBase();
}