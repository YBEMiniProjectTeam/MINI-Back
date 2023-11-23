package com.fastcampus.mini9.domain.accommodation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.mini9.common.response.DataResponseBody;

@RestController
public class AccommodationController {
	@GetMapping("/accommodations")
	public DataResponseBody getAccommodations(
		@RequestParam(required = false) String region, @RequestParam(required = false) String district,
		@RequestParam String startDate, @RequestParam String endDate,
		@RequestParam String category, @RequestParam String keyword) {
		System.out.println("here");
		return DataResponseBody.success(null, "SUCCESS");
	}

	@GetMapping("/accommodations/{accommodationId}")
	public DataResponseBody getAccommodation(@PathVariable Long accommodationId) {
		return DataResponseBody.success(null, "SUCCESS");
	}

	@GetMapping("/accommodations/{accommodationId}/rooms")
	public DataResponseBody getRooms(@PathVariable Long accommodationId,
		@RequestParam String startDate, @RequestParam String endDate, @RequestParam Long guestNum) {
		return DataResponseBody.success(null, "SUCCESS");
	}

	@GetMapping("/rooms/{roomId}")
	public DataResponseBody getRoom(@PathVariable Long roomId) {
		return DataResponseBody.success(null, "SUCCESS");
	}
}
