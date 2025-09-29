package com.example.hotel.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<BookingEntity,Long> {
    Optional<BookingEntity> findById(Long id);
}
