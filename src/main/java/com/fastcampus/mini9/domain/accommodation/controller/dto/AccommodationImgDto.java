package com.fastcampus.mini9.domain.accommodation.controller.dto;

import com.fastcampus.mini9.domain.accommodation.entity.AccommodationImage;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationImgDto {

	private String url;

	public static AccommodationImgDto fromEntity(AccommodationImage accommodationImage) {
		return AccommodationImgDto.builder()
			.url(accommodationImage.getUrl())
			.build();
	}

}