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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StandartViewEmployeeController implements ViewEmployeeController {

    private final EmployeeController standartEmployeeController;

    //        @Override
    //200 - if true
    public String showAllEmployees(ModelMap model) {
        ResponseEntity<List<Employee>> response = standartEmployeeController.getEmployees();
        model = model.addAttribute("employeeList", response.getBody());
        return "employees";
    }

    public String showAddEmployee(Model model) {
        EmployeeDto employee = new EmployeeDto(null, new ProfileDto("firstName", "surname", 0));
        model.addAttribute("add", true);
        model.addAttribute("employee", employee);
        return "employee-edit";
    }

    public String addEmployee(Model model,
                              EmployeeDto employee) {
        Employee newEmployee = new EmployeeMapper(employee).mapToEntity();
        standartEmployeeController.pushEmployees(newEmployee);
        return "redirect:/api/v2/employees/" + newEmployee.getId() + "/show";
    }

    public String showEditEmployee(Model model,
                                   long employeeId) {
        EmployeeDto employee = null;
        ResponseEntity<List<Employee>> response = standartEmployeeController.getEmployees((int) employeeId);
        employee = new EmployeeMapper(response.getBody().get(0)).mapToDto();
        model.addAttribute("add", false);
        model.addAttribute("employee", employee);
        return "employee-edit";
    }

    public String updateEmployee(Model model,
                                 Integer employeeId,
                                 EmployeeDto employee) {
        employee.setId(employeeId);
        standartEmployeeController.putEmployee(employee);
        ResponseEntity<List<Employee>> response = standartEmployeeController.getEmployees();
        model = model.addAttribute("employeeList", response.getBody());
        return "redirect:/api/v2/employees/" + employee.getId() + "/show";
    }

    public String showDeleteEmployeeById(ModelMap model,
                                         long employeeId) {
        model = model.addAttribute("employeeList", standartEmployeeController.getEmployees().getBody());
        return "employees";
    }

    @PostMapping(value = {"/{employeeId}/delete"})
    public String deleteEmployeeById(@ModelAttribute("model") ModelMap model,
                                     @PathVariable Integer employeeId) {
        ResponseEntity<List<Employee>> response = standartEmployeeController.getEmployees(employeeId);
        Supplier<List<Employee>> getBodyFromResponse = response::getBody;
        new ObjectNotEmpty<>(employeeId,
                () -> new ObjectNotEmpty<>(response,
                        () -> new CollectionNotEmpty<>(getBodyFromResponse.get(),
                                () -> standartEmployeeController.deleteEmployee(getBodyFromResponse.get()
                                        .toArray(Employee[]::new)))));
        model = model.addAttribute("employeeList", standartEmployeeController.getEmployees().getBody());
        return "redirect:/api/v2/employees/all";
    }

    @Override
    public String getNoteById(Model model, @PathVariable Integer employeeId) {
        ResponseEntity<List<Employee>> response = standartEmployeeController.getEmployees(employeeId);
        Supplier<List<Employee>> getBodyFromResponse = response::getBody;
        Supplier<Employee> firstEmployee = () -> getBodyFromResponse.get()
                .get(0);
        Model[] models = new Model[]{model};
        new ObjectNotEmpty<>(employeeId,
                () -> new ObjectNotEmpty<>(response,
                        () -> new CollectionNotEmpty<>(getBodyFromResponse.get(),
                                () -> new ObjectNotEmpty<>(firstEmployee.get(),
                                        () -> models[0].addAttribute("employee", new EmployeeMapper(firstEmployee.get())
                                                .mapToDto())))));
        models[0].addAttribute("allowDelete", false);
        return "employee";
    }
}