package com.example.hotel.Auth;

import com.example.hotel.Common.Enums.Role;
import com.example.hotel.Config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse createUser (UserDto userDto ){
        if (userRepo.existsByPhoneNumber(userDto.getPhoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists with this phone number.");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDto.getUserName());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setGander(userDto.getGander());
        userEntity.setAge(userDto.getAge());
        userEntity.setRole(Role.CLIENT);
        userEntity.setPhoneNumber(String.valueOf(userDto.getPhoneNumber()));


        if (userEntity.getRole() == null) {
            userEntity.setRole(Role.CLIENT);
        }

        UserEntity savedUser = userRepo.save(userEntity);


        String token = jwtService.generateToken(savedUser);
        return new AuthResponse(token, savedUser.getRole().name());
    }



    public AuthResponse login(UserDto userDto) {
        UserEntity user = userRepo.findByPhoneNumber(userDto.getPhoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }



        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getRole().name());
    }

}
