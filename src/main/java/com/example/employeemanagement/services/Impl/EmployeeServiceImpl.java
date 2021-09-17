package com.example.employeemanagement.services.Impl;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();

    }

    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);


    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent()){
            employee = optional.get();

        } else {
            throw new RuntimeException("Employee not found for id :: "+ id);
        }
        return employee;
    }

//    public Employee getEmployeeByEmailAndPassword(String email,String password){
//        return employeeRepository.getEmployeeByEmailAndPassword(email, password);
//    }
}
