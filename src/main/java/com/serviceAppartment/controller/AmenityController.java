package com.serviceAppartment.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceAppartment.dto.AmenityDTO;
import com.serviceAppartment.model.Amenity;
import com.serviceAppartment.service.AmenityService;

@RestController
@RequestMapping("/amenities")
public class AmenityController {

    private final AmenityService amenityService;

    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @PostMapping
    public ResponseEntity<Amenity> createAmenity(@RequestBody AmenityDTO amenityDto) {
        Amenity createdAmenity = amenityService.createOrUpdateAmenity(amenityDto);
        return ResponseEntity.created(URI.create("/amenities/" + createdAmenity.getId())).body(createdAmenity);
    }
}
