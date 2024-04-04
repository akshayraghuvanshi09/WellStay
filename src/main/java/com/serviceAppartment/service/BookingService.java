package com.serviceAppartment.service;

import java.util.List;

import com.serviceAppartment.dto.BookingRequestDTO;
import com.serviceAppartment.dto.BookingResponseDto;
import com.serviceAppartment.model.Booking;

public interface BookingService {
	public List<Booking> getAllBookings();

	public BookingResponseDto createBooking(BookingRequestDTO BookingRequestDto);

	
}
