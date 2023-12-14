package com.fastcampus.mini9.domain.accommodation.service.util;

import static com.fastcampus.mini9.domain.accommodation.service.usecase.RoomQuery.*;

import com.fastcampus.mini9.domain.accommodation.entity.accommodation.Accommodation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.fastcampus.mini9.domain.accommodation.entity.room.Room;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomServiceMapper {

	// findRoom()
	@Mapping(target = "capacity_max", source = "capacityMax")
	@Mapping(target = "stock", constant = "2")
	@Mapping(target = "description", source = "room.details")
	@Mapping(target = "checkInAt", source = "room.accommodation.checkIn")
	@Mapping(target = "checkOutAt", source = "room.accommodation.checkOut")
	FindRoomResponse entityToFind(Room room);
}
