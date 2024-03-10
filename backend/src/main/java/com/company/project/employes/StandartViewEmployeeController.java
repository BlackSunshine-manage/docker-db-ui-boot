package com.company.project.employes;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v2/employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StandartViewEmployeeController implements ViewEmployeeController {

    private final EmployeeController standartEmployeeController;

    //        @Override
    @GetMapping("/")
    //200 - if true
    public String showAllEmployees(String name,
                                   @ModelAttribute("model") ModelMap model) {
        ResponseEntity<List<Employee>> response = standartEmployeeController.getEmployees();
        model = model.addAttribute("employeeList", response.getBody());
        return "employees";
    }

    @GetMapping(value = {"/add"})
    public String showAddEmployee(Model model) {
        return "";
    }

    @PostMapping(value = "/add")
    public String addEmployee(Model model,
                              @ModelAttribute("employee") EmployeeDto employee) {
        return "";
    }

    @GetMapping(value = {"/{employeeId}/edit"})
    public String showEditEmployee(Model model,
                                   @PathVariable long employeeId) {
        return "";
    }

    @PostMapping(value = {"/{employeeId}/edit"})
    public String updateEmployee(Model model,
                             @PathVariable long employeeId,
                             @ModelAttribute("employee") EmployeeDto employee) {
        return "";
    }

    @GetMapping(value = {"/{employeeId}/delete"})
    public String showDeleteEmployeeById(Model model,
                                         @PathVariable long employeeId) {
        return "";
    }

    @PostMapping(value = {"/{employeeId}/delete"})
    public String deleteEmployeeById(Model model,
                                     @PathVariable long employeeId) {
        return "";
    }
}