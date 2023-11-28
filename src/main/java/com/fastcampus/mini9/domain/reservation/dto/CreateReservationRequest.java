package com.fastcampus.mini9.domain.reservation.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateReservationRequest(

	@NotNull
	@Min(1)
	Long roomId,

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future
	LocalDateTime checkIn,

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future
	LocalDateTime checkOut,

	@Min(1000)
	int totalPrice
) {
}
