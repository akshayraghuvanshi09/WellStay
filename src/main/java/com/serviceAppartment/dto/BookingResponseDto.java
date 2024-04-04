package com.serviceAppartment.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDto {
	private Long id;
	private ApartmentDTO apartment;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
}
