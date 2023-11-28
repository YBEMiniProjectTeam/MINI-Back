package com.fastcampus.mini9.domain.cart.dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public record FindCartResponse(

	@Schema(example = "조선 호텔 (숙소명)")
	String accommodationName,

	List<FindCartResponse.RoomInfo> roomInfos) {

	public record RoomInfo(

		@Schema(example = "1")
		Long cartId,

		@Schema(example = "2")
		int quantity,

		@Schema(example = "서울시 동대문구 (숙소 주소)")
		String address,

		@Schema(example = "스위트룸 (객실명)")
		String roomName,

		@Schema(example = "숙소 썸네일 Image URL")
		String accommodationThumbnailUrl,

		@Schema(example = "50000")
		int price,

		@Schema(example = "15:00")
		LocalTime checkIn,

		@Schema(example = "11:00")
		LocalTime checkOut,

		@Schema(example = "2")
		int capacity,

		@Schema(example = "4")
		int capacityMax) {

		public String getCheckIn() {
			return checkIn.format(DateTimeFormatter.ofPattern("HH:mm"));
		}

		public String getCheckOut() {
			return checkOut.format(DateTimeFormatter.ofPattern("HH:mm"));
		}
	}
}
