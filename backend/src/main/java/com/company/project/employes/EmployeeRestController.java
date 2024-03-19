package com.company.project.employes;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v2/employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeRestController implements ViewEmployeeController {
    private final ViewEmployeeController exceptionCatchedViewEmployeeController;
    @Override
    @GetMapping("/all")
    public String showAllEmployees(@ModelAttribute("model") ModelMap model) {
        return exceptionCatchedViewEmployeeController.showAllEmployees(model);
    }

    @Override
    @GetMapping(value = "/add")
    public String showAddEmployee(Model model) {
        return exceptionCatchedViewEmployeeController.showAddEmployee(model);
    }

    @Override
    @PostMapping(value = "/add")
    public String addEmployee(Model model,  @ModelAttribute("employee") EmployeeDto employee) {
        return exceptionCatchedViewEmployeeController.addEmployee(model, employee);
    }

    @Override
    @GetMapping(value = {"/{employeeId}/edit"})
    public String showEditEmployee(Model model, @PathVariable Integer employeeId) {
        return exceptionCatchedViewEmployeeController.showEditEmployee(model, employeeId);
    }

    @Override
    @PostMapping(value = {"/{employeeId}/edit"})
    public String updateEmployee(Model model,
                                 @PathVariable Integer employeeId,
                                 @ModelAttribute("employee") EmployeeDto employee) {
        return exceptionCatchedViewEmployeeController.updateEmployee(model, employeeId, employee);
    }

    @Override
    @GetMapping(value = {"/{employeeId}/delete"})
    public String showDeleteEmployeeById(@ModelAttribute("model") ModelMap model,
                                         @PathVariable Integer employeeId) {
        return exceptionCatchedViewEmployeeController.showDeleteEmployeeById(model, employeeId);
    }

    @Override
    @PostMapping(value = {"/{employeeId}/delete"})
    public String deleteEmployeeById(@ModelAttribute("model") ModelMap model,
                                     @PathVariable Integer employeeId) {
        return exceptionCatchedViewEmployeeController.deleteEmployeeById(model, employeeId);
    }

    @Override
    @GetMapping(value = "/{employeeId}/show")
    public String getNoteById(Model model, @PathVariable Integer employeeId) {
        return exceptionCatchedViewEmployeeController.getNoteById(model, employeeId);
    }
}
