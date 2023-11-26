package com.fastcampus.mini9.domain.accommodation.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fastcampus.mini9.domain.accommodation.entity.Accommodation;
import com.fastcampus.mini9.domain.accommodation.entity.AccommodationType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationResDto {

	private Long id;
	private String name;
	private AccommodationType type;
	private DistrictDto district;
	private RegionDto region;
	private List<AccommodationImgDto> accommodationImage;
	private AccommodationDetailInfoDto accommodationDetailInfo;
	private Boolean isWish;

	public static AccommodationResDto fromEntity(Accommodation accommodation, Boolean isWish) {
		return AccommodationResDto.builder()
			.id(accommodation.getId())
			.name(accommodation.getName())
			.type(accommodation.getType())
			// .district(DistrictDto.fromEntity(accommodation.getDistrict()))
			// .region(RegionDto.fromEntity(accommodation.getRegion()))
			.accommodationImage(
				accommodation.getAccommodationImage().stream()
					.map(AccommodationImgDto::fromEntity)
					.collect(Collectors.toList())
			)
			.accommodationDetailInfo(
				AccommodationDetailInfoDto.fromEntity(accommodation.getAccommodationDetailInfo()))
			.isWish(isWish)
			.build();

	}

}
