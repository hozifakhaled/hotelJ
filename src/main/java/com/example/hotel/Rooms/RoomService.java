package com.example.hotel.Rooms;

import com.example.hotel.shared.ApiException;
import com.example.hotel.shared.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoomService {
    @Autowired
    private RoomRepo roomRepo;

    public RoomEntity addRoom(RoomDto roomDto) {
        if (roomRepo.findByRoomNumber(roomDto.getRoomNumber()).isPresent()) {
            throw new ApiException("Room number already exists", HttpStatus.BAD_REQUEST);
        }
        RoomEntity room = new RoomEntity();
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setIsReversed(roomDto.isReversed());
        room.setImage(roomDto.getImage());
        room.setPrice(roomDto.getPrice());
        room.setType(roomDto.getType());
        room.setDescription(roomDto.getDescription());
        room.setAmenities(roomDto.getAmenities());

        return roomRepo.save(room);
    }

    public List<RoomEntity> getAllRooms() {
        List<RoomEntity> rooms= roomRepo.findAll();
        if(rooms.isEmpty()){
            throw new ApiException("No Rooms Found", HttpStatus.NOT_FOUND);

        }
        return rooms;
    }

    public RoomEntity updateRoom(UpdateRoomDto roomDto, Long roomNumber) {
        RoomEntity room = roomRepo.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new ApiException("Room Not Found", HttpStatus.NOT_FOUND));

        if (roomDto.getRoomNumber() != null) {
            room.setRoomNumber(roomDto.getRoomNumber());
        }
        if (roomDto.getIsReversed() != null) {
            room.setIsReversed(roomDto.getIsReversed());
        }
        if (roomDto.getImage() != null) {
            room.setImage(roomDto.getImage());
        }
        if (roomDto.getPrice() != null) {
            room.setPrice(roomDto.getPrice());
        }
        if (roomDto.getType() != null) {
            room.setType(roomDto.getType());
        }
        if (roomDto.getDescription() != null) {
            room.setDescription(roomDto.getDescription());
        }
        if (roomDto.getAmenities() != null) {
            room.setAmenities(roomDto.getAmenities());
        }

        return roomRepo.save(room);
    }


public RoomEntity getRoom(Long roomNumber) {
    RoomEntity room = roomRepo.findByRoomNumber(roomNumber)
            .orElseThrow(() -> new ApiException("Room Not Found", HttpStatus.NOT_FOUND));

    return room;
}

public List<RoomEntity> getRoomsByType(RoomTypes type) {
        List<RoomEntity> rooms = roomRepo.findByType(type).orElseThrow(() ->
                new ApiException("Room Not Found", HttpStatus.NOT_FOUND));

        return  rooms;
}


}
