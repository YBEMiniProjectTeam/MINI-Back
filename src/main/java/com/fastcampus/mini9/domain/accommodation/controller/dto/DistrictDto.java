package com.fastcampus.mini9.domain.accommodation.controller.dto;

import com.fastcampus.mini9.domain.accommodation.entity.District;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DistrictDto {
	private String name;

	public static DistrictDto fromEntity(District district) {
		return DistrictDto.builder()
			.name(district.getName())
			.build();
	}
}