package com.example.hotel.maintenance;

import com.example.hotel.Rooms.RoomEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDto {
    private Long id;

    private RoomEntity room;
    private String issueTitle;      // Broken TV
    private String category;        // Electronics
    private String priority;        // Medium Priority
    private String status;          // Completed
    private String assignedTo;      // Sara Electronics
    private String estimatedTime;   // 1 hour
    private Double cost;
}
