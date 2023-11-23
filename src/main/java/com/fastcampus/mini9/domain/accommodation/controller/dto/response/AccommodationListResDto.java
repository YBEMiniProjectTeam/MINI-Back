package com.fastcampus.mini9.domain.accommodation.controller.dto.response;

import java.util.List;

public record AccommodationListResDto(
	List<AccommodationResDto> accommodations,
	Long page_num,
	Long total_pages,
	Long page_size,
	Long total_result,
	Boolean first,
	Boolean last
) {
	private record AccommodationResDto(
		Long id,
		String name,
		String type,
		String description,
		String url,
		Long price,
		Boolean isWish
	) {

	}
}
