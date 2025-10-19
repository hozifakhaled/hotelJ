package com.example.hotel.Rooms;
import com.example.hotel.shared.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private  RoomRepo roomRepo;
    @Autowired
    private  RoomMapper roomMapper;

    @Override
    public RoomEntity addRoom(RoomDto roomDto) {
        if (roomRepo.findByRoomNumber(roomDto.getRoomNumber()).isPresent()) {
            throw new ApiException("Room number already exists", HttpStatus.BAD_REQUEST);
        }
        RoomEntity room = roomMapper.fromDto(roomDto);
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

     roomMapper.updateEntity(room, roomDto);
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
