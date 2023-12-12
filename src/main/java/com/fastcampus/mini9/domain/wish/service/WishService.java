package com.fastcampus.mini9.domain.wish.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.mini9.common.exception.EntityNotFoundException;
import com.fastcampus.mini9.config.security.token.UserPrincipal;
import com.fastcampus.mini9.domain.accommodation.entity.accommodation.Accommodation;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;
import com.fastcampus.mini9.domain.member.entity.Member;
import com.fastcampus.mini9.domain.member.exception.NotFoundMemberException;
import com.fastcampus.mini9.domain.member.repository.MemberRepository;
import com.fastcampus.mini9.domain.wish.entity.Wish;
import com.fastcampus.mini9.domain.wish.exception.AlreadyWishException;
import com.fastcampus.mini9.domain.wish.repository.WishRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WishService {

	private final WishRepository wishRepository;
	private final AccommodationRepository accommodationRepository;
	private final MemberRepository memberRepository;

	public void addWish(Long accommodationId, UserPrincipal userPrincipal) {
		Accommodation accommodation = accommodationRepository.findById(accommodationId)
			.orElseThrow(() -> new EntityNotFoundException("해당 숙소가 존재하지 않습니다."));
		Member member = memberRepository.findById(userPrincipal.id())
			.orElseThrow(NotFoundMemberException::new);

		if (wishRepository.existsByAccommodationAndMember(accommodation, member)) {
			throw new AlreadyWishException();
		}

		Wish newWish = new Wish(accommodation, member);
		wishRepository.saveAndFlush(newWish);
	}

	public void deleteWish(Long accommodationId, UserPrincipal userPrincipal) {
		Accommodation accommodation = accommodationRepository.findById(accommodationId)
			.orElseThrow(() -> new EntityNotFoundException("해당 숙소가 존재하지 않습니다."));
		Member member = memberRepository.findById(userPrincipal.id())
			.orElseThrow(NotFoundMemberException::new);

		if (!wishRepository.existsByAccommodationAndMember(accommodation, member)) {
			throw new AlreadyWishException("등록되지 않은 숙소");
		}

		wishRepository.deleteByAccommodationAndMember(accommodation, member);
	}

	public List<Wish> findWishes(UserPrincipal userPrincipal) {
		Member member = memberRepository.findById(userPrincipal.id())
			.orElseThrow(NotFoundMemberException::new);
		List<Wish> allByMember = wishRepository.findAllByMember(member);
		return allByMember;
	}
}
