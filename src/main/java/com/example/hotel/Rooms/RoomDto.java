package com.example.hotel.Rooms;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    @NotNull(message = "Room number is required")
    @Positive(message = "Room number must be positive")
    private Long roomNumber;

    private boolean isReversed = false;

    @NotBlank(message = "Image URL is required")
    private String image;

    @Positive(message = "Price must be greater than zero")
    private double price;

    @NotNull(message = "Room type is required")
    private RoomTypes type;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotEmpty(message = "Amenities must not be empty")
    private Set<@NotBlank(message = "Amenity must not be blank") String> amenities;
}
