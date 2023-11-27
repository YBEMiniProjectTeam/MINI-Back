package com.fastcampus.mini9.domain.accommodation.controller.dto.response;

import java.util.List;

public record AccommodationListResDto(
	List<AccommodationResDto> accommodations,
	Integer page_num,
	Integer page_size,
	Integer total_pages,
	Long total_result,
	boolean first,
	boolean last
) {
	public record AccommodationResDto(
		Long id,
		String name,
		String type,
		String thumbnail,
		Integer min_price,
		boolean isWish
	) {
	}
}
