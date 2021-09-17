package com.example.employeemanagement.controller;

import com.example.employeemanagement.services.AdminSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminSalaryController {
    @Autowired
    private AdminSalaryService adminSalaryService;

    @GetMapping("/paySalary/{id}")
    public String paySalary (@PathVariable(value = "id") long id) {
        adminSalaryService.paySalaryByEmployeeId(id);
        return "redirect:/adminDashboard";
    }
}
