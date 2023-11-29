package com.fastcampus.mini9.domain.reservation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record FindPaymentResponse(

	@Schema(example = "박경탁 (예약자명)")
	String reservationName,

	@Schema(example = "조선 호텔 (숙소명)")
	String accommodationName,

	FindPaymentResponse.RoomInfo roomInfo) {

	public record RoomInfo(

		@Schema(example = "스위트룸 (객실명)")
		String roomName,

		@Schema(example = "50000")
		int price,

		@Schema(example = "2023-11-24 15:00")
		LocalDateTime checkIn,

		@Schema(example = "2023-11-26 11:00")
		LocalDateTime checkOut,

		@Schema(example = "2")
		int capacity,

		@Schema(example = "4")
		int capacityMax
	) {
		public String getCheckIn() {
			return checkIn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		}

		public String getCheckOut() {
			return checkOut.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		}
	}
}
