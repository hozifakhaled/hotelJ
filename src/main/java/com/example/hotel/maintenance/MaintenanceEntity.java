package com.example.hotel.maintenance;

import com.example.hotel.Rooms.RoomEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "maintenance")
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)

     private RoomEntity room;
    private String issueTitle;      // Broken TV
    private String category;        // Electronics
    private String priority;        // Medium Priority
    private String status;          // Completed
    private String assignedTo;      // Sara Electronics
    private LocalDateTime estimatedTime;   // 1 hour
    private Double cost;            // 300.0
}
