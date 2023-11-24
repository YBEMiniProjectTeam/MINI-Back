package com.fastcampus.mini9.domain.accommodation.controller.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * 사용방법
 * @Mapping(source = "numberOfSeats", target = "seatCount")
 * CarDto carToCarDto(Car car);
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccommodationDtoMapper {

	// AccommodationDtoMapper INSTANCE = Mappers.getMapper(AccommodationDtoMapper.class);
	//
	// AccommodationDto accommodationDto(Accommodation accommodation);
	//
	// AccommodationImgDto accommodationImgDto(AccommodationImage accommodationImage);
	//
	// LocationDto locationDto(Location location);
	//
	// DistrictDto districtDto(District district);
	//
	// RegionDto regionDto(Region region);
}
