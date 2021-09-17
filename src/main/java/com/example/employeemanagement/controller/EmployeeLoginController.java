package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeLoginDto;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class EmployeeLoginController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String employeeLogin(Model model) {
        model.addAttribute("employeeLogin", new EmployeeLoginDto());
        System.out.println("checking");
        return "index";


    }

    @PostMapping("/Login")
    public String employeeLogin(@ModelAttribute("employeeLogin") EmployeeLoginDto employeeLoginDto, HttpSession httpSession, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "index";
        }
        Employee status = employeeRepository.getEmployeeByEmailAndPassword(employeeLoginDto.getEmail(), employeeLoginDto.getPassword());
        System.out.println("checking");
        if (status != null) {
            httpSession.setAttribute("employee", status);
                return "redirect:homePage";
        } else {
            return "index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
