package com.example.hotel.maintenance;

import com.example.hotel.Rooms.RoomEntity;
import com.example.hotel.Rooms.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class MaintenanceService {
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private MaintenanceRepo maintenanceRepo;

    public MaintenanceEntity addIssuse  (MaintenanceDto maintenanceDto){
        RoomEntity room = roomRepo.findByRoomNumber(maintenanceDto.getRoom().getRoomNumber()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "room Not Found"));;
        MaintenanceEntity maintenance = new MaintenanceEntity();
        maintenance.setCost(maintenanceDto.getCost());
        maintenance.setCategory(maintenanceDto.getCategory());
        maintenance.setAssignedTo(maintenanceDto.getAssignedTo());
        maintenance.setPriority(maintenanceDto.getPriority());
        maintenance.setEstimatedTime(LocalDateTime.now());
        maintenance.setIssueTitle(maintenanceDto.getIssueTitle());
        maintenance.setRoom(room);
        maintenance.setStatus("Pending");
        return maintenanceRepo.save(maintenance);
    }
   public List<MaintenanceEntity> allMaintenanceWork (){

        List<MaintenanceEntity> maintenances = (List<MaintenanceEntity>) maintenanceRepo.findAll();
        return maintenances ;
   }
}
