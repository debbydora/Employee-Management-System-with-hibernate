package com.example.employeemanagement.model;

import com.example.employeemanagement.enums.AttendanceStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Entity
public class EmployeeAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private LocalTime time;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus attendanceStatus;
    @ManyToOne
    private Employee employee;


}
