package com.serviceAppartment.service.impl;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceAppartment.dto.CancellationDTO;
import com.serviceAppartment.model.Booking;
import com.serviceAppartment.model.Cancellation;
import com.serviceAppartment.repository.BookingRepository;
import com.serviceAppartment.repository.CancellationRepository;
import com.serviceAppartment.service.CancellationService;

@Service
public class CancellationServiceImpl implements CancellationService{
	 @Autowired
	    private CancellationRepository cancellationRepository;
	 
	 @Autowired
	 private BookingRepository bookingRepository;

	    @Autowired
	    private ModelMapper modelMapper;

	    public CancellationDTO cancelBooking(Long bookingId) {
	    	Booking booking = bookingRepository.getReferenceById(bookingId);
	        Cancellation cancellation = new Cancellation();
	        cancellation.setBookingId(bookingId);
	        cancellation.setApartmentId(booking.getApartment().getId());
	        cancellation.setCancellationDate(LocalDate.now());
	        cancellation = cancellationRepository.save(cancellation);
	        return modelMapper.map(cancellation, CancellationDTO.class);
	    }
}
