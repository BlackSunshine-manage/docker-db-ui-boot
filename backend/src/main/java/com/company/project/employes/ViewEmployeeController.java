package com.company.project.employes;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

public interface ViewEmployeeController {
    String showAllEmployees(ModelMap model);

    String showAddEmployee(Model model);

    String addEmployee(Model model, EmployeeDto employee);

    String showEditEmployee(Model model, Integer employeeId);

    String updateEmployee(Model model,
                          Integer employeeId,
                          EmployeeDto employee);

    String showDeleteEmployeeById(ModelMap model,
                                  Integer employeeId);

    String deleteEmployeeById(ModelMap model,
                              Integer employeeId);

    String getNoteById(Model model, Integer employeeId);
}