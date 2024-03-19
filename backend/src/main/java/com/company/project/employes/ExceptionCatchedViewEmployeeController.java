package com.company.project.employes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionCatchedViewEmployeeController implements ViewEmployeeController {
    private final ViewEmployeeController standartViewEmployeeController;

    @Override
    public String showAllEmployees(ModelMap model) {
        return standartViewEmployeeController.showAllEmployees(model);
    }

    @Override
    public String showAddEmployee(Model model) {
        return standartViewEmployeeController.showAddEmployee(model);
    }

    @Override
    public String addEmployee(Model model, EmployeeDto employee) {
        try {
            return standartViewEmployeeController.addEmployee(model, employee);
        } catch (Exception exception) {
            String errorMessage = exception.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "employee-edit";
        }
    }

    @Override
    public String showEditEmployee(Model model, long employeeId) {
        EmployeeDto employee = null;
        try {
            return standartViewEmployeeController.showEditEmployee(model, employeeId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("employee", employee);
        return "employee-edit";
    }

    @Override
    public String updateEmployee(Model model,
                                 Integer employeeId,
                                 EmployeeDto employee) {
        try {
            return standartViewEmployeeController.updateEmployee(model, employeeId, employee);
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", false);
            return "employee-edit";
        }
    }

    @Override
    public String showDeleteEmployeeById(ModelMap model,
                                         long employeeId) {
        return standartViewEmployeeController.showDeleteEmployeeById(model, employeeId);
    }

    @Override
    public String deleteEmployeeById(ModelMap model, Integer employeeId) {
        return standartViewEmployeeController.deleteEmployeeById(model, employeeId);
    }

    @Override
    public String getNoteById(Model model, Integer employeeId) {
        try {
            standartViewEmployeeController.getNoteById(model, employeeId);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            model.addAttribute("errorMessage", ex.getMessage());
        }
        return "employee";
    }
}
