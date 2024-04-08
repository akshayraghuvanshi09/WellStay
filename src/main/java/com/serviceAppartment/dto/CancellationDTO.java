package com.serviceAppartment.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancellationDTO {

	private Long id;
	private Long bookingId;
	private LocalDate cancellationDate;
	private boolean isCanceled;
}
