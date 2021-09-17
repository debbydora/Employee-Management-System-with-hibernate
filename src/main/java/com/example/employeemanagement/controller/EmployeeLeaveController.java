package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeLeave;
import com.example.employeemanagement.enums.LeaveStatus;
import com.example.employeemanagement.repository.EmployeeLeaveRepository;
import com.example.employeemanagement.services.EmployeeLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class EmployeeLeaveController {
    @Autowired
    private EmployeeLeaveRepository employeeLeaveRepository;
    @Autowired
    private EmployeeLeaveService employeeLeaveService;

    @GetMapping("/leaveRequestForm")
    public String leaveRequest(Model model){
        model.addAttribute("leaveRequest",new EmployeeLeave());
        return "leaveRequest";

    }

    @PostMapping("/displayEmployeeRequest")
    public String showEmployeeLeave(@ModelAttribute("leaveRequest") EmployeeLeave employeeLeave, HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        employeeLeave.setEmployee(employee);
        employeeLeave.setLeaveStatus(LeaveStatus.PENDING);
        employeeLeaveRepository.save(employeeLeave);
        return "redirect:/homePage";

    }

    @GetMapping("/adminViewEmployeeLeaveRequest")
    public String adminViewEmployeeLeaveRequest(Model model) {
        model.addAttribute("employeeLeaveList", employeeLeaveService.getAllEmployeeLeave());
        return "managementLeaveDashboard";
    }

    @GetMapping("/approveEmployeeLeaveRequest/{id}")
    public String approveEmployeeLeaveRequest(@PathVariable(value = "id") long id){
        EmployeeLeave employeeLeave = employeeLeaveService.getLeaveRequestById(id);
        employeeLeave.setLeaveStatus(LeaveStatus.APPROVED);
        employeeLeaveService.leaveRequest(employeeLeave);
        return "redirect:/adminViewEmployeeLeaveRequest";

    }

    @GetMapping("/rejectEmployeeLeaveRequest/{id}")
    public String rejectLeaveRequest(@PathVariable(value = "id") long id){
        EmployeeLeave employeeLeave = employeeLeaveService.getLeaveRequestById(id);
        employeeLeave.setLeaveStatus(LeaveStatus.REJECTED);
        employeeLeaveService.leaveRequest(employeeLeave);
        return "redirect:/adminViewEmployeeLeaveRequest";


    }
    @GetMapping("/viewEmployeeLeaveStatus")
    public String viewEmployeeLeaveRequestStatus(Model model, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        model.addAttribute("viewEmployeeLeaveStatus", employeeLeaveRepository.findEmployeeLeaveByEmployee_Id(employee.getId()));
        return "EmployeeLeaveStatus";
    }

}
