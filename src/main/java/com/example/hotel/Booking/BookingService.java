package com.example.hotel.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepo bookingRepo;

    public String AddBooking(BookingDto bookingdto){
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setCheckOut(bookingdto.getCheckOut());
        bookingEntity.setCheckIn(bookingdto.getCheckIn());
        bookingEntity.setPaymentMethod(bookingdto.getPaymentMethod());
        bookingEntity.setNumberOfDays(bookingdto.getNumberOfDays());

        bookingRepo.save(bookingEntity);
        return "Booking has been added successfully";
    }


}
