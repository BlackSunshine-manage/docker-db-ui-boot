package com.company.project.employes;

import com.company.project.utils.CollectionNotEmpty;
import com.company.project.utils.ObjectNotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
@Controller
@RequestMapping("/api/v2/employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StandartViewEmployeeController implements ViewEmployeeController {

    private final EmployeeController exceptionCatchedViewEmployeeController;

    //        @Override
    @GetMapping("/all")
    //200 - if true
    public String showAllEmployees(@ModelAttribute("model") ModelMap model) {
        ResponseEntity<List<Employee>> response = exceptionCatchedViewEmployeeController.getEmployees();
        model = model.addAttribute("employeeList", response.getBody());
        return "employees";
    }

    @GetMapping(value = "/add")
    public String showAddEmployee(Model model) {
        EmployeeDto employee = new EmployeeDto(null, new ProfileDto("firstName", "surname", 0));
        model.addAttribute("add", true);
        model.addAttribute("employee", employee);
        return "employee-edit";
    }

    @PostMapping(value = "/add")
    public String addEmployee(Model model,
                              @ModelAttribute("employee") EmployeeDto employee) {
        try {
            Employee newEmployee = new EmployeeMapper(employee).mapToEntity();
            exceptionCatchedViewEmployeeController.pushEmployees(newEmployee);
            return "redirect:/api/v2/employees/" + newEmployee.getId() + "/show";
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
            ResponseEntity<List<Employee>> response = exceptionCatchedViewEmployeeController.getEmployees((int) employeeId);
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
            exceptionCatchedViewEmployeeController.putEmployee(employee);
            ResponseEntity<List<Employee>> response = exceptionCatchedViewEmployeeController.getEmployees();
            model = model.addAttribute("employeeList", response.getBody());
            return "redirect:/api/v2/employees/" + employee.getId() + "/show";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", false);
            return "employee-edit";
        }
    }

    @GetMapping(value = {"/{employeeId}/delete"})
    public String showDeleteEmployeeById(@ModelAttribute("model") ModelMap model,
                                         @PathVariable long employeeId) {
        model = model.addAttribute("employeeList", exceptionCatchedViewEmployeeController.getEmployees().getBody());
        return "employees";
    }

    @PostMapping(value = {"/{employeeId}/delete"})
    public String deleteEmployeeById(@ModelAttribute("model") ModelMap model,
                                     @PathVariable Integer employeeId) {
        ResponseEntity<List<Employee>> response = exceptionCatchedViewEmployeeController.getEmployees(employeeId);
        Supplier<List<Employee>> getBodyFromResponse = response::getBody;
        new ObjectNotEmpty<>(employeeId,
                () -> new ObjectNotEmpty<>(response,
                        () -> new CollectionNotEmpty<>(getBodyFromResponse.get(),
                                () -> exceptionCatchedViewEmployeeController.deleteEmployee(getBodyFromResponse.get()
                                        .toArray(Employee[]::new)))));
        model = model.addAttribute("employeeList", exceptionCatchedViewEmployeeController.getEmployees().getBody());
        return "redirect:/api/v2/employees/all";
    }

    @Override
    @GetMapping(value = "/{employeeId}/show")
    public String getNoteById(Model model, @PathVariable Integer employeeId) {
        ResponseEntity<List<Employee>> response = exceptionCatchedViewEmployeeController.getEmployees(employeeId);
        Supplier<List<Employee>> getBodyFromResponse = response::getBody;
        Supplier<Employee> firstEmployee = () -> getBodyFromResponse.get()
                .get(0);
        Model[] models = new Model[] {model};
        try {
            new ObjectNotEmpty<>(employeeId,
                    () -> new ObjectNotEmpty<>(response,
                            () -> new CollectionNotEmpty<>(getBodyFromResponse.get(),
                                    () -> new ObjectNotEmpty<>(firstEmployee.get(),
                                            () -> models[0].addAttribute("employee", new EmployeeMapper(firstEmployee.get())
                                                    .mapToDto())))));
            models[0].addAttribute("allowDelete", false);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            model.addAttribute("errorMessage", ex.getMessage());
        }
        return "employee";
    }
}