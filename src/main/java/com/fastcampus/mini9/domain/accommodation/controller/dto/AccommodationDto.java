package com.fastcampus.mini9.domain.accommodation.controller.dto;

import java.util.List;

import com.fastcampus.mini9.domain.accommodation.entity.AccommodationType;

public class AccommodationDto {

	private Long id;
	private String name;
	private AccommodationType type;
	private LocationDto location;
	private List<AccommodationImgDto> accommodationImage;

}
