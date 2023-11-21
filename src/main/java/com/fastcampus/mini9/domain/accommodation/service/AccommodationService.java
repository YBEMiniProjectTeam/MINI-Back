package com.fastcampus.mini9.domain.accommodation.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fastcampus.mini9.domain.accommodation.controller.dto.AccommodationResDto;
import com.fastcampus.mini9.domain.accommodation.controller.dto.AccommodationResDto.OriginAccommodationDto;
import com.fastcampus.mini9.domain.accommodation.entity.Accommodation;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;

@Service
public class AccommodationService {

	private final AccommodationRepository accommodationRepository;

	public AccommodationService(AccommodationRepository accommodationRepository) {
		this.accommodationRepository = accommodationRepository;
	}

	public ResponseEntity<OriginAccommodationDto> detailOfAccommodation(Long accommodationId) {

		Accommodation findAccommodationById = accommodationRepository.findById(accommodationId)
			.orElseThrow(() -> new RuntimeException("해당 숙소 정보가 없습니다."));

		OriginAccommodationDto accommodationDto = AccommodationResDto.OriginAccommodationDto.fromEntity(
			findAccommodationById);
		return ResponseEntity.ok(accommodationDto);
	}
}
