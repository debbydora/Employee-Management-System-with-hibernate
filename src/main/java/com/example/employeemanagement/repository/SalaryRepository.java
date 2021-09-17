package com.example.employeemanagement.repository;

import com.example.employeemanagement.model.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.Optional;
@Repository
public interface SalaryRepository extends JpaRepository<EmployeeSalary,Long> {
    Optional<EmployeeSalary> findEmployeeSalaryByMonthInViewAndEmployee_Id(Month month, Long aLong);
}


