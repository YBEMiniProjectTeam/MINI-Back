package com.fastcampus.mini9.domain.cart.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CartIdRequest(

	@NotNull(message = "cart_id는 필수로 입력되어야 합니다.")
	@Min(value = 1, message = "cart_id는 최소 1 이상이어야 합니다.")
	Long cartId) {
}
