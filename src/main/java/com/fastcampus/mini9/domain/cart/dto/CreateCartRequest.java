package com.fastcampus.mini9.domain.cart.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CreateCartRequest(

	@NotNull
	@Min(value = 1, message = "room_id는 최소 1 이상이어야 합니다.")
	Long roomId,

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent(message = "check_in_date는 현재보다 미래 날짜여야 합니다.")
	LocalDate checkInDate,

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future(message = "check_out_date는 현재보다 미래 날짜여야 합니다.")
	LocalDate checkOutDate
) {
}
