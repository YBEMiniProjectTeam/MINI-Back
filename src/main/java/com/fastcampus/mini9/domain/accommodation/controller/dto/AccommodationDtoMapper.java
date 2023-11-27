package com.fastcampus.mini9.domain.accommodation.controller.dto;

import static com.fastcampus.mini9.domain.accommodation.service.usecase.AccommodationQuery.*;
import static com.fastcampus.mini9.domain.accommodation.service.usecase.RoomQuery.*;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.fastcampus.mini9.domain.accommodation.controller.dto.response.AccommodationListResDto;
import com.fastcampus.mini9.domain.accommodation.controller.dto.response.AccommodationResDto;
import com.fastcampus.mini9.domain.accommodation.controller.dto.response.RoomListResDto;
import com.fastcampus.mini9.domain.accommodation.controller.dto.response.RoomResDto;

/**
 * 사용방법
 * @Mapping(source = "numberOfSeats", target = "seatCount")
 * CarDto carToCarDto(Car car);
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccommodationDtoMapper {
	// getAccommodations()
	@Mapping(target = "accommodations", source = "accommodationResponses")
	AccommodationListResDto searchResultToResponseDto(SearchAccommodationsResponse searchResult);

	List<AccommodationListResDto.AccommodationResDto> searchResultToResponseDto(
		List<AccommodationResponse> accommodationResponses);

	// TODO: @Mapping(target = "min_price", source = "?")
	AccommodationListResDto.AccommodationResDto accommodationResponseToResDto(
		AccommodationResponse accommodationResponse);

	// getAccommodation()
	// TODO: WishCount 기능 구현 후 제거
	@Mapping(target = "totalWishCounts", constant = "10L")
	AccommodationResDto findResultToDto(FindAccommodationResponse findResult);

	// getRooms()
	@Mapping(target = "rooms", source = "roomResponses")
	RoomListResDto findResultToDto(FindRoomsInAccommodationResponse findResult);

	List<RoomListResDto.RoomResDto> findResultToDto(List<RoomResponse> findResult);

	@Mapping(target = "stock_quantity", source = "stock")
	RoomListResDto.RoomResDto findResultToDto(RoomResponse findResult);

	// getRoom()
	RoomResDto findResultToDto(FindRoomResponse findResult);

	// Accommodation acc(Acc)
}

