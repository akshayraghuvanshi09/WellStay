package com.serviceAppartment.service;

import com.serviceAppartment.dto.AmenityDTO;
import com.serviceAppartment.model.Amenity;

public interface AmenityService {
	 Amenity createOrUpdateAmenity(AmenityDTO amenityDto);

//	AmenityDTO getAmenityById(Long id);
//
//	List<AmenityDTO> getAllAmenities();
//
//	AmenityDTO updateAmenity(Long id, AmenityDTO amenityDTO);
//
//	void deleteAmenity(Long id);
}
