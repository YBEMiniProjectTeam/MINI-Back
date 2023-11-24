package com.fastcampus.mini9.domain.room.service;

import static com.fastcampus.mini9.domain.room.controller.dto.RoomResDtoList.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fastcampus.mini9.domain.accommodation.entity.Accommodation;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;
import com.fastcampus.mini9.domain.room.controller.dto.RoomSpecificDto;
import com.fastcampus.mini9.domain.room.entity.Room;
import com.fastcampus.mini9.domain.room.entity.Stock;
import com.fastcampus.mini9.domain.room.repository.RoomRepository;

@Service
public class RoomService {

	private final RoomRepository roomRepository;
	private final AccommodationRepository accommodationRepository;

	private RoomService(RoomRepository roomRepository, AccommodationRepository accommodationRepository) {
		this.roomRepository = roomRepository;
		this.accommodationRepository = accommodationRepository;
	}

	public ResponseEntity<List<RoomResDto>> roomsInfo(Long accommodationId, LocalDate startDate,
		LocalDate endDate, int guestNum) {

		Accommodation findByIdAccommodation = accommodationRepository.findById(accommodationId).orElseThrow();
		List<RoomResDto> roomList = new ArrayList<>();

		//room 체크인 체크아웃 기준으로 잔여 룸인지 확인 어떻게 해야하는가?

		for (Room room : findByIdAccommodation.getRooms()) {

			// Room findRoom = roomRepository.findByDateBetween(startDate, endDate);

			for (Stock stock : room.getStockList()) {

				if (0 < stock.getQuantity() && startDate.compareTo(stock.getDate()) <= 0 && 0 <= endDate.compareTo(
					stock.getDate())) {

					int minGuestNum = room.getCapacity();
					int maxGuestNum = room.getCapacityMax();

					System.out.println((stock.getDate()));

					if (minGuestNum <= guestNum && guestNum <= maxGuestNum) {
						RoomResDto roomResDto = RoomResDto.fromEntity(room);
						roomList.add(roomResDto);
					}
				}
			}
		}
		return ResponseEntity.ok(roomList);
	}

	public ResponseEntity<RoomSpecificDto> detailRoom(Long roomId) {
		Room findByIdRoom = roomRepository.findById(roomId).orElseThrow();
		return ResponseEntity.ok(RoomSpecificDto.fromEntity(findByIdRoom));
	}
}
