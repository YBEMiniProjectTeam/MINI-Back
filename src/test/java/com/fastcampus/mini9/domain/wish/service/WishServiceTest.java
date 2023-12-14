package com.fastcampus.mini9.domain.wish.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.fastcampus.mini9.config.security.token.UserPrincipal;
import com.fastcampus.mini9.domain.accommodation.entity.accommodation.Accommodation;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;
import com.fastcampus.mini9.domain.accommodation.service.AccommodationService;
import com.fastcampus.mini9.domain.accommodation.vo.AccommodationType;
import com.fastcampus.mini9.domain.member.entity.Member;
import com.fastcampus.mini9.domain.member.repository.MemberRepository;
import com.fastcampus.mini9.domain.member.service.MemberService;
import com.fastcampus.mini9.domain.wish.entity.Wish;
import com.fastcampus.mini9.domain.wish.repository.WishRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class WishServiceTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WishService wishService;

	@Autowired
	private AccommodationRepository accommodationRepository;

	@Autowired
	private WishRepository wishRepository;

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRepository memberRepository;

	private ObjectMapper objectMapper = new ObjectMapper();
	private Accommodation accommodation;


	@Test
	@DisplayName("/accommodations/{accommodationId}/wish 추가 ")


	public void setWishServiceService() throws Exception {


		accommodation = Accommodation.builder()
			.type(AccommodationType.HOTEL)
			.name("test")
			.images(new ArrayList<>())
			.checkIn(LocalTime.now())
			.checkOut(LocalTime.now())
			.details(null)
			.rooms(new ArrayList<>())
			.build();

		Accommodation savedAccommodation = accommodationRepository.save(accommodation);

		Member member = Member.builder()
			.birthday(LocalDate.now())
			.email("test@gmail.com")
			.pwd("test123123")
			.name("testMember")
			.build();

		Member savedMember = memberRepository.save(member);


		Wish wish = new Wish(savedAccommodation, savedMember);

		Wish newWish = wishRepository.save(wish);
		assertEquals(newWish.getMember().getEmail(),member.getEmail());

	}




	@Test
	@DisplayName("/accommodations/{accommodationId}/wish 삭제 ")
	public void accommodationService() throws Exception {

		accommodation = Accommodation.builder()
			.type(AccommodationType.HOTEL)
			.name("test")
			.images(new ArrayList<>())
			.checkIn(LocalTime.now())
			.checkOut(LocalTime.now())
			.details(null)
			.rooms(new ArrayList<>())
			.build();

		Accommodation savedAccommodation = accommodationRepository.save(accommodation);

		Member member = Member.builder()
			.birthday(LocalDate.now())
			.email("test@gmail.com")
			.pwd("test123123")
			.name("testMember")
			.build();

		Member savedMember = memberRepository.save(member);



		Wish wish = new Wish(savedAccommodation, savedMember);

		Wish newWish = wishRepository.save(wish);
		assertEquals(newWish.getMember().getEmail(),member.getEmail());


		wishRepository.deleteByAccommodationAndMember(savedAccommodation,savedMember);


		List<Wish> deletedWish = wishRepository.findAll();
		assertEquals(deletedWish.size(),0);

	}



}