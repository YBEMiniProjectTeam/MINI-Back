package com.fastcampus.mini9.domain.accommodation.controller.dto.response;

public record RoomResDto(
	Long id,
	String name,
	Integer price,
	Integer capacity,
	Integer capacity_max
) {
}
