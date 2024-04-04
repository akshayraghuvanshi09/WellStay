package com.serviceAppartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceAppartment.model.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

}
