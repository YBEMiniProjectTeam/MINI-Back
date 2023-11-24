package com.fastcampus.mini9.domain.room.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.mini9.domain.room.controller.dto.RoomResDtoList.RoomResDto;
import com.fastcampus.mini9.domain.room.controller.dto.RoomSpecificDto;
import com.fastcampus.mini9.domain.room.service.RoomService;

@RestController
public class RoomController {

	private final RoomService roomService;

	private RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	//숙소 기준 객실 정보
	@GetMapping("/accommodations/{accommodationId}/rooms")
	public ResponseEntity<List<RoomResDto>> roomsInfo(
		@PathVariable Long accommodationId,
		@RequestParam(value = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
		@RequestParam(value = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
		@RequestParam(value = "guest_num") @DateTimeFormat(pattern = "yyyy-MM-dd") int guestNum
	) {

		return roomService.roomsInfo(accommodationId, startDate, endDate, guestNum);
	}

	//객실 상세정보
	@GetMapping("/rooms/{roomId}")
	public ResponseEntity<RoomSpecificDto> detailRoom(
		@PathVariable Long roomId
	) {
		return roomService.detailRoom(roomId);
	}
}
