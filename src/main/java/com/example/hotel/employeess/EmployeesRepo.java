package com.example.hotel.employeess;

import com.example.hotel.Auth.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeesRepo extends CrudRepository<EmployeesEntity, Long> {
    Optional< EmployeesEntity> findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);

}
