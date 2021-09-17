package com.example.employeemanagement.services;


import com.example.employeemanagement.model.EmployeeLeave;

import java.util.List;

public interface EmployeeLeaveService {
    List<EmployeeLeave> getAllEmployeeLeave();
    EmployeeLeave getLeaveRequestById(long id);
    void leaveRequest(EmployeeLeave employeeLeave);
}
