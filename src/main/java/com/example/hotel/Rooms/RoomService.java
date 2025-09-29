package com.example.hotel.Rooms;

import java.util.List;

public interface RoomService {
    RoomEntity addRoom(RoomDto roomDto);
    List<RoomEntity> getAllRooms();
    RoomEntity updateRoom(UpdateRoomDto roomDto, Long roomNumber);
    RoomEntity getRoom(Long roomNumber);
    List<RoomEntity> getRoomsByType(RoomTypes type);
}
