package com.fastcampus.mini9.domain.wish.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.mini9.domain.accommodation.entity.accommodation.Accommodation;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;
import com.fastcampus.mini9.domain.member.entity.Member;
import com.fastcampus.mini9.domain.member.repository.MemberRepository;
import com.fastcampus.mini9.domain.wish.controller.dto.WishDtoMapper;
import com.fastcampus.mini9.domain.wish.entity.Wish;
import com.fastcampus.mini9.domain.wish.exception.AlreadyWishException;
import com.fastcampus.mini9.domain.wish.repository.WishRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WishService {

	private final WishRepository wishRepository;
	private final AccommodationRepository accommodationRepository;
	private final MemberRepository memberRepository;

	public void addWish(Long accommodationId, String userEmail) {
		Accommodation accommodation = accommodationRepository.findById(accommodationId).orElseThrow();
		Member member = memberRepository.findByEmail(userEmail).orElseThrow();

		if (wishRepository.existsByAccommodationAndMember(accommodation, member)) {
			throw new AlreadyWishException();
		}

		Wish wish = WishDtoMapper.INSTANCE.fromWishDto(accommodation, member);

		wishRepository.save(wish);
	}

	public void deleteWish(Long accommodationId, String userEmail) {
		Accommodation accommodation = accommodationRepository.findById(accommodationId).orElseThrow();
		Member member = memberRepository.findByEmail(userEmail).orElseThrow();

		if (wishRepository.existsByAccommodationAndMember(accommodation, member)) {
			throw new AlreadyWishException("등록되지 않은 숙소");
		}

		wishRepository.deleteByAccommodationAndMember(accommodation, member);
	}

}
