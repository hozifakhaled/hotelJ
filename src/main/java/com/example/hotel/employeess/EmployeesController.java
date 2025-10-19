package com.example.hotel.employeess;

import com.example.hotel.Auth.AuthResponse;
import com.example.hotel.Auth.UserDto;
import com.example.hotel.Auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/empolyer")
@RequiredArgsConstructor
public class EmployeesController {
    @Autowired
    EmployeesService employeesService;
    @PostMapping("/register")
    public ResponseEntity<?>  register(@RequestBody EmployeesDto employeesDto) {
        AuthResponse employee = employeesService.createEmployee(employeesDto);
        return ResponseEntity.status(200).body(employee);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmployeesDto employeesDto) {
        AuthResponse employee = employeesService.login(employeesDto);

        return ResponseEntity.status(200).body(employee);
    }
}
