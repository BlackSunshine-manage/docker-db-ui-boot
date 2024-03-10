package com.company.project.employes;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StandartEmployeeController implements EmployeeController {

    private final EmployeeService standartEmployeeService;

    @Override
    @GetMapping
    //200 - if true
    public ResponseEntity<List<Employee>> getEmployees() {
        AtomicReference<ResponseEntity<List<Employee>>> response = new AtomicReference<>(ok().build());
        standartEmployeeService.supplyEmployees((employees -> response.set(ok(employees))));
        return response.get();
    }

    @Override
    @PutMapping
    // 204 no content
    public ResponseEntity<List<Employee>> pushEmployees(Employee... employees) {
        AtomicReference<ResponseEntity<List<Employee>>> response = new AtomicReference<>(ok().build());
        standartEmployeeService.addEmployees(List.of(employees));
        return response.get();
    }

    @Override
    @PostMapping
    //204 - if update 201 if create
    public ResponseEntity<Employee> putEmployee(Employee employee) {
        AtomicReference<ResponseEntity<Employee>> response = new AtomicReference<>(ok().build());
        standartEmployeeService.putEmployee(employee, response);
        return response.get();
    }

    @Override
    @DeleteMapping
    // 204 no content
    public ResponseEntity<Employee> deleteEmployee(Employee... employees) {
        AtomicReference<ResponseEntity<Employee>> response = new AtomicReference<>(ok().build());
        standartEmployeeService.deleteEmployees(List.of(employees));
        return response.get();
    }

    @Override
    @GetMapping
    //200 - if true
    public ResponseEntity<List<Employee>> getEmployees(Integer ... employeeIds) {
        AtomicReference<ResponseEntity<List<Employee>>> response = new AtomicReference<>(ok().build());
        standartEmployeeService.supplyEmployees((employees -> response.set(ok(employees))), employeeIds);
        return response.get();
    }
}