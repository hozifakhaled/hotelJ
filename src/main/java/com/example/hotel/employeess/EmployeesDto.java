package com.example.hotel.employeess;

import com.example.hotel.Auth.GenderEnums;
import com.example.hotel.Common.Enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesDto {
    private Long id;
    private String userName;
    private String password;
    private String phoneNumber;
    private int age;
    private double salary;
    private Role role;
    private GenderEnums gander;
}
