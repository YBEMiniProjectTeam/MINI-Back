package com.fastcampus.mini9.domain.accommodation.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fastcampus.mini9.domain.accommodation.controller.dto.AccommodationResDto;
import com.fastcampus.mini9.domain.accommodation.entity.Accommodation;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;
import com.fastcampus.mini9.domain.member.entity.Member;
import com.fastcampus.mini9.domain.member.repository.MemberRepository;
import com.fastcampus.mini9.domain.wish.repository.WishRepository;

@Service
public class AccommodationService {

	private final AccommodationRepository accommodationRepository;
	private final MemberRepository memberRepository;

	private final WishRepository wishRepository;

	public AccommodationService(
		AccommodationRepository accommodationRepository,
		MemberRepository memberRepository,
		WishRepository wishRepository
	) {
		this.accommodationRepository = accommodationRepository;
		this.memberRepository = memberRepository;
		this.wishRepository = wishRepository;
	}

	public ResponseEntity<AccommodationResDto> detailOfAccommodation(Long accommodationId, String userEmail) {

		Accommodation findAccommodationById = getAccommodation(accommodationId);
		Member member = memberRepository.findByEmail(userEmail).orElseThrow();
		Boolean isWish = wishRepository.existsByAccommodationAndMember(findAccommodationById, member);

		AccommodationResDto accommodationDto = AccommodationResDto.fromEntity(
			findAccommodationById, isWish);
		return ResponseEntity.ok(accommodationDto);
	}

	public Accommodation getAccommodation(Long accommodationId) {
		return accommodationRepository.findById(accommodationId)
			.orElseThrow(() -> new RuntimeException("해당 숙소 정보가 없습니다."));
	}
}
