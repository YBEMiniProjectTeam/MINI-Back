package com.fastcampus.mini9.domain.reservation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.swagger.v3.oas.annotations.media.Schema;

public record FindReservationResponse(

	@Schema(example = "조선 호텔 (숙소명)")
	String accommodationName,

	FindReservationResponse.RoomInfo roomInfo) {

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
