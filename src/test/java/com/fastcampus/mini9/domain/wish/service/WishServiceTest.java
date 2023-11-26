package com.fastcampus.mini9.domain.wish.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import com.fastcampus.mini9.domain.accommodation.entity.Accommodation;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;
import com.fastcampus.mini9.domain.member.entity.Member;
import com.fastcampus.mini9.domain.member.repository.MemberRepository;
import com.fastcampus.mini9.domain.wish.controller.dto.WishDtoMapper;
import com.fastcampus.mini9.domain.wish.entity.Wish;

@ExtendWith(MockitoExtension.class)
class WishServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private AccommodationRepository accommodationRepository;

	@InjectMocks
	private WishService wishService;

	@Test
	@DisplayName("Mapper 확인")
	@WithMockUser
	public void testMapper() {

		Accommodation accommodation = Accommodation.builder()
			.name("accommodation test")
			.build();

		Member member = Member.builder()
			.name("test name")
			.build();

		Wish withMapper = WishDtoMapper.INSTANCE.fromWishDto(accommodation, member);

		assertThat(withMapper.getMember().getName()).isEqualTo(withMapper.getMember().getName());
		assertThat(withMapper.getAccommodation().getName()).isEqualTo(withMapper.getAccommodation().getName());

	}

}