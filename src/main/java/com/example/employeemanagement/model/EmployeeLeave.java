package com.example.employeemanagement.model;

import com.example.employeemanagement.enums.LeaveStatus;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
@Data
@Entity
public class EmployeeLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long leaveId;
    private Date leaveStartDate;
    private Date leaveEndDate;
    private String leaveType;
    @Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Employee employee;
}
