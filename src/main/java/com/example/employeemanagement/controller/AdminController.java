package com.example.employeemanagement.controller;


import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("homePage")
    public String viewHomePage(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee.getUserRoles().equalsIgnoreCase("admin")) {
            model.addAttribute("listEmployees", employeeService.getAllEmployees());
            return "adminDashboard";
        } else {
            model.addAttribute("employee", employee);
            return "employeeDashboard";
        }

    }


    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }


    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/homePage";

    }
    @GetMapping("/showEmployeeFormForUpdate/{id}")
    public String showEmployeeFormForUpdate(@PathVariable(value = "id") long id, Model model){
        //get employee from the service
        Employee employee = employeeService.getEmployeeById(id);
        //set employee as a model attribute to prepopulate the form
        model.addAttribute("employee", employee);
        return "update_employee";

    }
}





