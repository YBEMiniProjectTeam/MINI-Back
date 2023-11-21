package com.fastcampus.mini9.domain.accommodation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.mini9.domain.accommodation.controller.dto.AccommodationResDto.OriginAccommodationDto;
import com.fastcampus.mini9.domain.accommodation.service.AccommodationService;

@RestController
@RequestMapping("/accommodations")
public class AccommodationController {

	private final AccommodationService accommodationService;

	public AccommodationController(AccommodationService accommodationService) {
		this.accommodationService = accommodationService;
	}

	//숙소 상세정보
	@GetMapping("/{id}")
	public ResponseEntity<OriginAccommodationDto> detailOfAccommodations(
		@PathVariable Long accommodationId
	) {
		return accommodationService.detailOfAccommodation(accommodationId);
	}

}
