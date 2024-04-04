package com.serviceAppartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceAppartment.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}