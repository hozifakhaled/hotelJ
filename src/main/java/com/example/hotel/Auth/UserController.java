package com.example.hotel.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public AuthResponse register(@RequestBody UserDto user) {
        return userService.createUser(user);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserDto user) {
        return userService.login(user);
    }

}
