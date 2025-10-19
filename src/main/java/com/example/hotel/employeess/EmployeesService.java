package com.example.hotel.employeess;

import com.example.hotel.Auth.AuthResponse;
import com.example.hotel.Auth.UserDto;
import com.example.hotel.Auth.UserEntity;
import com.example.hotel.Auth.UserRepo;
import com.example.hotel.Common.Enums.Role;
import com.example.hotel.Config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor

public class EmployeesService  {

    private final EmployeesRepo  employeesRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse createEmployee (EmployeesDto employeesDto ) {
        if (employeesRepo.existsByPhoneNumber(employeesDto.getPhoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "e already exists with this phone number.");
        }
        EmployeesEntity employeesEntity = new EmployeesEntity();
        employeesEntity.setUserName(employeesDto.getUserName());
        employeesEntity.setSalary(employeesDto.getSalary());
        employeesEntity.setPassword(passwordEncoder.encode(employeesDto.getPassword()));
        employeesEntity.setGander(employeesDto.getGander());
        employeesEntity.setAge(employeesDto.getAge());
        employeesEntity.setRole(Role.EMPLOYER);
        employeesEntity.setPhoneNumber(String.valueOf(employeesDto.getPhoneNumber()));


        if (employeesEntity.getRole() == null) {
            employeesEntity.setRole(Role.EMPLOYER);
        }

        EmployeesEntity savedEmployee = employeesRepo.save(employeesEntity);


        String token = jwtService.generateToken(savedEmployee);
        return new AuthResponse(token, savedEmployee.getRole().name());
    }



    public AuthResponse login(EmployeesDto employeesDto) {
        EmployeesEntity employees = employeesRepo.findByPhoneNumber(employeesDto.getPhoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "e not found"));

        if (!passwordEncoder.matches(employeesDto.getPassword(), employees.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }



        String token = jwtService.generateToken(employees);
        return new AuthResponse(token, employees.getRole().name());
    }

}
