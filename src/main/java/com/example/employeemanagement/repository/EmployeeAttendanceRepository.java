package com.example.employeemanagement.repository;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance,Long>{
//    Employee findEmployeeAttendanceByAttendance_id(Long id);
    List<EmployeeAttendance> findEmployeeAttendancesByDateBetweenAndEmployee(LocalDate startDate, LocalDate endDate, Employee employee);


    Optional<EmployeeAttendance> findEmployeeAttendanceByIdAndDate(long id, LocalDate localDate);
}
