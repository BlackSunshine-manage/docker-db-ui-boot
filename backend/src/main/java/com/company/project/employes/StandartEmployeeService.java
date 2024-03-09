package com.company.project.employes;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@Component("standartEmployeeService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class StandartEmployeeService implements EmployeeService {
    private final EmployeeService timeSynchronizedEmployeeCache;

    public void supplyEmployees(Consumer<List<Employee>> usedEmployees) {
        timeSynchronizedEmployeeCache.supplyEmployees(usedEmployees);
    }

    @Override
    public synchronized void addEmployees(List<Employee> puttedEmployees) {
        timeSynchronizedEmployeeCache.addEmployees(puttedEmployees);
    }

    @Override
    public void deleteEmployees(List<Employee> deletedEmployees) {
        timeSynchronizedEmployeeCache.deleteEmployees(deletedEmployees);
    }

    public synchronized void putEmployee(Employee employee, AtomicReference<ResponseEntity<Employee>> response) {
        timeSynchronizedEmployeeCache.putEmployee(employee, response);
    }
}