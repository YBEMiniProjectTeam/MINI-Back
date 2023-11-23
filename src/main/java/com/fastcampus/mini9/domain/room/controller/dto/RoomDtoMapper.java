package com.fastcampus.mini9.domain.room.controller.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomDtoMapper {

	RoomDtoMapper INSTANCE = Mappers.getMapper(RoomDtoMapper.class);

	// Room fromEntity(RoomResDto roomResDto);

	RoomResDto roomResDto(Long accommodationId, String startDate, String endDate,
		String guestNum);
}
