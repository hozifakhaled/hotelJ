package com.example.hotel.maintenance;

import com.example.hotel.Rooms.RoomDto;
import com.example.hotel.Rooms.RoomEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maintenances/")
@RequiredArgsConstructor
public class MaintenanceController {
    @Autowired
    private  MaintenanceService maintenanceService;
    @PostMapping("addissuse")
    public ResponseEntity<?> addIssuse(@RequestBody @Valid MaintenanceDto maintenanceDto) {
        MaintenanceEntity savedIssuse = maintenanceService.addIssuse(maintenanceDto);
        return ResponseEntity.status(201).body(savedIssuse);
    }
@GetMapping ("allMaintenances")
    public ResponseEntity<?> allMaintenances() {
        return ResponseEntity.status(200).body(maintenanceService.allMaintenanceWork());
    }
}
