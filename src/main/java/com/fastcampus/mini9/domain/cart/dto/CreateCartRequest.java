package com.fastcampus.mini9.domain.cart.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateCartRequest(

	@NotNull
	@Min(1)
	Long roomId,

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future
	LocalDate checkInDate,

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future
	LocalDate checkOutDate) {
}
