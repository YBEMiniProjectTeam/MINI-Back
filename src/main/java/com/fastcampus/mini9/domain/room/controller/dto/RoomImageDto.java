package com.fastcampus.mini9.domain.room.controller.dto;

import com.fastcampus.mini9.domain.room.entity.RoomImage;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomImageDto {
	private Long id;
	private String url;

	public static RoomImageDto fromEntity(RoomImage roomImage) {

		return RoomImageDto.builder()
			.id(roomImage.getId())
			.url(roomImage.getUrl())
			.build();
	}
}
