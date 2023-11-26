package com.fastcampus.mini9.domain.accommodation.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.mini9.common.response.DataResponseBody;
import com.fastcampus.mini9.domain.accommodation.controller.dto.AccommodationResDto;
import com.fastcampus.mini9.domain.accommodation.service.AccommodationService;

@RestController
@RequestMapping("/accommodations")
public class AccommodationController {

	private final AccommodationService accommodationService;

	public AccommodationController(AccommodationService accommodationService) {
		this.accommodationService = accommodationService;
	}

	//숙소 상세정보

	@GetMapping("/accommodations")
	public DataResponseBody getAccommodations(
		@RequestParam(required = false) String region, @RequestParam(required = false) String district,
		@RequestParam String startDate, @RequestParam String endDate,
		@RequestParam String category, @RequestParam String keyword) {
		System.out.println("here");
		return DataResponseBody.success(null, "SUCCESS");
	}

	@GetMapping("/{accommodationId}")
	public ResponseEntity<AccommodationResDto> detailOfAccommodations(
		@PathVariable Long accommodationId,
		@AuthenticationPrincipal Principal principal
	) {
		return accommodationService.detailOfAccommodation(accommodationId, principal.getName());
	}

}
