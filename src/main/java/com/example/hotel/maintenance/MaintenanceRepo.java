package com.example.hotel.maintenance;

import com.example.hotel.employeess.EmployeesEntity;
import org.springframework.data.repository.CrudRepository;

public interface MaintenanceRepo  extends CrudRepository<MaintenanceEntity, Long> {

}
