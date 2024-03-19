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
@RequestMapping("/api/v2/employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionCatchedViewEmployeeController implements ViewEmployeeController {
    private final ViewEmployeeController standartViewEmployeeController;

    @Override
    @GetMapping("/all")
    public String showAllEmployees(@ModelAttribute("model") ModelMap model) {
        return standartViewEmployeeController.showAllEmployees(model);
    }

    @Override
    @GetMapping(value = "/add")
    public String showAddEmployee(Model model) {
        return standartViewEmployeeController.showAddEmployee(model);
    }

    @Override
    @PostMapping(value = "/add")
    public String addEmployee(Model model, @ModelAttribute("employee") EmployeeDto employee) {
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
    @GetMapping(value = {"/{employeeId}/edit"})
    public String showEditEmployee(Model model, @PathVariable long employeeId) {
        EmployeeDto employee = null;
        try {
//            ResponseEntity<List<Employee>> response = exceptionCatchedViewEmployeeController.getEmployees((int) employeeId);
//            employee = new EmployeeMapper(response.getBody().get(0)).mapToDto();
            return standartViewEmployeeController.showEditEmployee(model, employeeId);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("employee", employee);
        return "employee-edit";
    }

    @Override
    @PostMapping(value = {"/{employeeId}/edit"})
    public String updateEmployee(Model model,
                                 @PathVariable Integer employeeId,
                                 @ModelAttribute("employee") EmployeeDto employee) {
        try {
//            employee.setId(employeeId);
//            exceptionCatchedViewEmployeeController.putEmployee(employee);
//            ResponseEntity<List<Employee>> response = exceptionCatchedViewEmployeeController.getEmployees();
//            model = model.addAttribute("employeeList", response.getBody());
//            return "redirect:/api/v2/employees/" + employee.getId() + "/show";
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
    @GetMapping(value = {"/{employeeId}/delete"})
    public String showDeleteEmployeeById(@ModelAttribute("model") ModelMap model,
                                         @PathVariable long employeeId) {
        return standartViewEmployeeController.showDeleteEmployeeById(model, employeeId);
    }

    @Override
    public String deleteEmployeeById(ModelMap model, Integer employeeId) {
        return standartViewEmployeeController.deleteEmployeeById(model, employeeId);
    }

    @Override
    @GetMapping(value = "/{employeeId}/show")
    public String getNoteById(Model model, @PathVariable Integer employeeId) {
        try {
            standartViewEmployeeController.getNoteById(model, employeeId);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            model.addAttribute("errorMessage", ex.getMessage());
        }
        return "employee";
    }
}
