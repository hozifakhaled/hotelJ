package com.example.hotel.Auth;

import com.example.hotel.Common.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private String phoneNumber;
    private int age;
    private Role role;
    private GenderEnums gander;


}
