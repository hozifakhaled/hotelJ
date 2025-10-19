package com.example.hotel.Rooms;

import com.example.hotel.Rooms.RoomTypes;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "rooms")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private Long roomNumber;

    private boolean isReversed = false;

    private String image;

    private double price;

    @Enumerated(EnumType.STRING)
    private RoomTypes type;

    private String description;

    @Column(name = "amenities", columnDefinition = "TEXT")
    private String amenitiesJson; // يخزن JSON في العمود

    @Transient
    public List<String> getAmenities() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(amenitiesJson, List.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void setAmenities(Collection<String> amenities) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.amenitiesJson = mapper.writeValueAsString(amenities);
        } catch (Exception e) {
            this.amenitiesJson = "[]";
        }
    }

}
