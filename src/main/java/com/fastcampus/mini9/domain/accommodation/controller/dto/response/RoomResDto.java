package com.fastcampus.mini9.domain.accommodation.controller.dto.response;

import java.util.List;

public record RoomResDto(
	Long id,
	String name,
	Long price,
	Integer capacity,
	Integer capacity_max,
	List<Image> room_image
) {
	private record Image(
		String url
	) {

	}
}
