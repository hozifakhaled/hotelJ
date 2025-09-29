package com.example.hotel.Rooms;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RoomMapper {
    public RoomEntity fromDto(RoomDto dto) {
        RoomEntity room = new RoomEntity();
        room.setRoomNumber(dto.getRoomNumber());
        room.setReversed(dto.isReversed());
        room.setImage(dto.getImage());
        room.setPrice(dto.getPrice());
        room.setType(dto.getType());
        room.setDescription(dto.getDescription());
        room.setAmenities(dto.getAmenities());
        return room;
    }

    public void updateEntity(RoomEntity room, UpdateRoomDto dto) {
        if (dto.getRoomNumber() != null) room.setRoomNumber(dto.getRoomNumber());
        if (dto.getIsReversed() != null) room.setReversed(dto.getIsReversed());
        if (dto.getImage() != null) room.setImage(dto.getImage());
        if (dto.getPrice() != null) room.setPrice(dto.getPrice());
        if (dto.getType() != null) room.setType(dto.getType());
        if (dto.getDescription() != null) room.setDescription(dto.getDescription());
        if (dto.getAmenities() != null) room.setAmenities(new ArrayList<>(dto.getAmenities()));
    }
}
