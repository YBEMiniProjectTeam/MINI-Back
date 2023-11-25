package com.fastcampus.mini9.domain.accommodation.controller.dto.response;

import java.util.List;

import com.fastcampus.mini9.domain.accommodation.entity.location.District;

public record DistrictListResDto(
	List<DistrictResDto> districts
) {
	public record DistrictResDto(
		Long id,
		String name
	) {
		public static DistrictResDto fromEntity(District district) {
			return new DistrictResDto(district.getId(), district.getName());
		}
	}
}
