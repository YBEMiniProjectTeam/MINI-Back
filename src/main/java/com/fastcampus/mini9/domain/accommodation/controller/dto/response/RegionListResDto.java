package com.fastcampus.mini9.domain.accommodation.controller.dto.response;

import java.util.List;

import com.fastcampus.mini9.domain.accommodation.entity.location.Region;

public record RegionListResDto(
	List<RegionResDto> regions
) {
	public record RegionResDto(
		Long id,
		String name
	) {
		public static RegionResDto fromEntity(Region region) {
			return new RegionResDto(region.getId(), region.getName());
		}
	}
}
