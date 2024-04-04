package com.serviceAppartment.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceAppartment.dto.BookingRequestDTO;
import com.serviceAppartment.dto.BookingResponseDto;
import com.serviceAppartment.exceptions.ApartmentNotAvailableException;
import com.serviceAppartment.exceptions.ApartmentNotFoundException;
import com.serviceAppartment.model.Apartment;
import com.serviceAppartment.model.Booking;
import com.serviceAppartment.repository.ApartmentRepository;
import com.serviceAppartment.repository.BookingRepository;
import com.serviceAppartment.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApartmentRepository apartmentRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public BookingResponseDto createBooking(BookingRequestDTO bookingRequestDto) throws ApartmentNotAvailableException {
		Apartment apartment;
		try {
			apartment = apartmentRepository.findById(bookingRequestDto.getApartmentId()).get();
//					.orElseThrow(NoSuchElementException::new);
			System.out.println(apartment);
		} catch (ApartmentNotFoundException e) {
			throw new ApartmentNotFoundException("Apartment not found with ID: " + bookingRequestDto.getApartmentId());
		}

		List<Booking> byApartmentId = bookingRepository.findByApartmentId(bookingRequestDto.getApartmentId());
		System.out.println(byApartmentId);
		if (!apartment.isAvailable()) {
			throw new ApartmentNotAvailableException("Apartment with ID: " + apartment.getId() + " is not available.");
		}

		Booking booking = new Booking();
		booking.setCheckInDate(bookingRequestDto.getCheckInDate());
		booking.setCheckOutDate(bookingRequestDto.getCheckOutDate());
		booking.setApartment(apartment);
		Booking booked = bookingRepository.save(booking);

		// Update apartment availability status
		apartment.setAvailable(false);
		apartmentRepository.save(apartment);

		BookingResponseDto bookingResponseDto = modelMapper.map(booked, BookingResponseDto.class);
		return bookingResponseDto;
	}



}
