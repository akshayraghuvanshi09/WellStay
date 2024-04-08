package com.serviceAppartment.service;

import com.serviceAppartment.dto.CancellationDTO;

public interface CancellationService {
	public CancellationDTO cancelBooking(Long bookingId);
}
