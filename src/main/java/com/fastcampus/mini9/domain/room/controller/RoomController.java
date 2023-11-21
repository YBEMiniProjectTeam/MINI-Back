package com.fastcampus.mini9.domain.room.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.mini9.domain.room.service.RoomService;

@RestController
public class RoomController {

	private final RoomService roomService;

	public RoomController() {
		roomService = new RoomService();
	}

	//숙소 기준 객실 정보
	@GetMapping("/accommodations/{id}/rooms")
	public void roomInfo(
		@PathVariable Long accommodationId
	) {

	}

	//객실 상세정보
	@GetMapping("/rooms/{id}")
	public void detailOfRooms(
		@PathVariable Long roomId
	) {

	}
}
