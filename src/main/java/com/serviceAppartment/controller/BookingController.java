package com.serviceAppartment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceAppartment.Payloads.ResponseHandler;
import com.serviceAppartment.dto.BookingRequestDTO;
import com.serviceAppartment.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDTO bookingRequestDto) {
        return ResponseHandler.responseBuilder("Booking created successfully!", HttpStatus.CREATED, bookingService.createBooking(bookingRequestDto));
    }
}