package com.fastcampus.mini9.domain.accommodation.controller.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 사용방법
 * @Mapping(source = "numberOfSeats", target = "seatCount")
 * CarDto carToCarDto(Car car);
 */
@Mapper
public interface AccommodationDtoMapper {
	AccommodationDtoMapper INSTANCE = Mappers.getMapper(AccommodationDtoMapper.class);
}
