package com.serviceAppartment.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.serviceAppartment.Utils.ApartmentUtils;
import com.serviceAppartment.dto.ApartmentDTO;
import com.serviceAppartment.model.Address;
import com.serviceAppartment.model.Amenity;
import com.serviceAppartment.model.Apartment;
import com.serviceAppartment.model.Booking;
import com.serviceAppartment.model.Image;
import com.serviceAppartment.model.Room;
import com.serviceAppartment.repository.AmenityRepository;
import com.serviceAppartment.repository.ApartmentRepository;
import com.serviceAppartment.repository.BookingRepository;
import com.serviceAppartment.repository.ImageRepository;
import com.serviceAppartment.service.ApartmentService;


@Service
public class ApartmentServiceImpl implements ApartmentService {
	
	@Autowired
	private ImageRepository imageRepository;

	
	@Autowired
	private AmenityRepository amenityRepository;

	@Autowired
	private ApartmentRepository apartmentRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public List<ApartmentDTO> getAllApartments() {
		List<Apartment> apartments = apartmentRepository.findAll();
		return apartments.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public ApartmentDTO getApartmentById(Long id) {
		Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
		return optionalApartment.map(this::convertToDto).orElse(null);
	}

	@Override
    public ApartmentDTO createOrUpdateApartment(ApartmentDTO apartmentDto,List<MultipartFile> images) {
		
		
        Apartment apartment = new Apartment();
        apartment.setId(apartmentDto.getId());
        apartment.setName(apartmentDto.getName());
        apartment.setDescription(apartmentDto.getDescription());
        apartment.setType(apartmentDto.getType());
        apartment.setPrice(apartmentDto.getPrice());
        apartment.isAvailable();
        apartment.setFloor(apartmentDto.getFloor());
        apartment.setSize(apartmentDto.getSize());
        apartment.setFloor(apartmentDto.getFloor());
        apartment.setYearBuilt(apartmentDto.getYearBuilt());
      
        
        
        // Map AddressDto to Address entity
        Address address = new Address();
        address.setId(apartmentDto.getAddress().getId());
        address.setStreet(apartmentDto.getAddress().getStreet());
        address.setCity(apartmentDto.getAddress().getCity());
        address.setState(apartmentDto.getAddress().getState());
        address.setZipCode(apartmentDto.getAddress().getZipCode());

        // Map RoomDto to Room entity
        Room room = new Room();
        room.setId(apartmentDto.getRoom().getId());
        room.setName(apartmentDto.getRoom().getName());
        room.setBedrooms(apartmentDto.getRoom().getBedrooms());
        room.setBathrooms(apartmentDto.getRoom().getBathrooms());
        room.setSquareFootage(apartmentDto.getRoom().getSquareFootage());
        room.setAvailable(apartmentDto.getRoom().isAvailable());

        // Map AmenityDto list to Amenity entities
        List<Amenity> amenities = apartmentDto.getAmenities().stream()
                .map(amenityDto -> {
                    Amenity amenity = new Amenity();
                    amenity.setId(amenityDto.getId());
                    amenity.setName((amenityRepository.findById(amenityDto.getId())).get().getName());
                    return amenity;
                })
                .collect(Collectors.toList());

        apartment.setAddress(address);
        apartment.setRoom(room);
        apartment.setAmenities(amenities);

        Apartment apartments = apartmentRepository.save(apartment);
        
        
        for (MultipartFile imageFile : images) {
            try {
                String imagePath = ApartmentUtils.uploadImage(imageFile);
               
                Image image = new Image();
                image.setImagePath(imagePath);
                image.setApartment(apartment);
                imageRepository.save(image);
            } catch (IOException e) {
                // Handle image upload error
            }
        
      
    }
        return convertToDto(apartments);
        
	}


	@Override
	@Transactional
	public ApartmentDTO updateApartment(Long id, ApartmentDTO apartmentDto) {
		Apartment existingApartment = apartmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Apartment not found with id: " + id));

		// Update the existing apartment entity with the new data
		existingApartment.setName(apartmentDto.getName());
		existingApartment.setDescription(apartmentDto.getDescription());
		existingApartment.setType(apartmentDto.getType());

		// Save the updated apartment
		Apartment updatedApartment = apartmentRepository.save(existingApartment);
		return convertToDto(updatedApartment);
	}

	@Override
	@Transactional
	public void deleteApartment(Long id) {
		apartmentRepository.deleteById(id);
	}

	// Helper method to convert Apartment entity to ApartmentDto
	private ApartmentDTO convertToDto(Apartment apartment) {
		return modelMapper.map(apartment, ApartmentDTO.class);
	}

	// Helper method to convert ApartmentDto to Apartment entity
	private Apartment convertToEntity(ApartmentDTO apartmentDto) {
		return modelMapper.map(apartmentDto, Apartment.class);
	}
	
	public List<ApartmentDTO> getAvailableApartments(LocalDate checkInDate, LocalDate checkOutDate) {
		// Fetch all apartments
		List<ApartmentDTO> apartmentDTOs = getAllApartments();
		List<Apartment> allApartments = new ArrayList<>();
		for (ApartmentDTO apartmentDto : apartmentDTOs) {
			Apartment apartment = modelMapper.map(apartmentDto, Apartment.class);
			allApartments.add(apartment);
		}

		// Filter out apartments that are available for the given check-in and check-out
		// dates
		List<Apartment> availableApartments = allApartments.stream()
				.filter(apartment -> isApartmentAvailable(apartment.getId(), checkInDate, checkOutDate))
				.collect(Collectors.toList());

		List<ApartmentDTO> availableApartmentsList = availableApartments.stream().map(e->modelMapper.map(e,ApartmentDTO.class)).collect(Collectors.toList());
		
		
		return availableApartmentsList;
	}



	@Override
	public boolean isApartmentAvailable(Long apartmentId, LocalDate checkInDate, LocalDate checkOutDate) {
		// Fetch bookings for the apartment
				List<Booking> bookings = bookingRepository.findByApartmentId(apartmentId);

				// Check if there are any bookings overlapping with the given dates
				for (Booking booking : bookings) {
					LocalDate bookingCheckInDate = booking.getCheckInDate();
					LocalDate bookingCheckOutDate = booking.getCheckOutDate();
					if (checkInDate.isBefore(bookingCheckOutDate) && checkOutDate.isAfter(bookingCheckInDate)) {
						// Dates overlap, apartment is not available
						return false;
					}
				}

				return true;
	}
}
