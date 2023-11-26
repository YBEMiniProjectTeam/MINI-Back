package com.fastcampus.mini9.domain.accommodation.service.usecase;

import java.util.List;

public interface RoomQuery {
	FindRoomsInAccommodationResponse findRoomsInAccommodation(FindRoomsInAccommodationRequest request);

	FindRoomResponse findRoom(FindRoomRequest request);

	record FindRoomsInAccommodationRequest(
		Long accommodationId,
		String startDate,
		String endDate,
		Long guestNum
	) {
	}

	record FindRoomsInAccommodationResponse(
		List<RoomResponse> roomResponses
	) {
	}

	record RoomResponse(
		Long id,
		String name,
		Integer price,
		Integer capacity,
		Integer capacity_max,
		Integer stock
	) {
	}

	record FindRoomRequest(
		Long roomId
	) {
	}

	record FindRoomResponse(
		Long id,
		String name,
		Integer price,
		Integer capacity,
		Integer capacity_max,
		Integer stock
	) {
	}
}
