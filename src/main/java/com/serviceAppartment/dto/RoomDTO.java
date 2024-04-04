package com.serviceAppartment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private String name;
    private int bedrooms;
    private int bathrooms;
    private double squareFootage;
    private boolean available;
   
}
