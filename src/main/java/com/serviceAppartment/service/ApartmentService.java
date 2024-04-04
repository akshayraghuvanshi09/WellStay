package com.serviceAppartment.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.serviceAppartment.dto.ApartmentDTO;

public interface ApartmentService {
	List<ApartmentDTO> getAllApartments();

	ApartmentDTO getApartmentById(Long id);

	public ApartmentDTO createOrUpdateApartment(ApartmentDTO apartmentDto, List<MultipartFile> images);

	ApartmentDTO updateApartment(Long id, ApartmentDTO apartmentDTO);

	void deleteApartment(Long id);

	public List<ApartmentDTO> getAvailableApartments(LocalDate checkInDate, LocalDate checkOutDate);

	public boolean isApartmentAvailable(Long apartmentId, LocalDate checkInDate, LocalDate checkOutDate);
}
