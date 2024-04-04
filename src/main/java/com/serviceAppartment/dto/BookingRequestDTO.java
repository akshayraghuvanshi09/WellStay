package com.serviceAppartment.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {
	private Long apartmentId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;

	
}
