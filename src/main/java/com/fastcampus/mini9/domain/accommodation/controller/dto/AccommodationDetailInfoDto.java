package com.fastcampus.mini9.domain.accommodation.controller.dto;

import com.fastcampus.mini9.domain.accommodation.entity.AccommodationDetailInfo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccommodationDetailInfoDto {
	private String description;
	private String latitude;
	private String longitude;
	private String phoneNum;
	private Boolean parkingSpace;
	private Boolean cookingArea;

	public static AccommodationDetailInfoDto fromEntity(
		AccommodationDetailInfo accommodationDetailInfo) {
		return AccommodationDetailInfoDto.builder()
			.description(accommodationDetailInfo.getDescription())
			.latitude(accommodationDetailInfo.getLatitude())
			.longitude(accommodationDetailInfo.getLongitude())
			.phoneNum(accommodationDetailInfo.getPhoneNum())
			.parkingSpace(accommodationDetailInfo.getParkingSpace())
			.cookingArea(accommodationDetailInfo.getCookingArea())
			.build();
	}

}
