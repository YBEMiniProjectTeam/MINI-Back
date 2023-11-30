package com.fastcampus.mini9.domain.accommodation.service.usecase;

import java.time.LocalDate;
import java.util.List;

import com.fastcampus.mini9.config.security.token.UserPrincipal;
import com.fastcampus.mini9.domain.accommodation.vo.AccommodationType;

public interface AccommodationQuery {
	SearchAccommodationsResponse searchAccommodations(SearchAccommodationsRequest request, UserPrincipal userPrincipal);

	FindAccommodationResponse findAccommodation(FindAccommodationRequest request);

	record SearchAccommodationsRequest(
		String region,
		String district,
		LocalDate startDate,
		LocalDate endDate,
		String category,
		String keyword,
		Integer pageNum,
		Integer pageSize
	) {
	}

	record SearchAccommodationsResponse(
		List<SearchAccommodation> accommodationResponses,
		Integer page_num,
		Integer page_size,
		Integer total_pages,
		Long total_result,
		boolean first,
		boolean last
	) {
	}

	record SearchAccommodation(
		Long id,
		String name,
		AccommodationType type,
		String region,
		String district,
		String checkIn,
		String checkOut,
		boolean isWish,
		String thumbnail,
		Integer min_price
	) {
	}

	record FindAccommodationRequest(
		Long id
	) {
	}

	record FindAccommodationResponse(
		Long id,
		String name,
		AccommodationType type,
		FindAccommodationDescription description,
		String region,
		String district,
		String checkIn,
		String checkOut,
		boolean isWish,
		Long totalWishCounts,
		List<String> accommodation_image
	) {
	}

	record FindAccommodationDescription(
		String description,
		String address,
		String latitude,
		String longitude,
		String tel,
		boolean parking,
		boolean cooking,
		String others
	) {
	}
}
