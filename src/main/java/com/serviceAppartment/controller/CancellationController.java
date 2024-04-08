package com.serviceAppartment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceAppartment.dto.CancellationDTO;
import com.serviceAppartment.service.CancellationService;

@RestController
@RequestMapping("/cancellations")
public class CancellationController {

    @Autowired
    private CancellationService cancellationService;

    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<CancellationDTO> cancelBooking(@PathVariable Long bookingId) {
        CancellationDTO cancellationDTO = cancellationService.cancelBooking(bookingId);
        return new ResponseEntity<>(cancellationDTO, HttpStatus.CREATED);
    }
}
