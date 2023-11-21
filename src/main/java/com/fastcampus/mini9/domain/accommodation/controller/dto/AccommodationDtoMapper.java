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
}
