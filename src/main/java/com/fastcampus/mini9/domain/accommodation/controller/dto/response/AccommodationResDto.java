package com.fastcampus.mini9.domain.accommodation.controller.dto.response;

import java.util.List;

public record AccommodationResDto(
	Long id,
	String name,
	String type,
	Description description,
	String region,
	String district,
	boolean isWish,
	Long totalWishCounts,
	List<String> accommodation_image
) {
	public record Description(
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
