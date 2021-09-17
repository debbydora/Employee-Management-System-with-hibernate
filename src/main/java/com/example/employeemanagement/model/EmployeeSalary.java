package com.example.employeemanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Month;
@Data
@Entity
public class EmployeeSalary {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long salaryId;
        private double amountPaid;
        private LocalDate datePaid;
        @Enumerated(EnumType.STRING)
        private Month monthInView;
        @ManyToOne
        private Employee employee;
    }

