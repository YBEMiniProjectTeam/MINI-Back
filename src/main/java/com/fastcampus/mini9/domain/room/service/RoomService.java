package com.fastcampus.mini9.domain.room.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fastcampus.mini9.domain.accommodation.entity.Accommodation;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;
import com.fastcampus.mini9.domain.room.controller.dto.RoomDtoMapper;
import com.fastcampus.mini9.domain.room.controller.dto.RoomResDto;
import com.fastcampus.mini9.domain.room.repository.RoomRepository;

@Service
public class RoomService {

	private final RoomRepository roomRepository;
	private final AccommodationRepository accommodationRepository;

	private RoomService(RoomRepository roomRepository, AccommodationRepository accommodationRepository) {
		this.roomRepository = roomRepository;
		this.accommodationRepository = accommodationRepository;
	}

	public ResponseEntity<RoomResDto> roomsInfo(Long accommodationId, String startDate, String endDate,
		String guestNum) {

		//객실 조회시 체크인날짜, 체크아웃날짜, 객실인원에 맞는 객실 찾아야함.

		//문제 객실 조회할때 체크인 시간, 체크 아웃 시간 기간동안 해당 잔고 가 있는지 확인?

		Accommodation findAccommodation = accommodationRepository.findById(accommodationId).orElseThrow();

		return ResponseEntity.ok(
			RoomDtoMapper.INSTANCE.roomResDto(
				accommodationId,
				startDate,
				endDate,
				guestNum));
	}

	public ResponseEntity<RoomResDto> detailRoom(Long roomId) {

		return ResponseEntity.ok(null);
	}
}
