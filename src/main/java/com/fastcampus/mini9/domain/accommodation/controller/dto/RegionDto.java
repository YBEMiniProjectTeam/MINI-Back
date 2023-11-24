package com.fastcampus.mini9.domain.accommodation.controller.dto;

import com.fastcampus.mini9.domain.accommodation.entity.Region;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegionDto {
	private String name;

	public static RegionDto fromEntity(Region region) {
		return RegionDto.builder()
			.name(region.getName())
			.build();
	}
}