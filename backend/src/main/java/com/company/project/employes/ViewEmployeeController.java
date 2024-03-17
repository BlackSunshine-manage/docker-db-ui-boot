package com.company.project.employes;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

public interface ViewEmployeeController {
    String showAllEmployees(ModelMap model);

    String showAddEmployee(Model model);

    String addEmployee(Model model, EmployeeDto employee);

    String showEditEmployee(Model model, long employeeId);

    String updateEmployee(Model model,
                          @PathVariable Integer employeeId,
                          @ModelAttribute("employee") EmployeeDto employee);

    String showDeleteEmployeeById(ModelMap model,
                                  @PathVariable long employeeId);

    String deleteEmployeeById(ModelMap model,
                              @PathVariable Integer employeeId);

    String getNoteById(Model model, @PathVariable Integer employeeId);
}