package com.fastcampus.mini9.domain.accommodation.controller.dto;

import java.util.List;

import com.fastcampus.mini9.domain.accommodation.entity.AccommodationType;

import lombok.Data;
import lombok.Getter;

@Getter
@Data

public class AccommodationDto {

	private Long id;
	private String name;
	private AccommodationType type;
	private DistrictDto district;
	private RegionDto region;
	private List<AccommodationImgDto> accommodationImage;

}
