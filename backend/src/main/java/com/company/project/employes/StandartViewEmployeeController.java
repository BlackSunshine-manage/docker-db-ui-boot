package com.company.project.employes;

import com.company.project.entity.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/api/v2/employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StandartViewEmployeeController implements ViewEmployeeController {

//    private final EmployeeController standartEmployeeController;

    //        @Override
    @GetMapping("/")
    //200 - if true
    public String showAllEmployees(String name, @ModelAttribute("model") ModelMap model) {
//        ResponseEntity<List<Employee>> response = standartEmployeeController.getEmployees();
        model = model.addAttribute("employeeList", List.of(new StandartEmployee(0), new StandartEmployee(1)));
        return "employees";
    }

    //        @Override
//    @PutMapping
//    // 204 no content
//    public String pushEmployees(Employee... employees) {
//        ResponseEntity<List<Employee>> response = standartEmployeeController.getEmployees();
//
//
//        return "";
//    }
//
//    //        @Override
//    @PostMapping
//    //204 - if update 201 if create
//    public String putEmployee(Employee employee) {
//        ResponseEntity<Employee> response = new AtomicReference<>(ok().build());
//        return "";
//    }
//
//    //        @Override
//    @DeleteMapping
//    // 204 no content
//    public String deleteEmployee(Employee... employees) {
//        ResponseEntity<Employee> response = new AtomicReference<>(ok().build());
//
//        return "";
//    }
}