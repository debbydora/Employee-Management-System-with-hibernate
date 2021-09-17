package com.example.employeemanagement.services.Impl;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeSalary;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.repository.SalaryRepository;
import com.example.employeemanagement.services.AdminSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Optional;
@Service
public class AdminSalaryServiceImpl implements AdminSalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void paySalaryByEmployeeId(long id) {


        int monthValue = Calendar.getInstance().get(Calendar.MONTH);
        Month monthInView = Month.of(monthValue);
        Optional<EmployeeSalary> optionalSalary = salaryRepository.findEmployeeSalaryByMonthInViewAndEmployee_Id(monthInView,id);
        if (optionalSalary.isEmpty()) {
            Employee employee = employeeRepository.getById(id);
            EmployeeSalary salary = new EmployeeSalary();
            salary.setEmployee(employee);
           // salary.setAmountPaid(employee.getSalary());
            salary.setDatePaid(LocalDate.now());
            salary.setMonthInView(monthInView);
            salaryRepository.save(salary);
        } else {
            System.out.println("Salary already paid");
        }

    }
}
