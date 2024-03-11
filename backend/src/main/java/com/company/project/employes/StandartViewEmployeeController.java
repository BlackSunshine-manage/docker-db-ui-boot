package com.company.project.employes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/v2/employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StandartViewEmployeeController implements ViewEmployeeController {

    private final EmployeeController standartEmployeeController;

    //        @Override
    @GetMapping("/all")
    //200 - if true
    public String showAllEmployees(String name,
                                   @ModelAttribute("model") ModelMap model) {
        ResponseEntity<List<Employee>> response = standartEmployeeController.getEmployees();
        model = model.addAttribute("employeeList", response.getBody());
        return "employees";
    }

    @GetMapping(value = "/add")
    public String showAddEmployee(Model model) {
        EmployeeDto employee = new EmployeeDto(null, new ProfileDto("", "", 0));
        model = model.addAttribute("add", true);
        model = model.addAttribute("employee", employee);
        return "employee-edit";
    }

    @PostMapping(value = "/add")
    public String addEmployee(Model model,
                              @ModelAttribute("employee") EmployeeDto employee) {
        try {
            Employee newEmployee = new EmployeeMapper(employee).mapToEntity();
            standartEmployeeController.pushEmployees(newEmployee);
            return "redirect:/api/v2/employees/" + newEmployee.getId();
        } catch (Exception exception) {
            String errorMessage = exception.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "employee-edit";
        }
    }

    @GetMapping(value = {"/{employeeId}/edit"})
    public String showEditEmployee(Model model,
                                   @PathVariable long employeeId) {
        EmployeeDto employee = null;
        try {
            ResponseEntity<List<Employee>> response = standartEmployeeController.getEmployees((int) employeeId);
            employee = new EmployeeMapper(response.getBody().get(0)).mapToDto();
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("add", false);
        model.addAttribute("employee", employee);
        return "employee-edit";
    }

    @PostMapping(value = {"/{employeeId}/edit"})
    public String updateEmployee(Model model,
                             @PathVariable Integer employeeId,
                             @ModelAttribute("employee") EmployeeDto employee) {
        try {
            employee.setId(employeeId);
            standartEmployeeController.putEmployee(employee);
            return "redirect:/api/v2/employees/" + employee.getId();
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", false);
            return "employee-edit";
        }
    }
//
//    @GetMapping(value = {"/{employeeId}/delete"})
//    public String showDeleteEmployeeById(Model model,
//                                         @PathVariable long employeeId) {
//        return "";
//    }
//
    @PostMapping(value = {"/{employeeId}/delete"})
    public String deleteEmployeeById(Model model,
                                     @PathVariable long employeeId) {
        return "";
    }
}