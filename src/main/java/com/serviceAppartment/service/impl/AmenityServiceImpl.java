package com.serviceAppartment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceAppartment.dto.AmenityDTO;
import com.serviceAppartment.model.Amenity;
import com.serviceAppartment.repository.AmenityRepository;
import com.serviceAppartment.service.AmenityService;

@Service
public class AmenityServiceImpl implements AmenityService {

	@Autowired
	private AmenityRepository amenityRepository;

	@Override
	public Amenity createOrUpdateAmenity(AmenityDTO amenityDto) {
		Amenity amenity = new Amenity();
		amenity.setId(amenityDto.getId());
		amenity.setName(amenityDto.getName());
		return amenityRepository.save(amenity);
	}

}
