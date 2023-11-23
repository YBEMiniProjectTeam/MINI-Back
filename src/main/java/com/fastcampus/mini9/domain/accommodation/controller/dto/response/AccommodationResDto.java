package com.fastcampus.mini9.domain.accommodation.controller.dto.response;

import java.util.List;

public record AccommodationResDto(
	Long id,
	String name,
	String type,
	String description,
	String region,
	String district,
	Boolean isWish,
	Long totalWishCounts,
	List<Image> accommodation_image
) {
	private record Image(
		String url
	) {

	}
}
