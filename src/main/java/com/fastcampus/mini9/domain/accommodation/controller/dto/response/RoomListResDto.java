package com.fastcampus.mini9.domain.accommodation.controller.dto.response;

import java.util.List;

public record RoomListResDto(
	List<RoomResDto> rooms
) {
	public record RoomResDto(
		Long id,
		String name,
		Integer price,
		Integer capacity,
		Integer capacity_max,
		Integer stock_quantity
	) {

	}
}
