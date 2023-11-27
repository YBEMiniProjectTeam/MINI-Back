package com.fastcampus.mini9.domain.accommodation.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.mini9.config.security.token.UserPrincipal;
import com.fastcampus.mini9.domain.accommodation.entity.accommodation.Accommodation;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;
import com.fastcampus.mini9.domain.accommodation.service.usecase.AccommodationQuery;
import com.fastcampus.mini9.domain.accommodation.service.util.AccommodationServiceMapper;
import com.fastcampus.mini9.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccommodationService implements AccommodationQuery {
	private final AccommodationRepository accommodationRepository;
	private final MemberRepository memberRepository;
	private final AccommodationServiceMapper mapper;

	@Override
	public SearchAccommodationsResponse searchAccommodations(SearchAccommodationsRequest searchAccommodationsRequest,
		UserPrincipal userPrincipal) {
		// TODO: 검색 쿼리(QueryDSL) 구현. 이때 AccommodationDetails까지 fetch join.
		Page<Accommodation> all = accommodationRepository.findAll(
			PageRequest.of(searchAccommodationsRequest.pageNum(), searchAccommodationsRequest.pageSize()));
		// TODO: 현재 로그인 상태면 검색된 accommodation에 좋아요 표시. 1안:UserPrincipal->Member. 2안:MemberService에 구현. 3안: 여기에 구현
		// Optional<Member> loginMember = memberRepository.findById(userPrincipal.id());
		// if(loginMember.isPresent()) {
		// }

		List<AccommodationResponse> accommodationResponses = mapper.entityListToResponseList(all.toList());
		return new SearchAccommodationsResponse(accommodationResponses,
			searchAccommodationsRequest.pageNum(), searchAccommodationsRequest.pageSize(),
			all.getTotalPages(), all.getTotalElements(), all.isFirst(), all.isLast());
	}

	@Override
	public FindAccommodationResponse findAccommodation(
		FindAccommodationRequest findAccommodationRequest) {
		Accommodation findAccommodation = accommodationRepository.findById(findAccommodationRequest.id())
			.orElseThrow();
		FindAccommodationResponse findAccommodationResponse = mapper.entityToResponseDetail(
			findAccommodation);
		return findAccommodationResponse;
	}
}
