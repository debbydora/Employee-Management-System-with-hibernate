package com.example.employeemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "first_name")
  @NonNull
  private String firstName;

  @Column(name = "last_name")
  @NonNull
  private String lastName;

  @Column(name = "email")
  @NonNull
  private String email;

  @NonNull
  private Date dob;

  @Column(name = "phone_number")
  @NonNull
  private String phoneNumber;

  @Column(name = "password")
  @NonNull
  private String password;

  @Column(name = "department")
  private String department;

  private String userRoles;

}
