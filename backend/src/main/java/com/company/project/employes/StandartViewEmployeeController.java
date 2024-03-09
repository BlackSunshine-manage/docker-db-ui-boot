package com.company.project.employes;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v2/employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StandartViewEmployeeController implements ViewEmployeeController {

    private final EmployeeController standartEmployeeController;

    //        @Override
    @GetMapping("/")
    //200 - if true
    public String showAllEmployees(String name, @ModelAttribute("model") ModelMap model) {
        ResponseEntity<List<Employee>> response = standartEmployeeController.getEmployees();
        model = model.addAttribute("employeeList", response.getBody());
        return "employees";
    }
}