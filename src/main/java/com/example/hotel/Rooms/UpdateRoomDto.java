package com.example.hotel.Rooms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoomDto {

    private Long roomNumber;  // مش إجباري
    private Boolean isReversed=false;
    private String image;
    private Double price;
    private RoomTypes type;
    private String description;
    private Set<String> amenities;
}
