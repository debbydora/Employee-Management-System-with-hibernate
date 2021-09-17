package com.example.employeemanagement.services;

import org.springframework.stereotype.Service;

@Service
public interface AdminSalaryService {
    void paySalaryByEmployeeId(long id);
}
