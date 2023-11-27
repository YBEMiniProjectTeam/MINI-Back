package com.fastcampus.mini9.domain.accommodation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.mini9.domain.accommodation.entity.accommodation.Accommodation;
import com.fastcampus.mini9.domain.accommodation.entity.room.Room;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;
import com.fastcampus.mini9.domain.accommodation.repository.RoomRepository;
import com.fastcampus.mini9.domain.accommodation.service.usecase.RoomQuery;
import com.fastcampus.mini9.domain.accommodation.service.util.RoomServiceMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService implements RoomQuery {
	private final AccommodationRepository accommodationRepository;
	private final RoomRepository roomRepository;
	private final RoomServiceMapper mapper;

	@Override
	public FindRoomsInAccommodationResponse findRoomsInAccommodation(FindRoomsInAccommodationRequest request) {
		Accommodation accommodation = accommodationRepository.findById(request.accommodationId()).orElseThrow();

		List<Room> findRoomsByCondition = accommodation.getRooms().stream()
			.filter(Room -> request.guestNum() >= Room.getCapacity() && request.guestNum() <= Room.getCapacityMax())
			// TODO: startDate ~ endDate에 재고가 있는 Room만 조회
			.collect(Collectors.toList());
		// TODO: stock 조회 로직
		List<RoomResponse> responseList = mapper.entityListToResponseList(findRoomsByCondition);
		return new FindRoomsInAccommodationResponse(responseList);
	}

	@Override
	public FindRoomResponse findRoom(FindRoomRequest request) {
		Room room = roomRepository.findById(request.roomId()).orElseThrow();
		FindRoomResponse response = mapper.entityToFind(room);
		return response;
	}
}
