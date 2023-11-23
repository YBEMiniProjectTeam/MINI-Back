package com.fastcampus.mini9.domain.accommodation.service;

import static com.fastcampus.mini9.domain.accommodation.controller.dto.AccommodationResDto.*;
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

import com.fastcampus.mini9.domain.accommodation.entity.Accommodation;
import com.fastcampus.mini9.domain.accommodation.entity.AccommodationType;
import com.fastcampus.mini9.domain.accommodation.entity.District;
import com.fastcampus.mini9.domain.accommodation.entity.Region;
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
		Accommodation accommodation = Accommodation.builder()
			.name("test name")
			.accommodationImage(new ArrayList<>())
			.type(AccommodationType.HOTEL)
			.district(District.builder().name("test district").build())
			.region(Region.builder().name("test region").build())
			.build();

		given(accommodationRepository.findById(anyLong()))
			.willReturn(Optional.of(accommodation));

		//when
		ResponseEntity<OriginAccommodationDto> result = accommodationService.detailOfAccommodation(anyLong());

		//then
		OriginAccommodationDto accommodationDtoResult = OriginAccommodationDto.fromEntity(accommodation);

		// AccommodationDto accommodationDtoResult = AccommodationDtoMapper.INSTANCE.accommodationDto(accommodation);

		System.out.println(accommodationDtoResult.getName() + "===========================");

		assertThat(Objects.requireNonNull(result.getBody()).getId()).isEqualTo(accommodationDtoResult.getId());
		assertThat(result.getBody().getType()).isEqualTo(accommodationDtoResult.getType());
		assertThat(result.getBody().getName()).isEqualTo(accommodationDtoResult.getName());
		assertThat(result.getBody().getDistrict().getName()).isEqualTo(
			accommodationDtoResult.getDistrict().getName());
		assertThat(result.getBody().getRegion().getName()).isEqualTo(accommodationDtoResult.getRegion().getName());
		assertThat(result.getBody().getName()).isEqualTo(accommodationDtoResult.getName());
		assertThat(result.getBody().getAccommodationImage()).isEqualTo(accommodationDtoResult.getAccommodationImage());
	}

}