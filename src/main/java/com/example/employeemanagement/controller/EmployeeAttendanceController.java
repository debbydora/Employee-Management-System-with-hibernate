package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeAttendanceDto;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeAttendance;
import com.example.employeemanagement.repository.EmployeeAttendanceRepository;
import com.example.employeemanagement.services.EmployeeAttendanceService;
import com.example.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class EmployeeAttendanceController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;

    @Autowired
    private EmployeeAttendanceService employeeAttendanceService;

    @GetMapping(value = "/saveAttendance")
    public String markAttendance(HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");
        employeeAttendanceService.markAttendance(employee);
        return "redirect:/employeeDashboard";
    }

    @GetMapping("/viewAttendanceList/{id}")
    public String viewAttendanceSheet(Model model, @PathVariable(name = "id") long id) {
        EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();
        employeeAttendanceDto.setEmployeeId(id);
        model.addAttribute("attendanceDisplay", employeeAttendanceDto);
        return "attendanceSheet";

    }

    @PostMapping("/postAttendanceList")
    public String displayAttendanceSheet(@ModelAttribute("attendanceDisplay") EmployeeAttendanceDto employeeAttendanceDto, Model model, @RequestParam(value = "id") long id) {
        Employee employee = employeeService.getEmployeeById(id);
        LocalDate date1 = LocalDate.parse(employeeAttendanceDto.getStartDate());
        LocalDate date2 = LocalDate.parse(employeeAttendanceDto.getEndDate());
        List<EmployeeAttendance> attendanceList = employeeAttendanceService.viewAttendanceList(date1, date2, employee);
        model.addAttribute("attendanceList", attendanceList);
        return "displayAttendance";


    }
}