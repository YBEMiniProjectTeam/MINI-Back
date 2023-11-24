package com.fastcampus.mini9.domain.accommodation.controller.dto;

import com.fastcampus.mini9.domain.accommodation.entity.Location;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LocationDto {

	private RegionDto region;
	private DistrictDto district;

	public static LocationDto fromEntity(Location location) {

		return LocationDto.builder()
			.region(RegionDto.fromEntity(location.getRegion()))
			.district(DistrictDto.fromEntity(location.getDistrict()))
			.build();
	}

}