package com.serviceAppartment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    
    private Long id;
    private Long apartmentId;
    private Long userId;
    private int rating;
    private String comment;
}
