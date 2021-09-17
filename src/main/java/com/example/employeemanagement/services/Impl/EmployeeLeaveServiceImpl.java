package com.example.employeemanagement.services.Impl;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeLeave;
import com.example.employeemanagement.repository.EmployeeLeaveRepository;
import com.example.employeemanagement.services.EmployeeLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService {

    @Autowired
    private EmployeeLeaveRepository employeeLeaveRepository;

    @Override
    public List<EmployeeLeave> getAllEmployeeLeave() {
        return employeeLeaveRepository.findAll();
    }

    @Override
    public EmployeeLeave getLeaveRequestById(long id) {
        Optional<EmployeeLeave> leave = employeeLeaveRepository.findById(id);
        EmployeeLeave employeeLeave;
        if(leave.isPresent()){
            employeeLeave= leave.get();

        } else {
            throw new RuntimeException("Request not found for id :: "+ id);
        }
        return employeeLeave;
    }

    @Override
    public void leaveRequest(EmployeeLeave employeeLeave) {
        employeeLeaveRepository.save(employeeLeave);
    }
}
