package com.example.hotel.Rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<RoomEntity,Long> {
    Optional<RoomEntity> findByRoomNumber(Long roomNumber);
    Optional<List<RoomEntity>> findByType(RoomTypes type);

}
