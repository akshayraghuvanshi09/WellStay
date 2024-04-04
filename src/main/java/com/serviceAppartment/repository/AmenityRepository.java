package com.serviceAppartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceAppartment.model.Amenity;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}