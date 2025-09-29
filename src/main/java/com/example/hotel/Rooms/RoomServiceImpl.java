package com.example.hotel.Rooms;

import com.example.hotel.shared.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;

    public RoomServiceImpl(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @Override
    public RoomEntity addRoom(RoomDto roomDto) {
        if (roomRepo.findByRoomNumber(roomDto.getRoomNumber()).isPresent()) {
            throw new ApiException("Room number already exists", HttpStatus.BAD_REQUEST);
        }
        RoomEntity room = new RoomEntity();
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setReversed(roomDto.isReversed());
        room.setImage(roomDto.getImage());
        room.setPrice(roomDto.getPrice());
        room.setType(roomDto.getType());
        room.setDescription(roomDto.getDescription());
        room.setAmenities(roomDto.getAmenities());

        return roomRepo.save(room);
    }

    @Override
    public List<RoomEntity> getAllRooms() {
        List<RoomEntity> rooms = roomRepo.findAll();
        if (rooms.isEmpty()) {
            throw new ApiException("No Rooms Found", HttpStatus.NOT_FOUND);
        }
        return rooms;
    }

    @Override
    public RoomEntity updateRoom(UpdateRoomDto roomDto, Long roomNumber) {
        RoomEntity room = roomRepo.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new ApiException("Room Not Found", HttpStatus.NOT_FOUND));

        if (roomDto.getRoomNumber() != null) {
            room.setRoomNumber(roomDto.getRoomNumber());
        }
        if (roomDto.getIsReversed() != null) {
            room.setReversed(roomDto.getIsReversed());
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
            room.setAmenities(new ArrayList<>(roomDto.getAmenities()));
        }

        return roomRepo.save(room);
    }

    @Override
    public RoomEntity getRoom(Long roomNumber) {
        return roomRepo.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new ApiException("Room Not Found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<RoomEntity> getRoomsByType(RoomTypes type) {
        return roomRepo.findByType(type)
                .orElseThrow(() -> new ApiException("Room Not Found", HttpStatus.NOT_FOUND));
    }
}
