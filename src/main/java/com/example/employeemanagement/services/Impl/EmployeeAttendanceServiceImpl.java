package com.example.employeemanagement.services.Impl;

import com.example.employeemanagement.enums.AttendanceStatus;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.EmployeeAttendance;
import com.example.employeemanagement.repository.EmployeeAttendanceRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.services.EmployeeAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {
    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;

    @Override
    public void markAttendance(Employee employee) {
        LocalTime resumptionTime= LocalTime.parse("08:00:00.000");
        LocalTime closingTime= LocalTime.parse("10:00:00.000");
        LocalDate localDate= LocalDate.now();
        LocalTime localTime= LocalTime.now();
        Optional<EmployeeAttendance> attendance = employeeAttendanceRepository.
                findEmployeeAttendanceByIdAndDate(employee.getId(),localDate);
        if (attendance.isEmpty()) {
            EmployeeAttendance employeeAttendance = new EmployeeAttendance();
            employeeAttendance.setEmployee(employee);
            if (localTime.isAfter(resumptionTime) && localTime.isAfter(closingTime)) {
                employeeAttendance.setAttendanceStatus(AttendanceStatus.ABSENT);
            } else if (localTime.isBefore(resumptionTime)){
                employeeAttendance.setAttendanceStatus(AttendanceStatus.PRESENT);
            } else {
                employeeAttendance.setAttendanceStatus(AttendanceStatus.LATE);
            }
            employeeAttendance.setDate(localDate);
            employeeAttendance.setTime(localTime);
            employeeAttendanceRepository.save(employeeAttendance);
        } else {
            System.out.println("marked already");
        }
    }

    @Override
    public List<EmployeeAttendance> viewAttendanceList(LocalDate startDate, LocalDate endDate, Employee employee) {
        return employeeAttendanceRepository.findEmployeeAttendancesByDateBetweenAndEmployee(startDate, endDate, employee);
    }
//    @Override
//    public void markAttendance(EmployeeAttendance employeeAttendance) {
//        employeeAttendanceRepository.save(employeeAttendance);
//    }
}
