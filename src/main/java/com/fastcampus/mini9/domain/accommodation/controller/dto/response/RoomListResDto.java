package com.fastcampus.mini9.domain.accommodation.controller.dto.response;

import java.util.List;

public record RoomListResDto(
	List<RoomResDto> rooms
) {
	private record RoomResDto(
		Long id,
		String name,
		Long price,
		Integer capacity,
		Integer capacity_max,
		String intro_message,
		Integer stock_quantity,
		List<Image> room_image
	) {

	}

	private record Image(
		String url
	) {

	}
}
