package com.serviceAppartment.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentDTO {
    private Long id;
    private String name;
    private String description;
    private String type;
    private double price; 
    private boolean available;
    private double size; 
    private int floor; 
    private int yearBuilt; 
    private String imageUrl;
    private AddressDTO address;
    private RoomDTO room;
    private List<AmenityDTO> amenities;
}
