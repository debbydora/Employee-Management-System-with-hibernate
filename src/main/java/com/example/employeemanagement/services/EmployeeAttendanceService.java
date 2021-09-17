package com.example.employeemanagement.services;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeAttendance;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EmployeeAttendanceService {
    void markAttendance(Employee employe);
    List<EmployeeAttendance> viewAttendanceList(LocalDate startDate, LocalDate endDate, Employee employee);
    //String viewAtttendanceSheet(Model model);
}
