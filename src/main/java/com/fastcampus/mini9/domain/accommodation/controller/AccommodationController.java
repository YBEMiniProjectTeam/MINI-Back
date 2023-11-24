package com.fastcampus.mini9.domain.accommodation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.mini9.common.response.DataResponseBody;
import com.fastcampus.mini9.common.response.ErrorResponseBody;
import com.fastcampus.mini9.domain.accommodation.controller.dto.response.AccommodationListResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class AccommodationController {
	@Operation(summary = "숙소 검색")
	@ApiResponses(value = {
		@ApiResponse(
			description = "정상적으로 호출했을 때",
			responseCode = "200",
			useReturnTypeSchema = true
		),
		@ApiResponse(
			description = "잘못된 형식으로 요청했을 때",
			responseCode = "400",
			content = {@Content(schema = @Schema(implementation = ErrorResponseBody.class))}
		),
		@ApiResponse(
			description = "서버 에러",
			responseCode = "500",
			content = {@Content(schema = @Schema(implementation = ErrorResponseBody.class))}
		)
	})
	@GetMapping("/accommodations")
	public DataResponseBody<AccommodationListResDto> getAccommodations(
		@RequestParam(required = false) String region, @RequestParam(required = false) String district,
		@RequestParam(required = false, name = "start_date") String startDate,
		@RequestParam(required = false, name = "end_date") String endDate,
		@RequestParam(required = false) String category, @RequestParam(required = false) String keyword) {
		return DataResponseBody.success(null, "SUCCESS");
	}

	@Operation(summary = "숙소 상세조회")
	@ApiResponses(value = {
		@ApiResponse(
			description = "정상적으로 호출했을 때",
			responseCode = "200",
			useReturnTypeSchema = true
		),
		@ApiResponse(
			description = "잘못된 형식으로 요청했을 때",
			responseCode = "400",
			content = {@Content(schema = @Schema(implementation = ErrorResponseBody.class))}
		),
		@ApiResponse(
			description = "서버 에러",
			responseCode = "500",
			content = {@Content(schema = @Schema(implementation = ErrorResponseBody.class))}
		)
	})
	@GetMapping("/accommodations/{accommodationId}")
	public DataResponseBody getAccommodation(@PathVariable Long accommodationId) {
		return DataResponseBody.success(null, "SUCCESS");
	}

	@Operation(summary = "숙소에서 제공하는 객실 조회")
	@ApiResponses(value = {
		@ApiResponse(
			description = "정상적으로 호출했을 때",
			responseCode = "200",
			useReturnTypeSchema = true
		),
		@ApiResponse(
			description = "잘못된 형식으로 요청했을 때",
			responseCode = "400",
			content = {@Content(schema = @Schema(implementation = ErrorResponseBody.class))}
		),
		@ApiResponse(
			description = "서버 에러",
			responseCode = "500",
			content = {@Content(schema = @Schema(implementation = ErrorResponseBody.class))}
		)
	})
	@GetMapping("/accommodations/{accommodationId}/rooms")
	public DataResponseBody getRooms(@PathVariable Long accommodationId,
		@RequestParam String startDate, @RequestParam String endDate, @RequestParam Long guestNum) {
		return DataResponseBody.success(null, "SUCCESS");
	}

	@Operation(summary = "객실 상세조회")
	@ApiResponses(value = {
		@ApiResponse(
			description = "정상적으로 호출했을 때",
			responseCode = "200",
			useReturnTypeSchema = true
		),
		@ApiResponse(
			description = "잘못된 형식으로 요청했을 때",
			responseCode = "400",
			content = {@Content(schema = @Schema(implementation = ErrorResponseBody.class))}
		),
		@ApiResponse(
			description = "서버 에러",
			responseCode = "500",
			content = {@Content(schema = @Schema(implementation = ErrorResponseBody.class))}
		)
	})
	@GetMapping("/rooms/{roomId}")
	public DataResponseBody getRoom(@PathVariable Long roomId) {
		return DataResponseBody.success(null, "SUCCESS");
	}
}
