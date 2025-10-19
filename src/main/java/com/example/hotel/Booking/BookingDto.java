package com.example.hotel.Booking;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    @NotNull(message = "Check-in date is required")
    @FutureOrPresent(message = "Check-in date must be today or in the future")
    private Date checkIn;

    @NotNull(message = "Check-out date is required")
    @Future(message = "Check-out date must be in the future")
    private Date checkOut;

    @Min(value = 1, message = "Booking must be at least 1 day")
    @Max(value = 60, message = "Booking cannot exceed 60 days")
    private int numberOfDays;

    @NotBlank(message = "Payment method is required")
    @Pattern(regexp = "^(CASH|CREDIT_CARD|PAYPAL)$",
            message = "Payment method must be CASH, CREDIT_CARD, or PAYPAL")
    private String paymentMethod;
}
