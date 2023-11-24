package com.fastcampus.mini9.domain.accommodation.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.fastcampus.mini9.domain.accommodation.controller.dto.AccommodationResDto;
import com.fastcampus.mini9.domain.accommodation.entity.Accommodation;
import com.fastcampus.mini9.domain.accommodation.entity.AccommodationDetailInfo;
import com.fastcampus.mini9.domain.accommodation.entity.AccommodationType;
import com.fastcampus.mini9.domain.accommodation.repository.AccommodationRepository;

@ExtendWith(MockitoExtension.class)
class AccommodationServiceTest {

	@Mock
	private AccommodationRepository accommodationRepository;

	@InjectMocks
	private AccommodationService accommodationService;

	@Test
	@DisplayName("숙박 정보 조회 성공 케이스")
	public void successSearchAccomodation() {

		//given

		AccommodationDetailInfo accommodationDetailInfo = AccommodationDetailInfo.builder()
			.parkingSpace(true)
			.cookingArea(true)
			.longitude("123")
			.latitude("456")
			.description("good place")
			.phoneNum("123-1234-1234")
			.build();

		Accommodation accommodation = Accommodation.builder()
			.name("test name")
			.accommodationImage(new ArrayList<>())
			.type(AccommodationType.HOTEL)
			.accommodationDetailInfo(accommodationDetailInfo)
			// .district(District.builder().name("test district").build())
			// .region(Region.builder().name("test region").build())

			.build();

		given(accommodationRepository.findById(anyLong()))
			.willReturn(Optional.of(accommodation));

		//when
		ResponseEntity<AccommodationResDto> result = accommodationService.detailOfAccommodation(anyLong());

		//then
		AccommodationResDto accommodationDtoResult = AccommodationResDto.fromEntity(accommodation);

		// AccommodationDto accommodationDtoResult = AccommodationDtoMapper.INSTANCE.accommodationDto(accommodation);

		System.out.println(accommodationDtoResult.getName() + "===========================");

		assertThat(Objects.requireNonNull(result.getBody()).getId()).isEqualTo(accommodationDtoResult.getId());
		assertThat(result.getBody().getType()).isEqualTo(accommodationDtoResult.getType());
		assertThat(result.getBody().getName()).isEqualTo(accommodationDtoResult.getName());

		assertThat(result.getBody().getAccommodationDetailInfo().getCookingArea()).isEqualTo(
			accommodationDtoResult.getAccommodationDetailInfo().getCookingArea());
		assertThat(result.getBody().getAccommodationDetailInfo().getDescription()).isEqualTo(
			accommodationDtoResult.getAccommodationDetailInfo().getDescription());
		assertThat(result.getBody().getAccommodationDetailInfo().getLatitude()).isEqualTo(
			accommodationDtoResult.getAccommodationDetailInfo().getLatitude());

		assertThat(result.getBody().getAccommodationImage()).isEqualTo(accommodationDtoResult.getAccommodationImage());
	}

}