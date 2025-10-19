package com.example.hotel.Rooms;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomServiceImpl roomServiceimpl;

    @PostMapping("/addRoom")
    public ResponseEntity<?> addRoom(@RequestBody @Valid RoomDto roomDto) {
        RoomEntity savedRoom = roomServiceimpl.addRoom(roomDto);
        return ResponseEntity.status(201).body(savedRoom);
    }

    @GetMapping("/getRooms")
    public ResponseEntity<?> getAllRooms(){
        List<RoomEntity> rooms= roomServiceimpl.getAllRooms();
        return ResponseEntity.status(200).body(rooms);
    }

    @PutMapping("/updateRoom/{roomNumber}")
    public ResponseEntity<?> updateRoom(@RequestBody @Valid UpdateRoomDto updateRoomDto, @PathVariable Long roomNumber){
        RoomEntity savedRoom = roomServiceimpl.updateRoom(updateRoomDto,roomNumber);
        return ResponseEntity.status(200).body(savedRoom);
    }

    @GetMapping("/getRoomByid/{roomNumber}")
    public ResponseEntity<?> getRoomByid(@PathVariable Long roomNumber){
        RoomEntity room =roomServiceimpl.getRoom(roomNumber);
        return ResponseEntity.status(200).body(room);
    }

    @GetMapping("/getRoomsByType/{type}")
    public ResponseEntity<?> getRoomsByType(@PathVariable RoomTypes type){

        List<RoomEntity> rooms= roomServiceimpl.getRoomsByType(type);
        return ResponseEntity.status(200).body(rooms);
    }
}
